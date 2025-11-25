package edu.univ.erp.ui.instructor;

import javax.swing.*;
import java.awt.*;

public class InstructorHome extends JPanel
{
    public InstructorHome(String username)
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Welcome, " + username + " 👨‍🏫", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        add(title, BorderLayout.NORTH);

        JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        cardsPanel.setBackground(Color.WHITE);

        cardsPanel.add(createCard("My Courses", "4"));
        cardsPanel.add(createCard("Students", "120"));
        cardsPanel.add(createCard("Pending Results", "8"));

        add(cardsPanel, BorderLayout.CENTER);
    }

    private JPanel createCard(String title, String value)
    {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(63, 81, 181));
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel t = new JLabel(title, JLabel.CENTER);
        t.setForeground(Color.WHITE);
        t.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JLabel v = new JLabel(value, JLabel.CENTER);
        v.setForeground(Color.WHITE);
        v.setFont(new Font("Segoe UI", Font.BOLD, 32));

        card.add(t, BorderLayout.NORTH);
        card.add(v, BorderLayout.CENTER);

        return card;
    }
}
