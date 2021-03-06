import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Random;

public class Global {	
	public static void main(String[] args) {
		PrintWriter output = null;
		double mst, maxST;
		
		for(int i = 0; i < 50; i++) {
			String fileName = "MST 50 ";
			String r = "";	// Results
			String s;	// general purpose
			
			EWG graph = genGraph(10);
			Kruskal test1 = new Kruskal(graph);
			SDS test2 = new SDS(graph,200,500,30);
			
			// Automated file creation
			fileName += String.valueOf(i+1);
			try {
				output = new PrintWriter(new FileWriter(fileName, false));
			} catch(IOException e) {
				System.err.print(e.toString());
			}
			
			// Print SDS results
			for(int j = 0; j < 3; j++) {
				test2.run(j);
				r += "Algorithm " + String.valueOf(j+1) + "\t";
				for(double[] d: test2.getResults()) {
					for(double v: d) {
						s = String.valueOf(v);
						// 4 decimal places
						if(s.length() - s.indexOf('.') > 4)	s = s.substring(0, s.indexOf('.')+5);
						r += s + "\t";
					}
					r += "\t";
				}
				r += "\n";
			}
			
			// Calculate fitness
			r += "Krushkal" + "\t";		
			mst = (test1.mst().weight() * 100) / graph.weight();
			maxST = (test1.maxST().weight() * 100) / graph.weight();
			
			// Print kruskal results
			for(int j = 0; j < 2; j++) {
				if(j == 0) s = String.valueOf(mst);
				else s = String.valueOf(maxST);
				if(s.length() - s.indexOf('.') > 4)	s = s.substring(0, s.indexOf('.')+5); // 4 decimal places
				r += s + "\t\t";
			}
			
			output.print(r);
			output.close();
			System.out.println(r + "\n");
		}
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
				double weight = (double)rand.nextInt(91) + 10;
				if(!(i == j)) cGraph.addEdge(v1,v2,weight);
			}
		}
		return cGraph;
	}
}