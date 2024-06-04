package indexers;

import java.util.ArrayList;
import java.util.List;
import tokenizers.*;

public class IndexUsingList implements Indexers {
	private List<FullOccucranceStat<String, Double, String>> indexedList; // Déclaration de l'attribut indexedList

	public IndexUsingList() {
		this.indexedList = new ArrayList<>();
	}

	public void indexer(List<OccurenceStat<String, Double>> statistics, String path) {
		for (OccurenceStat<String, Double> pair : statistics) {
			String word = pair.getKey();
			Double frequency = pair.getValue();
			// Ajouter le chemin du fichier à la paire pour en faire un triplet
			FullOccucranceStat<String, Double, String> triplet = new FullOccucranceStat<>(word, frequency, path);
			indexedList.add(triplet);
		}
	}

	public void Supprimer(List<String> paths) {
		// Parcourir la liste indexedList
		List<FullOccucranceStat<String, Double, String>> indexcopy = new ArrayList<>(indexedList);
		for (FullOccucranceStat<String, Double, String> item : indexcopy) {
			// Vérifier si le chemin de l'élément est présent dans la liste paths
			if (paths.contains(item.getThird())) {
				// Supprimer l'élément de la liste indexedList
				indexedList.remove(item);
			}
		}
	}

	public List<FullOccucranceStat<String, Double, String>> getFileStatistics(List<String> requete) {

		List<FullOccucranceStat<String, Double, String>> listeStat = new ArrayList<>();
		for (String mot : requete) {
			for (FullOccucranceStat<String, Double, String> s : indexedList) {
				if (mot.equals(s.getFirst())) {
					listeStat.add(s);

				}
			}
		}
		return listeStat;
	}

	public void displayIndexContent() {
		System.out.println("Contenu de l'index :");
		for (FullOccucranceStat<String, Double, String> triplet : indexedList) {
			System.out.println(triplet.getFirst() + ", " + triplet.getSecond() + ", " + triplet.getThird());
		}
	}

}
