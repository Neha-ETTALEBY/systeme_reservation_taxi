package ui;

import javax.swing.*;

public class FirstPage extends JFrame {
    private JPanel FirstPagePanel;
    private JTextField NomField;
    private JTextField PrenomField;
    private JTextField EmailField;
    private JTextField TelField;
    private JPasswordField PasswordField;

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
