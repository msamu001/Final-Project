import java.util.HashSet;
import java.util.Iterator;

public class Vertex {
	private String label;
	private HashSet<Edge> edges; // Stores a set of all edges connected to this vertex
	
	public Vertex(String l) { // Constructor for no type input
		label = l;
		edges = new HashSet<Edge>();
	}
	
	public void add(Edge e) {
		edges.add(e);
	}
	
	public void remove(Edge e) { // Remove given edge
		edges.remove(e);
	}
	
	public void remove(Vertex v) { // Removes the edge connecting to given vertex
		edges.remove(findEdge(v));
	}
	
	public Edge findEdge(Vertex vertex) { // 
		Iterator<Edge> edgeIt = edges.iterator();
		Edge e = null;
		while(edgeIt.hasNext()) {
			Edge tEdge = edgeIt.next();
			if(vertex.equals(tEdge.getVertex1())) e = tEdge;
			if(vertex.equals(tEdge.getVertex2())) e = tEdge;
		}
		return e;
	}
	
	public int degree() {
		return edges.size();
	}
	
	public String getLabel() {
		return label;
	}
	
	public HashSet<Edge> getEdges(){
		return edges;
	}

	public String toString() {
		String s = ("Vertex " + label + ": ");
		Iterator<Edge> i = edges.iterator();
		while(i.hasNext()){
			Edge tEdge = i.next();
			s += (tEdge.getOther(this).getLabel() + " " + tEdge.getWeight() + "   ");
		}
		return s;
	}
}
