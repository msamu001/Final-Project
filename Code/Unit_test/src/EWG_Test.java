import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EWG_Test {
	EWG graph;
	int countV;
	@Before
	public void setUp() throws Exception {
		graph = new EWG();
		countV = 0;
	}

	@Test
	public void test() {
		// Check vertex can exist in graph unconnected
		Assert.assertEquals(0, graph.getVertices().size());
		graph.addVertex("a");
		Assert.assertNotEquals(null, graph.getVertex("a"));
		Assert.assertEquals(1, graph.getVertices().size());
		
		// Check no duplicate labels allowed
		graph.addVertex(new Vertex("a"));
		for(String l: graph.getVertices().keySet()) {
			if(l.equals("a")) countV++;
		}
		Assert.assertEquals(1, countV);
		
		// Check vertex can exist in graph connected
		graph.addVertex("b");
		Assert.assertEquals(0, graph.degree("a"));
		Assert.assertEquals(0, graph.degree("b"));
		graph.addEdge("a", "b");
		Assert.assertEquals(1, graph.degree("a"));
		Assert.assertEquals(1, graph.degree("b"));
		
		// Check graph can be deconstructed
		graph.removeEdge("a", "b");
		
		// Check traversal is possible
		
		// Check edge 
		graph = new EWG();
		Assert.assertEquals(0, graph.getVertices().size());
		graph.addEdge(new Edge(new Vertex("c"), new Vertex("d")));
		Assert.assertNotEquals(null, graph.getVertex("c"));
		Assert.assertNotEquals(null, graph.getVertex("d"));
		Assert.assertEquals(2, graph.getVertices().size());		
	}
}