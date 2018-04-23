public class Kruskal {
	private EWG mst;
	
	public Kruskal(EWG graph) {
		mst = run(graph);
	}
	
	public EWG mst() {
		return mst;
	}
	
	private EWG run(EWG g) {
		EWG mst = new EWG(g);
		Edge[] edges = g.getEdges().toArray(new Edge[g.size()]);
		mst.removeAllEdges();
		
		quicksort(edges, 0, edges.length-1, true);
		for(Edge e: edges) {
			System.out.println(e.weight());
		}
		
		for(Edge e: edges) {
			mst.addEdge(e);			
			DFS checkG = new DFS(mst, e.getVertex1());
			if(checkG.hasCycle()) mst.removeEdge(e);
			if(checkG.isSpanTree()) return mst;
		}
		return mst;
	}
	
	private static Edge[] quicksort(Edge[] E, int lo, int hi) {
		return quicksort(E,lo,hi,false);
	}
	
	private static Edge[] quicksort(Edge[] E, int lo, int hi, boolean ascend) {
		int p = 0;
		if (lo < hi) {
			p = partition(E, lo, hi, ascend);
			quicksort(E, lo, p, ascend);
			quicksort(E, p+1, hi, ascend);
		}
		return E;
	}
	
	private static int partition(Edge[] E, int lo, int hi, boolean ascend) {
		double pivot = E[lo + (hi - lo)/2].weight(); // middle value
		int i = lo;
		int j = hi;
		while(i <= j) {
			if(ascend) {
				while(E[i].weight() < pivot) i++;
				while(E[j].weight() > pivot) j--;
			} else {
				while(E[i].weight() > pivot) i++;
				while(E[j].weight() < pivot) j--;
			}			
			if(i >= j) return j;
			if(i < j) { // swap
				Edge temp = E[i];
				E[i] = E[j];
				E[j] = temp;
				i++;
				j--;
			}
		}
		return j;
	}
}