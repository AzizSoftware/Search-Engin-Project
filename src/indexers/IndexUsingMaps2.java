package indexers;

import java.util.*;
import tokenizers.*;

public class IndexUsingMaps2 implements Indexers {

    private List<FullOccucranceStat<String, Double, String>> indexedList; // Déclaration de l'attribut indexedList

    public IndexUsingMaps2() {
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

    public List<FullOccucranceStat<String, Double, String>> getFileStatistics(List<String> requete) {
        List<FullOccucranceStat<String, Double, String>> result = new ArrayList<>();

        // Créer une carte pour stocker les fréquences des mots de la requête
        Map<String, Integer> requeteFrequencyMap = new HashMap<>();
        for (String mot : requete) {
            requeteFrequencyMap.put(mot, requeteFrequencyMap.getOrDefault(mot, 0) + 1);
        }

        // Parcourir chaque triplet dans l'index
        for (FullOccucranceStat<String, Double, String> triplet : indexedList) {
            String motIndex = triplet.getFirst();
            // Vérifier si le mot du triplet existe dans la requête
            if (requeteFrequencyMap.containsKey(motIndex)) {
                // Ajouter le triplet correspondant au résultat en conservant la fréquence
                // d'occurrence
                result.add(triplet);
            }
        }

        // Ajouter les mots de la requête qui n'ont pas été trouvés dans l'index avec
        // une fréquence de 0
        for (Map.Entry<String, Integer> entry : requeteFrequencyMap.entrySet()) {
            String motRequete = entry.getKey();
            if (!result.stream().anyMatch(triplet -> triplet.getFirst().equals(motRequete))) {
                // Si le mot de la requête n'existe pas dans l'index, ajouter un triplet avec
                // une fréquence de 0
                result.add(new FullOccucranceStat<>(motRequete, 0.0, "Non trouvé"));
            }
        }
        System.out.println(result.size());
        return result;
    }

    public void Supprimer(List<String> paths) {
        // Utiliser un itérateur pour parcourir la liste et supprimer des éléments en
        // toute sécurité
        Iterator<FullOccucranceStat<String, Double, String>> iterator = indexedList.iterator();
        while (iterator.hasNext()) {
            FullOccucranceStat<String, Double, String> item = iterator.next();
            if (paths.contains(item.getThird())) {
                iterator.remove(); // Supprimer l'élément en utilisant l'itérateur
            }
        }
    }

    // Méthode pour afficher le contenu de l'index
    public void displayIndexContent() {
        System.out.println("Contenu de l'index :");
        for (FullOccucranceStat<String, Double, String> triplet : indexedList) {
            System.out.println(triplet.getFirst() + ", " + triplet.getSecond() + ", " + triplet.getThird());
        }
    }

}
