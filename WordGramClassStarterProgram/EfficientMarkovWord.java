
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> hm;
    
    public EfficientMarkovWord(int x) {
	myOrder = x;
        myRandom = new Random();
	hm = new HashMap<WordGram, ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}

    private int indexOf(String[] words, WordGram target, int start) {
        ArrayList<WordGram> list = new ArrayList<WordGram>();
        for(int j = 0; j < words.length - myOrder; j++) {
            WordGram wg = new WordGram(words, j, myOrder);
            list.add(wg);
        }
        for(int i = start; i < words.length; i++) {
            if(i >= list.size()) {
                return -1;
            }
            if(target.equals(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

	/*public void testIndexOf() {
		String t= "this is just a test yes this is a simple test";
		String[] t1 = t.split("\\s+");
		System.out.println(indexOf(t1, "test", 5));
	}*/
	
	public String getRandomText(int numWords){
		buildMap();
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
		WordGram key = new WordGram(myText, index, myOrder);
		sb.append(key);
        	sb.append(" ");
		for(int k=0; k < numWords-myOrder; k++){
		    ArrayList<String> follows = getFollows(key);
		//System.out.println(key+"  "+follows);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = key.shiftAdd(next);
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(WordGram key) {
	    	return hm.get(key);
    }



	public void buildMap() {
		
		//int index = 0;
		
		
		for(int k=0; k < myText.length-myOrder+1; k++){
			WordGram key = new WordGram(myText, k, myOrder);
			if (!hm.containsKey(key.hashCode())) {
				ArrayList<String> follows = new ArrayList<String>();
				int pos = 0;
				while (pos < myText.length) {
					int start = indexOf(myText, key, pos);
					if (start == -1) {break;}
					//if (start + key.length() >= myText.length-myOrder) {break;}
					String next = myText[start+myOrder];
					follows.add(next);
					pos = start+myOrder;
				}
				hm.put(key, follows);
			}
			//index += 1;
			
			//key = myText.substring(index, index+N);;
		}
		printHashMapInfo();
		
	}

	public int getMax() {
		int ans = 0;
		for (WordGram key : hm.keySet()) {
			if (hm.get(key).size()>ans) {
				ans = hm.get(key).size();
			}
		}
		return ans;
	}

	public void getMaxString(int ans) {
		for (WordGram key : hm.keySet()) {
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
        	MarkovWord pr = new MarkovWord();
        	pr.testIndexOf();
    	}*/
}
