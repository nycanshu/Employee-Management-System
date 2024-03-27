package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Splash extends JFrame implements ActionListener {
   
    JButton adminLogin,employeeLogin;
    Splash() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        heading.setBounds(80, 30, 1200, 60);
        heading.setFont(new Font("serif", Font.PLAIN, 60));
        heading.setForeground(Color.RED);
        add(heading);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/front.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1100, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(50, 100, 1050, 500);
        add(image);
        
        adminLogin = new JButton("Admin Login");
adminLogin.setBounds(400, 400, 300, 70);
adminLogin.setBackground(Color.BLACK);
adminLogin.setForeground(Color.WHITE);
adminLogin.addActionListener(this);
image.add(adminLogin);

employeeLogin = new JButton("Employee Login");
employeeLogin.setBounds(710, 400, 300, 70); // Adjusted position
employeeLogin.setBackground(Color.BLACK);
employeeLogin.setForeground(Color.WHITE);
employeeLogin.addActionListener(this);
image.add(employeeLogin);

        
        
        setSize(1170, 650);
        setLocation(200, 50);
        setVisible(true);
        
        while(true) {
            heading.setVisible(false);
            try {
                Thread.sleep(500);
            } catch (Exception e){
                
            }
            
            heading.setVisible(true);
            try {
                Thread.sleep(500);
            } catch (Exception e){
                
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == adminLogin) {
            setVisible(false);
            new Login();
        }
         else {
            setVisible(false);
            new EmployeeLogin();
        }
    }
    public static void main(String args[]) {
        new Splash();
    }
}