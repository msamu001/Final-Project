import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Edge_Test {
	Edge edge, edge2;
	String error;
	@Before
	public void setUp() throws Exception {
		edge = new Edge(new Vertex("test2"), new Vertex("test"));
	}

	@Test
	public void test() {
		// Check edge orders vertex classes
		Assert.assertEquals("test", edge.getVertex1().getLabel());
		Assert.assertEquals("test2", edge.getVertex2().getLabel());
		
		// Check an edge is connected to two vertices
		Assert.assertNotEquals(null, edge.getVertex1());
		Assert.assertNotEquals(null, edge.getVertex2());
		
		// Check an edge will not add vertices that create self-loops
		edge.setVertex2(new Vertex("test"));
		Assert.assertNotEquals(edge.getVertex1().getLabel(), edge.getVertex2().getLabel());
		
		// Check a new edge will not create a self-looping vertex
		try{
			edge2 = new Edge(new Vertex("test"), new Vertex("test"));
		} catch (Exception e){
			error = e.toString();
		}
		Assert.assertEquals("java.lang.IllegalArgumentException: Cannot connect a vertex to itself", error);
		Assert.assertEquals(null, edge2);
	}
}