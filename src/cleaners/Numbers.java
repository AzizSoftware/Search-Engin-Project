package cleaners;

import java.util.*;

public class Numbers implements TextCleaner {
    public List<String> cleanText(List<String> content) {
        List<String> newContent = new ArrayList<>();

        for (String line : content) {
            String[] lineWords = line.split("\\s+");
            StringBuilder cleanedLine = new StringBuilder();
            for (String word : lineWords) {
                if (!word.matches(".*\\d.*")) { // Vérifie si le mot ne contient pas de chiffre
                    cleanedLine.append(word).append(" ");
                }
            }
            if (cleanedLine.length() > 0) {
                cleanedLine.setLength(cleanedLine.length() - 1); // Supprimer le dernier espace
                newContent.add(cleanedLine.toString());
            }
        }
        return newContent;
    }
}
