package cleaners;
import java.util.*;
public class Abreviation implements TextCleaner {
	  List<String> abrevationList = Arrays.asList("'d", "'s", "'t", "'m", "'ll", "'ve", "'re");

	    public List<String> cleanText(List<String> content) {
	        List<String> newContent = new ArrayList<>();
	        for (String line : content) {
	            String[] wordLine = line.split("\\s+");
	            StringBuilder cleanedLine = new StringBuilder();
	            for (String singleWord : wordLine) {
	                String cleanedWord = singleWord;
	                for (String abbreviation : abrevationList) {
	                    if (singleWord.endsWith(abbreviation)) {
	                        cleanedWord = singleWord.substring(0, singleWord.length() - abbreviation.length());
	                        break;
	                    }
	                }
	                cleanedLine.append(cleanedWord).append(" ");
	            }
	            newContent.add(cleanedLine.toString().trim());
	        }
	        return newContent;
	    }
}
