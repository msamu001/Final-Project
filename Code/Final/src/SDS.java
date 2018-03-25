import java.util.Random;

public class SDS {
	private EWG graph;
	private Agent[] agent;
	private double actiRate;
	private int it;
	private Random rand;
	
	public SDS(EWG g, int agentNum, double activation, int iteration) {
		graph = g;
		agent = new Agent[agentNum];
		actiRate = activation * 0.01;
		it = iteration;
		rand = new Random();
		init();
	}
	
	public void run(){
		for(int i = 0; i < it; i++) {
			test();
			diffuse();
		}
	}
	
	public void setAgents(Agent[] a) {
		agent = a;
	}
	
	public Agent[] getAgents() {
		return agent;
	}
	
	public void setRate(double ar) {
		actiRate = ar;
	}
	
	public double getRate() {
		return actiRate;
	}
	
	public void setGraph(EWG g) {
		graph = g;
	}
	
	public EWG getGraph() {
		return graph;
	}
	
	public int getIt() {
		return it;
	}
	
	private void init() {
		
	}
	
	private void test() {
		
	}
	
	private void diffuse() {
		
	}	
}
