import java.util.Random;
import java.util.HashSet;
import java.util.Iterator;

public class Agent {
	private boolean status;
	private EWG hypo;
	private double fitness;
	
	public Agent(EWG hypothesis) {
		status = false;
		hypo = genHypo(hypothesis);
	}
	
	public void setStatus(boolean s) {
		status = s;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setHypo(EWG h) {
		hypo = h;
	}
	
	public EWG getHypo() {
		return hypo;
	}
	
	public void setFitness(double f) {
		fitness = f;
	}
	
	public double getFitness() {
		return fitness;
	}
	
	public String toString() {
		return 	"Status: " + status + "\n" +
				"Fitness: " + String.format("%.2f", fitness*100) + "%" + "\n" +
				hypo.toString();
	}
	
	private EWG genHypo(EWG g) {
		Random rand = new Random();
		EWG hypo = new EWG(g);
		HashSet<Edge> edges = hypo.getEdges();	// List of all edges
		hypo.removeAllEdges();
		
		while(edges.size() > 0) {
			Edge rEdge;
			int rIndex = rand.nextInt(edges.size());
			Iterator<Edge> edgeIt = edges.iterator();
			
			// Selects random edge
			for(int i = 0; i < rIndex; i++)	edgeIt.next();
			rEdge = edgeIt.next();
			
			// check if edge creates loop
			edges.remove(rEdge);
			hypo.addEdge(rEdge);
			DFS checkG = new DFS(hypo, rEdge.getVertex1());
			
			if(checkG.hasCycle()) hypo.removeEdge(rEdge);
			if(checkG.isSpanTree()) return hypo;
		}
		return hypo;
	}
}
