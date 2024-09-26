import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class UpdateUser {
    private JFrame frame;
    private JTextField nameField, emailField, passwordField, genderField;
    private String userEmail;
    private ManageUsers manageUsers;

    public UpdateUser(String userEmail, ManageUsers manageUsers) {
        this.userEmail = userEmail;
        this.manageUsers = manageUsers;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Update User");
        frame.setSize(400, 300);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(nameLabel, gbc);

        nameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(nameField, gbc);

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(emailLabel, gbc);

        emailField = new JTextField(userEmail, 20);
        emailField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(passwordLabel, gbc);

        passwordField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(passwordField, gbc);

        JLabel genderLabel = new JLabel("Gender:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(genderLabel, gbc);

        genderField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        frame.add(genderField, gbc);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(updateButton, gbc);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(backButton, gbc);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Login();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        frame.add(logoutButton, gbc);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        loadUserData();
    }

    private void loadUserData() {
        try {
            File file = new File("userdata.txt");
            if (!file.exists()) {
                JOptionPane.showMessageDialog(frame, "No users registered yet!");
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].equals(userEmail)) {
                    nameField.setText(parts[0]);
                    passwordField.setText(parts[2]);
                    genderField.setText(parts[3]);
                    break;
                }
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateUser() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String gender = genderField.getText();

        if (name.isEmpty() || password.isEmpty() || gender.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields!");
            return;
        }

        try {
            File file = new File("userdata.txt");
            if (!file.exists()) {
                JOptionPane.showMessageDialog(frame, "No users registered yet!");
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder updatedData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].equals(userEmail)) {
                    updatedData.append(String.join(",", name, email, password, gender)).append("\n");
                } else {
                    updatedData.append(line).append("\n");
                }
            }
            reader.close();

            FileWriter writer = new FileWriter(file);
            writer.write(updatedData.toString());
            writer.close();

            JOptionPane.showMessageDialog(frame, "User information updated successfully!");
            frame.dispose();
            manageUsers.displayUsers(); // Refresh the user data in ManageUsers
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
