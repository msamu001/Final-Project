import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Agent_Test {
	Agent agent;
	EWG graph, hypo;
	DFS dfs;
	Boolean changed;
	@Before
	public void setUp() throws Exception {
		graph = new EWG();
		graph.addEdge(new Edge(new Vertex("a"), new Vertex("b")));
		graph.addEdge(new Edge(new Vertex("c"), new Vertex("b")));
		agent = new Agent(graph);
		dfs = new DFS(graph, "a");
		changed = false;
	}

	@Test
	public void test() {
		// Check status is set to false by default
		Assert.assertEquals(false, agent.getStatus());
		
		// Check hypothesis is a minimum spanning tree
		Assert.assertEquals(3, agent.getHypo().getVertices().size());
		Assert.assertEquals(2, agent.getHypo().getEdges().size());
		Assert.assertEquals(true, dfs.isSpanTree());
		Assert.assertEquals(false, dfs.hasLoop());
		
		// Check hypothesis is random
		hypo = agent.getHypo();
		for(int i = 0; i < 50; i++) {
			agent = new Agent(graph);
			if(!hypo.equals(agent.getHypo())){
				changed = true;
				break;
			}
		}
		Assert.assertEquals(true, changed);
	}
}