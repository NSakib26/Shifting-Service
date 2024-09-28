package User_class;

import User_class.User;
import Classes.Payment;
import Classes.Login;
import Classes.Login;
import Classes.Payment;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;

public class UserDashboard implements ActionListener {
    private JFrame frame;
    private User user;
	JCheckBox cb2,cb3,cb5,cb4;
  
  

    public UserDashboard(User user) {
        this.user = user;

        frame = new JFrame("User Dashboard");
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
		
		ImageIcon img = new ImageIcon("Images/bg.jpg"); // Load the local image
        JLabel background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 600, 400); // Set size to match the frame
        frame.add(background);
		

        JLabel welcomeLabel = new JLabel("Welcome to User Dashboard, " + user.getName() + "!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//welcomeLabel.setBounds(10,30,300,40);
        frame.add(welcomeLabel, BorderLayout.NORTH);


  
        JButton profileButton = new JButton("View Profile" );
		profileButton.setBounds(20, 10, 120, 25);
		profileButton.setForeground(Color.white);
		profileButton.setBackground(Color.green);
        profileButton.addActionListener(this);
        background.add(profileButton);

        JButton updateButton = new JButton("Update Profile");
		updateButton.setBounds(150, 10, 120, 25);
		updateButton.setForeground(Color.white);
		updateButton.setBackground(Color.green);
        updateButton.addActionListener(this);
        background.add(updateButton);

		JLabel l=new JLabel("Get Our Services Now ");  
        l.setBounds(150,60,300,30);  
        //cb1=new JCheckBox("No");  
        //cb1.setBounds(200,80,150,20);  
        cb2=new JCheckBox("Small Truck");  
        cb2.setBounds(200,110,150,20);  
        cb3=new JCheckBox("Large Truck");  
        cb3.setBounds(200,140,150,20);  
		cb4=new JCheckBox("3 workers ");  
        cb4.setBounds(200,170,150,20); 
		cb5=new JCheckBox("5 workers");  
        cb5.setBounds(200,200,150,20); 
		
		background.add(l);
		background.add(cb4);
		background.add(cb2);
		background.add(cb3); 
		background.add(cb5); 
		
	
		
		/*JLabel p=new JLabel("How many people do you need?");  
        p.setBounds(150,170,400,20);  
		JTextField people= new JTextField();
		people.setBounds(240,200,60,20); 
		//int a=people;
		background.add(p);
		background.add(people);*/
		
		
		

        JButton logoutButton = new JButton("Logout");
		logoutButton.setBounds(400, 150, 100, 30);
		logoutButton.setForeground(Color.white);
		logoutButton.setBackground(Color.red);
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Login();
            }
        });
        frame.add(logoutButton, BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
		
		JButton k=new JButton("OK");  
        k.setBounds(250,250,80,30);  
		k.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
        
        HashMap<String, Double> selectedServices = new HashMap<>();

        if (cb4.isSelected()) {
            selectedServices.put("3 workers", 2500.0);
        }
        if (cb2.isSelected()) {
            selectedServices.put("Small Truck", 6000.0);
        }
        if (cb3.isSelected()) {
            selectedServices.put("Large Truck", 10000.0);
        }
        if (cb5.isSelected()) {
            selectedServices.put("5 workers", 5500.0);
        }

        if (selectedServices.isEmpty()) {
            JOptionPane.showMessageDialog(background, "No services selected!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Pass the selected services to the Payment class and process payment
            Payment payment = new Payment(selectedServices);
            payment.processPayment();
        }
    }
});

		
		
		
		
		
		
       /* k.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			float amount=0;  
			String msg="";  
			if(cb4.isSelected()){  
            amount+=2500;  
            msg=" 3 workers  : 2500\n";  
			}  
			if(cb2.isSelected()){  
            amount+=6000;  
            msg+="Small Truck: 6000\n";  
			}  
			if(cb3.isSelected()){  
            amount+=10000;  
            msg+="Large Truck: 10000\n";  
        
    }
		if(cb5.isSelected()){  
            amount+=5500;  
            msg+="5 workers : 5500\n";  
        
    }

 
		//amount+=a*500;
        msg+="-----------------\n";  
        JOptionPane.showMessageDialog(background,msg+"Total: "+amount); 
		
				
			}
			
			
			
		});  */
		
		background.add(k);
		
		
		
		
    }


		


    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("View Profile")) {
            viewProfile();
        } else if (e.getActionCommand().equals("Update Profile")) {
            updateProfile();
        }
		
		
		
		
		

 }




    private void viewProfile() {
        JOptionPane.showMessageDialog(frame, "Name: " + user.getName() + "\nEmail: " + user.getEmail() + "\nGender: " + user.getGender(), "User Profile", JOptionPane.PLAIN_MESSAGE);
    }

    private void updateProfile() {
    JFrame updateFrame = new JFrame("Update Profile");
    updateFrame.setSize(400, 200);
    updateFrame.setLayout(new BorderLayout());

  
    JPanel fieldPanel = new JPanel(new GridLayout(4, 2));
    JTextField nameField = new JTextField(user.getName());
    JTextField emailField = new JTextField(user.getEmail());
    JTextField passwordField = new JTextField(user.getPassword());
    JTextField genderField = new JTextField(user.getGender());

    
    fieldPanel.add(new JLabel("Name:"));
    fieldPanel.add(nameField);
    fieldPanel.add(new JLabel("Email:"));
    fieldPanel.add(emailField);
    fieldPanel.add(new JLabel("Password:"));
    fieldPanel.add(passwordField);
    fieldPanel.add(new JLabel("Gender:"));
    fieldPanel.add(genderField);

    // Add field panel to the update frame
    updateFrame.add(fieldPanel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    // Add update button
    JButton updateButton = new JButton("Update");
    updateButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Update user information
            user.setName(nameField.getText());
            user.setEmail(emailField.getText());
            user.setPassword(passwordField.getText());
            user.setGender(genderField.getText());

            // Update userdata.txt
            updateUserDataFile(user);

            // Show confirmation message
            JOptionPane.showMessageDialog(updateFrame, "Profile updated successfully!");
        }
    });
    buttonPanel.add(updateButton);

    // Add logout button
    JButton logoutButton = new JButton("Logout");
    logoutButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        
            updateFrame.dispose();
        }
    });
    buttonPanel.add(logoutButton);

    
    updateFrame.add(buttonPanel, BorderLayout.SOUTH);

    updateFrame.setVisible(true);
    updateFrame.setLocationRelativeTo(null); 
	}


    private void updateUserDataFile(User user) {
        try {
            File file = new File("Data/userdata.txt");
            if (!file.exists()) {
                JOptionPane.showMessageDialog(frame, "User data file not found!");
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].equals(user.getEmail())) {
                    line = user.getName() + "," + user.getEmail() + "," + user.getPassword() + "," + user.getGender();
                }
                sb.append(line).append("\n");
            }
            reader.close();
		}
           
		   catch (IOException ex) {
            ex.printStackTrace();
			
        }
		
		
		

    }

   
   

   

}