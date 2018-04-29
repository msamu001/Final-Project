import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class SDS {
	private EWG graph;
	private Agent[] agent;
	private double actiRate;
	private int it;
	private Random rand;
	private double[][] results; // best, worst, mean, median
	
	public SDS(EWG g, int agentNum, double activation, int iteration) {
		graph = g;
		agent = new Agent[agentNum];
		actiRate = activation * 0.01;
		it = iteration;
		rand = new Random();
		results = new double[4][iteration];
		init();
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
	
	public double[] getBest() {
		return results[0];
	}
	
	public double[] getWorst() {
		return results[1];
	}
	
	public double[] getMean() {
		return results[2];
	}
	
	public double[] getMedian() {
		return results[3];
	}
	
	public double[][] getResults() {
		return results;
	}
	
	public void run() {
		run(0);
	}
	
	public void run(int type) {
		for(int i = 0; i < it; i++) {
			switch(type) {		
			case 1:
				test(true);
				break;
			case 2:
				testD();
				break;
			default:
				test();
				break;
			}
			diffuse();
			calcResults(i);
		}
	}
	
	private void init() {
		for(int i = 0; i < agent.length; i++) {
			agent[i] = new Agent(graph);
		}
	}
	
	private void test() {
		test(false);
	}
	
	private void test(boolean roulette) {
		double graphWeight = graph.weight();
		double sumFit = 0;
		double sum = 0;
		
		// Calculates the fitness of each agent
		for(int i = 0; i < agent.length; i++) {
			double hypoWeight = agent[i].getHypo().weight();
			agent[i].setFitness(graphWeight - hypoWeight);
			sumFit += agent[i].getFitness();
		}
		
		// Convert fitness to % 
		for(int i = 0; i < agent.length; i++) {
			double fitness = agent[i].getFitness();
			fitness /= sumFit;
			sum += fitness;
			if(sum < 100 && i == agent.length-1) {
				fitness += 100 - sum;
			}
			agent[i].setFitness(fitness);
		}
		
		// Agents are activated by roulette selection
		if(roulette) {
			while(sum < actiRate) {
				int rNum = rand.nextInt(100);
				int rSum = 0;
				
				// Locates agent based on the random number
				for(int i = 0; i < agent.length; i++) {
					rSum += agent[i].getFitness(); // creates roulette
					if(rSum >= rNum) {
						if(!agent[i].getStatus()) { // skips active agents
							agent[i].setStatus(true);
							sum += agent[i].getFitness();
							break;
						}
					}
				}
			}
		} else {
			// Sorts agents in descending order
			quicksort(agent, 0, agent.length-1);
			
			// Activate the top X agents when X is actiRate
			for(int i = 0; i < agent.length; i++) {
				sum += agent[i].getFitness();
				if(i == 0) agent[i].setStatus(true);
				else if(sum < actiRate) agent[i].setStatus(true);
				else agent[i].setStatus(false);
			}
		}
	}
	
	private void testD() {
		int rNum = 0;
		for(Agent a: agent) {
			double weight = a.getHypo().weight();
			a.setFitness(weight);
		}
		for(int i = 0; i < agent.length; i++) {
			while(rNum == i)
				rNum = rand.nextInt(agent.length);
			if(agent[i].getFitness() < agent[rNum].getFitness()) agent[i].setStatus(true);
		}
	}
	
	private void diffuse() {
		for(int i = 0; i < agent.length; i++) {
			if(agent[i].getStatus() == false) {
				int rAgent = rand.nextInt(agent.length);
				if(agent[rAgent].getStatus()) {
					agent[i].setHypo(agent[rAgent].getHypo());
//					update(agent[i]);
				}
				else agent[i] = new Agent(graph);
			}
		}
	}
	
	private void update(Agent a) {
		EWG hypoU = a.getHypo();
		HashSet<Edge> avaEdges = graph.getEdges();
		avaEdges.removeAll(hypoU.getEdges());
		
		while(avaEdges.size() > 0) {
			HashSet<Edge> swapEdges;
			DFS checkH;
			Vertex v1,v2;
			Edge rEdge;
			int rIndex = rand.nextInt(avaEdges.size());
			Iterator<Edge> edgeIt = avaEdges.iterator();
			
			// select random edge
			for(int i = 0; i < rIndex; i++)	edgeIt.next();
			rEdge = edgeIt.next();
			
			avaEdges.remove(rEdge);
			hypoU.addEdge(rEdge);
			
			// start from vertex with smallest degree
			v1 = rEdge.getVertex1();
			v2 = rEdge.getVertex2();
			if(v1.degree() <= v2.degree()) checkH = new DFS(hypoU, v1);
			else checkH = new DFS(hypoU, v2);
			
			if(checkH.hasCycle()) { // swap edge randomly while keeping spanTree
				swapEdges = checkH.calcCycle();
				swapEdges.remove(rEdge); // prevents newly added edge being removed
				
				rIndex = rand.nextInt(swapEdges.size());			
				Iterator<Edge> edgeIt2 = swapEdges.iterator();
				
				// randomly select an edge to be removed
				for(int i = 0; i < rIndex; i++)	edgeIt2.next();
				rEdge = edgeIt2.next();				
				hypoU.removeEdge(rEdge);
				a.setHypo(hypoU);
				return;
			} else hypoU.removeEdge(rEdge);
		}
	}
	
	// normalize weight of hypo
	private double calcFitness(Agent a, EWG g) {
		double hypo = a.getHypo().weight();
		double graph = g.weight();
		return (hypo * 100)/graph;
	}
	
	private void calcResults(int i) {
		double sum = 0;
		
		for(Agent a: agent)	a.setFitness(calcFitness(a, graph));
		quicksort(agent, 0, agent.length-1, true); // sorts in ascending order
			
		results[0][i] = agent[0].getFitness(); // best
		results[1][i] = agent[agent.length-1].getFitness(); // worst
			
		// mean
		for(Agent a: agent) sum += a.getFitness();
		results[2][i] = sum/agent.length;
		sum = 0;
			
		// median
		if(agent.length%2 == 0) {
			sum = agent[agent.length/2].getFitness();
			sum += agent[agent.length/2 - 1].getFitness();
			results[3][i] = sum/2;
		} else results[3][i] = agent[agent.length/2].getFitness();
	}
	
	private static Agent[] quicksort(Agent[] E, int lo, int hi) {
		return quicksort(E,lo,hi,false);
	}
	
	private static Agent[] quicksort(Agent[] A, int lo, int hi, boolean ascend) {
		if (lo < hi) {
			int p = partition(A, lo, hi, ascend);
			quicksort(A, lo, p, ascend);
			quicksort(A, p+1, hi, ascend);
		}
		return A;
	}
	
	private static int partition(Agent[] A, int lo, int hi, boolean ascend) {
		double pivot = A[lo + (hi - lo)/2].getFitness(); // middle value
		int i = lo;
		int j = hi;
		while(i <= j) {
			if(ascend) {
				while(A[i].getFitness() < pivot) i++;
				while(A[j].getFitness() > pivot) j--;
			} else {
				while(A[i].getFitness() > pivot) i++;
				while(A[j].getFitness() < pivot) j--;
			}
			if(i >= j) return j;
			if(i < j) { // swap
				Agent temp = A[i];
				A[i] = A[j];
				A[j] = temp;
				i++;
				j--;
			}
		}
		return j;
	}
}