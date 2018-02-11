import java.util.HashSet;
import java.util.TreeMap;

public class EWG {
	private HashSet<Edge> edges;
	private TreeMap<String, Vertex> vertices;
	
	public EWG(){
		edges = new HashSet<Edge>();
		vertices = new TreeMap<String, Vertex>();
	}
	
	public EWG(TreeMap v) {
		edges = new HashSet<Edge>();
		vertices = v;
	}
	
	public EWG(EWG g) {
		edges = new HashSet<Edge>(g.getEdges());
		vertices = new TreeMap<String, Vertex>(g.getVertices());
	}
	
	public int size() { // Returns the number of Vertices in graph
		return vertices.size();
	}
	
	public int edgeNum() { // Returns the number of edges in the graph
		return edges.size();
	}
	
	public TreeMap<String, Vertex> getVertices() { // Returns the vertices treeMap
		return vertices;
	}
	
	public Vertex getVertex(String label) { // Returns the vertex associated with input label
		return null;
	}
	
	public void addVertex(String label) { 
		vertices.put(label, new Vertex(label));
	}
	
	public void addVertex(Vertex v) {
		String label = v.getLabel();
		vertices.put(label, v);
	}
	
	public void removeVertex(Vertex v) {
	}
	
	public HashSet<Edge> getEdges() {
		return edges;
	}
	
	public void addEdge(Edge e) {
		
	}
	
	public void addEdge(String vLabel1, String vLabel2, double w) { // Attaches an edge to each of the vertices via the labels
		edges.add(new Edge(getVertex(vLabel1), getVertex(vLabel2), w));
	}
	
	public void addEdge(String vLabel1, String vLabel2) { 
		addEdge(vLabel1, vLabel2, 0);
	}
	
	public void addEdge(Vertex v1, Vertex v2, double w) { // Attaches an edge to each of the vertices
		edges.add(new Edge(v1, v2, w));
	}
	
	public void addEdge(Vertex v1, Vertex v2) {
		addEdge(v1, v2, 0);
	}
	
	public void removeEdge(String vLabel1, String vLabel2) {
		
	}
	
	public void removeEdge(Vertex v1, Vertex v2) {
		
	}
	
	public void removeEdge(Edge e) {
		
	}
	
	public int degree(String label) {
		return getVertex(label).degree();
	}
	
	public double weight() {
		return 0;
	}
	
	public String toString() {
		return null;
	}
}
