package analyzers;

//analyseur utilise lecteur word en utilisant liste des mots 
import java.util.ArrayList;
import java.util.List;
import tokenizers.*;

public class AnalyseurWordByWord implements Anlayzer {
	public List<OccurenceStat<String, Double>> OccurenceCalculator(List<String> content) {

		List<OccurenceStat<String, Double>> liste = new ArrayList<>();
		List<Integer> occurrences = new ArrayList<>();
		List<String> motsUniques = new ArrayList<>();
		for (String mot : content) {

			int index = motsUniques.indexOf(mot);
			if (index != -1) {
				occurrences.set(index, occurrences.get(index) + 1);
			} else {
				motsUniques.add(mot);
				occurrences.add(1);
			}
		}
		for (int i = 0; i < motsUniques.size(); i++) {
			OccurenceStat<String, Double> s = new OccurenceStat<String, Double>(motsUniques.get(i),
					(double) occurrences.get(i));
			liste.add(s);

		}
		return liste;
	}

}
