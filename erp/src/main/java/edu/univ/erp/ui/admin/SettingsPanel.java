package edu.univ.erp.ui.admin;

import edu.univ.erp.api.admin.AdminAPI;
import edu.univ.erp.data.SettingsData;
import edu.univ.erp.domain.Settings;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel
{
    private JLabel statusLabel;

    public SettingsPanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("SYSTEM MAINTENANCE MODE", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));

        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        JButton onBtn = new JButton("TURN ON");
        JButton offBtn = new JButton("TURN OFF");

        onBtn.setBackground(new Color(200,50,50));
        onBtn.setForeground(Color.WHITE);

        offBtn.setBackground(new Color(50,150,50));
        offBtn.setForeground(Color.WHITE);

        JPanel center = new JPanel(new GridLayout(3,1,15,15));
        center.add(statusLabel);
        center.add(onBtn);
        center.add(offBtn);

        add(title, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);

        loadStatus();

        onBtn.addActionListener(e -> updateMaintenance(true));
        offBtn.addActionListener(e -> updateMaintenance(false));
    }

    private void loadStatus()
    {
        Settings setting = SettingsData.getSetting("Maintenance_Mode");

        if (setting == null)
        {
            statusLabel.setText("⚠ Setting not found in database");
            return;
        }

        boolean isOn = Boolean.parseBoolean(setting.getValue());

        statusLabel.setText(isOn ?
                            "MAINTENANCE MODE: ON (Students & Instructors are blocked)" :
                            "MAINTENANCE MODE: OFF (System operating normally)");
    }

    private void updateMaintenance(boolean newStatus)
    {
        Settings setting = SettingsData.getSetting("Maintenance_Mode");
        if (setting == null)
        {
            JOptionPane.showMessageDialog(this, "Maintenance setting not found in database",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean currentStatus = Boolean.parseBoolean(setting.getValue());

        if (currentStatus == newStatus)
        {
            JOptionPane.showMessageDialog(this, newStatus
                                        ? "Maintenance Mode is already ON"
                                        : "Maintenance Mode is already OFF",
                                        "No Change Needed",
                                        JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        AdminAPI api = new AdminAPI();
        boolean success = api.toggleMaintenance(newStatus);

        if (success)
        {
            JOptionPane.showMessageDialog(this, "Maintenance mode updated successfully!");
            loadStatus();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Failed to update maintenance mode.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
        }
    }
}
