import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EWG_Test {
	EWG graph;
	Edge edge;
	Vertex vertex;
	int countV;
	String error;
	
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
		Assert.assertEquals(1, graph.order());
		Assert.assertEquals(0, graph.degree("a"));
		
		// Check no duplicate labels allowed
		graph.addVertex(new Vertex("a"));
		for(String l: graph.getVertices().keySet()) {
			if(l.equals("a")) countV++;
		}
		Assert.assertEquals(1, countV);
		
		// Check vertex can exist in graph connected
		graph.addVertex("b");
		Assert.assertEquals(0, graph.degree("b"));
		graph.addEdge("a","b");
		Assert.assertEquals(1, graph.degree("a"));
		Assert.assertEquals(1, graph.degree("b"));
		Assert.assertEquals(1, graph.size());
		
		// Check graph does not allow duplicate connections
		graph.addEdge("a","b");
		Assert.assertEquals(1, graph.degree("a"));
		Assert.assertEquals(1, graph.degree("b"));
		Assert.assertEquals(1, graph.size());
		graph.addEdge(new Edge(new Vertex("a"), new Vertex("b")));
		Assert.assertEquals(1, graph.degree("a"));
		Assert.assertEquals(1, graph.degree("b"));
		Assert.assertEquals(1, graph.size());
		
		// Check traversal is possible
		edge = graph.getVertex("a").findEdge("b");
		Assert.assertNotEquals(null, edge);
		vertex = edge.getOther(graph.getVertex("a"));
		Assert.assertEquals(graph.getVertex("b"), vertex);
		
		// Check graph can be deconstructed
		graph.removeVertex("b");
		Assert.assertEquals(0, graph.degree("a"));
		Assert.assertEquals(null, graph.getVertex("b"));
		
		// Check edge cannot be created without vertices
		graph = new EWG();
		Assert.assertEquals(0, graph.getVertices().size());
		graph.addEdge(new Edge(new Vertex("a"), new Vertex("b")));
		Assert.assertNotEquals(null, graph.getVertex("a"));
		Assert.assertNotEquals(null, graph.getVertex("b"));
		Assert.assertEquals(2, graph.getVertices().size());
		graph.addEdge("c","d");
		Assert.assertNotEquals(null, graph.getVertex("c"));
		Assert.assertNotEquals(null, graph.getVertex("d"));
		Assert.assertEquals(4, graph.getVertices().size());
		
		// Check edge A-B and B-A are considered duplicates
		graph = new EWG();
		graph.addEdge("a","b");
		Assert.assertEquals(1,graph.size());
		graph.addEdge("b","a");
		Assert.assertEquals(1,graph.size());
	}
}