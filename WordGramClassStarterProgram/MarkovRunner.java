
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(5); 
        runModel(markovWord, st, 50, 844); 
    } 

    public void testHashMap() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString();
	//String st = "this is a test yes this is really a test"; 
        st = st.replace('\n', ' '); 
        EfficientMarkovWord markovWord = new EfficientMarkovWord(2); 
        runModel(markovWord, st, 50, 65); 
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

	public void compareMethods() {
		EfficientMarkovWord gm = new EfficientMarkovWord(2);
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 100;
		int seed = 42;
        	//MarkovZero mz = new MarkovZero();
		long s1 = System.nanoTime();
        	runModel(gm, st, size, seed);
		long t1 = System.nanoTime() - s1;
		MarkovWord gm1 = new MarkovWord(2);
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
        	MarkovRunner pr = new MarkovRunner();
        	pr.testHashMap();
    	}

}
