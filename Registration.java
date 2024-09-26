import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class Registration implements ActionListener {
    private JFrame frame;
    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JComboBox<String> genderBox;

    public Registration() {
        frame = new JFrame("Registration");
        frame.setSize(600, 400);
        frame.setLayout(null);
		
        ImageIcon img1 = new ImageIcon("bg1.jpg"); // Load the local image
        JLabel background = new JLabel("", img1, JLabel.CENTER);
        background.setBounds(0, 0, 600, 400); // Set size to match the frame
        frame.add(background);
		
		JLabel l1 = new JLabel("Create your Account Now!");
		l1.setFont(new Font("Serif",Font.BOLD,25));
		l1.setForeground(Color.BLUE);
		l1.setForeground(Color.BLUE); 
		l1.setBounds(130,10,300,30);
		background.add(l1);
		
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 60, 120, 30);
        background.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(130, 60, 180, 25);
        background.add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 110, 120, 30);
        background.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(130, 110, 180, 25);
        background.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 160, 120, 30);
        background.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(130, 160, 180, 25);
        background.add(passwordField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 210, 120, 30);
        background.add(genderLabel);

        String[] genders = {"Male", "Female", "Other"};
        genderBox = new JComboBox<>(genders);
        genderBox.setBounds(130, 210, 180, 25);
        background.add(genderBox);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(200, 260, 100, 30);
		registerButton.setForeground(Color.white);
		registerButton.setBackground(Color.green);
        registerButton.addActionListener(this);
        background.add(registerButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(310, 260, 100, 30);
		clearButton.setForeground(Color.white);
		clearButton.setBackground(Color.red);
        clearButton.addActionListener(this);
        background.add(clearButton);

        JLabel loginLabel = new JLabel("Already Registered? Login");
        loginLabel.setForeground(Color.BLUE);
        loginLabel.setBounds(200, 300, 200, 30);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new Login();
                new Login();
            }
        });
        frame.add(loginLabel);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Register")) {
                register();
            } else if (button.getText().equals("Clear")) {
                clearFields();
            }
        }
    }

    private void register() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String gender = (String) genderBox.getSelectedItem();

        // Check for empty fields
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields!");
            return;
        }

        // Check for existing email
        if (isEmailExists(email)) {
            JOptionPane.showMessageDialog(frame, "User with this email already exists!");
            return;
        }

        User newUser = new User(name, email, password, gender);

        try {
            FileWriter writer = new FileWriter("userdata.txt", true);
            writer.write(newUser.getName() + "," + newUser.getEmail() + "," + newUser.getPassword() + "," + newUser.getGender() + "\n");
            writer.close();
            JOptionPane.showMessageDialog(frame, "Registration Successful!");
            frame.dispose();
            new Login();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean isEmailExists(String email) {
        try {
            File file = new File("userdata.txt");
            if (!file.exists()) {
                return false;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].equals(email)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        genderBox.setSelectedIndex(0);
    }
}
