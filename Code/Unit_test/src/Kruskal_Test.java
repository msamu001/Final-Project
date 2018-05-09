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
		// complete graph with 4 vertices
		graph = new EWG();
		graph.addEdge("a","b",1);
		graph.addEdge("a","c",2);
		graph.addEdge("a","d",3);
		graph.addEdge("b","c",4);
		graph.addEdge("b","d",4);
		graph.addEdge("c","d",4);
		k = new Kruskal(graph);
		dfs = new DFS(k.mst(), "a");
	}

	@Test
	public void test() {
		Assert.assertEquals(6, (int)k.mst().weight());
		Assert.assertEquals(true, dfs.isSpanTree());
		Assert.assertEquals(false, dfs.hasCycle());
	}
}