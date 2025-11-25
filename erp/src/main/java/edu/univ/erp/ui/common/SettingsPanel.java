package edu.univ.erp.ui.common;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel
{
    public SettingsPanel()
    {
        setLayout(new GridLayout(5,1,10,10));
        setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        setBackground(Color.WHITE);

        add(new JLabel("⚙ Settings", JLabel.CENTER));

        add(new JCheckBox("Enable notifications"));
        add(new JCheckBox("Dark mode"));
        add(new JCheckBox("Auto logout after 10 min"));

        JButton save = new JButton("Save Settings");
        save.setBackground(new Color(33,150,90));
        save.setForeground(Color.WHITE);

        add(save);
    }
}
