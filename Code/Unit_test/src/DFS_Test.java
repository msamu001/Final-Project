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
		graph4 = new EWG();
		
		graph.addVertex("a");
		
		graph2.addEdge("a","b");
		
		graph3.addEdge("z","a");
		graph3.addEdge("a","b");
		graph3.addEdge("a","c");
		graph3.addEdge("a","d");
		graph3.addEdge("c","d");
		
		graph4.addEdge("1","3");
		graph4.addEdge("1","0");
		graph4.addEdge("0","4");
		graph4.addEdge("4","2");
		graph4.addEdge("2","0");
	}

	@Test
	public void test() {
//		// Check DFS identifies single vertex graphs correctly
//		dfs = new DFS(graph, "a");
//		Assert.assertEquals(false, dfs.isSpanTree());
//		Assert.assertEquals(false, dfs.hasCycle());
//		
//		// Check DFS identifies spanning trees correctly
//		dfs = new DFS(graph2, "a");
//		Assert.assertEquals(true, dfs.isSpanTree());
//		Assert.assertEquals(false, dfs.hasCycle());
//		
		// Check DFS identifies cycles correctly
		dfs = new DFS(graph4, "4");
		Assert.assertEquals(false, dfs.isSpanTree());
		Assert.assertEquals(true, dfs.hasCycle());
		Assert.assertEquals(3, dfs.getCycle().size());
		
//		dfs = new DFS(graph3, "b");
//		Assert.assertEquals(false, dfs.isSpanTree());
//		Assert.assertEquals(true, dfs.hasCycle());
//		Assert.assertEquals(3, dfs.getCycle().size());
	}

}
