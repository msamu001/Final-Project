import java.util.Random;

public class SDS {
	private EWG graph;
	private Agent[] agent;
	private double actiRate;
	private Random rand;
	
	public SDS(EWG g, int agentNum, double activation, int iteration) {
		graph = g;
		agent = new Agent[agentNum];
		actiRate = activation * 0.01;
		rand = new Random();
		
		init();
		for(int i = 0; i < iteration; i++) {
			test();
			diffuse();
		}
	}
	
	private void init() {
		
	}
	
	private void test() {
		
	}
	
	private void diffuse() {
		
	}
}
