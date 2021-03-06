import java.util.HashSet;
import java.util.Iterator;

public class Vertex {
	private String label;
	private HashSet<Edge> edges; // Stores a set of all edges connected to this vertex
	
	public Vertex(String l) { // Constructor for no type input
		label = l;
		edges = new HashSet<Edge>();
	}
	
	public Edge findEdge(String l) { // 
		Iterator<Edge> edgeIt = edges.iterator();
		Edge e = null;
		while(edgeIt.hasNext()) {
			Edge tEdge = edgeIt.next();
			if(l.equals(tEdge.getVertex1().getLabel())) {
				e = tEdge;
				break;
			}
			if(l.equals(tEdge.getVertex2().getLabel())) {
				e = tEdge;
				break;
			}
		}
		return e;
	}
	
	public void add(Edge e) {
		Vertex check = e.getOther(this);
		if(findEdge(check.getLabel()) == null) { // Checks if the edge already exists
			edges.add(e);
		}
	}
	
	public void remove(Edge e) { // Remove given edge
		edges.remove(e);
	}
	
	public void remove(Vertex v) { // Removes the edge connecting to given vertex
		edges.remove(findEdge(v.getLabel()));
	}
	
	public void remove(String l) { // Removes the edge connecting to given vertex
		edges.remove(findEdge(l));
	}
	
	public void removeAll() { // Removes all edges
		edges = new HashSet<Edge>();
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
		String s = ("Vertex " + label + "| ");
		Iterator<Edge> i = edges.iterator();
		while(i.hasNext()){
			Edge tEdge = i.next();
			s += ("V" + tEdge.getOther(this).getLabel() + " " + tEdge.weight() + "   ");
		}
		return s;
	}
}
