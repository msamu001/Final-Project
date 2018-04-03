import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SDS_Test {
	SDS sds;
	EWG graph;
	double sum;
	Agent[] agents;
	@Before
	public void setUp() throws Exception {
		graph = new EWG();
		graph.addEdge(new Edge(new Vertex("a"), new Vertex("b"), 1));
		graph.addEdge(new Edge(new Vertex("c"), new Vertex("b"), 2));
		graph.addEdge(new Edge(new Vertex("a"), new Vertex("c"), 3));
		
		sds = new SDS(graph, 10, 30, 1);
		agents = sds.getAgents();
		sum = 0;
	}

	@Test
	public void test() {
		// Check agents have been generated and each agent has a hypothesis
		for(int i = 0; i < agents.length; i++) {
			Agent a = agents[i];
			Assert.assertNotEquals(null, a);
			Assert.assertNotEquals(null, a.getHypo());
		}
		
		// Check agent's fitness has been calculated
		sds.run();
		for(int i = 0; i < agents.length; i++) {
			Agent a = agents[i];
			Assert.assertNotEquals(0, a.getFitness());
			sum += a.getFitness();
		}
	}
}
