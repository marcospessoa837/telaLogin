package telaLogin;

import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class MainMenuScreen extends JFrame {

    public MainMenuScreen() {

        setTitle("Menu Principal");

        setSize(400, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null); // Centralize a tela



        JMenuBar menuBar = new JMenuBar();

        JMenu menuOpcoes = new JMenu("Opções");

        JMenuItem opcao1 = new JMenuItem("Opção 1");

        JMenuItem opcao2 = new JMenuItem("Opção 2");

        JMenuItem opcao3 = new JMenuItem("Opção 3");

        JMenuItem opcao4 = new JMenuItem("Opção 4");

        JMenuItem opcao5 = new JMenuItem("Opção 5");

        menuOpcoes.add(opcao1);

        menuOpcoes.add(opcao2);

        menuOpcoes.add(opcao3);

        menuOpcoes.add(opcao4);

        menuOpcoes.add(opcao5);

        menuBar.add(menuOpcoes);



        JButton botaoSair = new JButton("Sair do Sistema");

        botaoSair.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }

        });



        JPanel panel = new JPanel();

        panel.add(botaoSair);



        setJMenuBar(menuBar);

        add(panel);

    }



private void resetPassword(String username) {

    String newPassword = JOptionPane.showInputDialog("Digite a nova senha:");

    if (newPassword != null && !newPassword.isEmpty()) {

        String url = "jdbc:mysql://localhost:3306/seu_banco_de_dados";

        String user = "seu_usuario";

        String pass = "sua_senha";



        try {

            Connection connection = DriverManager.getConnection(url, user, pass);

            String query = "UPDATE usuarios SET password=? WHERE username=?";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, newPassword);

            statement.setString(2, username);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {

                JOptionPane.showMessageDialog(this, "Senha resetada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(this, "Usuário não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(this, "Erro ao resetar a senha!", "Erro", JOptionPane.ERROR_MESSAGE);

        }

    } else {

        JOptionPane.showMessageDialog(this, "Nova senha não pode ser vazia!", "Erro", JOptionPane.ERROR_MESSAGE);

    }

}


public class LoginScreen extends JFrame implements ActionListener {

    private JTextField usernameField;

    private JPasswordField passwordField;

    private JButton loginButton;

    private JButton resetPasswordButton;



    public LoginScreen() {

        // Seu código existente...



        Object password;
		Object username;
		if (login(username, password)) {

            JOptionPane.showMessageDialog(this, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            // Abrir a próxima janela do aplicativo após o login

            dispose(); // Fechar a tela de login

            new MainMenuScreen().setVisible(true); // Abrir a próxima tela do menu principal

        } else {

            // Seu código existente...

        }

    }



	private boolean login(Object username, Object password) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

