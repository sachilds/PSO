package assign03;
/*	Assign03
	Handles the experiment running and storing of data for Assignment 03  */
public class Assign03 {
	private int iterations = 600;
	private int swarmSize = 30;
	public Assign03() {}
	
	// Runs the Random Search and PSO with the designated parameter configurations 
	// Stores the output results in a CSV file
	public void RunExperiments() {
		int numExperiments = 7;
		long[] seeds = {52, 95, 13, 707, 29, 4, 164};
		double[] inertiaValues = {0.729844, 0.4, 1.0, -1.0};
		double[] termValues = {1.496180, 1.2, 2.0, 2.0};
		BestParticle bp;
		RandomSearch rs;
		PSO pso;
		
		FileHandler fileHandler = new FileHandler("files/RandomSearch.csv");
		System.out.println("[Beginning Random Search]");
		// Do random search and record values
		for(int i = 0; i < numExperiments; i++) {
			rs = new RandomSearch(iterations*swarmSize);
			bp = rs.Run(seeds[i]);
			Write(fileHandler, bp);
		}
		fileHandler.Done();
		
		// Run through each PSO experiement
		for(int i = 0; i < 4; i++) {
			fileHandler = new FileHandler("files/PSO-" + (i+1) + ".csv");
			System.out.println("[Beginning PSO " + (i+1)+ " Config.]");
			for(int j = 0; j < numExperiments; j++) {
				pso = new PSO(swarmSize, iterations, inertiaValues[i], termValues[i], termValues[i]);
				bp = pso.Run(seeds[j]);
				Write(fileHandler, bp);
			}
			fileHandler.Done();
		}
		
		System.out.println("[Experiments Complete]");
	}
	
	// Writes the BestParticle object result to the specified file
	private void Write(FileHandler fileHandler, BestParticle bp) {
		fileHandler.Write(bp.fitness + ", ");
		for(int j = 0; j < Particle.DIMENSIONS; j++) {
			if(j+1 >= Particle.DIMENSIONS)
				fileHandler.Write(bp.position[j] + "\n");
			else
				fileHandler.Write(bp.position[j] + ", ");
		}
		fileHandler.Flush();
	}
	
	// Main
	public static void main(String[] args) {
		Assign03 a3 = new Assign03();
		a3.RunExperiments();
	}
} // End of Assign03
