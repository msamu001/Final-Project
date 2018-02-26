import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Agent_Test {
	Agent agent;
	EWG graph;
	@Before
	public void setUp() throws Exception {
		graph.addEdge(new Vertex("a"), new Vertex("b"));
		graph.addEdge(new Vertex("c"), new Vertex("b"));
		agent = new Agent(graph);
	}

	@Test
	public void test() {
		// Check status is set to false by default
		Assert.assertEquals(false, agent.getStatus());
		
		
	}
}