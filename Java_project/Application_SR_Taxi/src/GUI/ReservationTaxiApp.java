package GUI;

import metier.Client;
import metier.GestionReservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class ReservationTaxiApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {//pour garantir que la création et l'affichage de l'interface graphique sont exécutés dans l'EDT (Event Dispatch Thread).
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Réservation de Taxi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran

        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.black);
                ImageIcon imageIcon = new ImageIcon("background.png");
                Image image = imageIcon.getImage();
                int x = getWidth() - image.getWidth(null); // Positionner l'image à droite
                int y = 0; // Modifier la position verticale de l'image (0 pour l'aligner en haut)
                g.drawImage(image, x, y, null);
            }
        };


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espacement entre les composants

        JLabel welcomeLabel = new JLabel("Hello!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Agrandir la taille de la police
        welcomeLabel.setForeground(Color.WHITE);

        JLabel sourceLabel = new JLabel("Lieu de départ:");
        sourceLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Agrandir la taille de la police
        sourceLabel.setForeground(Color.WHITE);
        JTextField sourceField = new JTextField(20);
        sourceField.setPreferredSize(new Dimension(200, 30));
        sourceField.setFont(new Font("Arial", Font.BOLD, 16)); // Ajustez la taille de la police


        JLabel destinationLabel = new JLabel("Lieu de destination:");
        destinationLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Agrandir la taille de la police
        destinationLabel.setForeground(Color.WHITE);
        JTextField destinationField = new JTextField(20);
        destinationField.setPreferredSize(new Dimension(200, 30));
        destinationField.setFont(new Font("Arial", Font.BOLD, 16)); // Ajustez la taille de la police


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

                // Créez un objet Client avec des valeurs fictives (à remplacer par la logique de récupération du client)
                Client client = new Client("client1", "clt1", "03222666", "lvvf@vfv4.com", "c01M$5");

                // Construisez le message pour afficher dans la boîte de dialogue
                String message = "Détails de la réservation :\n\n" +
                        "Lieu de départ: " + source + "\n" +
                        "Lieu de destination: " + destination + "\n" +
                        "Heure de réservation: " + time + "\n\n" +
                        "Confirmer la réservation ?";

                // Affichez la boîte de dialogue avec les détails de la réservation
                int choice = JOptionPane.showConfirmDialog(null, message, "Confirmation de Réservation", JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    // Effectuez la réservation
                    GestionReservation gestionReservation = new GestionReservation();
                    int dispo = gestionReservation.effectuerReservation(client, source, destination, "carte bancaire");

                    if (dispo != -1) {
                        // Si la réservation a réussi
                        String msg = "tarif sera : "+gestionReservation.CalculerTarifEnFctDistance(source,destination)+"DH";
                        JOptionPane.showMessageDialog(null, "Réservation effectuée avec succès !\n"+msg);
                    } else {
                        // Si la réservation a échoué
                        JOptionPane.showMessageDialog(null, "Désolé, aucun taxi disponible pour le moment. Veuillez réessayer plus tard.");
                    }
                } else {
                    // Annulez la réservation
                    JOptionPane.showMessageDialog(null, "Réservation annulée par le client.");
                }
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