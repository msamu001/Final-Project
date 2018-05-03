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
		graph = Global.genGraph(10);
		agent = new Agent(graph);
		changed = false;
	}

	@Test
	public void test() {
		// Check status is set to false by default
		Assert.assertEquals(false, agent.getStatus());
		
		hypo = agent.getHypo();
		for(int i = 0; i < 1; i++) {
			agent = new Agent(graph);
			dfs = new DFS(agent.getHypo(), "1");
			
			// Check hypothesis is a spanning tree
			Assert.assertEquals(true, dfs.isSpanTree());
			Assert.assertEquals(false, dfs.hasCycle());
			Assert.assertEquals(10, agent.getHypo().order());
			Assert.assertEquals(9, agent.getHypo().size());
			
			// Check hypothesis is random
			if(hypo.weight() != agent.getHypo().weight()){
				changed = true;
			}
		}
		Assert.assertEquals(true, changed);
	}
}