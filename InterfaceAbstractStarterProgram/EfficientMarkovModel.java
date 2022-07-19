
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */
import edu.duke.*;
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
	private int N;
	private HashMap<String, ArrayList<String>> hm;
	
	public EfficientMarkovModel(int n) {
		N = n;
		myRandom = new Random();
		hm = new HashMap<String, ArrayList<String>>();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}

	public String toString() {
		return "Efficient Markov Model of "+N+" number";
	}

	public void buildMap() {
		
		//int index = 0;
		
		
		for(int k=0; k < myText.length()-N+1; k++){
			String key = myText.substring(k, k+N);
			if (!hm.containsKey(key)) {
				ArrayList<String> follows = new ArrayList<String>();
				int pos = 0;
				while (pos < myText.length()) {
					int start = myText.indexOf(key, pos);
					if (start == -1) {break;}
					if (start + key.length() >= myText.length()) {break;}
					String next = myText.substring(start+key.length(), start+key.length()+1);
					follows.add(next);
					pos = start+key.length();
				}
				hm.put(key, follows);
			}
			//index += 1;
			
			//key = myText.substring(index, index+N);;
		}
		printHashMapInfo();
		
	}

	
	public String getRandomText(int numChars){
		buildMap();
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-N);
		String key = myText.substring(index, index+N);
		sb.append(key);
		for(int k=0; k < numChars-N; k++){
			ArrayList<String> follows = getFollows(key);
			if (follows.size() == 0){break;}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			key = key.substring(1) + next;
		}
		//printHashMapInfo();
		return sb.toString();
	}

	@Override
	protected ArrayList<String> getFollows(String key) {
		return hm.get(key);
	}

	public int getMax() {
		int ans = 0;
		for (String key : hm.keySet()) {
			if (hm.get(key).size()>ans) {
				ans = hm.get(key).size();
			}
		}
		return ans;
	}

	public void getMaxString(int ans) {
		for (String key : hm.keySet()) {
			if (hm.get(key).size() == ans) {
				System.out.println(key);
			}
		}
	}

	public void printHashMapInfo() {
		//System.out.println(hm);
		System.out.println(hm.size());
		System.out.println(getMax());
		getMaxString(getMax());
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
