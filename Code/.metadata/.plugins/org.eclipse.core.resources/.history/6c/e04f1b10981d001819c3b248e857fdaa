import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Edge_Test {
	Edge edge, edge2;
	String error;
	@Before
	public void setUp() throws Exception {
		edge = new Edge(new Vertex("test"), new Vertex("test2"));
		try{
			edge2 = new Edge(new Vertex("test"), new Vertex("test"));
		} catch (Exception e){
			error = e.toString();
		}
	}

	@Test
	public void test() {
		// Check an edge is connected to two vertices
		Assert.assertNotEquals(null, edge.getVertex1());
		Assert.assertNotEquals(null, edge.getVertex2());
		
		// Check a new edge will not create a self-looping vertex
		Assert.assertEquals("java.lang.IllegalArgumentException: Cannot connect a vertex to itself", error);
		Assert.assertEquals(null, edge2);
		
		// Check an edge will not add vertices that create self-loops
		edge.setVertex2(new Vertex("test"));
		Assert.assertNotEquals(edge.getVertex1().getLabel(), edge.getVertex2().getLabel());
	}
}