
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
	markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		int seed = 45;
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

    }

	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}

	public void testHashMap() {
		EfficientMarkovModel gm = new EfficientMarkovModel(5);
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 500;
		int seed = 531;
        	//MarkovZero mz = new MarkovZero();
        	runModel(gm, st, size, seed);
	}

	public void compareMethods() {
		EfficientMarkovModel gm = new EfficientMarkovModel(2);
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 1000;
		int seed = 42;
        	//MarkovZero mz = new MarkovZero();
		long s1 = System.nanoTime();
        	runModel(gm, st, size, seed);
		long t1 = System.nanoTime() - s1;
		MarkovModel gm1 = new MarkovModel(2);
		//FileResource fr1 = new FileResource();
		//String st1 = fr1.asString();
		//st1 = st1.replace('\n', ' ');
		//int size = 50;
		//int seed = 42;
        	//MarkovZero mz = new MarkovZero();
		long s2 = System.nanoTime();
        	runModel(gm1, st, size, seed);
		long t2 = System.nanoTime() - s2;
		System.out.println("EMM took "+t1+" ns whereas MM took "+t2+" ns.");
	}

	public static void main (String[] args) {
        	MarkovRunnerWithInterface pr = new MarkovRunnerWithInterface();
        	pr.testHashMap();
    	}
	
}
