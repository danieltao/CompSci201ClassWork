import java.util.*;

public class EfficientMarkov extends MarkovModel {
	Map<String, ArrayList<String>> myMap;

	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap();
	}

	public void setTraining(String text) {
		myText = text;
		myMap.clear();

		for (int i = 0; i <= text.length() - myOrder; i++) {
			String key = text.substring(i, i + myOrder);
			ArrayList<String> follows = new ArrayList();
			if (i == text.length() - myOrder) {
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
					follows.add(text.substring(i + myOrder, i + myOrder + 1));
					myMap.put(key, follows);
				} else {
					follows.clear();
					follows.add(text.substring(i + myOrder, i + myOrder + 1));
					myMap.put(key, follows);
				}
			}
		}

	}

	public ArrayList<String> getFollows(String key) {
		if (myMap.containsKey(key)) {
			return myMap.get(key);
		} else {
			throw new NoSuchElementException("Such key does not exist!\n");
		}
	}
}
