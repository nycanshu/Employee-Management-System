package employee.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ViewEmployeeWorkAsAdmin extends JFrame implements ActionListener {
    private JComboBox<String> employeeIdComboBox;
    private JButton getDataButton;

    public ViewEmployeeWorkAsAdmin() {
        setTitle("Admin Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        employeeIdComboBox = new JComboBox<>();
        getDataButton = new JButton("Get Data");
        getDataButton.addActionListener(this);

        add(new JLabel("Select Employee ID:"));
        add(employeeIdComboBox);
        add(getDataButton);

        // Populate the employee ID dropdown
        populateEmployeeIds();

        setSize(300, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void populateEmployeeIds() {
        try {
            Conn conn = new Conn();
            Statement stmt = conn.c.createStatement();
            String query = "SELECT EmployeeID FROM EmployeeLogin";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String employeeId = rs.getString("EmployeeID");
                employeeIdComboBox.addItem(employeeId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving employee IDs", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getDataButton) {
            String selectedEmployeeId = (String) employeeIdComboBox.getSelectedItem();
            new ViewWorkHistory(selectedEmployeeId);
        }
    }

    public static void main(String[] args) {
        //new AdminInterface();
    }
}
