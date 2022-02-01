/* *********************************************************************
* File:       DSAGraph.java
* Author:     G.G.T.Shashen
* Created:    01/09/2021
* Modified:   19/10/2021
* Desc:       DSAGraph implementation from Practical 6
************************************************************************/
import java.io.*;
public class DSAGraph implements Serializable {

    private DSALinkedList vertices;
    private DSALinkedList edges;
    private int vertexCount;
    private int edgesCount;

    public DSAGraph() {
        // initializing variables
        vertices = new DSALinkedList();
        edges = new DSALinkedList();
        vertexCount = 0;
        edgesCount = 0;
    }

    public void addVertex(String label, String weight) {
        // Checking if the vertex is already added then adding the vertex
        if (hasVertex(label) == false) {
            DSAGraphNode myVertex = new DSAGraphNode(label,weight);
            vertices.insertLast(myVertex);
            vertexCount++;
        }
    }

    public void addEdge(String label1, String label2, String nodeWeight, String edgeWeight) {
        DSAGraphNode lab1 = new DSAGraphNode(label1,nodeWeight);
        DSAGraphNode lab2 = new DSAGraphNode(label2,nodeWeight);
        // Checking the label if it is currently available 
        lab1 = labFind(lab1.getLabel());
        lab2 = labFind(lab2.getLabel());
        // Checking the edge if it is already available
        DSAGraphEdge edge = lab1.getEdge(lab2.getLabel());
        // If it is not available, make a new edge between the 2 nodes
        if(edge == null) {
            edge = new DSAGraphEdge(lab1, lab2);
        }
        // If both vertices are available, link is created for each other
        if(vertices.find(lab1) != null && vertices.find(lab2) != null) {
            lab1.addEdge(lab2, edge);
            lab2.addEdge(lab1, edge);
            edge.addWeight(edgeWeight,edge.getLabel());
            edges.insertLast(edge);
            edgesCount++;
        }
        
    }

    public int getVertexCount() {
        // returns the vertex count in the graph
        return vertexCount;
    }

    public int getEdgeCount() {
        // returns the edges count in the graph
        return edgesCount;
    }

    public DSALinkedList getVerticeList() {
        return vertices;
    }

    public DSALinkedList getEdgeList() {
        return edges;
    }

    public boolean hasVertex(String label) {
        // Checks if the vertex is already in the graph
        boolean flag;
        flag = false;
        for (Object o : vertices) {
            if (o == labFind(label)) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean hasEdge(DSAGraphNode vertex1, DSAGraphNode vertex2) {
        boolean value = false;
        for (Object o : vertex1.getAdjacent()) {
            DSAGraphNode node = (DSAGraphNode)o;
            if(node.getLabel() == vertex2.getLabel())
            {
                value = true;
            }
        }
        return value;
    }

    public DSAGraphNode labFind(String label) {
        // Finds the corresponding vertex for the given label
        DSAGraphNode newNd = null;
        for (Object o : vertices) {
            DSAGraphNode node = (DSAGraphNode)o;
            if(label.equals(node.getLabel()))
            {
                newNd = node;
            }
        }
        return newNd;
    }

    public void removeNodes() {
        for (Object o : vertices) {
            if (o != null) {
                vertices.removeFirst();
            }
        }   
    }

    public void removeAtNode(DSAGraphNode inNode) {
        for (Object o : vertices) {
            if (o == inNode) {
                DSAGraphNode node = (DSAGraphNode)o;
                node.setVisited();
            }
        }
    }

    public void removeAtEdge(DSAGraphNode node1, DSAGraphNode node2) {
        for (Object o : node1.getAdjacent()) {
            DSAGraphNode node = (DSAGraphNode)o;
            node.setVisited();
        }
    }

    // Method to display adjacency list graph
    public void displayGraph() {
        // Display the vertices in the graph
        System.out.println("\n");
        
        for (Object n : vertices) {
            DSAGraphNode node = (DSAGraphNode)n;
            if (node.getVisited() != true) {
                System.out.print("Node : "+node.getLabel() + " ["+node.getNodeWeight()+"]"+" -> ");
                // Calls the display method to print the edges for the vertices
                node.display();
            }
        }
    }

    // Method to convert adjacency list to adjacency matrix
    public void displayAsMatrix() {
        int k = 0;
        int l = 0;
        // Make arrays for all the vertices, edges and weights
        DSAGraphNode[] verticesArr = new DSAGraphNode[getVertexCount()];
        String[] edgesArr = new String[getEdgeCount()];
        String[] weightsArr = new String[getEdgeCount()];
        String[][] array = new String[vertexCount][vertexCount];
        // Initialize the matrix with 0s
        for (int i = 0; i < getVertexCount(); i++) {
            for (int j = 0; j < getVertexCount(); j++) {
                array[i][j] = "0";
            }
        }
        // Get the vertices and edges to the arrays 
        for (Object n : vertices) {
            DSAGraphNode node = (DSAGraphNode)n;
            verticesArr[k] = node;
            k++;
        }
        for (Object e : edges) {
            DSAGraphEdge edge = (DSAGraphEdge)e;
            edgesArr[l] = edge.getLabel();
            weightsArr[l] = edge.findWeight(edge.getLabel());
            l++;
        }

        // Storing the weights in the matrix for the corresponding edge
        for (int i = 0; i < getVertexCount(); i++) {
            for (int j = 0; j < getVertexCount(); j++) {
                if (hasEdge(verticesArr[i], verticesArr[j]) == true) {
                    if (weightsArr[j] == "0") {
                        array[i][j] =  "1";
                    }else {
                        array[i][j] = weightsArr[j];
                    }
                }
            }
        }

        // Printing the matrix to the console
        for(int i = 0; i < getVertexCount(); i++)
        {
            for(int j = 0; j < getVertexCount(); j++)
            {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void dfs(DSAGraphNode start, DSAGraphNode end, DSAQueue queue, DSALinkedList nodesList) {
        queue.enqueue(start.getLabel());
        start.setVisited();
        if (start.getLabel() == end.getLabel()) {
            nodesList.insertFirst(queue.peek());
        }else {
            for (Object object : start.getAdjacent()) {
                DSAGraphNode node = (DSAGraphNode)object;
                if (node.getVisited() != true) {
                    dfs(start, end, queue, nodesList);
                }
                
            }
        }
        queue.deQueue();
        start.clearVisited();
    }
}