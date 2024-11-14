//dataHandler class
import java.io.*;
import java.util.Scanner;


class dataHandler
{
    //add to checkout.csv with checkout command - passed callNumber and studentID
    //remove from checkout.csv with return command - passed callNumber
    //list info about a book or DVD with info command - passed callNumber

     public dataHandler()
     {
        //constructor
     }

    //handleCheckout
    //handleReturn
    //handleInfo
    //handleSearch
    //handleAdd
    //handleRemove
    //handleBookList
    //handleDVDList
    //handleCheckoutList


    //handleCheckout
    public void handleCheckout(String callNumber, String studentID)
    {
        //add callNumber and studentID to checkout.csv
        try
        {
            FileWriter writer = new FileWriter("checkout.csv", true);
            writer.write(callNumber + "," + studentID + "\n");
            writer.close();
        } catch (IOException e)
        {
            System.out.println("An error occurred.");

        }
    }

    //handleReturn
    public void handleReturn(String callNumber)
    {
        try
        {
            File inputFile = new File("checkout.csv"); //input file
            File tempFile = new File("checkout_temp.csv"); //temporary file to write to

            BufferedReader reader = new BufferedReader(new FileReader(inputFile)); //read from input file
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile)); //write to temporary file

            String line; //line to read from input file
            boolean header = true;

            while ((line = reader.readLine()) != null) //read each line from the file
            {

                if (line.startsWith(callNumber)) //check if the line starts with the callNumber
                {
                    continue;
                }


                if (header)
                {
                    writer.write(line);
                    writer.newLine();
                    header = false;
                }
                else
                {
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();

            if (inputFile.delete())
            {
                tempFile.renameTo(inputFile);
            }
            else
            {
                System.out.println("Failed to delete the original file.");
            }

        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
        }
    }

    //handleInfo - display information about a book or DVD
    public void handleInfo(String callNumber)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("library.csv"));
            String line;
            boolean found = false;

            //read through each line in the CSV
            while ((line = reader.readLine()) != null)
            {
                String[] fields = line.split(",");

                //skip over the header
                if (fields[0].equals("Type")) {
                    continue;
                }

                //check if the callNumber matches
                if (fields[5].equals(callNumber))
                {
                    found = true;
                    System.out.println("Item found!");
                    if (fields[0].equals("Book"))
                    {
                        System.out.println("Type: " + fields[0]);
                        System.out.println("Title: " + fields[1]);
                        System.out.println("Authors: " + fields[2]);
                        System.out.println("Publication Year: " + fields[3]);
                        System.out.println("ISBN: " + fields[4]);
                        System.out.println("Call Number: " + fields[5]);
                    }
                    else if (fields[0].equals("DVD"))
                    {
                        System.out.println("Type: " + fields[0]);
                        System.out.println("Title: " + fields[1]);
                        System.out.println("Year: " + fields[3]);
                        System.out.println("Call Number: " + fields[5]);
                    }
                    break;
                }
            }

            reader.close();

            if (!found)
            {
                System.out.println("No item found with the call number " + callNumber);
            }
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
        }
    }

    //handleSearch - search for a book or DVD based off a keyword
    public void handleSearch(String keyword)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("library.csv"));
            String line;
            boolean found = false;

            // Read each line in the CSV file
            while ((line = reader.readLine()) != null)
            {
                String[] fields = line.split(",");

                // Skip header
                if (fields[0].equals("Type")) {
                    continue;
                }

                // Check if any field contains the keyword (case insensitive)
                for (String field : fields)
                {
                    if (field.toLowerCase().contains(keyword.toLowerCase()))
                    {
                        found = true;
                        System.out.println("Match found:");
                        if (fields[0].equals("Book"))
                        {
                            System.out.println("Type: " + fields[0]);
                            System.out.println("Title: " + fields[1]);
                            System.out.println("Authors: " + fields[2]);
                            System.out.println("Publication Year: " + fields[3]);
                            System.out.println("ISBN: " + fields[4]);
                            System.out.println("Call Number: " + fields[5]);
                        }
                        else if (fields[0].equals("DVD"))
                        {
                            System.out.println("Type: " + fields[0]);
                            System.out.println("Title: " + fields[1]);
                            System.out.println("Year: " + fields[3]);
                            System.out.println("Call Number: " + fields[5]);
                        }
                        System.out.println(); // Blank line between matches
                        break;
                    }
                }
            }

            reader.close();

            if (!found)
            {
                System.out.println("No items found matching the keyword \"" + keyword + "\".");
            }
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
        }
    }

    //handleAddDVD
    //test with: add dvd dvd111111 The_Great_Gatsby_2 2045
    public void handleAddDVD(String callNumber, String title, String year) {
        try {
            FileWriter writer = new FileWriter("library.csv", true);
            writer.write("DVD," + title + ",," + year + ",," + callNumber + "\n");
            writer.close();
        } catch (IOException e)
        {
            System.out.println("An error occurred.");
        }

    }

    //handleAddBook
    //test with: add book bk111111 The_Great_Gatsby_2 Scott_Fitzgerald_IV 2045 9782343373665
    public void handleAddBook(String callNumber, String title, String author, String year, String ISBN)
    {
        try
        {
            FileWriter writer = new FileWriter("library.csv", true);
            writer.write("Book," + title + "," + author + "," + year + "," + ISBN + "," + callNumber + "\n");
            writer.close();
        } catch (IOException e)
        {
            System.out.println("An error occurred.");
        }
    }

    //handleRemove -- the same as handleReturn, but for library.csv
    public void handleRemove(String callNumber)
    {
        try
        {
            File inputFile = new File("library.csv"); //input file
            File tempFile = new File("library_temp.csv"); //temporary file to write to

            BufferedReader reader = new BufferedReader(new FileReader(inputFile)); //read from input file
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile)); //write to temporary file

            String line; //line to read from input file
            boolean header = true;

            while ((line = reader.readLine()) != null) //read each line from the file
            {
                if (line.startsWith("Type")) //check if the line starts with the callNumber
                {
                    writer.write(line);
                    writer.newLine();
                    header = false;
                    continue;
                }

                String[] fields = line.split(",");

                if (fields[5].equals(callNumber)) //check if the line starts with the callNumber
                {
                    continue;
                }

                if (header)
                {
                    writer.write(line);
                    writer.newLine();
                    header = false;
                }
                else
                {
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();

            if (inputFile.delete())
            {
                tempFile.renameTo(inputFile);
            }
            else
            {
                System.out.println("Failed to delete the original file.");
            }

        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
        }
    }


    //getBookList
    public void getBookList()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("library.csv"));
            String line;

            //read through each line in the CSV
            while ((line = reader.readLine()) != null)
            {
                String[] fields = line.split(",");

                //skip over the header
                if (fields[0].equals("Type")) {
                    continue;
                }

                if (fields[0].equals("Book"))
                {
                    System.out.println("Type: " + fields[0]);
                    System.out.println("Title: " + fields[1]);
                    System.out.println("Authors: " + fields[2]);
                    System.out.println("Publication Year: " + fields[3]);
                    System.out.println("ISBN: " + fields[4]);
                    System.out.println("Call Number: " + fields[5]);
                    System.out.println();
                }
            }

            reader.close();
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
        }
    }


    //getDVDList
    public void getDVDList()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("library.csv"));
            String line;

            //read through each line in the CSV
            while ((line = reader.readLine()) != null)
            {
                String[] fields = line.split(",");

                //skip over the header
                if (fields[0].equals("Type")) {
                    continue;
                }

                if (fields[0].equals("DVD"))
                {
                    System.out.println("Type: " + fields[0]);
                    System.out.println("Title: " + fields[1]);
                    System.out.println("Year: " + fields[3]);
                    System.out.println("Call Number: " + fields[5]);
                    System.out.println();
                }
            }

            reader.close();
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
        }
    }

    //getCheckoutList
    public void getCheckoutList()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("checkout.csv"));
            String line;

            //read through each line in the CSV
            while ((line = reader.readLine()) != null)
            {
                String[] fields = line.split(",");

                System.out.println("Call Number: " + fields[0]);
                System.out.println("Student ID: " + fields[1]);
                System.out.println();
            }

            reader.close();
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
        }
    }

}
