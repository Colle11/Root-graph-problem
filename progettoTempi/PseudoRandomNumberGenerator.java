
public class PseudoRandomNumberGenerator {
	
	// class attributes
	private static final int a = 16807;
	private static final int m = 2147483647;
	private static final int q = 127773;
	private static final int r = 2836;
	
	// instance attributes
	private double seed; 
	
	// PseudoRandomNumberGenerator constructor
	public PseudoRandomNumberGenerator(double s){
		seed = s;
	}
	
	// method to get a pseudo-random number
	public double getRanNum(){
		double hi = Math.ceil(seed/q);
		double lo = seed - q * hi;
		double test = a * lo - r * hi;
		
		if(test < 0.0){
			seed = test + m;
		}else{
			seed = test;
		}
		
		double res = seed/m;
		res = Math.max(res, 0.0);
		return res;
	}
	
	// method to get seed
	public double getSeed(){
		return seed;
	}
	
	// method to set seed
	public void setSeed(double sd){
		seed = sd;
	}
	
}
