
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */
import edu.duke.*;
import java.util.*;

public class MarkovOne extends AbstractMarkovModel {
	
	public MarkovOne() {
		myRandom = new Random();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}

	
	public String getRandomText(int numChars){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-1);
		String key = myText.substring(index, index+1);
		sb.append(key);
		for(int k=0; k < numChars-1; k++){
			ArrayList<String> follows = getFollows(key);
			if (follows.size() == 0){break;}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			key = next;
		}
		
		return sb.toString();
	}
/*
	public static void main (String[] args) {
        	FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovOne markov = new MarkovOne();
		markov.setRandom(42);
		markov.setTraining(st);
        	System.out.println(markov.getFollows("t").size());
    	}
*/
}
