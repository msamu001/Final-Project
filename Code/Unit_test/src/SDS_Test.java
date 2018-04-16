import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SDS_Test {
	SDS sds;
	EWG graph;
	Agent[] agents;
	int it = 1;
	@Before
	public void setUp() throws Exception {
		graph = new EWG();
		graph.addEdge(new Edge(new Vertex("a"), new Vertex("b"), 1));
		graph.addEdge(new Edge(new Vertex("c"), new Vertex("b"), 2));
		graph.addEdge(new Edge(new Vertex("a"), new Vertex("c"), 3));
		
		sds = new SDS(graph, 10, 30, it);
		agents = sds.getAgents();
	}

	@Test
	public void test() {
		// Test agents have been generated and each agent has a hypothesis
		for(Agent a: agents) {
			Assert.assertNotEquals(null, a);
			Assert.assertNotEquals(null, a.getHypo());
		}
		
		// Test agent's fitness has been calculated
		sds.run();
		for(Agent a: agents) {
			Assert.assertNotEquals(0, a.getFitness());
			Assert.assertNotEquals(a.getHypo().weight(), a.getFitness());
		}
		
		// Test agent's fitness has been calculated
		sds.run(1);
		for(Agent a: agents) {
			Assert.assertNotEquals(0, a.getFitness());
			Assert.assertNotEquals(a.getHypo().weight(), a.getFitness());
		}
		
		// Test agent's fitness has been calculated
		sds.run(2);
		for(Agent a: agents) {
			Assert.assertNotEquals(0, a.getFitness());
		}
	}
}
