package telaLogin;

import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;



public class LoginScreen extends JFrame implements ActionListener {

    private JTextField usernameField;

    private JPasswordField passwordField;

    private JButton loginButton;

    private JButton resetPasswordButton;



    public LoginScreen() {

        setTitle("Login");

        setSize(300, 200);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null); // Centralizar a tela



        JPanel panel = new JPanel(new GridLayout(3, 2));

        usernameField = new JTextField();

        passwordField = new JPasswordField();

        loginButton = new JButton("Login");

        resetPasswordButton = new JButton("Resetar Senha");



        panel.add(new JLabel("Usuário:"));

        panel.add(usernameField);

        panel.add(new JLabel("Senha:"));

        panel.add(passwordField);

        panel.add(loginButton);

        panel.add(resetPasswordButton);



        loginButton.addActionListener(this);

        resetPasswordButton.addActionListener(this);



        add(panel);

        setVisible(true);

    }



    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                new LoginScreen();

            }

        });

    }



    @Override

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {

            String username = usernameField.getText();

            String password = new String(passwordField.getPassword());

            if (login(username, password)) {

                JOptionPane.showMessageDialog(this, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                // Aqui você pode abrir a próxima janela do aplicativo após o login

            } else {

                JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);

            }

        } else if (e.getSource() == resetPasswordButton) {

            // Lógica para resetar a senha

        }

    }



    private boolean login(String username, String password) {

        String url = "jdbc:mysql://localhost:3306/seu_banco_de_dados";

        String user = "seu_usuario";

        String pass = "sua_senha";



        try {

            Connection connection = DriverManager.getConnection(url, user, pass);

            String query = "SELECT * FROM usuarios WHERE username=? AND password=?";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, username);

            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next(); // Se houver algum resultado, o login é válido

        } catch (SQLException ex) {

            ex.printStackTrace();

            return false;

        }

    }

}