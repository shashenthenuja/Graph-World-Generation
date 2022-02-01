/* *********************************************************************
* File:       gameofcatz.java
* Author:     G.G.T.Shashen
* Created:    15/10/2021
* Modified:   19/10/2021
* Desc:       Program to do graph operations from a given file
************************************************************************/
import java.io.*;
public class gameofcatz implements Serializable {
    
    // Main Method
    public static void main(String[] args) {
        DSAGraph graph;
        FileIO file;
        InteractiveMode im = new InteractiveMode();
        SimulationMode sm = new SimulationMode();
        graph = new DSAGraph();
        file = new FileIO();
        if (args.length == 0) {
            usageInfo();
        }else if(args[0].equals("-i") && args.length == 1) {
            im.menu(file, graph);
        }else if(args[0].equals("-s") && args.length == 3) {
            sm.simulation(file, graph, args[1], args[2]);
        }else {
            usageInfo();
        }
    }

    /* Method to show the user usage information of the program
    Imports : None
    Exports : none */
    public static void usageInfo() {
        System.out.println("");
        System.out.println("");
        System.out.println("............................::      Usage Information      ::............................");
        System.out.println("...                                                                                   ...");
        System.out.println("...                     gameofcatz -i : To use interative mode                        ...");
        System.out.println("...                                                                                   ...");
        System.out.println("...           gameofcatz -s [inputfile] [outputfile] : To use simulation mode         ...");
        System.out.println("...                                                                                   ...");
        System.out.println(".........................................................................................");
        System.out.println("");
        System.out.println("");
    }
    
}