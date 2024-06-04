package cleaners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ponctuation implements TextCleaner {
    List<String> punctuationSymbols = Arrays.asList(
            ".", ",", ":", ";", "!", "?", "'", "\"", "-", "(", ")", "[", "]", "{", "}", "<", ">", "/", "\\",
            "|", "&", "@", "#", "$", "%", "^", "*", "_", "+", "=", "~");

    public List<String> cleanText(List<String> content) {
        List<String> newContent = new ArrayList<>();

        for (String line : content) {
            String[] words = line.split("\\s+");
            StringBuilder newLine = new StringBuilder();

            for (String singleWord : words) {
                if (!containsPunctuation(singleWord)) {
                    newLine.append(singleWord).append(" ");
                }
            }
            if (newLine.length() > 0) {
                newLine.setLength(newLine.length() - 1); // Supprimer le dernier espace
                newContent.add(newLine.toString());
            }
        }
        return newContent;
    }

    private boolean containsPunctuation(String word) {
        for (String symbol : punctuationSymbols) {
            if (word.contains(symbol)) {
                return true;
            }
        }
        return false;
    }
}