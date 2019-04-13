package assign03;
import java.util.Random;
/*	Particle
	Is the representation of a particle object for the swarm of a PSO. Holds the position
	velocity and personal best information for the particle. Alongside functions used to 
	apply updates and evaluations for it. */
public class Particle {
	public static final int DIMENSIONS = 30; // Particle size
	public static final double MAX_VALUE = 5.12; // holds the range of the function - [-5.12, 5.12]
	
	private double[] position; 
	private double[] velocity;
	private BestParticle personalBest;
	private double fitness;
	
	// Initializes the default values
	public Particle() {
		position = new double[DIMENSIONS];
		velocity = new double[DIMENSIONS];
		personalBest = new BestParticle(new double[DIMENSIONS], Double.MAX_VALUE);
	}
	
	// Initializes the particle's position to random spots within search space
	public void Initialize(Random rnd) {
		for(int i = 0; i < DIMENSIONS; i++) {
			// Initialize random value between -MAX, +MAX
			position[i] = (MAX_VALUE*2)*rnd.nextDouble() - MAX_VALUE; // 10.24*double - 5.12 brings in into range [-5.12,5.12]
			// Init velocity to zero
			velocity[i] = 0;
		}
		fitness = Double.MAX_VALUE;
	}
	
	// Updates the velocity and position of the particle
	public void Update(Random rnd, BestParticle nbhBest) {
		// Update the velocity
		for(int i = 0; i < DIMENSIONS; i++) {
			//Velocity = Inertia + Cognitive + Social;
			velocity[i] = (PSO.InertiaWeight*velocity[i]) 
					+ PSO.CognitiveWeight*rnd.nextDouble()*(personalBest.position[i] - position[i])
					+ PSO.SocialWeight*rnd.nextDouble()*(nbhBest.position[i] - position[i]);
			// Update the position - NewPosition = OldPosition + velocity
			position[i] += velocity[i]; 
		}
		
	}
	
	// Evaluates the fitness of the particle and updates the personal best
	public void Evaluate() {
		fitness = 0;
		double tmpSum = 0;
		for(int i = 0; i < DIMENSIONS; i++) {
			tmpSum += (position[i]*position[i]) - 10*Math.cos(2*Math.PI*position[i]);
		}
		fitness = 10*DIMENSIONS + tmpSum; 
		
		// Update the personal best
		// If the absolute value of fitness < personal Best (closer to zero) then update
		if(Math.abs(personalBest.fitness) > Math.abs(fitness)) {
			personalBest = new BestParticle(position, fitness);
		}
	}
	
	public double GetFitness() { return fitness; }
	public double[] GetPosition() { return position; }
	
}// End of Particle
