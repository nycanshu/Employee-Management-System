package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EmployeeLogin extends JFrame implements ActionListener {
    JTextField tfusername; // Declare text field for username
    JPasswordField tfpassword; // Declare password field for password
    //String employeeId;
    public EmployeeLogin() { // Corrected constructor name
        setTitle("Employee Login");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblusername = new JLabel("Employee Id");
        lblusername.setBounds(40, 20, 100, 30);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 30);
        add(tfusername);
        //employeeId = tfusername.toString();

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 70, 100, 30);
        add(lblpassword);

        tfpassword = new JPasswordField(); // Corrected instantiation
        tfpassword.setBounds(150, 70, 150, 30);
        add(tfpassword);

        JButton login = new JButton("LOGIN");
        login.setBounds(150, 140, 150, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 200, 200);
        add(image);

        setSize(600, 300);
        setLocation(450, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String username = tfusername.getText();
            String password = new String(tfpassword.getPassword()); // Get password as string
            
            Conn c = new Conn();
            String query = "select * from employeelogin where employeeId = '"+username+"' and password = '"+password+"'";
            
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                setVisible(false);
                // You need to define 'employeeId' here before calling EmployeeHome
                 String employeeId = username;
                 new EmployeeHome(employeeId);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password");
                // setVisible(false); // Should not hide the login frame here
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Properly handle or log the exception
        }
    }

    public static void main(String[] args) {
        new EmployeeLogin(); // Create an instance of EmployeeLogin
    }
}
