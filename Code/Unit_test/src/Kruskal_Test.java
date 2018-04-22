import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Kruskal_Test {
	Kruskal k;
	EWG graph;
	DFS dfs;
	
	@Before
	public void setUp() throws Exception {
		graph = new EWG();
		graph.addEdge(new Edge(new Vertex("a"), new Vertex("b"), 1));
		graph.addEdge(new Edge(new Vertex("c"), new Vertex("b"), 2));
		graph.addEdge(new Edge(new Vertex("a"), new Vertex("c"), 3));
		k = new Kruskal(graph);
	}

	@Test
	public void test() {
		dfs = new DFS(k.mst(), "a");
		Assert.assertEquals(3, (int)k.mst().weight());
		Assert.assertEquals(true, dfs.isSpanTree());
		Assert.assertEquals(false, dfs.hasCycle());
	}
}