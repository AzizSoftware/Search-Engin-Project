package Scorer;

import tokenizers.FullOccucranceStat;
import tokenizers.OccurenceStat;
import java.util.*;

public class ScoreCalculatorWordInFile implements CalculateurDeScore {

	// nombre de mots de la requte dans le fichier
	public List<OccurenceStat<String, Double>> ScoreFileCalcul(
			List<FullOccucranceStat<String, Double, String>> Filescore, int size) {
		Map<String, Double> scores = new HashMap<>();

		for (FullOccucranceStat<String, Double, String> entry : Filescore) {
			String fichier = entry.getThird();
			if (scores.containsKey(fichier)) {
				scores.put(fichier, scores.get(fichier) + 1.0);
			} else {
				scores.put(fichier, 1.0);
			}
		}
		List<OccurenceStat<String, Double>> result = new ArrayList<>();
		for (Map.Entry<String, Double> scoreEntry : scores.entrySet()) {
			result.add(new OccurenceStat<>(scoreEntry.getKey(), scoreEntry.getValue()));
		}

		return result;
	}
}
