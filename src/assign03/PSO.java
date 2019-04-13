package assign03;
import java.util.Random;
/*	PSO
	Acts as a particle swarm optimization, and applies the vanilla PSO algorithm 
	to attempt to optimize the Rastrigin function  */
public class PSO {
	public static double InertiaWeight = 0;
	public static double SocialWeight = 0;
	public static double CognitiveWeight = 0;
	
	private Particle[] swarm;
	private BestParticle swarmBest;
	private int maxIterations;
	private int popSize;
	
	// Initializes the weight values for the PSO alongside the other variables 
	public PSO(int popSize, int maxIterations, double inertia, double social, double cognitive) {
		if(popSize <= 0 || maxIterations <= 0) {
			popSize = 25;
			maxIterations = 50;
		}
		swarm = new Particle[popSize];
		this.maxIterations = maxIterations;
		this.popSize = popSize;
		PSO.InertiaWeight = inertia;
		PSO.SocialWeight = social;
		PSO.CognitiveWeight = cognitive;
	}
	
	// Runs the PSO and returns the best particle found 
	public BestParticle Run(long seed) {
		Random rnd = new Random(seed);
		int currIteration = 0;
		// Initalize particles randomly
		for(int i = 0; i < popSize; i++) {
			swarm[i] = new Particle();
			swarm[i].Initialize(rnd);
		}
		
		// Set the starting swarm's best position
		swarmBest = new BestParticle(swarm[0].GetPosition(), swarm[0].GetFitness());
		
		// while iterations < max iterations OR stopping criteria not met DO
		while(currIteration < maxIterations && swarmBest.fitness != 0) {
			// evalute fitness + update personal best
			for(int i = 0; i < popSize; i++) {
				swarm[i].Evaluate();
			}
				
			// update neighbourhood best particle
			for(int i = 0; i < popSize; i++) {
				if(swarm[i].GetFitness() < swarmBest.fitness) {
					swarmBest = new BestParticle(swarm[i].GetPosition(), swarm[i].GetFitness());
					//System.out.println("Swarm best updated to: " + swarmBest.fitness + " Iteration: " + currIteration);
				}
			}
			
			// calculate new velocity + update position
			for(int i = 0; i < popSize; i++) {
				swarm[i].Update(rnd, swarmBest);
			}
			currIteration++;
		}
		// return global best particle
		return swarmBest;
	}
	
} // End of PSO
