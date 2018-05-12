public class Edge {
	private Vertex[] vertex;
	private double weight;
	private String label;
	
	public Edge(Vertex v1, Vertex v2) {
		weight = 0;
		initialize(v1, v2, true);
	}
	
	public Edge(Vertex v1, Vertex v2, double w) {
		weight = w;
		initialize(v1, v2, true);
	}
	
	public Edge(Vertex v1, Vertex v2, double w, boolean attach) {
		weight = w;
		initialize(v1, v2, attach);
	}
	
	public void setVertex1(Vertex v1) {
		if(vertex[1].getLabel().equals(v1.getLabel())) return;
		vertex[0] = v1;
	}
	
	public void setVertex2(Vertex v2) {
		if(vertex[0].getLabel().equals(v2.getLabel())) return;
		vertex[1] = v2;
	}
	
	public Vertex getVertex1() {
		return vertex[0];
	}
	
	public Vertex getVertex2() {
		return vertex[1];
	}
	
	public String getLabel() {
		return label;
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
		return ("Vertex " + vertex[0].getLabel() + "|   " +
				"Vertex " + vertex[1].getLabel() + "|   " +
				"Edge Weight: " + weight + " ");
	}
	
	// Adds this edge to both vertices
	private void initialize(Vertex v1, Vertex v2, boolean attach){
		if(v1.getLabel().equals(v2.getLabel())) throw new IllegalArgumentException("Cannot connect a vertex to itself");
		if(orderVertex(v1,v2)) vertex = new Vertex[]{v1,v2};
		else vertex = new Vertex[]{v2,v1};
		label = "";
		for(Vertex v: vertex) label += v.getLabel();
		if(attach) {
			v1.add(this);
			v2.add(this);
		}
	}
	
	private boolean orderVertex(Vertex v1, Vertex v2) {
		int asciiV1 = 0;
		int asciiV2 = 0;
		
		// Calculate value of label
		for(char c: v1.getLabel().toCharArray()) asciiV1 += (int)c;
		for(char c: v2.getLabel().toCharArray()) asciiV2 += (int)c;
		
		if(asciiV1 > asciiV2) return false;
		return true;
	}
}
