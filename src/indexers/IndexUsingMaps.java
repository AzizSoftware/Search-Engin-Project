package indexers;

import java.util.*;

import tokenizers.*;

public class IndexUsingMaps implements Indexers {

	private Map<String, List<FullOccucranceStat<String, Double, String>>> indexedMap; // Déclaration de l'index

	public IndexUsingMaps() {
		this.indexedMap = new HashMap<>();
	}

	public void indexer(List<OccurenceStat<String, Double>> statistics, String path) {
		for (OccurenceStat<String, Double> pair : statistics) {

			String word = pair.getKey();
			Double frequency = pair.getValue();
			FullOccucranceStat<String, Double, String> triplet = new FullOccucranceStat<>(word, frequency, path);
			if (!indexedMap.containsKey(word)) {
				indexedMap.put(word, new ArrayList<>());
			}
			indexedMap.get(word).add(triplet);
		}
	}

	public void Supprimer(List<String> paths) {
		// Créer une copie de l'indexedMap pour éviter la modification concurrente
		Map<String, List<FullOccucranceStat<String, Double, String>>> copyMap = new HashMap<>(indexedMap);
		// Parcourir la copie de l'indexedMap pour supprimer les clés nécessaires
		for (String key : new ArrayList<>(copyMap.keySet())) {
			if (paths.contains(copyMap.get(key).get(copyMap.get(key).size() - 1).getThird())) {
				indexedMap.remove(key);
			}
		}
	}

	public List<FullOccucranceStat<String, Double, String>> getFileStatistics(List<String> requete) {
		List<FullOccucranceStat<String, Double, String>> result = new ArrayList<>();
		for (String mot : requete) {
			if (indexedMap.containsKey(mot)) {
				result.addAll(indexedMap.get(mot));
			}
		}
		return result;
	}

	public void displayIndexContent() {
		System.out.println("Contenu de l'index :");
		for (Map.Entry<String, List<FullOccucranceStat<String, Double, String>>> entry : indexedMap.entrySet()) {
			for (FullOccucranceStat<String, Double, String> triplet : entry.getValue()) {
				System.out.println(triplet.getFirst() + ", " + triplet.getSecond() + ", " + triplet.getThird());
			}
		}
	}

}
