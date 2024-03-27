package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EmployeeHome extends JFrame implements ActionListener {

    JButton addWork, updateWork, changePassword;
    private final String employeeId;

    public EmployeeHome(String employeeId) {
        this.employeeId = employeeId;
        setTitle("Employee Home");
        setLayout(null);

        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon scaledBackgroundIcon = new ImageIcon(backgroundImage);
        JLabel backgroundLabel = new JLabel(scaledBackgroundIcon);
        backgroundLabel.setBounds(0, 0, 1120, 630);
        add(backgroundLabel);

        JLabel heading = new JLabel("Employee Home");
        heading.setBounds(500, 20, 300, 40);
        heading.setFont(new Font("Raleway", Font.BOLD, 25));
        backgroundLabel.add(heading);

        addWork = new JButton("Add Work");
        addWork.setBounds(450, 80, 150, 40);
        addWork.addActionListener(this);
        backgroundLabel.add(addWork);

//        updateWork = new JButton("Update Work");
//        updateWork.setBounds(620, 80, 150, 40);
//        updateWork.addActionListener(this);
//        backgroundLabel.add(updateWork);

        changePassword = new JButton("Change Password");
        changePassword.setBounds(620, 80, 150, 40);
        changePassword.addActionListener(this);
        backgroundLabel.add(changePassword);

        setSize(1120, 630);
        setLocation(250, 100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addWork) {
            new AddEmployeeWork(employeeId);
        } else if (ae.getSource() == updateWork) {
           
            JOptionPane.showMessageDialog(this, "Update Work button clicked");
        } else {
            new UpdateEmployeePassword(employeeId);
        }
    }

    public static void main(String[] args) {
        // Not required as this class is not intended to be run independently
    }
}
