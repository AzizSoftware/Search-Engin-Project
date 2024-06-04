package Scorer;

import java.util.*;
import tokenizers.*;

public class ScoreCalculatorOccV1 implements CalculateurDeScore {

    // calcluleer la somme des occurences
    public List<OccurenceStat<String, Double>> ScoreFileCalcul(
            List<FullOccucranceStat<String, Double, String>> Filescore, int size) {
        Map<String, Double> pathOccurrenceMap = new HashMap<>();

        // Calculate total occurrences for each path file
        for (FullOccucranceStat<String, Double, String> triplet : Filescore) {
            String path = triplet.getThird();
            double occurrence = triplet.getSecond();

            // Increment occurrence count for each path
            pathOccurrenceMap.put(path, pathOccurrenceMap.getOrDefault(path, 0.0) + occurrence);
        }

        // Calculate score for each path file
        List<OccurenceStat<String, Double>> result = new ArrayList<>();
        for (Map.Entry<String, Double> entry : pathOccurrenceMap.entrySet()) {
            String path = entry.getKey();
            double occurrenceSum = entry.getValue();
            double score = (double) occurrenceSum / size; // Divide by length of the request list

            // Add path and score pair to the result list
            result.add(new OccurenceStat<>(path, score));
        }
        return result;
    }

    // Méthode pour afficher les scores
    public void afficherScores(List<OccurenceStat<String, Double>> scores) {
        for (OccurenceStat<String, Double> score : scores) {
            System.out.println("Path : " + score.getKey() + ", Score : " + score.getValue());
        }
    }

}
