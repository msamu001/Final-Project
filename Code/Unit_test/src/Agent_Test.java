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
		graph.addEdge(new Edge(new Vertex("a"), new Vertex("b"), 1));
		graph.addEdge(new Edge(new Vertex("c"), new Vertex("b"), 2));
		graph.addEdge(new Edge(new Vertex("a"), new Vertex("c"), 3));
		agent = new Agent(graph);
		changed = false;
	}

	@Test
	public void test() {
		// Check status is set to false by default
		Assert.assertEquals(false, agent.getStatus());
		
		hypo = agent.getHypo();
		for(int i = 0; i < 50; i++) {
			agent = new Agent(graph);
			dfs = new DFS(agent.getHypo(), "a");
			
			// Check hypothesis is a spanning tree
			Assert.assertEquals(3, agent.getHypo().getVertices().size());
			Assert.assertEquals(2, agent.getHypo().getEdges().size());
			Assert.assertEquals(graph.size()-1, agent.getHypo().size());
			Assert.assertEquals(true, dfs.isSpanTree());
			Assert.assertEquals(false, dfs.hasCycle());
			
			// Check hypothesis is random
			if(hypo.weight() != agent.getHypo().weight()){
				changed = true;
			}
		}
		Assert.assertEquals(true, changed);
	}
}