package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EmployeeHome extends JFrame implements ActionListener {

    JButton addWork,  changePassword, viewworkhistory;
    private final String employeeId;

    public EmployeeHome(String employeeId) {
        this.employeeId = employeeId;
        setTitle("Employee Home");

        JPanel contentPane = new JPanel(); // Create a content pane
        contentPane.setLayout(new BorderLayout()); // Use BorderLayout for the content pane

        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon scaledBackgroundIcon = new ImageIcon(backgroundImage);
        JLabel backgroundLabel = new JLabel(scaledBackgroundIcon);
        contentPane.add(backgroundLabel, BorderLayout.CENTER); // Add background label to the content pane

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Panel for buttons
        buttonPanel.setOpaque(false); // Make the panel transparent
        contentPane.add(buttonPanel, BorderLayout.NORTH); // Add button panel to the top of content pane

        JLabel heading = new JLabel("Employee Home");
        heading.setFont(new Font("Raleway", Font.BOLD, 25));
        buttonPanel.add(heading);

        addWork = new JButton("Add Work");
        addWork.addActionListener(this);
        buttonPanel.add(addWork);

//        updateWork = new JButton("Update Work");
//        updateWork.addActionListener(this);
//        buttonPanel.add(updateWork);

        
        viewworkhistory = new JButton("View Work History");
        viewworkhistory.addActionListener(this);
        buttonPanel.add(viewworkhistory);

        
        changePassword = new JButton("Change Password");
        changePassword.addActionListener(this);
        buttonPanel.add(changePassword);

        setContentPane(contentPane); // Set content pane
        pack(); // Pack components
        setLocationRelativeTo(null); // Center the frame on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Default close operation
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addWork) {
             setVisible(false);
            new AddEmployeeWork(employeeId);
        } else if (ae.getSource() == viewworkhistory) {
             setVisible(false);
             new ViewWorkHistory(employeeId);
             //JOptionPane.showMessageDialog(this, "viewworkhistory  button clicked");
            
        } else {
            new UpdateEmployeePassword(employeeId);
        }
    }

    public static void main(String[] args) {
        // Not required as this class is not intended to be run independently
    }
}
