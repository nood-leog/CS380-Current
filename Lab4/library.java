//library.java
//Alex Boyce

//!!! IMPORTANT !!!
//to test the program, run 'test'

import java.util.Scanner;

class library extends dataHandler
{
    public static void main(String[] args)
    {
        //create new library object
        new library();
    }

    //dataHandler object
    dataHandler dH = new dataHandler();


    //library constructor
    public library()
    {
        System.out.println("Library inventory management system. \nType 'help' for a list of commands and available programs.\n");

        //get user input
        Scanner input = new Scanner(System.in);

        //loop to keep program running
        while (true)
        {
            //call command method
            System.out.print("> ");
            String userInput = input.nextLine().toLowerCase(); //convert to lowercase to make commands case-insensitive

            //parse user input to determine which command to run
            String command = userInput.split(" ")[0]; //split the user input by spaces and get the first word ie the command
            String argument = userInput.substring(command.length()).trim(); //get the rest of the user input as the argument


            switch (command)
            {
                case "quit": //quit the program
                    quit();
                    break;
                case "help": //display a list of commands
                    help();
                    break;
                case "test": //run a test of the functions of the program
                    test();
                    break;
                case "checkout":
                    checkout(argument);
                    break;
                case "return":
                    returnItem(argument);
                    break;
                case "info":
                    info(argument);
                    break;
                case "search":
                    search(argument);
                    break;
                case "add":
                    add(argument);
                    break;
                case "remove":
                    remove(argument);
                    break;
                case "booklist":
                    booklist();
                    break;
                case "dvdlist":
                    dvdlist();
                    break;
                case "checkoutlist":
                    checkoutlist();
                    break;
                default:
                    System.out.println("Invalid command. Type 'help' for a list of commands and usage.");
                    break;
            }
        }
    }

    //quit method
    public void quit() {
        System.out.println("Quitting program...");
        System.exit(0);
    }

    //help method
    public void help() {
        System.out.println("""
                Available commands:
                quit - quit the program
                help - display a list of commands and available programs
                test - run a test of the functions of the program
                checkout <callNumber> <studentID> - check out a book or DVD by adding a record to the checkout.csv file
                return <callNumber> - return a book or DVD by removing the record from the checkout.csv file
                info <callNumber> - display information about a book or DVD
                search <keyword> - search for a book or DVD by title or author
                add <type> <title> <author> <year> <ISBN> <callNumber> - add a book or DVD to the library inventory
                remove <callNumber> - remove a book or DVD from the library inventory
                booklist - display a list of all books in the library inventory
                dvdlist - display a list of all DVDs in the library inventory
                checkoutlist - display a list of all checked out books and DVDs
                """);
    }

    //checkout method
    public void checkout(String argument)
    {
        //check to make sure the argument is not empty
        if (argument.isEmpty())
        {
            System.out.println("Usage: checkout <callNumber> <studentID>");
            //return;
        }
        else
        {
            //split the argument by spaces
            String[] parts = argument.split(" ");

            //check to make sure there are two parts
            if (parts.length != 2)
            {
                System.out.println("Usage: checkout <callNumber> <studentID>");
            }
            else
            {
                //get the call number and student ID
                String callNumber = parts[0];
                String studentID = parts[1];

                //check out the book or DVD
                System.out.println("Checking out " + callNumber + " to student " + studentID);
                dH.handleCheckout(callNumber, studentID);
                System.out.println("Checkout successful.");

            }

        }


    }

    //return method
    public void returnItem(String argument)
    {
        //check to make sure the argument is not empty
        if (argument.isEmpty())
        {
            System.out.println("Usage: return <callNumber>");
            //return;
        }
        else
        {
            //return the book or DVD
            System.out.println("Returning " + argument);
            dH.handleReturn(argument);
            System.out.println("Return successful.");
        }
    }

    //info method
    public void info(String argument)
    {
        //check to make sure the argument is not empty
        if (argument.isEmpty())
        {
            System.out.println("Usage: info <callNumber>");
            //return;
        }
        else
        {
            //display information about the book or DVD
            System.out.println("Displaying information about " + argument);
            dH.handleInfo(argument);
        }
    }

    //search method
    public void search(String argument)
    {
        //check to make sure the argument is not empty
        if (argument.isEmpty())
        {
            System.out.println("Usage: search <keyword>");
            //return;
        }
        else
        {
            //search for the book or DVD
            System.out.println("Searching for " + argument);
            dH.handleSearch(argument);
            System.out.println("Search complete.");
        }
    }

    //add method
    public void add(String argument)
    {
        //check to make sure the argument is not empty
        if (argument.isEmpty())
        {
            System.out.println("Usage for Books: add book <callNumber> <title> <author> <year> <ISBN> "); //5 parts
            System.out.println("Usage for DVDs: add dvd <callNumber> <title> <year>"); //4 parts
        }
        else
        {
            //split the argument by spaces
            String[] parts = argument.split(" ");

            //get the type of item
            String type = parts[0];

            if (type.equals("book"))
            {
                //check to make sure there are 5 parts
                if (parts.length != 6)
                {
                    System.out.println("Usage: add book <callNumber> <title> <author> <year> <ISBN>");
                }
                else
                {
                    //get the call number, title, author, year, and ISBN
                    String callNumber = parts[1];
                    String title = parts[2];
                    String author = parts[3];
                    String year = parts[4];
                    String ISBN = parts[5];

                    //add the book
                    System.out.println("Adding book " + callNumber + " " + title + " " + author + " " + year + " " + ISBN);
                    dH.handleAddBook(callNumber, title, author, year, ISBN);
                    System.out.println("Book added successfully.");
                }
            }
            else if (type.equals("dvd"))
            {
                //check to make sure there are 4 parts
                if (parts.length != 4)
                {
                    System.out.println("Usage: add dvd <callNumber> <title> <year>");
                }
                else
                {
                    //get the call number, title, and year
                    String callNumber = parts[1];
                    String title = parts[2];
                    String year = parts[3];

                    //add the DVD
                    System.out.println("Adding DVD " + callNumber + " " + title + " " + year);
                    dH.handleAddDVD(callNumber, title, year);
                    System.out.println("DVD added successfully.");

                }
            }
            else
            {
                System.out.println("Usage for Books: add book <callNumber> <title> <author> <year> <ISBN> ");
                System.out.println("Usage for DVDs: add dvd <callNumber> <title> <year>");
            }
        }
    }

    //remove method
    public void remove(String argument)
    {
        //check to make sure the argument is not empty
        if (argument.isEmpty())
        {
            System.out.println("Usage: remove <callNumber>");
            //return;
        }
        else
        {
            //remove the book or DVD
            System.out.println("Removing " + argument);
            dH.handleRemove(argument);
            System.out.println("Removal successful.");
        }
    }

    //booklist method
    public void booklist()
    {
        //display a list of all books in the library inventory
        System.out.println("Displaying book list...");
        getBookList();
        System.out.println("============================");
    }

    //dvdlist method
    public void dvdlist()
    {
        //display a list of all DVDs in the library inventory
        System.out.println("Displaying DVD list...");
        getDVDList();
        System.out.println("============================");
    }

    //checkoutlist method
    public void checkoutlist()
    {
        //display a list of all checked out books and DVDs
        System.out.println("Displaying checkout list...");
        getCheckoutList();
        System.out.println("============================");
    }

    //test method
    public void test()
    {
        //test add
        System.out.println("Testing add...");
        add("book bk1007456 testBook testAuthor 2024 9780743273565");
        System.out.println("============================");

        //test checkout
        System.out.println("Testing checkout...");
        checkout("dvd200456,STU1073948");
        checkout("bk1007456,STU1073948");
        System.out.println("============================");

        //test info
        System.out.println("Testing info...");
        info("dvd200456");
        info("bk1007456");
        System.out.println("============================");

        //test booklist
        System.out.println("Testing booklist...");
        booklist();
        System.out.println("============================");

        //test dvdlist
        System.out.println("Testing dvdlist...");
        dvdlist();
        System.out.println("============================");

        //test checkoutlist
        System.out.println("Testing checkoutlist...");
        checkoutlist();
        System.out.println("============================");

        //test search
        System.out.println("Testing search...");
        search("testBook");
        search("testAuthor");
        System.out.println("============================");

        //test return
        System.out.println("Testing return...");
        returnItem("dvd200456");
        returnItem("bk1007456");
        System.out.println("============================");

        //test remove
        System.out.println("Testing remove...");
        remove("dvd200456");
        remove("bk1007456");
        System.out.println("============================");

        System.out.println("Test complete.");
        System.out.println("============================");
    }


}