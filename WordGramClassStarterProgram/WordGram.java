
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        // TODO: Complete this method
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        // TODO: Complete this method
	for (int k=0; k<myWords.length; k++){
		ret += myWords[k] + " ";
	}
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
	if (this.length() != other.length()) {return false;}
	for (int k=0; k<myWords.length; k++) {
		if (! myWords[k].equals(other.wordAt(k))) {
			return false;
		}
	}
        return true;

    }

    public WordGram shiftAdd(String word) {
	WordGram out = new WordGram(myWords, 0, myWords.length);
        String[] shiftedWords = myWords;
        for(int i = 0; i < myWords.length; i++) {
            if(i + 1 == myWords.length) {
                shiftedWords[i] = word;
            } else {
                shiftedWords[i] = out.wordAt(i + 1);
            }
            out = new WordGram(shiftedWords, 0, shiftedWords.length);
        }
        return out;
    }

	public int hashCode() {
		return toString().hashCode();
	}

}
