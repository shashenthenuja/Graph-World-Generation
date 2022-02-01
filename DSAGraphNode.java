/* *********************************************************************
* File:       DSAGraphNode.java
* Author:     G.G.T.Shashen
* Created:    01/09/2021
* Modified:   17/10/2021
* Desc:       DSAGraphNode implementation from Practical 6
************************************************************************/
import java.io.*;

public class DSAGraphNode implements Serializable {
    private String label;
    private String nodeWeight;
    private DSALinkedList links;
    private DSALinkedList edges;
    private DSALinkedList nodeWeights;
    private boolean visited;

    public DSAGraphNode(String Label, String weight) {
        // Initilization of the variables
        this.label = Label;
        this.nodeWeight = weight;
        links = new DSALinkedList();
        edges = new DSALinkedList();
        nodeWeights = new DSALinkedList();
    }

    public String getLabel() {
        return label;
    }

    public String getNodeWeight() {
        return nodeWeight;
    }

    public DSALinkedList getAdjacent() {
        return links;
    }

    public DSALinkedList getNodeWeightList() {
        return nodeWeights;
    }

    public void setNodeWeight(String weight) {
        nodeWeight = weight;
    }

    public DSAGraphEdge getEdge(String label)
    {
        // Get the edges corresponding to the given label in the graph
        DSAGraphEdge newNd = null;
        String name = this.label+label;
        for (Object o : edges) {
            DSAGraphEdge edge = (DSAGraphEdge)o;
            if(name.equals(edge.getLabel()))
            {
                newNd = edge;
            }
        }
        return newNd;
    }

    public void addEdge(DSAGraphNode vertex, DSAGraphEdge edge) {
        // Checks if the vertices are null and adds the given vertex
        if(links.find(vertex) == null)
        {
            links.insertLast(vertex);
        }
        // Inserts the given edge
        edges.insertLast(edge);
    }

    public void setVisited() {
        visited = true;
    }

    public void clearVisited() {
        visited = false;
    }

    public boolean getVisited() {
        return visited;
    }

    public void display() {
        // Displays all the edges in the graph
        System.out.print("Edges : ");
        DSAGraphNode link = null;
        DSAGraphEdge weight = null;
        
        for (Object o : links) {
            link = (DSAGraphNode)o;
            if (link.getVisited() != true) {
                for (Object e : edges) {
                    weight = (DSAGraphEdge)e;
                }
                System.out.print(link.getLabel() + " [" + weight.findWeight(weight.getLabel()) +"]");
                if(o != null)
                {
                    System.out.print(" ");
                }
            }
        }
        System.out.println("\n\n");
    }

}
