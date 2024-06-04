package analyzers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tokenizers.*;

import java.util.ArrayList;
// % occ/taille 

public class AnalyseurOccDivLength implements Anlayzer {
    public List<OccurenceStat<String, Double>> OccurenceCalculator(List<String> content) {
        Map<String, Integer> occurences = new HashMap<>();

        int totalWords = 0;

        // Compter le nombre total de mots dans le texte
        for (String line : content) {
            String[] mots = line.split("\\s+");
            totalWords += mots.length;
            for (String mot : mots) {
                occurences.put(mot, occurences.getOrDefault(mot, 0) + 1);
            }
        }

        // Convertir les occurrences de mots en une liste de paires avec des fréquences
        // relatives
        List<OccurenceStat<String, Double>> statistiques = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : occurences.entrySet()) {
            Double frequence = (double) entry.getValue() / totalWords;
            statistiques.add(new OccurenceStat<>(entry.getKey(), frequence));
        }

        return statistiques;
    }
}
