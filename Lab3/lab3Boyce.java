/**
 * A gui program that allows the user to add, remove, update, and print a table of student grades from the connected sql database.
 * @author Alex Boyce
 * @since 2024-10-29
 *
 */


//url, username, and password for the sql database can be found in the connect method on line 314
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

    /**
    * Main method to create the frame of the gui
    * */
    public static void main(String[] args)
    {

        lab3Boyce frame = new lab3Boyce();

    }

    /**
     * Constructor for the gui:
     * Creates the frame and sets the size to 40% of the screen size
     * Centers the frame
     * Sets the content pane to the mainPanel
     * Makes the frame visible
     * Calls the connect method
     * Adds action listeners to the buttons
     *
     */
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
                add(connectInfo);
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
                update(connectInfo);
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

    //gui methods---------------------------------------------------

    /**
     * add method is called when the add button is clicked in the gui
     * It gets the text from the text fields and assigns them to variables
     * It then runs an SQL insert statement with the variables
     * <p>
     * If the statement is successful, it calls the populateTable method to refresh the table data
     * If the statement fails, it prints an error message
     *
     * @param con The connection to the sql database passed when clicked
     */
    //add method
    public void add(Connection con)
    {
        System.out.println("\nAdding entry...");

        String[] enteredText = getTextFieldValues(); //get entered text from text fields in an array

        //assign array values to variables
        String studentID = enteredText[0];
        String firstName = enteredText[1];
        String lastName = enteredText[2];
        String midtermGrade = enteredText[3];

        //run SQL insert statement with values from the array variables
        String query = "INSERT INTO 302_grades (studentID, firstName, lastName, midtermGrade) VALUES ('" + studentID + "', '" + firstName + "', '" + lastName + "', '" + midtermGrade + "')";

        try (Statement statement = con.createStatement())
        {
            int affectedRows = statement.executeUpdate(query);

            if (affectedRows > 0)
            {
                System.out.println("Record added successfully.");
                populateTable(con); //refresh the table data by calling the populateTable method again
            } else
            {
                System.out.println("Failed to add the record.");
            }
        } catch (SQLException e)
        {
            System.out.println("Error adding record: " + e.getMessage());
        }
    }

    /**
     * remove method is called when the remove button is clicked in the gui
     * It gets the student ID from the text field and assigns it to a variable
     * It then runs an SQL delete statement with the student ID variable
     * <p>
     * If the statement is successful, it calls the populateTable method to refresh the table data
     * If the statement fails, it prints an error message
     *
     * @param con The connection to the sql database passed when clicked
     */
    //remove method
    public void remove(Connection con)
    {
        System.out.println("\nRemoving entry...");

        String[] enteredText = getTextFieldValues();
        String studentID = enteredText[0];

        System.out.println("Student ID Being removed: " + studentID);

        //run SQL delete statement with the entered student ID
        String query = "DELETE FROM 302_grades WHERE studentID = '" + studentID + "'";

        try (Statement statement = con.createStatement())
        {
            int affectedRows = statement.executeUpdate(query);

            if (affectedRows > 0)
            {
                System.out.println("Record deleted successfully.");

                populateTable(con); //refresh the table data
            } else
            {
                System.out.println("No record found with the given student ID.");
            }
        } catch (SQLException e)
        {
            System.out.println("Error deleting record: " + e.getMessage());
        }
    }

    /**
     * update method is called when the update button is clicked in the gui
     * It gets the text from the text fields and assigns them to variables, similar to the add method
     * It then runs an SQL insert statement with the variables - also including DUPLICATE KEY UPDATE to update the record if it already exists
     * <p>
     * If the statement is successful, it calls the populateTable method to refresh the table data
     * If the statement fails, it prints an error message
     *
     * @param con The connection to the sql database passed when clicked
     */
    //update method
    public void update(Connection con)
    {
        System.out.println("\nUpdating table...");

        String[] enteredText = getTextFieldValues(); //get entered text from text fields in an array

        //assign array values to variables
        String studentID = enteredText[0];
        String firstName = enteredText[1];
        String lastName = enteredText[2];
        String midtermGrade = enteredText[3];

        //run SQL insert statement with values from the array variables
        String query = "INSERT INTO 302_grades (studentID, firstName, lastName, midtermGrade) VALUES ('" + studentID + "', '" + firstName + "', '" + lastName + "', '" + midtermGrade + "')" + "ON DUPLICATE KEY UPDATE " + "firstName = '" + firstName + "', " + "lastName = '" + lastName + "', " + "midtermGrade = '" + midtermGrade + "'";

        try (Statement statement = con.createStatement())
        {
            int affectedRows = statement.executeUpdate(query);

            if (affectedRows > 0)
            {
                System.out.println("Record updated successfully.");
                populateTable(con); //refresh the table data by calling the populateTable method again
            } else
            {
                System.out.println("Failed to update the record.");
            }
        } catch (SQLException e)
        {
            System.out.println("Error updating record: " + e.getMessage());
        }

    }


    /**
     * printTable method is called when the print table button is clicked in the gui
     * It gets the table model and row count
     * It then prints the table data in a loop
     *
     */
    //print table method
    public void printTable()
    {
        System.out.println("\nPrinting Table...");

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

    /**
     * getTextFieldValues method is called by the add, remove, and update methods
     * It gets the text from the text fields and assigns them to variables
     * It then returns the variables in an array
     *
     * @return String[] An array of the text field values input by the user
     */
    //get text field values method
    public String[] getTextFieldValues()
    {
        String studentID = textField1.getText();
        String firstName = textField2.getText();
        String lastName = textField3.getText();
        String midtermGrade = textField4.getText();

        System.out.println("Entry being modified: " + studentID + "," + firstName + "," + lastName + "," + midtermGrade);


        return new String[]{studentID, firstName, lastName, midtermGrade};
    }

    //sql-----------------------------------------------------------

    /**
     * connect method is called when the gui is created
     * It connects to the sql database using the jdbc driver
     * It then calls the populateTable method to display the table data
     *
     * @return Connection The connection to the sql database
     */
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

    /**
     * populateTable method is called when the gui is created and when the add, remove, and update methods are called
     * It runs an SQL select statement to get the student ID, first name, last name, and midterm grade from the database
     * It then populates the table model with the column names and the rows from the result set
     *
     * @param con The connection to the sql database passed when clicked
     */
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
