package cleaners;

import java.util.*;

public class EmptyWord implements TextCleaner {

	List<String> motsVides = Arrays.asList(
			"a", "about", "above", "after", "again", "against", "all", "am", "an", "and",
			"any", "are", "aren't", "as", "at", "be", "because", "been", "before", "being",
			"below", "between", "both", "but", "by", "can", "cannot", "can", "could", "couldn",
			"did", "didn", "do", "does", "doesn", "doing", "don", "down", "during", "each",
			"few", "for", "from", "further", "had", "hadn", "has", "hasn", "have", "haven",
			"having", "he", "her", "here", "here", "hers", "herself",
			"him", "himself", "his", "how", "i", "if", "in",
			"into", "is", "isn", "it", "its", "itself", "let", "me", "more", "most",
			"mustn", "my", "myself", "no", "nor", "not", "of", "off", "on", "once", "only", "or",
			"other", "ought", "our", "ours", "ourselves", "out", "over", "own", "same", "shan",
			"she", "should", "shouldn't", "so", "some", "such", "than",
			"that", "that's", "the", "their", "theirs", "them", "themselves", "then", "there",
			"these", "they", "this", "those",
			"through", "to", "too", "under", "until", "up", "very", "was", "wasn", "we",
			"we'll", "we're", "we've", "were", "weren't", "what", "what's", "when", "when's", "where",
			"which", "while", "who", "whom", "why", "with", "won",
			"would", "wouldn", "you", "your", "yours", "yourself", "yourselves");

	public List<String> cleanText(List<String> content) {
		List<String> newcontent = new ArrayList<>();
		for (String line : content) {
			String[] mots = line.split("\\s+");
			StringBuilder cleanedLine = new StringBuilder();
			for (String mot : mots) {
				if (!motsVides.contains(mot.toLowerCase())) {// pour verifier si le mots rst vide ou non
					cleanedLine.append(mot).append(" ");// pour assuer l'espace entre les mots
				}
			}
			if (cleanedLine.length() > 0) {
				cleanedLine.setLength(cleanedLine.length() - 1); // Supprimer le dernier espace
				newcontent.add(cleanedLine.toString());
			}
		}
		return newcontent;
	}
}
