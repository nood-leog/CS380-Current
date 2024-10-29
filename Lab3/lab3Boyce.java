
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;

public class lab3Boyce
{
    private JButton addButton;
    private JButton removeButton;
    private JButton printTableButton;
    private JButton updateButton;
    private JPanel buttons;
    private JPanel table;
    private JTable table1;
    private JPanel textArea;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JLabel studentIDLabel;
    private JLabel finalGradeLabel;
    private JLabel lastNameLabel;
    private JLabel firstNameLabel;
    private JPanel mainPanel;
    private JFrame mainFrame;


    public static void main(String[] args)
    {

        lab3Boyce frame = new lab3Boyce();

    }

    public lab3Boyce()
    {
        Connection connectInfo = connect(); //call connect method before drawing gui


        mainFrame = new JFrame("myCWU Official Grades");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get screen dimensions and set frame size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.40);
        int height = (int) (screenSize.height * 0.40);
        mainFrame.setSize(width, height);
        mainFrame.setLocationRelativeTo(null); // Center the frame

        mainFrame.setContentPane(mainPanel);

        mainFrame.setVisible(true);

        //buttons-----------------------------------------------------------
        //add button action listeners
        addButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                add();
            }
        });

        //remove button action listener
        removeButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                remove(connectInfo);
            }
        });

        //update button action listener
        updateButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                update();
            }
        });

        //print table button action listener
        printTableButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                printTable();
            }
        });

    }

    //gui helper methods-----------------------------------------------------------

    //add method
    public void add() {
        System.out.println("add method called");

        String[] enteredText = getTextFieldValues();

        String studentID = enteredText[0];
        String firstName = enteredText[1];
        String lastName = enteredText[2];
        String midtermGrade = enteredText[3];

        //run sql insert statement
        String query = "INSERT INTO 302_grades (studentID, firstName, lastName, midtermGrade) VALUES (?, ?, ?, ?)";

        try (Connection con = connect(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, studentID);
            pstmt.setString(2, firstName); //BUG! First name = student ID???
            pstmt.setString(3, lastName);
            pstmt.setString(4, midtermGrade);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Record added successfully.");
                // Optionally, you can refresh the table data here
                populateTable(con);
            } else {
                System.out.println("Failed to add the record.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding record: " + e.getMessage());
        }
    }

    //remove method
    public void remove(Connection con)
    {
        System.out.println("remove method called");

        String[] enteredText = getTextFieldValues();

        String studentID = enteredText[0];

        //run sql delete statement
        String query = "DELETE FROM 302_grades WHERE studentID = ?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, studentID);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Record deleted successfully.");
                // Optionally, you can refresh the table data here
                populateTable(con);
            } else {
                System.out.println("No record found with the given student ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting record: " + e.getMessage());
        }
    }

    //update method
    public void update()
    {
        System.out.println("Updating table...");
    }

    //print table method
    public void printTable()
    {
        System.out.println("Printing Table...");

        System.out.println("====================");
        //get table model
        DefaultTableModel model = (DefaultTableModel) table1.getModel();

        //get row count
        int rowCount = model.getRowCount();

        //print table loop
        for (int i = 0; i < rowCount; i++)
        {
            String studentID = model.getValueAt(i, 0).toString();
            String firstName = model.getValueAt(i, 1).toString();
            String lastName = model.getValueAt(i, 2).toString();
            String midtermGrade = model.getValueAt(i, 3).toString();

            System.out.println(studentID + " " + firstName + " " + lastName + " " + midtermGrade);
        }
        System.out.println("====================");
    }

    //get text field values method
    public String[] getTextFieldValues()
    {
        String studentID = textField1.getText();
        String firstName = textField2.getText();
        String lastName = textField3.getText();
        String midtermGrade = textField4.getText();

        return new String[]{studentID, firstName, lastName, midtermGrade};
    }



    //sql-----------------------------------------------------------

    //connect method
    public Connection connect()
    {

        String url = "jdbc:mysql://localhost:3306/mycwugrades";
        String userName = "root";
        String password = "";

        try
        {
            Connection con = DriverManager.getConnection(url, userName, password);
            System.out.println("connected");

            populateTable(con); //call select method

            return con;

        }catch (Exception e)
        {
            System.out.println("Connect method exception: "+ e.getMessage());
        }

        return null;
    }

    //populate table method
    public void populateTable(Connection con)
    {

        String query = "SELECT studentID, firstName, lastName, midtermGrade FROM 302_grades";

        try (Statement statement = con.createStatement(); ResultSet result = statement.executeQuery(query))
        {

            //table model with column names
            DefaultTableModel model = new DefaultTableModel(new String[]{"Student ID", "First Name", "Last Name", "Midterm Grade"}, 0);

            //populate model loop with rows from ResultSet
            while (result.next())
            {
                String studentID = result.getString("studentID");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                String midtermGrade = result.getString("midtermGrade");

                model.addRow(new Object[]{studentID, firstName, lastName, midtermGrade});
            }

            //set model to table
            table1.setModel(model);
        } catch (SQLException e)
        {
            System.out.println("Error populating table: " + e.getMessage());
        }
    }
}
