package analyzers;

import tokenizers.*;
import java.util.List;

public interface Anlayzer {
	List<OccurenceStat<String, Double>> OccurenceCalculator(List<String> content);

}
