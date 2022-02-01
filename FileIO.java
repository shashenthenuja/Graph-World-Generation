/* *********************************************************************
* File:       FileIO.java
* Author:     G.G.T.Shashen
* Created:    15/10/2021
* Modified:   19/10/2021
* Desc:       FileIO class to extract and store data in the graph
************************************************************************/
import java.io.*;
public class FileIO {

    private String fileName;
    // Reads the number of lines in the given file
    public int readLines(String fileName) {
        String line="";
        int count=0;
        String fileLoc = fileName;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileLoc));
            while((line = br.readLine()) != null) {
                if(line.trim().length() > 0) {
                    if (line.contains("#")) {
                    }else {
                        count++;
                    }
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return count;
    }

    // Extract the data from the given file and return a 2D array 
    public String[][] readFile(String fileName) {
        String line="";
        int count=0;
        int size = readLines(fileName);
        String[][] array=new String[size][5];
        String fileLoc = fileName;
        this.fileName = fileName;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileLoc));
            while((line = br.readLine()) != null) {
                if (line.contains("#")) {
                }else if(line.startsWith("Ncode")) {
                    String[] values = line.split(" ");
                    array[count][0] = values[0];
                    array[count][1] = values[1];
                    array[count][2] = values[2];
                    count++;
                }else if (line.startsWith("Node")) {
                    String[] values = line.split(" ");
                    array[count][0] = values[0];
                    array[count][1] = values[1];
                    array[count][2] = values[2];
                    count++;
                }else if (line.startsWith("Ecode")) {
                    String[] values = line.split(" ");
                    array[count][0] = values[0];
                    array[count][1] = values[1];
                    array[count][2] = values[2];
                    count++;
                }else if (line.startsWith("Edge")) {
                    String[] values = line.split(" ");
                    array[count][0] = values[0];
                    array[count][1] = values[1];
                    array[count][2] = values[2];
                    array[count][3] = values[3];
                    count++;
                }else if (line.startsWith("Start")) {
                    String[] values = line.split(" ");
                    array[count][0] = values[0];
                    array[count][1] = values[1];
                    count++;
                }else if (line.startsWith("Target")) {
                    String[] values = line.split(" ");
                    array[count][0] = values[0];
                    array[count][1] = values[1];
                    count++;
                }else {
                    br.close();
                    throw new NullPointerException("File Does Not Contain Valid Data!");
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found In Directory!");
            System.out.println("Terminating System!");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return array;
    }

    public String getFileName() {
        return fileName;
    }

    // Get the data from the give file and process the data to display the graph
    /* public void getData(String fileName) {
        DSAGraph gr = new DSAGraph();
        String[][] array = readFile(fileName);
        for (int i = 0; i < array.length; i++) {
            gr.addVertex(array[i][0]);
            gr.addVertex(array[i][1]);
            gr.addEdge(array[i][0], array[i][1]);
        }
        gr.displayGraph();
    } */

    public void saveSerial(DSAGraph graph, String filename)
    {
        FileOutputStream fileStrm;
        ObjectOutputStream objStrm;
        try {
            fileStrm = new FileOutputStream(filename);
            objStrm = new ObjectOutputStream(fileStrm);
            objStrm.writeObject(graph);
            objStrm.close();
            fileStrm.close();
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Unable to save object to file : "+ e.getLocalizedMessage());
        }
    }

    public DSAGraph load(String filename) throws IllegalArgumentException
    {
        FileInputStream fileStrm;
        ObjectInputStream objStrm;
        DSAGraph inObj=null;
        try {
            fileStrm = new FileInputStream(filename);
            objStrm = new ObjectInputStream(fileStrm);
            inObj = (DSAGraph)objStrm.readObject();
            System.out.println("Processing...");
            objStrm.close();
            fileStrm.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("Class not found : " + e.getMessage());
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Unable to load object from file : "+ e.getLocalizedMessage());
        }
        return inObj;
    }

    /* public void dataWrite(DSAGraph graph)
    {
        String fileName = getFileName();
        // File format creation and Error Handling
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            } else {
              System.out.println("File already exists.");
              return;
            }
            // FileWriter to write the data into the file
            FileWriter myWriter = new FileWriter(fileName);
            // Printing status to the console
            System.out.println("");
            System.out.println("Writing to File Please Wait...");
            System.out.println("");
            // Write the data into the file
            myWriter.write(graph.displayAsMatrix());
            // Printing status to the console
            System.out.println("Bubble Sort : OK");
            System.out.println("Selection Sort : OK");
            System.out.println("Insertion Sort : OK");
            System.out.println("");
            myWriter.close();
            System.out.println("");
            System.out.println("Successfully wrote all data to the file.");
            System.out.println("");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    } */
}
