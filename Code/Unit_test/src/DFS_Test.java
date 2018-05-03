import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DFS_Test {
	DFS dfs;
	EWG graph, graph2, graph3, graph4;
	@Before
	public void setUp() throws Exception {
		graph = new EWG();
		graph2 = new EWG();
		graph3 = new EWG();
		
		graph.addVertex("a");
		
		graph2.addEdge(new Edge(new Vertex("a"), new Vertex("b")));
		
		graph3.addEdge(new Edge(new Vertex("a"), new Vertex("b")));
		graph3.addEdge(new Edge(new Vertex("c"), new Vertex("b")));
		graph3.addEdge(new Edge(new Vertex("a"), new Vertex("c")));
	}

	@Test
	public void test() {
		// Check DFS identifies single vertex graphs correctly
		dfs = new DFS(graph, "a");
		Assert.assertEquals(false, dfs.isSpanTree());
		Assert.assertEquals(false, dfs.hasCycle());
		
		// Check DFS identifies spanning trees correctly
		dfs = new DFS(graph2, "a");
		Assert.assertEquals(true, dfs.isSpanTree());
		Assert.assertEquals(false, dfs.hasCycle());
		
		// Check DFS identifies cycles correctly
		dfs = new DFS(graph3, "a");
		Assert.assertEquals(false, dfs.isSpanTree());
		Assert.assertEquals(true, dfs.hasCycle());
	}

}
