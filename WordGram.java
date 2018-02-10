import java.lang.*;
public class WordGram implements Comparable<WordGram> {

	private int myHash;
	private String[] myWords;

	public WordGram(String[] words, int index, int size) {
		// complete this constructor
		myWords = new String[size];
		for (int i = index; i < index +size; i++) {
			myWords[i - index] = words[i];
		}
		myHash = 17;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		for (int i = 0; i < myWords.length; i++) {
			int charsum = 0;
			String word=myWords[i];
			for (int k=0;k<word.length();k++) {
				charsum += (int)word.charAt(k)*Math.pow(3, k);
			}
			hash += (int)charsum * Math.pow(3, i);
		}
		myHash = hash;
		return myHash;
	}

	@Override
	public String toString() {
		String s=String.join(" ", this.myWords);
		return s;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof WordGram)) {
			return false;
		}
		WordGram wg = (WordGram) other;
		for (int i=0;i<this.length();i++) {
			if (!wg.myWords[i].equals(this.myWords[i])) {return false;}
		}
		return true;
	}

	@Override
	public int compareTo(WordGram wg) {
		if (wg.myWords.length > this.length()) {
			return -1;
		} else if (wg.myWords.length < this.length()) {
			return 1;
		}
		for (int i = 0; i < wg.myWords.length; i++) {
			if (this.myWords[i].compareTo(wg.myWords[i]) < 0) {
				return -1;
			}
			if (this.myWords[i].compareTo(wg.myWords[i]) > 0) {
				return 1;
			}
		}
		return 0;
	}

	public int length() {
		return myWords.length;
	}

	public WordGram shiftAdd(String last) {
		String[] words = new String[this.length() + 1];
		for (int i = 0; i < this.length(); i++) {
			words[i] = this.myWords[i];
		}
		words[this.length()] = last;
		return new WordGram(words, 1, this.length());
	}
}
