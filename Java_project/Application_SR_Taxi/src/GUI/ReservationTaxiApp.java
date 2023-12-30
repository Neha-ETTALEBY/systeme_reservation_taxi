package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class ReservationTaxiApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Réservation de Taxi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran

        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.BLACK); // Définir l'arrière-plan en noir
                ImageIcon imageIcon = new ImageIcon("background.jpg");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espacement entre les composants

        JLabel welcomeLabel = new JLabel("Bienvenue dans l'application de réservation de taxi !");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Agrandir la taille de la police
        welcomeLabel.setForeground(Color.WHITE);

        JLabel sourceLabel = new JLabel("Lieu de départ:");
        sourceLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Agrandir la taille de la police
        sourceLabel.setForeground(Color.WHITE);
        JTextField sourceField = new JTextField(20);
        sourceField.setPreferredSize(new Dimension(200, 30));

        JLabel destinationLabel = new JLabel("Lieu de destination:");
        destinationLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Agrandir la taille de la police
        destinationLabel.setForeground(Color.WHITE);
        JTextField destinationField = new JTextField(20);
        destinationField.setPreferredSize(new Dimension(200, 30));

        JLabel timeLabel = new JLabel("Heure de réservation:");
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Agrandir la taille de la police
        timeLabel.setForeground(Color.WHITE);
        JComboBox<String> timeComboBox = new JComboBox<>();

        // Obtenir l'heure actuelle
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);

        // Ajouter l'heure actuelle à la liste déroulante
        String currentHourString = String.format("%02d:00 (Maintenant)", currentHour);
        timeComboBox.addItem(currentHourString);

        // Ajouter les heures disponibles à la liste déroulante
        for (int i = currentHour + 1; i <= 23; i++) {
            String hour = String.format("%02d:00", i);
            timeComboBox.addItem(hour);
        }

        JButton reserveButton = new JButton("Réserver");
        reserveButton.setFont(new Font("Arial", Font.BOLD, 16)); // Agrandir la taille de la police

        JButton cancelButton = new JButton("Annuler");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16)); // Agrandir la taille de la police

        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String source = sourceField.getText();
                String destination = destinationField.getText();
                String time = (String) timeComboBox.getSelectedItem();

                System.out.println("Réservation effectuée:");
                System.out.println("Lieu de départ: " + source);
                System.out.println("Lieu de destination: " + destination);
                System.out.println("Heure de réservation: " + time);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sourceField.setText("");
                destinationField.setText("");
                timeComboBox.setSelectedIndex(0);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(welcomeLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(sourceLabel, gbc);

        gbc.gridx = 1;
        panel.add(sourceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(destinationLabel, gbc);

        gbc.gridx = 1;
        panel.add(destinationField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(timeLabel, gbc);

        gbc.gridx = 1;
        panel.add(timeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(reserveButton, gbc);

        gbc.gridx = 1;
        panel.add(cancelButton, gbc);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}