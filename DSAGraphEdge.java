/* *********************************************************************
* File:       DSAGraphEdge.java
* Author:     G.G.T.Shashen
* Created:    01/09/2021
* Modified:   17/10/2021
* Desc:       DSAGraphEdge implementation from Practical 6
************************************************************************/
import java.io.*;

public class DSAGraphEdge implements Serializable {
    private String label;
    private DSAGraphNode from;
    private DSAGraphNode to;
    private DSALinkedList weights;

    public DSAGraphEdge(DSAGraphNode fromVertex, DSAGraphNode toVertex) {
        this.from = fromVertex;
        this.to = toVertex;
        this.label = fromVertex.getLabel() + " " + toVertex.getLabel();
        this.weights = new DSALinkedList();
    }

    // Get the weight corresponding to the given edge in the graph
    public String findWeight(String edge) {
        String weightVal = null;
        // Iterator the data until the label is found
        for (Object o : weights) {
            DSAEdgeWeight edgeVal = (DSAEdgeWeight)o;
            if(edge.equals(edgeVal.getEdge()))
            {
                weightVal = edgeVal.getWeight();
            }
        }
        return weightVal;

    }

    public void addWeight(String weight, String edge)
    {
        weights.insertLast(new DSAEdgeWeight(weight, edge));
    }

    public DSALinkedList getWeights() {
        return weights;
    }

    public String getLabel()
    {
        return label;
    }
    
    public DSAGraphNode getFrom()
    {
        return from;
    }
    
    public DSAGraphNode getTo()
    {
        return to;
    }
}
