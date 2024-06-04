package Scorer;

import java.util.ArrayList;
import java.util.List;
import tokenizers.*;

public class ScoreCalculatorOccV2 implements CalculateurDeScore {

	//// calcul de somme d'occurence

	public List<OccurenceStat<String, Double>> ScoreFileCalcul(
			List<FullOccucranceStat<String, Double, String>> Filescore, int size) {
		List<OccurenceStat<String, Double>> maliste = new ArrayList<>();
		List<String> paths = new ArrayList<>();
		List<Double> occurrences = new ArrayList<>();

		for (FullOccucranceStat<String, Double, String> s : Filescore) {
			int indice = paths.indexOf(s.getThird());
			if (indice == -1) {
				paths.add(s.getThird());
				occurrences.add(s.getSecond());
			} else {
				occurrences.set(paths.indexOf(s.getThird()), occurrences.get(indice) + s.getSecond());

			}
		}
		for (int i = 0; i < paths.size(); i++) {

			double score = (double) occurrences.get(i) / size;

			maliste.add(new OccurenceStat<>(paths.get(i), score));

		}
		return maliste;
	}

}
