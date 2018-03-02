import java.util.HashSet;
import java.util.LinkedList;
import java.util.Iterator;

public class DFS {
	boolean loop;
	boolean spanTree;
	
	public DFS(EWG graph, Vertex start) {
		loop = false;
		spanTree = false;
		search(graph, start);
	}
	
	public DFS(EWG graph, String start) {
		loop = false;
		spanTree = false;
		search(graph, graph.getVertex(start));
	}
	
	public boolean hasLoop() {
		return loop;
	}
	
	public boolean isSpanTree() {
		return spanTree;
	}
	
	public String toString() {
		return 	"Loop: " + loop + "\n" +
				"Spanning Tree: " + spanTree;
	}
	
	private void search(EWG g, Vertex s) {
		HashSet<Vertex> visited = new HashSet<Vertex>();
		HashSet<Edge> used = new HashSet<Edge>();
		LinkedList<Vertex> frontier = new LinkedList<Vertex>();
		frontier.add(s);
		search(g, visited, used, frontier);
	}
	
	private void search(EWG g, HashSet<Vertex> visited, HashSet<Edge> used, LinkedList<Vertex> frontier) {
		if(frontier.size() == 0) {
			if(visited.size() == g.getVertices().size()) spanTree = true;
			return;
		}
		
		// Open last node in frontier (stack)
		Vertex v = frontier.removeLast();
		visited.add(v);
		
		// Add accessible nodes to frontier (stack)
		Iterator<Edge> edgeIt = v.getEdges().iterator();
		while(edgeIt.hasNext()) {
			Edge tEdge = edgeIt.next();
			if(!used.contains(tEdge)) {
				if(frontier.contains(tEdge.getOther(v))) {
					loop = true;
					return;
				}
				frontier.add(tEdge.getOther(v));
				used.add(tEdge);
			}
		}		
		search(g, visited, used, frontier);
	}
}