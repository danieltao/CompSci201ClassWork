import java.util.*;
public class EfficientWordMarkov extends WordMarkovModel{
	Map<WordGram,ArrayList<String>> myMap;
	public EfficientWordMarkov(int order) {
		super(order);
		myMap=new HashMap();
	}
	
	@Override
	public void setTraining(String text) {
		myWords = text.split("\\s+");
		myMap.clear();
		for (int i = 0; i <= myWords.length - myOrder; i++) {
			WordGram key=new WordGram(myWords,i,myOrder);
			ArrayList<String> follows=new ArrayList();
			if (i == myWords.length - myOrder) {
				if (myMap.containsKey(key)) {
					follows = myMap.get(key);
					follows.add(PSEUDO_EOS);
					myMap.put(key, follows);
				} else {
					follows.clear();
					follows.add(PSEUDO_EOS);
					myMap.put(key, follows);
				}
			} else {
				if (myMap.containsKey(key)) {
					follows = myMap.get(key);
					follows.add(myWords[i+myOrder]);
					myMap.put(key, follows);
				} else {
					follows.clear();
					follows.add(myWords[i+myOrder]);
					myMap.put(key, follows);
				}
			}
		}

	}

	public ArrayList<String> getFollows(WordGram key) {
		if (myMap.containsKey(key)) {
			return myMap.get(key);
		} else {
			throw new NoSuchElementException("Such key does not exist!\n");
		}
	}
}
