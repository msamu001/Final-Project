import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Random;

public class Global {
	
	public static void main(String[] args) {
		PrintWriter output = null;
		String fileName = "Results";
		String r = "";
		EWG graph = genGraph(10);
		SDS test = new SDS(graph,200,30,20);
		
		try {
			output = new PrintWriter(new FileWriter(fileName, false));
		} catch(IOException e) {
			System.err.print(e.toString());
		}
		
		for(int i = 0; i < 3; i++) {
			test.run(i);
			r += "Algorithm " + String.valueOf(i+1) + "\n";
			for(double[] d: test.getResults()) {
				for(double v: d) {
					String s = String.valueOf(v);
					// 4 decimal places
					if(s.length() - s.indexOf('.') > 4)	s = s.substring(0, s.indexOf('.')+5);
					r += s + "\t";
				}
				r += "\n";
			}
			r += "\n";
		}
		output.print(r);
		output.close();
		System.out.println(r);
	}
	
	public static EWG genGraph(int nodeNum) {
		Random rand = new Random(); 
		EWG cGraph = new EWG();
		
		// Creates vertices
		for(int i = 0; i < nodeNum; i++) {
			String s = String.valueOf(i);
			cGraph.addVertex(s);
		}
		
		// Adds edges to create a complete graph
		for(int i = 0; i < nodeNum; i++) {
			String s1 = String.valueOf(i);
			Vertex v1 = cGraph.getVertex(s1);
			for(int j = 0; j < nodeNum; j++) {
				String s2 = String.valueOf(j);
				Vertex v2 = cGraph.getVertex(s2);
				double weight = (double)rand.nextInt(90) + 10;
				if(!(i == j)) cGraph.addEdge(v1,v2,weight);
			}
		}
		return cGraph;
	}
}