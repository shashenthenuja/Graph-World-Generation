JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	DSAGraph.java \
    DSAGraphEdge.java \
    DSAGraphNode.java \
	DSAEdgeWeight.java \
	DSALinkedList.java \
	DSAQueue.java \
	InteractiveMode.java \
	SimulationMode.java \
	FileIO.java \
    gameofcatz.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class