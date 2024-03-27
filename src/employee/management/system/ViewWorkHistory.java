

package employee.management.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ViewWorkHistory extends JFrame implements ActionListener {

    private final String employeeId;
    private final JComboBox<String> dateComboBox;
    private final JButton showDataButton;
    private final DefaultCategoryDataset dataset;
    private final ChartPanel chartPanel;

    public ViewWorkHistory(String employeeId) {
        this.employeeId = employeeId;
        setTitle("Work History for Employee: " + employeeId);
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel headingLabel = new JLabel("Work History");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(headingLabel);

        // Date Selection Panel
        JPanel datePanel = new JPanel();
        datePanel.setBackground(Color.WHITE);
        JLabel dateLabel = new JLabel("Select Date:");
        dateComboBox = new JComboBox<>();
        datePanel.add(dateLabel);
        datePanel.add(dateComboBox);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        showDataButton = new JButton("Show Data");
        showDataButton.addActionListener(this);
        buttonPanel.add(showDataButton);

        // Chart Panel
        dataset = new DefaultCategoryDataset();
        JFreeChart chart = ChartFactory.createBarChart(
                "Work Distribution",
                "Task Category",
                "Total Time (hours)",
                dataset, // Changed y-axis label to "Total Time (hours)"
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));

        // Add components to frame
        add(headerPanel, BorderLayout.NORTH);
        add(datePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(chartPanel, BorderLayout.WEST);

        setSize(800, 500); // Adjusted frame size
        setLocationRelativeTo(null);
        setVisible(true);

        // Populate date combo box
        populateDateComboBox();
    }

    private void populateDateComboBox() {
        try {
            Conn conn = new Conn();
            Statement stmt = conn.c.createStatement();
            String query = "SELECT DISTINCT DateTimeDuration FROM EmployeeWork WHERE EmployeeID = '" + employeeId + "'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String date = rs.getString("DateTimeDuration");
                dateComboBox.addItem(date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving dates", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showData() {
    String selectedDate = (String) dateComboBox.getSelectedItem();
    dataset.clear();

    try {
        Conn conn = new Conn();
        Statement stmt = conn.c.createStatement();
        String query = "SELECT TaskCategory, SUM(TIMESTAMPDIFF(MINUTE, StartTime, EndTime)) AS Duration " +
                "FROM EmployeeWork " +
                "WHERE EmployeeID = '" + employeeId + "' AND DateTimeDuration = '" + selectedDate + "' " +
                "GROUP BY TaskCategory";
        ResultSet rs = stmt.executeQuery(query);

        int colorIndex = 0; // Index to track the color to be used
        while (rs.next()) {
            String taskCategory = rs.getString("TaskCategory");
            int durationInMinutes = rs.getInt("Duration");
            double durationInHours = durationInMinutes / 60.0; // Convert minutes to hours
            dataset.addValue(durationInHours, "Time (hours)", taskCategory); // Changed to hours
            
            // Set custom color for the current task category
            chartPanel.getChart().getCategoryPlot().getRenderer().setSeriesPaint(colorIndex, getRandomColor());
            
            colorIndex++; // Move to the next color
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Work Distribution on " + selectedDate,
                "Task Category",
                "Total Time (hours)", // Changed y-axis label to "Total Time (hours)"
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        chartPanel.setChart(chart);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error retrieving work data", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private Color getRandomColor() {
    // Generate random RGB values to create a color
    int r = (int) (Math.random() * 256);
    int g = (int) (Math.random() * 256);
    int b = (int) (Math.random() * 256);
    return new Color(r, g, b);
}


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showDataButton) {
            showData();
        }
    }

    public static void main(String[] args) {
        // For testing purposes
        // new ViewWorkHistory("EMP001");
    }
}
