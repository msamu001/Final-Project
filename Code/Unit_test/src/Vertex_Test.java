import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Vertex_Test {
	Vertex vertex;
	Edge tEdge;
	@Before
	public void setUp() throws Exception {
		vertex = new Vertex("test");
	}

	@Test
	public void test() {
		// Check Hashset<Edges> has been initialized
		Assert.assertNotEquals(null, vertex.getEdges());
		
		// Check no edges are initially connected
		Assert.assertEquals(0, vertex.degree());
		
		// Check an edge can be added correctly
		tEdge = new Edge(vertex, new Vertex("test2"));
		Assert.assertEquals(1, vertex.degree());
		
		// Check no duplicates allowed using existing edge
		vertex.add(tEdge);
		Assert.assertEquals(1, vertex.degree());
		
		// Check no duplicates allowed using new edge
		vertex.add(new Edge(vertex, new Vertex("test2")));
		Assert.assertEquals(1, vertex.degree());
		
		// Check edges can be removed correctly
		vertex.remove("test2");
		Assert.assertEquals(0, vertex.degree());
	}
}