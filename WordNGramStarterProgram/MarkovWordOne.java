
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}

	private int indexOf(String[] ar, String target, int s) {
		
		for (int k=s; k<ar.length; k++) {
			if (ar[k].equals(target)) {return k;}
		}
		return -1;
	}

	public void testIndexOf() {
		String t= "this is just a test yes this is a simple test";
		String[] t1 = t.split("\\s+");
		System.out.println(indexOf(t1, "test", 5));
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);
		//System.out.println(key+"  "+follows);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key) {
	    	ArrayList<String> follows = new ArrayList<String>();
		int pos = 0;
		while (pos < myText.length) {
			int start = indexOf(myText, key, pos);
			if (start == -1) {break;}
			if (start + key.length() >= myText.length) {break;}
			String next = myText[start+1];
			follows.add(next);
			pos = start+1;
		}
		return follows;
    }
/*
	public static void main (String[] args) {
        	MarkovWordOne pr = new MarkovWordOne();
        	pr.testIndexOf();
    	}*/
}
