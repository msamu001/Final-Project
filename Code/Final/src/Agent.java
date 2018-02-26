public class Agent {
	private boolean status;
	private EWG hypo;
	private double fitness;
	
	public Agent(EWG h) {
		status = false;
		hypo = genHypo(h);
	}
	
	public void setStatus(boolean s) {
		status = s;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setHypo(EWG h) {
		hypo = h;
	}
	
	public EWG getHypo() {
		return hypo;
	}
	
	public void setFitness(double f) {
		fitness = f;
	}
	
	public double getFitness() {
		return fitness;
	}
	
	public String toString() {
		return 	"Status: " + status + "\n" +
				"Fitness: " + String.format("%.2f", fitness*100) + "%" + "\n" +
				hypo.toString();
	}
	
	private EWG genHypo(EWG g) {
		return null;
	}
}
