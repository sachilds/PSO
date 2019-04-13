package assign03;
/*	Best Particle
	Designed like a struct, holds the position and fitness together. Is used 
	to retain the best values encountered so far. */
public class BestParticle {
	public double[] position;
	public double fitness;
	
	// Sets the position and fitness of the best particle obj
	public BestParticle(double[] pos, double fit) {
		fitness = fit;		
		position = new double[pos.length];
		for(int i = 0; i < pos.length; i++) {
			position[i] = pos[i];
		}
	}
} // End of BestParticle
