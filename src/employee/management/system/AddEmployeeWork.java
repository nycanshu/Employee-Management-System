package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

public class AddEmployeeWork extends JFrame implements ActionListener {

    JTextField tfEmployeeName, tfEmployeeId, tfRole, tfProject, tfTaskCategory, tfDescription;
    JDateChooser dcDateTimeDuration;
    JButton addWork, back;
    private final String employeeId;

    public AddEmployeeWork(String employeeId) {
        this.employeeId = employeeId;
        
        setTitle("Add Your Status");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Add Employee Work Detail");
        heading.setBounds(250, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        JLabel labelEmployeeName = new JLabel("Employee Name");
        labelEmployeeName.setBounds(50, 100, 150, 30);
        labelEmployeeName.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelEmployeeName);

        tfEmployeeName = new JTextField();
        tfEmployeeName.setBounds(250, 100, 150, 30);
        add(tfEmployeeName);

        JLabel labelEmployeeId = new JLabel("Employee ID");
        labelEmployeeId.setBounds(450, 100, 150, 30);
        labelEmployeeId.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelEmployeeId);

        tfEmployeeId = new JTextField(employeeId);
        tfEmployeeId.setEditable(false);
        tfEmployeeId.setBounds(600, 100, 150, 30);
        add(tfEmployeeId);

        JLabel labelRole = new JLabel("Role");
        labelRole.setBounds(50, 150, 150, 30);
        labelRole.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelRole);

        tfRole = new JTextField();
        tfRole.setBounds(250, 150, 150, 30);
        add(tfRole);

        JLabel labelProject = new JLabel("Project");
        labelProject.setBounds(450, 150, 150, 30);
        labelProject.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelProject);

        tfProject = new JTextField();
        tfProject.setBounds(600, 150, 150, 30);
        add(tfProject);

        JLabel labelTaskCategory = new JLabel("Task Category");
        labelTaskCategory.setBounds(50, 200, 150, 30);
        labelTaskCategory.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelTaskCategory);

        tfTaskCategory = new JTextField();
        tfTaskCategory.setBounds(250, 200, 150, 30);
        add(tfTaskCategory);

        JLabel labelDescription = new JLabel("Description");
        labelDescription.setBounds(50, 250, 150, 30);
        labelDescription.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelDescription);

        tfDescription = new JTextField();
        tfDescription.setBounds(250, 250, 500, 100);
        add(tfDescription);

        JLabel labelDateTimeDuration = new JLabel("Date");
        labelDateTimeDuration.setBounds(50, 380, 150, 30);
        labelDateTimeDuration.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelDateTimeDuration);

        dcDateTimeDuration = new JDateChooser();
        dcDateTimeDuration.setBounds(250, 380, 150, 30);
        add(dcDateTimeDuration);

        addWork = new JButton("Add Work");
        addWork.setBounds(250, 450, 150, 40);
        addWork.addActionListener(this);
        addWork.setBackground(Color.BLACK);
        addWork.setForeground(Color.WHITE);
        add(addWork);

        back = new JButton("Back");
        back.setBounds(450, 450, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        add(back);

        setSize(900, 550);
        setLocation(300, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == addWork) {
        // Check if any of the fields are empty
        if (tfEmployeeName.getText().isEmpty() || tfRole.getText().isEmpty() || 
            tfProject.getText().isEmpty() || tfTaskCategory.getText().isEmpty() ||
            tfDescription.getText().isEmpty() || dcDateTimeDuration.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please fill in all details", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // All fields are filled, proceed with adding work details
            String employeeName = tfEmployeeName.getText();
            String employeeId = tfEmployeeId.getText(); // Assuming you have a text field for employee ID
            String role = tfRole.getText();
            String project = tfProject.getText();
            String taskCategory = tfTaskCategory.getText();
            String description = tfDescription.getText();
            Date dateTimeDuration = dcDateTimeDuration.getDate();

            try {
                Conn conn = new Conn();
                String query = "INSERT INTO EmployeeWork (EmployeeID, EmployeeName, Role, Project, TaskCategory, Description, DateTimeDuration) " +
                               "VALUES (?, ?, ?, ?, ?, ?, ?)";
                var pstmt = conn.c.prepareStatement(query);
                pstmt.setString(1, employeeId);
                pstmt.setString(2, employeeName);
                pstmt.setString(3, role);
                pstmt.setString(4, project);
                pstmt.setString(5, taskCategory);
                pstmt.setString(6, description);
                pstmt.setDate(7, new java.sql.Date(dateTimeDuration.getTime()));

                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Work added successfully");
                setVisible(false);
                new EmployeeHome(employeeId); // Navigate back to the Home screen after adding work
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    } else if (ae.getSource() == back) {
        setVisible(false);
        new EmployeeHome(employeeId); // Navigate back to the Home screen
    }
}



    public static void main(String args[]) {
       
    }
}
