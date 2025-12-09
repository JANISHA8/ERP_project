package edu.univ.erp.ui.admin;

import javax.swing.*;
import java.awt.*;

public class AdminHome extends JPanel
{
    public AdminHome()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Admin Overview", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        add(title, BorderLayout.NORTH);

        JPanel cardsPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        cardsPanel.setBackground(Color.WHITE);
        cardsPanel.add(createCard("Total Users", "120"));
        cardsPanel.add(createCard("Total Courses", "35"));
        cardsPanel.add(createCard("Active Students", "87"));
        cardsPanel.add(createCard("Instructors", "15"));

        add(cardsPanel, BorderLayout.CENTER);
    }

    private JPanel createCard(String title, String value)
    {
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(180, 120));
        card.setBackground(new Color(33, 150, 243));
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JLabel valueLabel = new JLabel(value, JLabel.CENTER);
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }
}
