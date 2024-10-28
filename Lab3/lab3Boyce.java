//CS380 Lab3
//Alex Boyce

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class lab3Boyce
{

    public static void main(String[] args)
    {

        System.out.println("Running lab3 main method");

        lab3Boyce frame = new lab3Boyce();
    }

    public lab3Boyce()
    {

        //sql-----------------------------------------------------------
        connect(); //call connect method


        //gui-----------------------------------------------------------
        JFrame frame = new JFrame("myCwu Official Grades");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get screen dimensions and set frame size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.40);
        int height = (int) (screenSize.height * 0.40);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null); // Center the frame


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        //add button - calls add() method
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                add(); // call the add method
            }
        });

        //remove button - calls remove() method
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                remove(); // call the remove method
            }
        });


        //update button - calls update() method
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                update(); // call the update method
            }
        });


        //print table button - calls printTable() method
        JButton printTableButton = new JButton("Print Table");
        printTableButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                printTable(); // call the printTable method
            }
        });

        panel.add(addButton);
        panel.add(removeButton);
        panel.add(updateButton);
        panel.add(printTableButton);

        frame.add(panel);
        frame.setVisible(true);

    }

    //gui helper methods-----------------------------------------------------------

    //add method
    public void add()
    {
        System.out.println("add method called");
    }

    //remove method
    public void remove()
    {
        System.out.println("remove method called");
    }

    //update method
    public void update()
    {
        System.out.println("update method called");
    }

    //print table method
    public void printTable()
    {
        System.out.println("printTable method called");
    }


    //sql-----------------------------------------------------------

    //connect method
    public void connect()
    {

        System.out.println("Connect method called");

        String url = "jdbc:mysql://localhost:3306/mycwugrades";
        String userName = "root";
        String password = "";

        try
        {
            Connection con = DriverManager.getConnection(url, userName, password);
            System.out.println("connected");

            select(con); //call select method

        }catch (Exception e)
        {
            System.out.println("Connect method exception: "+ e.getMessage());
        }

    }

    //select method
    public void select(Connection con) throws SQLException
    {
        System.out.println("Select method called");

        String query = "SELECT * FROM 302_grades";

        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(query);

        while(result.next())
        {
            System.out.println(result.getString("studentID" ));
            System.out.println(result.getString("firstName"));
            System.out.println(result.getString("lastName"));
            System.out.println(result.getString("midtermGrade"));
        }
    }

}
