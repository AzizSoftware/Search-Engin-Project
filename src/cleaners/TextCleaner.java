package cleaners;

import java.util.List;

public interface TextCleaner {
	public abstract List<String> cleanText(List<String> fileContent);
}
