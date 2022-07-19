
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}

	private int indexOf(String[] ar, String t1, String t2, int s) {
		
		for (int k=s; k<ar.length; k++) {
			if (ar[k].equals(t1) && ar[k+1].equals(t2)) {return k;}
		}
		return -1;
	}

	public void testIndexOf() {
		String t= "this is just a test yes this is a simple test";
		String[] t1 = t.split("\\s+");
		System.out.println(getFollows("this", "is"));
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-2);  // random word to start with
		String key1 = myText[index];
		String key2 = myText[index+1];
		sb.append(key1);
		sb.append(" ");
		sb.append(key2);
		sb.append(" ");
		for(int k=0; k < numWords-2; k++){
		    ArrayList<String> follows = getFollows(key1, key2);
		//System.out.println(key+"  "+follows);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			key1 = key2;
			key2 = next;
			sb.append(key2);
			sb.append(" ");
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key1, String key2) {
	    	ArrayList<String> follows = new ArrayList<String>();
		int pos = 0;
		while (pos < myText.length) {
			int start = indexOf(myText, key1, key2, pos);
			if (start == -1) {break;}
			//if (start + key.length() >= myText.length) {break;}
			String next = myText[start+2];
			follows.add(next);
			pos = start+2;
		}
		return follows;
    }
/*
	public static void main (String[] args) {
        	MarkovWordTwo pr = new MarkovWordTwo();
        	pr.testIndexOf();
    	}*/
}
