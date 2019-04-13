package assign03;
import java.util.Random;
/*	RandomSearch
	Randomly samples positions within the search space to find the most optimal solution */
public class RandomSearch {
	private double[] position;
	private double fitness;
	
	private BestParticle bestPosition;
	private int maxIterations;
	
	// Initializes the random search with the number of iterations that will be performed
	public RandomSearch(int iterations) {
		if(iterations <= 0)
			iterations = 100;
		maxIterations = iterations;
	}
	
	// Runs the random search - will run for X iterations continuously looking at random samples in the search space
	public BestParticle Run(long seed) {
		Random rnd = new Random(seed);
		int currIteration = 0;
		// Init the best position to a null array and max double value
		position = new double[Particle.DIMENSIONS];
		fitness = Double.MAX_VALUE;
		bestPosition = new BestParticle(position, fitness);
		
		while(currIteration < maxIterations && fitness != 0) {
			// Select a new position
			for(int i = 0; i < Particle.DIMENSIONS; i++) {
				// Initialize random value between -MAX, +MAX
				position[i] = (Particle.MAX_VALUE*2)*rnd.nextDouble() - Particle.MAX_VALUE; // 10.24*double - 5.12 brings in into range [-5.12,5.12]
			}
			
			// Evaluate the position
			fitness = 0;
			double tmpSum = 0;
			for(int i = 0; i < Particle.DIMENSIONS; i++) {
				tmpSum += (position[i]*position[i]) - 10*Math.cos(2*Math.PI*position[i]);
			}
			fitness = 10*Particle.DIMENSIONS + tmpSum;
		
			// Update best position
			if(Math.abs(fitness) < Math.abs(bestPosition.fitness)){
				bestPosition = new BestParticle(position, fitness);
				//System.out.println("Random best updated to: " + bestPosition.fitness + " Iteration: " + currIteration);
			}
			currIteration++;
		}
		return bestPosition;
	}
} // End of RandomSearch
