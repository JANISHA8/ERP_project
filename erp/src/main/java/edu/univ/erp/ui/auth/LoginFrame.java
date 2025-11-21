package edu.univ.erp.ui.auth;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame
{
    // Background Image
    class ImagePanel extends JPanel
    {
        private Image image;
        private float opacity;
        public ImagePanel(String path, float Opacity)
        {
            this.image = new ImageIcon(getClass().getClassLoader().getResource("images/bg.png")).getImage();
            this.opacity = Opacity;
            setLayout(new GridBagLayout());
        }

        @Override protected void paintComponent(Graphics graphics)
        {
            super.paintComponent(graphics);
            Graphics2D graphics2d = (Graphics2D) graphics.create();
            graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            graphics2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            graphics2d.dispose();
        }
    }

    public LoginFrame()
    {
        // Dialog Properties
        setTitle("ERP");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background Image Panel
        ImagePanel imagePanel = new ImagePanel("images/bg.png", 1f);
        add(imagePanel);

        // Login Panel
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setOpaque(true);
        loginPanel.setBackground(new Color(0, 0, 0, 180));
        loginPanel.setPreferredSize(new Dimension(500, 600));

        // gbc
        GridBagConstraints gbc = new GridBagConstraints();

        // Title Label
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 15, 5, 15);
        gbc.fill = GridBagConstraints.NONE;

        JLabel title1 = new JLabel ("LOGIN");
        title1.setForeground(Color.WHITE);
        title1.setFont(new Font("Segoe UI", Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(title1, gbc);

        JLabel title2 = new JLabel ("TO YOUR");
        title2.setForeground(Color.WHITE);
        title2.setFont(new Font("Segoe UI", Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(title2, gbc);

        JLabel title3 = new JLabel ("ERP ACCOUNT");
        title3.setForeground(Color.WHITE);
        title3.setFont(new Font("Segoe UI", Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(title3, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // EmailID Label
        JLabel emailID = new JLabel("Email ID: ");
        emailID.setForeground(Color.WHITE);
        emailID.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        loginPanel.add(emailID, gbc);

        // EmailID Text Field
        JTextField EmailIDEntered = new JTextField(15);
        EmailIDEntered.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(EmailIDEntered, gbc);

        // Password Label
        JLabel password = new JLabel("Password: ");
        password.setForeground(Color.WHITE);
        password.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        loginPanel.add(password, gbc);

        // Password Password Field
        JTextField PasswordEntered = new JPasswordField(15);
        PasswordEntered.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(PasswordEntered, gbc);

        // Role Label
        JLabel role = new JLabel("Role: ");
        role.setForeground(Color.WHITE);
        role.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        loginPanel.add(role, gbc);

        // Role Dropdown
        String[] roles = {"ADMIN", "INSTRUCTOR", "STUDENT"};
        JComboBox<String> RoleDropdown = new JComboBox<>(roles);
        RoleDropdown.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(RoleDropdown, gbc);

        gbc.gridwidth = 2;
        /*-----------------------------------------------------
        // Cancel Button
        JButton CancelB = new JButton("CANCEL");
        CancelB.setForeground(Color.WHITE);
        CancelB.setBackground(new Color(90, 90, 90));
        CancelB.setFont(new Font("Segoe UI", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.CENTRE;
        gbc.fill = GridBagConstraints.NONE;
        loginPanel.add(CancelB, gbc);
        --------------------------------------------------------*/

        // Login Button
        JButton LoginB = new JButton("LOGIN");
        LoginB.setPreferredSize(new Dimension(140, 40));
        LoginB.setForeground(Color.WHITE);
        LoginB.setBackground(new Color(0, 102, 204));
        LoginB.setFont(new Font("Segoe UI", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        loginPanel.add(LoginB, gbc);

        imagePanel.add(loginPanel);
    }
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            LoginFrame dialog = new LoginFrame();
            dialog.setVisible(true);
        });
    }
}
