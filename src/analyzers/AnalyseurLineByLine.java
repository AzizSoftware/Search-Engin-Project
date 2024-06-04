package analyzers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import tokenizers.*;

// line by line 
public class AnalyseurLineByLine implements Anlayzer {

    public List<OccurenceStat<String, Double>> OccurenceCalculator(List<String> content) {
        Map<String, Integer> occurences = new HashMap<>();

        for (String line : content) {
            String[] mots = line.split("\\s+");
            for (String mot : mots) {
                occurences.put(mot, occurences.getOrDefault(mot, 0) + 1);
            }
        }

        // Convertir les occurrences de mots en une liste de paires
        List<OccurenceStat<String, Double>> statistiques = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : occurences.entrySet()) {
            statistiques.add(new OccurenceStat<>(entry.getKey(), (double) entry.getValue()));
        }

        return statistiques;
    }
}
