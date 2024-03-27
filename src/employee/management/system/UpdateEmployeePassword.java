package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateEmployeePassword extends JFrame implements ActionListener {
    private final String employeeId;
    private JTextField tfOldPassword, tfNewPassword;
    JButton updatePassword;

    UpdateEmployeePassword(String employeeId) {
        this.employeeId = employeeId;
        setTitle("Update Password");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Update Your Password");
        heading.setBounds(150, 30, 300, 40);
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        add(heading);

        JLabel lblEmployeeId = new JLabel("Employee ID:");
        lblEmployeeId.setBounds(50, 100, 150, 30);
        add(lblEmployeeId);

        JLabel lblEmpId = new JLabel(employeeId);
        lblEmpId.setBounds(200, 100, 150, 30);
        add(lblEmpId);

        JLabel lblOldPassword = new JLabel("Old Password:");
        lblOldPassword.setBounds(50, 150, 150, 30);
        add(lblOldPassword);

        tfOldPassword = new JPasswordField();
        tfOldPassword.setBounds(200, 150, 200, 30);
        add(tfOldPassword);

        JLabel lblNewPassword = new JLabel("New Password:");
        lblNewPassword.setBounds(50, 200, 150, 30);
        add(lblNewPassword);

        tfNewPassword = new JPasswordField();
        tfNewPassword.setBounds(200, 200, 200, 30);
        add(tfNewPassword);

        updatePassword = new JButton("Update Password");
        updatePassword.setBounds(150, 260, 200, 30);
        updatePassword.setBackground(Color.BLACK);
        updatePassword.setForeground(Color.WHITE);
        updatePassword.addActionListener(this);
        add(updatePassword);

        setSize(450, 350);
        setLocationRelativeTo(null); // Center the frame on screen
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updatePassword) {
            String oldPassword = tfOldPassword.getText();
            String newPassword = tfNewPassword.getText();
            
            if (oldPassword.isEmpty() || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in both old and new passwords", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

            try {
                Conn conn = new Conn();
                String query = "UPDATE employeelogin SET password = ? WHERE employeeId = ? AND password = ?";
                PreparedStatement pstmt = conn.c.prepareStatement(query);
                pstmt.setString(1, newPassword);
                pstmt.setString(2, employeeId);
                pstmt.setString(3, oldPassword);

                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Password updated successfully");
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update password. Please check your old password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String args[]) {
        // For testing purposes
    }
}
