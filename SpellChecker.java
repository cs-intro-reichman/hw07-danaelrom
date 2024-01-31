
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		String lower1 = word1.toLowerCase();
		String lower2 = word2.toLowerCase();

		if(lower1.length() == 0){
			return lower2.length();
		}
		if(lower2.length() == 0){
			return lower1.length();
		}
		if(lower1.charAt(0) == lower2.charAt(0) ){
			return levenshtein(tail(lower1),tail(lower2));
		}
		int x = (int)(Math.min(levenshtein(tail(word1), word2), levenshtein(word1, tail(word2))));
		return 1 + (int)(Math.min(x,levenshtein(tail(word1), tail(word2))));  

	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		int i = 0;
		while(!in.isEmpty()){
			dictionary[i] = in.readLine();
			i++;
		}
		return dictionary;
	}



	

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int min = word.length();
		String minimal = word;
		for(int i = 0; i < dictionary.length; i++){
			int distance = levenshtein(word, dictionary[i]);
			if((distance < min) && (distance <= threshold)){
				min = distance;
				minimal = dictionary[i];
			}
		}
		return minimal;
	}
	

}
