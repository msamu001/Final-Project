public class Edge {
	private Vertex[] vertex;
	private double weight;
	
	public Edge(Vertex v1, Vertex v2) {
		weight = 0;
		initialize(v1, v2);
	}
	
	public Edge(Vertex v1, Vertex v2, double w) {
		weight = w;
		initialize(v1, v2);
	}
	
	// Adds this edge to both vertices
	private void initialize(Vertex v1, Vertex v2){
		if(v1.getLabel().equals(v2.getLabel())) throw new IllegalArgumentException("Cannot connect a vertex to itself");
		vertex = new Vertex[]{v1,v2};
		v1.add(this);
		v2.add(this);
	}
	
	public void setVertex1(Vertex v1) {
		if(vertex[1].getLabel().equals(v1.getLabel())) return;
		vertex[0] = v1;
	}
	
	public Vertex getVertex1() {
		return vertex[0];
	}
	
	public void setVertex2(Vertex v2) {
		if(vertex[0].getLabel().equals(v2.getLabel())) return;
		vertex[1] = v2;
	}
	
	public Vertex getVertex2() {
		return vertex[1];
	}
	
	public double weight() {
		return weight;
	}
	
	public void setWeight(double w) {
		weight = w;
	}
	
	public Vertex getOther(Vertex v) {
		Vertex temp = null;
		if(vertex[0].equals(v)){
			temp = vertex[1];
		}
		else if(vertex[1].equals(v)) {
			temp = vertex[0];
		}
		return temp;
	}
	
	public String toString() {
		return ("Vertex 1: " + vertex[0].getLabel() + "   " +
				"Vertex 2: " + vertex[1].getLabel() + "   " +
				"Edge Weight: " + weight + " ");
	}
}
