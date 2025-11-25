package edu.univ.erp.ui.student;

import javax.swing.*;
import java.awt.*;

public class StudentHome extends JPanel
{
    public StudentHome(String username)
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Welcome, " + username + " 🎓", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(title, BorderLayout.NORTH);

        JPanel cards = new JPanel(new GridLayout(1, 3, 20, 20));
        cards.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        cards.setBackground(Color.WHITE);

        cards.add(createCard("Courses", "5"));
        cards.add(createCard("Attendance %", "91%"));
        cards.add(createCard("CGPA", "8.4"));

        add(cards, BorderLayout.CENTER);
    }

    private JPanel createCard(String title, String value)
    {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(96, 125, 139));
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
