package ui;

import javax.swing.*;

public class FirstPage extends JFrame {
    private JPanel FirstPagePanel;
    private JTextField NomField;
    private JTextField PrenomField;
    private JTextField EmailField;
    private JTextField TelField;
    private JPasswordField PasswordField;
    private JButton button1;
    private JButton button2;

    public FirstPage()
    {
        setContentPane(FirstPagePanel);
        setTitle("FirstPage");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    public static void main (String [] arg)
    {
     new FirstPage();
    }
}
