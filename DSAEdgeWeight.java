/* *********************************************************************
* File:       DSAEdgeWeight.java
* Author:     G.G.T.Shashen
* Created:    15/10/2021
* Modified:   17/10/2021
* Desc:       DSAEdgeWeight implementation from Practical 6
************************************************************************/
import java.io.*;

public class DSAEdgeWeight implements Serializable {
    private String weight;
    private String edge;

    public DSAEdgeWeight(String weight, String edge) {
        this.weight = weight;
        this.edge = edge;
    }

    public String getWeight() {
        return weight;
    }

    public String getEdge() {
        return edge;
    }
}
