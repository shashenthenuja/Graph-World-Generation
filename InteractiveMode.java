/* *********************************************************************
* File:       InteractiveMode.java
* Author:     G.G.T.Shashen
* Created:    15/10/2021
* Modified:   19/10/2021
* Desc:       Game Of Catz Interactive Mode to do graph operations from a given file
************************************************************************/
import java.io.*;
import java.util.*;

public class InteractiveMode implements Serializable{
    private static String fileName = null;
    /* Method to show the main menu of the program
    Imports : FileIO, DSAGraph
    Exports : none */
    public void menu(FileIO file, DSAGraph graph) {
        int choice;
        Scanner input = new Scanner(System.in);
        try {
            do {
                menuOptions();
                System.out.print("> Choose Option : ");
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        loadFile(file);
                        insertNode(file, graph);
                        addEdge(file, graph);
                        break;
                    case 2:
                        nodeOperations(file, graph);
                        break;
                    case 3:
                        edgeOperations(file, graph);
                        break;
                    case 4:
                        paramTweaks(file, graph);
                        break;
                    case 5:
                        displayGraph(file, graph);
                        break;
                    case 6:
                        displayWorld(file, graph);
                        break;
                    case 7:
                        generateRoutes(file, graph);
                        break;
                    case 8:
                        break;
                    case 9:
                        serialize(file, graph);
                        break;
                    default:
                        System.out.println("");
                        System.out.println("Invalid Option Please Choose Again!");
                        break;
                }
            } while (true);
        } catch (InputMismatchException e) {
            //TODO: handle exception
            System.out.println("");
            System.out.println("Invalid Option Please Choose Again!");
        }
        input.close();
    }

    /* Method to display the menu options to the user
    Imports : None
    Exports : none */
    public static void menuOptions() {
        System.out.println("");
        System.out.println("");
        System.out.println(".........................::     Interactive Mode     ::..................................");
        System.out.println("...                                                                                   ...");
        System.out.println("...                         [1] Load Input File                                       ...");
        System.out.println("...                         [2] Node Operations                                       ...");
        System.out.println("...                         [3] Edge Operations                                       ...");
        System.out.println("...                         [4] Parameter Tweaks                                      ...");
        System.out.println("...                         [5] Display Graph                                         ...");
        System.out.println("...                         [6] Display World                                         ...");
        System.out.println("...                         [7] Generate Routes                                       ...");
        System.out.println("...                         [8] Display Routes                                        ...");
        System.out.println("...                         [9] Save Network                                          ...");
        System.out.println("...                                                                                   ...");
        System.out.println(".........................................................................................");
        if (fileName != null) {
            System.out.println("");
            System.out.println("                 >> Loaded File : ["+fileName+"] <<");
            System.out.println("");
        }
        System.out.println("");
    }

    /* Method to load the data file to the program
    Imports : FileIO
    Exports : String [][] */
    public static String[][] loadFile(FileIO file) {
        String[][] array = null;
        Scanner input = new Scanner(System.in);
        if (fileName == null) {
            System.out.print("Enter File Name : ");
            fileName = input.next();
        }
        array = file.readFile(fileName);
        return array;
    }

    /* Method to get the 2D array of data from the file
    Imports : String[][]
    Exports : String[][] */
    public static String[][] getFileArray(String[][] arr) {
        return arr;
    }

    /* Method to select the node operations from the user
    Import : None
    Export : None */
    public void nodeOperations(FileIO file, DSAGraph graph) {
        if (fileName == null) {
            System.out.println("File is not currently loaded!");
            loadFile(file);
        }
        nodeMenu();
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("> Choose Option : ");
            int option = input.nextInt();
            switch (option) {
                case 1:
                    findNode(file, graph);
                    break;
                case 2:
                    insertNewNode(file, graph);
                    break;
                case 3:
                    deleteNode(file, graph);
                    break;
                case 4:
                    updateNode(file, graph);
                    break;
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        } catch (InputMismatchException e) {
            //TODO: handle exception
            System.out.println("Invalid Input!");
        }
    }

    /* Method to show the node operations menu to the user
    Import : None
    Export : None */
    public static void nodeMenu() {
        System.out.println("");
        System.out.println("");
        System.out.println(".........................::     Node Operations     ::...................................");
        System.out.println("...                                                                                   ...");
        System.out.println("...                         [1] Find                                                  ...");
        System.out.println("...                         [2] Insert                                                ...");
        System.out.println("...                         [3] Delete                                                ...");
        System.out.println("...                         [4] Update                                                ...");
        System.out.println("...                                                                                   ...");
        System.out.println(".........................................................................................");
        if (fileName != null) {
            System.out.println("");
            System.out.println("                 >> Loaded File : ["+fileName+"] <<");
            System.out.println("");
        }
        System.out.println("");
    }

    /* Method to find the node in the graph
    Import : FileIO, DSAGraph
    Export : None */
    public static void findNode(FileIO file, DSAGraph graph) {
        String node;
        System.out.print("Enter Node To Find : ");
        Scanner input = new Scanner(System.in);
        node = input.next();
        DSAGraphNode nodeVal = null;
        DSALinkedList edgeVals = null;
        String label="";
        try {
            nodeVal = graph.labFind(node);
            if (nodeVal != null) {
                label = nodeVal.getLabel();
                edgeVals = nodeVal.getAdjacent();
                System.out.print("Found Node : "+label+ " | Edges : ");
                for (Object e : edgeVals) {
                    DSAGraphNode edges = (DSAGraphNode)e;
                    System.out.print("["+edges.getLabel()+"]");
                    if (e != null) {
                        System.out.print(" ");
                    }
                }
            }else {
                System.out.println("Cannot Find Node In The Graph!");
            }
        } catch (NullPointerException e) {
            //TODO: handle exception
            System.out.println("Cannot Find Node In The Graph!");
        }
    }

    /* Method to insert a node in to the graph
    Import : FileIO, DSAGraph
    Export : None */
    public static void insertNewNode(FileIO file, DSAGraph graph) {
        String node;
        String nodeWeight;
        System.out.print("Enter Node : ");
        Scanner input = new Scanner(System.in);
        node = input.next();
        System.out.print("Enter Node Weight : ");
        nodeWeight = input.next();
        graph.addVertex(node, nodeWeight);
        System.out.println("Successfully Added New Node!");
    }

    /* Method to insert all the nodes from the file in to the graph
    Import : FileIO, DSAGraph
    Export : None */
    public static void insertNode(FileIO file, DSAGraph graph) {
        int count=0;
        int lines;
        if (fileName == null) {
            System.out.println("File is not currently loaded!");
            loadFile(file);
        }
        lines = file.readLines(fileName);
        String[][] array = getFileArray(loadFile(file));
        String[][] code = getOldCode(file, graph, "Ncode");
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
                count++;
            }
        }
        System.out.println(count+ " Node Successfully Added From " + fileName +"!");
    }

    /* Method to delete a node in the graph
    Import : FileIO, DSAGraph
    Export : None */
    public static void deleteNode(FileIO file, DSAGraph graph) {
        String label;
        System.out.print("Enter Node To Delete : ");
        Scanner input = new Scanner(System.in);
        label = input.next();
        DSAGraphNode node = graph.labFind(label);
        graph.removeAtNode(node);
        System.out.println("Removed From Graph!");
    }

    /* Method to update a node in the graph
    Import : FileIO, DSAGraph
    Export : None */
    public void updateNode(FileIO file, DSAGraph graph) {
        String node;
        System.out.print("Enter Node To Update : ");
        Scanner input = new Scanner(System.in);
        node = input.next();
        DSAGraphNode nodeVal = null;
        String nodeWeight;
        String newCode;
        String newWeight="";
        String weightLab="";
        String[][] oldCode = getOldCode(file, graph, "Ncode");
        String label="";
        try {
            // Check if the node is currently in the graph
            nodeVal = graph.labFind(node);
            if (nodeVal != null) {
                label = nodeVal.getLabel();
                // Get the weight of the node
                nodeWeight = nodeVal.getNodeWeight();
                // Get the corresponding code from the weight value
                for (int i = 0; i < oldCode.length; i++) {
                    if (nodeWeight.matches(oldCode[i][1])) {
                        weightLab = oldCode[i][0];
                    }
                }
                System.out.println("Found Node : "+label+ " -> Currently Has Code : " + weightLab);
                System.out.println("Current Codes : ");
                for (int i = 0; i < oldCode.length; i++) {
                    System.out.println(oldCode[i][0] + " : " + oldCode[i][1]);
                }
                System.out.print("Enter Node "+label+"'s New Code : ");
                newCode = input.next();
                // Get the corresponding weight value from the selected code
                for (int i = 0; i < oldCode.length; i++) {
                    if (newCode.matches(oldCode[i][0])) {
                        newWeight = oldCode[i][1];
                    }
                }
                if (newWeight == "") {
                    System.out.println("Cannot Find Code "+newCode+ "! Please Enter The Code Name");
                    nodeOperations(file, graph);
                }
                // Add the new weight to the node
                graph.addVertex(label, newWeight);
                System.out.println("Updated "+label + "'s Code from ["+weightLab+"] to ["+newCode+ "]" );
            }else {
                System.out.println("Cannot Find Node In The Graph!");
            }
        } catch (NullPointerException e) {
            //TODO: handle exception
            System.out.println("Cannot Find Node In The Graph!");
        }
    }

    /* Method to select edgeOperations from the user
    Import : FileIO, DSAGraph
    Export : None */
    public void edgeOperations(FileIO file, DSAGraph graph) {
        if (fileName == null) {
            System.out.println("File is not currently loaded!");
            loadFile(file);
        }
        edgeMenu();
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("> Choose Option : ");
            int option = input.nextInt();
            switch (option) {
                case 1:
                    findEdge(file, graph);
                    break;
                case 2:
                    addNewEdge(file, graph);
                    break;
                case 3:
                    removeEdge(file, graph);
                    break;
                case 4:
                    updateEdge(file, graph);
                    break;
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        } catch (InputMismatchException e) {
            //TODO: handle exception
            System.out.println("Invalid Input!");
        }
    }

    /* Method to show the edge operations menu to the user
    Import : None
    Export : None */
    public static void edgeMenu() {
        System.out.println("");
        System.out.println("");
        System.out.println(".........................::     Edge Operations     ::...................................");
        System.out.println("...                                                                                   ...");
        System.out.println("...                         [1] Find                                                  ...");
        System.out.println("...                         [2] Add                                                   ...");
        System.out.println("...                         [3] Remove                                                ...");
        System.out.println("...                         [4] Update                                                ...");
        System.out.println("...                                                                                   ...");
        System.out.println(".........................................................................................");
        if (fileName != null) {
            System.out.println("");
            System.out.println("                 >> Loaded File : ["+fileName+"] <<");
            System.out.println("");
        }
        System.out.println("");
    }

    /* Method to find an edge in the graph
    Import : FileIO, DSAGraph
    Export : None */
    public static void findEdge(FileIO file, DSAGraph graph) {
        String node1;
        String node2;
        boolean node1Found;
        boolean node2Found;
        boolean hasEdge;
        // Get the 2 nodes needed to see if the 2 nodes has the edge between them
        System.out.print("Enter First Node : ");
        Scanner input = new Scanner(System.in);
        node1 = input.next();
        System.out.print("Enter Second Node : ");
        node2 = input.next();
        DSAGraphNode nodeVal1 = null;
        DSAGraphNode nodeVal2 = null;
        node1Found = findNodeInEdge(graph, node1);
        node2Found = findNodeInEdge(graph, node2);
        try {
            // Check if 2 nodes are in the graph
            if (node1Found == true && node2Found == true) {
                nodeVal1 = graph.labFind(node1);
                nodeVal2 = graph.labFind(node2);
                // Get the correct edge corresponding to the 2 nodes
                hasEdge = graph.hasEdge(nodeVal1, nodeVal2);
                System.out.print("Found Edge : "+hasEdge);
            }else {
                System.out.println("Edge does not exist for give nodes!");
            }
        } catch (NullPointerException e) {
            //TODO: handle exception
            System.out.println("Cannot Find Node In The Graph!");
        }
    }

    /* Method to find the node in the graph
    Import : FileIO, DSAGraph
    Export : Boolean */
    public static boolean findNodeInEdge(DSAGraph graph, String node) {
        boolean found = false;
        DSAGraphNode nodeVal = null;
        String label="";
        try {
            nodeVal = graph.labFind(node);
            label = nodeVal.getLabel();
        } catch (NullPointerException e) {
            //TODO: handle exception
            found = false;
        }
        if (label.equals(node)) {
            found = true;
        }else {
            found = false;
        }
        return found;
    }

    /* Method to add a new edge to the graph
    Import : FileIO, DSAGraph
    Export : None */
    public static void addNewEdge(FileIO file, DSAGraph graph) {
        String node1;
        String node2;
        String edge;
        System.out.print("Enter Node : ");
        Scanner input = new Scanner(System.in);
        node1 = input.next();
        System.out.print("Enter 2nd Node : ");
        node2 = input.next();
        System.out.print("Enter Edge Weight : ");
        edge = input.next();
        graph.addEdge(node1, node2, "0", edge);
        System.out.println("Successfully Added New Edge!");
    }

    /* Method to add edges to the graph from the file
    Import : FileIO, DSAGraph
    Export : None */
    public static void addEdge(FileIO file, DSAGraph graph) {
        int count=0;
        int lines;
        if (fileName == null) {
            System.out.println("File is not currently loaded!");
            loadFile(file);
        }else if (graph.getVertexCount() == 0) {
            System.out.println("No Nodes Are Found!");
            System.out.println("Please add Nodes first by choosing option 2 in Node Operations!");
        }
        lines = file.readLines(fileName);
        // Get data from the file and store in arrays
        String[][] array = getFileArray(loadFile(file));
        String[][] code = getOldCode(file, graph, "Ecode");
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
                count++;
            }
        }
        System.out.println(count+ " Edges Successfully Added From " + fileName +"!");
    }

    /* Method to remove edges from the graph
    Import : FileIO, DSAGraph
    Export : None */
    public static void removeEdge(FileIO file, DSAGraph graph) {
        DSALinkedList links = graph.getVerticeList();
        DSALinkedList edges = graph.getEdgeList();
        String node1;
        String node2;
        boolean node1Found;
        boolean node2Found;
        String edge;
        // Get the 2 nodes needed to see if the 2 nodes has the edge between them
        System.out.print("Enter First Node : ");
        Scanner input = new Scanner(System.in);
        node1 = input.next();
        System.out.print("Enter Second Node : ");
        node2 = input.next();
        DSAGraphNode nodeVal1 = null;
        DSAGraphNode nodeVal2 = null;
        node1Found = findNodeInEdge(graph, node1);
        node2Found = findNodeInEdge(graph, node2);
        try {
            // Check if 2 nodes are in the graph
            if (node1Found == true && node2Found == true) {
                nodeVal1 = graph.labFind(node1);
                nodeVal2 = graph.labFind(node2);
                graph.removeAtEdge(nodeVal1,nodeVal2);
            }else {
                System.out.println("Edge does not exist for give nodes!");
            }
        } catch (NullPointerException e) {
            //TODO: handle exception
            System.out.println("Cannot Find Node In The Graph!");
        }
    }

    /* Method to update an edge in the graph
    Import : FileIO, DSAGraph
    Export : None */
    public void updateEdge(FileIO file, DSAGraph graph) {
        DSALinkedList links = graph.getVerticeList();
        DSALinkedList edges = graph.getEdgeList();
        String node1 = "";
        String node2 = "";
        DSAGraphNode node1Val = null;
        DSAGraphNode node2Val = null;
        DSAGraphEdge edgeVal = null;
        String edge="";
        String edgeWeight="";
        String newCode;
        String newWeight="";
        String weightLab="";
        String[][] oldCode = getOldCode(file, graph, "Ecode");
        String label="";
        System.out.print("Enter First Node : ");
        Scanner input = new Scanner(System.in);
        node1 = input.next();
        System.out.print("Enter Second Node : ");
        node2 = input.next();
        boolean node1Found = findNodeInEdge(graph, node1);
        boolean node2Found = findNodeInEdge(graph, node2);
        try {
            // Check if 2 nodes are in the graph
            if (node1Found == true && node2Found == true) {
                node1Val = graph.labFind(node1);
                node1Val = graph.labFind(node2);
                // Get the correct edge corresponding to the 2 nodes
                edgeVal = node1Val.getEdge(node2Val.getLabel());
                edge = edgeVal.getLabel();
            }
        } catch (NullPointerException e) {
            //TODO: handle exception
            System.out.println("Cannot Find Edge In The Graph!");
        }
        DSAGraphEdge weight = null;
        for (Object o : links) {
            DSAGraphNode link = (DSAGraphNode)o;
            if (o != null) {
                if (link.getLabel().matches(edge)) {
                    label = link.getLabel();
                    edgeWeight = weight.findWeight(weight.getLabel());
                }
            }
        }
        try {
            // Check if the edge is currently in the graph
            if (edgeWeight != null) {
                for (int i = 0; i < oldCode.length; i++) {
                    if (edgeWeight.matches(oldCode[i][1])) {
                        weightLab = oldCode[i][0];
                    }
                }
                System.out.println("Found Edge : "+label+ " -> Currently Has Code : " + weightLab);
                System.out.println("Current Codes : ");
                for (int i = 0; i < oldCode.length; i++) {
                    System.out.println(oldCode[i][0] + " : " + oldCode[i][1]);
                }
                System.out.print("Enter Edge "+label+"'s New Code : ");
                newCode = input.next();
                // Get the corresponding weight value from the selected code
                for (int i = 0; i < oldCode.length; i++) {
                    if (newCode.matches(oldCode[i][0])) {
                        newWeight = oldCode[i][1];
                    }
                }
                if (newWeight == "") {
                    System.out.println("Cannot Find Code "+newCode+ "!");
                    menu(file, graph);
                }
                // Add the new weight to the node
                graph.addEdge(node1Val.getLabel(), node2Val.getLabel(), "0", edgeWeight);
                System.out.println("Updated "+label + "'s Code from ["+weightLab+"] to ["+newCode+ "]" );
            }else {
                System.out.println("Cannot Find Node In The Graph!");
            }
        } catch (NullPointerException e) {
            //TODO: handle exception
            System.out.println("Cannot Find Node In The Graph!");
        }

    }

    /* Method to show the Parameter Tweaking menu to the user
    Import : None
    Export : None */
    public static void paramTweakMenu() {
        System.out.println("");
        System.out.println("");
        System.out.println(".........................::     Parameter Tweak     ::...................................");
        System.out.println("...                                                                                   ...");
        System.out.println("...                         [1] Change Ncode                                          ...");
        System.out.println("...                         [2] Change Ecode                                          ...");
        System.out.println("...                                                                                   ...");
        System.out.println(".........................................................................................");
        if (fileName != null) {
            System.out.println("");
            System.out.println("                 >> Loaded File : ["+fileName+"] <<");
            System.out.println("");
        }
        System.out.println("");
    }

    /* Method to select the paramater tweaking option from the menu
    Import : FileIO, DSAGraph
    Export : None */
    public static void paramTweaks(FileIO file, DSAGraph graph) {
        int choice;
        if (fileName == null) {
            System.out.println("File is not currently loaded!");
            loadFile(file);
        }
        paramTweakMenu();
        System.out.print("> Choose Option : ");
        Scanner input = new Scanner(System.in);
        choice = input.nextInt();
        switch (choice) {
            case 1:
                updateNcode(file, graph);
                break;
            case 2:
                updateEcode(file, graph);
                break;
            default:
                System.out.println("Invalid Option!");
                break;
        }
    }

    /* Method to update the Ncode in the graph
    Import : FileIO, DSAGraph
    Export : None */
    public static void updateNcode(FileIO file, DSAGraph graph) {
        int choice;
        String oldEdge;
        String newEdge;
        Scanner input = new Scanner(System.in);
        String[][] oldCode = getOldCode(file, graph, "Ncode");
        // Show the current nodes in the graph to the user to choose from
        System.out.println("Current Ncodes : ");
        for (int i = 0; i < oldCode.length; i++) {
            System.out.print("["+ (i+1) +"] Ncode ");
            System.out.println(oldCode[i][0] + " " + oldCode[i][1]);
        }
        System.out.print("Select Code To Update : ");
        choice = input.nextInt();
        oldEdge = oldCode[choice-1][1];
        System.out.println("Selected Code : ["+ (choice) + "] Ncode "+ oldCode[choice-1][0] +" "+ oldEdge);
        System.out.print("Enter New Weight : ");
        newEdge = input.next();
        System.out.println("Updating Ncode!");
        System.out.println("Successfully Updated Ncode!");
        updateGraphNodes(file, graph, oldEdge, newEdge);
    }

    /* Method to update the Ecode in the graph
    Import : FileIO, DSAGraph
    Export : None */
    public static void updateEcode(FileIO file, DSAGraph graph) {
        int choice;
        String oldEdge;
        String newEdge;
        Scanner input = new Scanner(System.in);
        String[][] oldCode = getOldCode(file, graph, "Ecode");
        System.out.println("Current Ecodes : ");
        // Show current Ecodes in the graphs to the user to choose from
        for (int i = 0; i < oldCode.length; i++) {
            System.out.print("["+ (i+1) +"] Ecode ");
            System.out.println(oldCode[i][0] + " " + oldCode[i][1]);
        }
        System.out.print("Select Code To Update : ");
        choice = input.nextInt();
        oldEdge = oldCode[choice-1][1];
        System.out.println("Selected Code : ["+ (choice) + "] Ecode "+ oldCode[choice-1][0] +" "+ oldEdge);
        // Get the new weight for the selected code
        System.out.print("Enter New Weight : ");
        newEdge = input.next();
        System.out.println("Updating Ecode!");
        System.out.println("Successfully Updated Ecode!");
        // Update the graph with the new weight values
        updateGraphEdges(file, graph, oldEdge, newEdge);
    }

    /* Method to update the graph nodes with the new weight values from the user
    Import : FileIO, DSAGraph, String, String
    Export : None */
    public static void updateGraphNodes(FileIO file, DSAGraph graph, String oldCode, String newCode) {
        int lines;
        // Make new arrays with the data
        String[][] array = getFileArray(loadFile(file));
        String[][] code = getNewCodes(file, graph, "Ncode", oldCode, newCode);
        lines = file.readLines(fileName);
        for (int i = 0; i < lines; i++) {
            if (array[i][0].startsWith("Node")) {
                if (array[i][2] != null) {
                    for (int j = 0; j < code.length; j++) {
                        if (array[i][2].matches(code[j][0])) {
                            graph.addVertex(array[i][1], code[j][1]);
                            System.out.println(array[i][1]+ " " + code[j][1]);
                        }
                    }
                }else {
                    graph.addVertex(array[i][1],"0");
                }
            }
        }
        System.out.println("All Nodes Successfully Updated!");
    }

    /* Method to update the graph edges with the new weight values from the user
    Import : FileIO, DSAGraph, String, String
    Export : None */
    public static void updateGraphEdges(FileIO file, DSAGraph graph, String oldCode, String newCode) {
        int lines;
        // Make new arrays with data
        String[][] array = getFileArray(loadFile(file));
        String[][] code = getNewCodes(file, graph, "Ecode", oldCode, newCode);
        lines = file.readLines(fileName);
        for (int i = 0; i < lines; i++) {
            if (array[i][0].startsWith("Edge")) {
                if (array[i][3] != null) {
                    for (int j = 0; j < code.length; j++) {
                        if (array[i][3].matches(code[j][0])) {
                            graph.addEdge(array[i][1], array[i][2], "0", code[j][1]);
                            System.out.println("Changed Weight of "+ array[i][1] + " to " + code[j][1]);
                        }
                    }
                }else {
                    graph.addEdge(array[i][1], array[i][2], "0", "0");
                }
            }
        }
        System.out.println("All Edges Successfully Updated!");

    }

    /* Method to get only the code values from the file
    Import : FileIO, DSAGraph, DSAGraph, String
    Export : String[][] */
    public static String[][] getOldCode(FileIO file, DSAGraph graph, String codeName) {
        int codeLines=0;
        int count=0;
        int lines;
        lines = file.readLines(fileName);
        // Get data from the file
        String[][] array = getFileArray(loadFile(file));
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

    /* Method to get the replaced new codes from the old data
    Import : FileIO, DSAGraph, String, String , String
    Export : String[][] */
    public static String[][] getNewCodes(FileIO file, DSAGraph graph, String codeName, String oldCode, String newCode) {
        String[][] codeValArr = getOldCode(file, graph, codeName);
        for (int i = 0; i < codeValArr.length; i++) {
            if (codeValArr[i][1].matches(oldCode)) {
                codeValArr[i][1] = newCode;
            }
        }
        return codeValArr;
    }

    /* Method to display the adjacency matrix to the user
    Import : FileIO, DSAGraph
    Export : None */
    public void displayGraph(FileIO file, DSAGraph graph) {
        if (fileName == null) {
            System.out.println("File is not currently loaded!");
            loadFile(file);
        }else if (graph.getVertexCount() == 0 || graph.getEdgeCount() == 0) {
            System.out.println("Cannot Display Graph Without Nodes or Edges!");
            System.out.println("Returning To Main Menu!");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            menu(file, graph);
        }else {
            System.out.println("");
            graph.displayAsMatrix();
        }
    }

    /* Method to display the adjacency list (world) to the user
    Import : FileIO, DSAGraph
    Export : None */
    public void displayWorld(FileIO file, DSAGraph graph) {
        if (fileName == null) {
            System.out.println("File is not currently loaded!");
            loadFile(file);
        }else if (graph.getVertexCount() == 0 || graph.getEdgeCount() == 0) {
            System.out.println("Cannot Display World Without Nodes or Edges!");
            System.out.println("Returning To Main Menu!");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            menu(file, graph);
        }else {
            System.out.println("");
            graph.displayGraph();
        }
    }

    /* Method to generate the routes from start to target
    Import : FileIO, DSAGraph
    Export : None */
    public void generateRoutes(FileIO file, DSAGraph graph) {
        if (fileName == null) {
            System.out.println("File is not currently loaded!");
            loadFile(file);
        }
        DSAQueue paths = new DSAQueue();
        int lines = file.readLines(fileName);
        String[][] array = getFileArray(loadFile(file));
        String start = "";
        String end = "";
        DSAGraphNode node = null;
        DSAGraphNode node2 = null;
        for (int i = 0; i < lines; i++) {
            // Include data only which contains the "Node" text from data file
            if (array[i][0].startsWith("Start")) {
                if (array[i][0] != null) {
                    start = array[i][1];
                }
            }
            if (array[i][0].startsWith("Target")) {
                if (array[i][0] != null) {
                    end = array[i][1];
                }
            }
        }
        System.out.println("Start : " + start + " End : " + end);
        node = graph.labFind(start);
        node2 = graph.labFind(end);
        paths.enqueue(node.getLabel());
        depthFirstSearch(graph, node, node2, paths);
    }

    /* Recursive Depth First Search method to find the routes
    Import : FileIO, DSAGraph
    Export : None */
    public void depthFirstSearch(DSAGraph graph, DSAGraphNode node, DSAGraphNode end, DSAQueue paths) {
        DSALinkedList neighbours = node.getAdjacent();
        if(node.getLabel() == end.getLabel()) {
            System.out.println(paths.peek());
            return;
        }
        node.setVisited();
        
        if (neighbours == null) {
            System.out.println("Null Vertices!");
        }else {
            for (Object i : neighbours) {
                DSAGraphNode nodes = (DSAGraphNode)i;
                if (nodes.getVisited() != true) {
                    paths.enqueue(nodes.getLabel());
                    depthFirstSearch(graph, node, end, paths);
                    paths.deQueue();
                }
            }
        }
        node.clearVisited();
    }

    /* Method to Serialize the graph to a file
    Import : FileIO, Graph
    Export : None */
    public static void serialize(FileIO file, DSAGraph graph) {
        if (fileName == null) {
            System.out.println("File is not currently loaded!");
            loadFile(file);
        }
        String fileName;
        System.out.print("Enter file name : ");
        Scanner input = new Scanner(System.in);
        fileName = input.nextLine();
        file.saveSerial(graph,fileName);
        System.out.println("File Written!");
    }
}
