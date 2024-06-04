package cleaners;

import java.util.List;
import java.util.ArrayList;

public class LowerCase implements TextCleaner {
	public List<String> cleanText(List<String> fileContent) {
		List<String> cleanedContent = new ArrayList<>();
		for (String line : fileContent) {
			cleanedContent.add(line.toLowerCase());
		}
		return cleanedContent;
	}

}
