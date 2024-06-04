package Trieur;

import java.util.Collections;
import java.util.List;
import tokenizers.OccurenceStat;

public class ScoreSorter {

	private ComparateurScore comparateurScore;

	public ScoreSorter() {
		this.comparateurScore = new ComparateurScore();
	}

	// Méthode pour trier la liste de manière décroissante selon le score
	public List<OccurenceStat<String, Double>> sortByScoreDescending(List<OccurenceStat<String, Double>> scoreList) {
		// Trie la liste selon le score en utilisant le comparateur défini
		Collections.sort(scoreList, comparateurScore);

		return scoreList;
	}

	// Méthode pour obtenir une liste de paires [path, score] triées par ordre
	// décroissant du score
	public List<OccurenceStat<String, Double>> getSortedPathAndScoreListDescending(
			List<OccurenceStat<String, Double>> scoreList) {
		List<OccurenceStat<String, Double>> sortedList = sortByScoreDescending(scoreList);
		return sortedList;
	}
}
