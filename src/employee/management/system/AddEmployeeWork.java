package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;
import java.sql.Time;
import java.sql.SQLException;

public class AddEmployeeWork extends JFrame implements ActionListener {

    JTextField tfEmployeeName, tfEmployeeId, tfRole, tfProject, tfTaskCategory, tfDescription;
    JDateChooser dcDateTimeDuration;
    JButton addWork, back;
    JComboBox<String> startTimeAMPM, endTimeAMPM; // Added combo boxes for AM/PM selection
    JSpinField startTimeHour, startTimeMinute, endTimeHour, endTimeMinute;
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

        JLabel labelTimeRange = new JLabel("Enter Time Range:");
        labelTimeRange.setBounds(50, 420, 400, 30);
        labelTimeRange.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelTimeRange);

        startTimeHour = new JSpinField(1, 12); // Hours in 12-hour format
        startTimeHour.setBounds(200, 420, 50, 30);
        add(startTimeHour);

        JLabel startTimeSeparator = new JLabel(":");
        startTimeSeparator.setBounds(255, 420, 10, 30);
        add(startTimeSeparator);

        startTimeMinute = new JSpinField(0, 59);
        startTimeMinute.setBounds(270, 420, 50, 30);
        add(startTimeMinute);

        startTimeAMPM = new JComboBox<>(new String[]{"AM", "PM"});
        startTimeAMPM.setBounds(330, 420, 60, 30);
        add(startTimeAMPM);

        JLabel startTimeDash = new JLabel("-");
        startTimeDash.setBounds(400, 420, 10, 30);
        add(startTimeDash);

        endTimeHour = new JSpinField(1, 12);
        endTimeHour.setBounds(420, 420, 50, 30);
        add(endTimeHour);

        JLabel endTimeSeparator = new JLabel(":");
        endTimeSeparator.setBounds(475, 420, 10, 30);
        add(endTimeSeparator);

        endTimeMinute = new JSpinField(0, 59);
        endTimeMinute.setBounds(490, 420, 50, 30);
        add(endTimeMinute);

        endTimeAMPM = new JComboBox<>(new String[]{"AM", "PM"});
        endTimeAMPM.setBounds(550, 420, 60, 30);
        add(endTimeAMPM);

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
                tfDescription.getText().isEmpty() || dcDateTimeDuration.getDate() == null ||
                startTimeHour.getValue() == 0 || startTimeMinute.getValue() == 0 ||
                endTimeHour.getValue() == 0 || endTimeMinute.getValue() == 0) {
                JOptionPane.showMessageDialog(this, "Please fill in all details", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // All fields are filled, proceed with adding work details
                String employeeName = tfEmployeeName.getText();
                String employeeId = tfEmployeeId.getText(); // Assuming you have a text field for employee ID
                String role = tfRole.getText();
                String project = tfProject.getText();
                String taskCategory = tfTaskCategory.getText();
                String description = tfDescription.getText();
                Date dateTime = dcDateTimeDuration.getDate();
                int startHour = startTimeHour.getValue();
                int startMinute = startTimeMinute.getValue();
                int endHour = endTimeHour.getValue();
                int endMinute = endTimeMinute.getValue();

                // Convert 12-hour format to 24-hour format
                if (startTimeAMPM.getSelectedItem().equals("PM") && startHour != 12) {
                    startHour += 12;
                }
                if (endTimeAMPM.getSelectedItem().equals("PM") && endHour != 12) {
                    endHour += 12;
                }

                // Validate time range
                if (startHour > endHour || (startHour == endHour && startMinute >= endMinute)) {
                    JOptionPane.showMessageDialog(this, "End time must be after start time", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Conn conn = new Conn();
                    Date startTime = new Date(dateTime.getYear(), dateTime.getMonth(), dateTime.getDate(), startHour, startMinute);
                    Date endTime = new Date(dateTime.getYear(), dateTime.getMonth(), dateTime.getDate(), endHour, endMinute);
                    Time startTimeSql = new Time(startTime.getTime());
                    Time endTimeSql = new Time(endTime.getTime());

                    String query = "INSERT INTO EmployeeWork (EmployeeID, EmployeeName, Role, Project, TaskCategory, Description, DateTimeDuration, StartTime, EndTime) " +
                                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    var pstmt = conn.c.prepareStatement(query);
                    pstmt.setString(1, employeeId);
                    pstmt.setString(2, employeeName);
                    pstmt.setString(3, role);
                    pstmt.setString(4, project);
                    pstmt.setString(5, taskCategory);
                    pstmt.setString(6, description);
                    pstmt.setDate(7, new java.sql.Date(dateTime.getTime()));
                    pstmt.setTime(8, startTimeSql);
                    pstmt.setTime(9, endTimeSql);

                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Work added successfully");
                    setVisible(false);
                    new EmployeeHome(employeeId); // Navigate back to the Home screen after adding work
                } catch (SQLException e) {
                    if (e.getSQLState().equals("45000")) {
                        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new EmployeeHome(employeeId); // Navigate back to the Home screen
        }
    }

    public static void main(String[] args) {
        // For testing purposes
    }
}
