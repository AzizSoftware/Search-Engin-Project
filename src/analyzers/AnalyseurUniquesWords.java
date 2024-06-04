package analyzers;

import java.util.*;

import tokenizers.OccurenceStat;

// existance de mot distinct
public class AnalyseurUniquesWords implements Anlayzer {
	public List<OccurenceStat<String, Double>> OccurenceCalculator(List<String> content) {
		Set<String> motsUniques = new HashSet<>();

		for (String line : content) {
			String[] mots = line.split("\\s+");
			// Ajouter mot à l'ensemble
			motsUniques.addAll(Arrays.asList(mots));
		}

		List<OccurenceStat<String, Double>> statistiques = new ArrayList<>();
		for (String mot : motsUniques) {
			statistiques.add(new OccurenceStat<>(mot, 1.0));
		}

		return statistiques;
	}
}
