package edu.univ.erp.ui.auth;

import javax.swing.*;
import java.awt.*;

import edu.univ.erp.api.auth.LoginAPI;
import edu.univ.erp.domain.User;
import edu.univ.erp.ui.student.StudentDashboard;
import edu.univ.erp.ui.instructor.InstructorDashboard;
import edu.univ.erp.ui.admin.AdminDashboard;

public class LoginFrame extends JFrame
{
    // Background Image
    class ImagePanel extends JPanel
    {
        private Image image;
        private float opacity;
        public ImagePanel(float Opacity)
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
        ImagePanel imagePanel = new ImagePanel(1f);
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
        gbc.weightx = 1;
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel space1 = new JLabel (" ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(space1, gbc);

        // EmailID Label
        JLabel emailIDL = new JLabel("Email ID: ");
        emailIDL.setForeground(Color.WHITE);
        emailIDL.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        loginPanel.add(emailIDL, gbc);

        // EmailID Text Field
        JTextField EmailIDEntered = new JTextField(25);
        EmailIDEntered.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(EmailIDEntered, gbc);

        // Password Label
        JLabel passwordL = new JLabel("Password: ");
        passwordL.setForeground(Color.WHITE);
        passwordL.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        loginPanel.add(passwordL, gbc);

        // Password Password Field
        JTextField PasswordEntered = new JPasswordField(25);
        PasswordEntered.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(PasswordEntered, gbc);

        // Role Label
        JLabel roleL = new JLabel("Role: ");
        roleL.setForeground(Color.WHITE);
        roleL.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        loginPanel.add(roleL, gbc);

        // Role Dropdown
        String[] roles = {"ADMIN", "INSTRUCTOR", "STUDENT"};
        JComboBox<String> RoleDropdown = new JComboBox<>(roles);
        RoleDropdown.setPreferredSize(new Dimension(280, 30));
        RoleDropdown.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(RoleDropdown, gbc);
        
        JLabel space2 = new JLabel (" ");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(space2, gbc);

        gbc.gridwidth = 2;
        /*-----------------------------------------------------
        // Signup Button
        JButton SignupB = new JButton("CANCEL");
        SignupB.setForeground(Color.WHITE);
        SignupB.setBackground(new Color(90, 90, 90));
        SignupB.setFont(new Font("Segoe UI", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.CENTRE;
        gbc.fill = GridBagConstraints.NONE;
        loginPanel.add(SignupB, gbc);
        --------------------------------------------------------*/

        // Login Button
        JButton LoginB = new JButton("LOGIN");
        LoginB.setPreferredSize(new Dimension(140, 243));
        LoginB.setForeground(Color.WHITE);
        LoginB.setBackground(new Color(33, 150, 90));
        LoginB.setFont(new Font("Segoe UI", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        loginPanel.add(LoginB, gbc);

        LoginAPI api = new LoginAPI();

        LoginB.addActionListener(e -> {

            String email = EmailIDEntered.getText();
            String password = new String(PasswordEntered.getText().trim());
            String role = RoleDropdown.getSelectedItem().toString();

            User user = api.login(email, password, role);

            if (user != null)
            {
                dispose(); // Close login window
                String username = user.getUsername();

                switch (role) {
                    case "ADMIN":
                        new AdminDashboard(username).setVisible(true);
                        break;

                    case "STUDENT":
                        new StudentDashboard(username).setVisible(true);
                        break;

                    case "INSTRUCTOR":
                        new InstructorDashboard(username).setVisible(true);
                        break;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this,
                        "Invalid Credentials",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
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
