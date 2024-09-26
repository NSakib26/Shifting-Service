import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class Login implements ActionListener {
    private JFrame frame;
    private JTextField emailField;
    private JPasswordField passwordField;

    public Login() {
        frame = new JFrame("Login");
        frame.setSize(600, 400);
        frame.setLayout(null);
		
		ImageIcon img = new ImageIcon("bg.jpg"); // Load the local image
        JLabel background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 600, 400); // Set size to match the frame
        frame.add(background);

		JLabel l1 = new JLabel("Welcome To Our Shifting Service");
		l1.setFont(new Font("Serif",Font.BOLD,20));   //font family Serif
		l1.setForeground(Color.BLUE);
		l1.setBounds(115,15,300,30);   //setBounds() method expects four parameters: x-coordinate, y-coordinate, width, and height.
		background.add(l1); // Add components to the background


        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 50, 100, 30);
        background.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 50, 200, 30);
        background.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 30);
        background.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 30);
        background.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(200, 150, 100, 30);
		loginButton.setForeground(Color.white);
		loginButton.setBackground(Color.green);
        loginButton.addActionListener(this);
        background.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(200, 200, 100, 30);
		registerButton.setForeground(Color.white);
		registerButton.setBackground(Color.green);
        registerButton.addActionListener(this);
        background.add(registerButton);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Login")) {
                login();
            } else if (button.getText().equals("Register")) {
                frame.dispose();
                new Registration();
            }
        }
    }

    private void login() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        // Check if admin login
        if (email.equals("admin@example.com") && password.equals("admin123")) {
            JOptionPane.showMessageDialog(frame, "Admin Login Successful!");
            frame.dispose();
            //new AdminDashboard();
            return;
        }

        // Proceed with regular user login
        User user = authenticateUser(email, password);
        if (user != null) {
            JOptionPane.showMessageDialog(frame, "Login Successful!");
            frame.dispose();
            new UserDashboard(user);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid email or password!");
        }
    }

    private User authenticateUser(String email, String password) {
        try {
            File file = new File("userdata.txt");
            if (!file.exists()) {
                JOptionPane.showMessageDialog(frame, "No user registered yet!");
                return null;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].equals(email) && parts[2].equals(password)) {
                    reader.close();
                    return new User(parts[0], parts[1], parts[2], parts[3]);
                }
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
