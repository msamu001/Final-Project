import java.util.HashSet;
import java.util.Iterator;
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
		for(String l: vertices.keySet()) {
			if(l.equals(label)) return vertices.get(l);
		}
		return null;
	}
	
	public void addVertex(String label) {
		vertices.put(label, new Vertex(label));
	}
	
	public void addVertex(Vertex v) {
		String label = v.getLabel();
		vertices.put(label, v);
	}
	
	public void removeVertex(String vLabel) {
		removeVertex(getVertex(vLabel));
	}
	
	public void removeVertex(Vertex v) {
		vertices.remove(v.getLabel());
		Iterator<Edge> it = v.getEdges().iterator();
		while(it.hasNext()) {
			Edge edgeR = it.next();
			removeEdge(v, edgeR.getOther(v));
		}
		v = null;
	}
	
	public HashSet<Edge> getEdges() {
		return edges;
	}
	
	public void addEdge(Edge e) {
		Vertex v1 = e.getVertex1();
		Vertex v2 = e.getVertex2();
		
		if(chkVertex(v1.getLabel())) { // Connect edge to existing vertex
			Vertex tVertex = vertices.get(v1.getLabel());
			e.setVertex1(tVertex);
			tVertex.add(e);
			vertices.put(v1.getLabel(), tVertex);
		} else addVertex(v1);
		
		if(chkVertex(v2.getLabel())) { // Connect edge to existing vertex		
			Vertex tVertex = vertices.get(v2.getLabel());
			e.setVertex2(tVertex);
			tVertex.add(e);
			vertices.put(v2.getLabel(), tVertex);
		} else addVertex(v2);
		
		edges.add(e);
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
		Vertex v1 = vertices.get(vLabel1);
		Edge e = v1.findEdge(vLabel2);
		removeEdge(e);
	}
	
	public void removeEdge(Vertex v1, Vertex v2) {
		Edge e = v1.findEdge(v2.getLabel());
		removeEdge(e);
	}
	
	public void removeEdge(Edge e) {
		edges.remove(e);
		e.getVertex1().remove(e);
		e.getVertex2().remove(e);
		e = null;
	}
	
	public void removeAllEdges() {
		for(Vertex v: vertices.values()) v.removeAll();
		edges = new HashSet<Edge>();			
	}
	
	public int degree(String label) {
		return getVertex(label).degree();
	}
	
	public double weight() {
		double sumWeight = 0;
		Iterator<Edge> edgeIt = edges.iterator();
		while(edgeIt.hasNext()) {
			sumWeight += edgeIt.next().getWeight();
		}
		return sumWeight;
	}
	
	public String toString() {
		return null;
	}
	
	private boolean chkVertex(String label) {
		if(vertices.containsKey(label)) return true;
		return false;
	}
}