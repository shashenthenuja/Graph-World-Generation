/* *********************************************************************
* File:       SimulationMode.java
* Author:     G.G.T.Shashen
* Created:    15/10/2021
* Modified:   19/10/2021
* Desc:       Simulation Mode of the gameofcatz program without UI
************************************************************************/
import java.io.*;

public class SimulationMode implements Serializable{
    public void simulation(FileIO file, DSAGraph graph, String inFileName, String outFileName) {
        insertNode(file, graph, inFileName);
        addEdge(file, graph, inFileName);
        file.saveSerial(graph, outFileName);
    }

    public static String[][] loadFile(FileIO file, String inFileName) {
        String[][] array = file.readFile(inFileName);
        return array;
    }

    public static String[][] getOldCode(FileIO file, DSAGraph graph, String codeName, String inFileName) {
        int codeLines=0;
        int count=0;
        int lines;
        lines = file.readLines(inFileName);
        // Get data from the file
        String[][] array = loadFile(file, inFileName);
        for (int i = 0; i < lines; i++) {
            if (array[i][0].startsWith(codeName)) {
                codeLines++;
            }
        }
        // Get selected code types and weights from the data
        String[][] code = new String[codeLines][2];
        for (int i = 0; i < lines; i++) {
            if (array[i][0].startsWith(codeName)) {
                code[count][0] = array[i][1];
                code[count][1] = array[i][2];
                count++;
            }
        }
        return code;
    }

    public static void insertNode(FileIO file, DSAGraph graph, String inFileName) {
        int lines;
        lines = file.readLines(inFileName);
        String[][] array = loadFile(file, inFileName);
        String[][] code = getOldCode(file, graph, "Ncode", inFileName);
        for (int i = 0; i < lines; i++) {
            // Include data only which contains the "Node" text from data file
            if (array[i][0].startsWith("Node")) {
                if (array[i][2] != null) {
                    for (int j = 0; j < code.length; j++) {
                        if (array[i][2].matches(code[j][0])) {
                            graph.addVertex(array[i][1], code[j][1]);
                        }
                    }
                }else {
                    graph.addVertex(array[i][1],"0");
                }
            }
        }
    }

    public static void addEdge(FileIO file, DSAGraph graph, String fileName) {
        int lines;
        lines = file.readLines(fileName);
        // Get data from the file and store in arrays
        String[][] array = loadFile(file, fileName);
        String[][] code = getOldCode(file, graph, "Ecode", fileName);
        for (int i = 0; i < lines; i++) {
            // Check each line that contains the "Edge" and insert the edge data and weight values
            if (array[i][0].startsWith("Edge")) {
                if (array[i][3] != null) {
                    for (int j = 0; j < code.length; j++) {
                        if (array[i][3].matches(code[j][0])) {
                            graph.addEdge(array[i][1], array[i][2], "0", code[j][1]);
                        }
                    }
                }else {
                    graph.addEdge(array[i][1], array[i][2], "0", "0");   
                }
            }
        }
    }
}
