package Affichers;

import java.util.List;

import tokenizers.OccurenceStat;

public class Afficheur implements dispaleyers {
	public void AfficherRsultat(List<OccurenceStat<String, Double>> result, int nombre) {
		int nombreDAffiches = Math.min(nombre, result.size());
		for (int i = 0; i < nombreDAffiches; i++) {
			OccurenceStat<String, Double> occurence = result.get(i);
			System.out.println("Path : " + occurence.getKey() + ", Score : " + occurence.getValue());
		}
		if (nombre > result.size()) {
			System.out.println("Seuls " + result.size() + " chemins trouvés.");
		}
	}
}
