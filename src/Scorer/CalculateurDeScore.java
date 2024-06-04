package Scorer;

import java.util.List;

import tokenizers.FullOccucranceStat;
import tokenizers.OccurenceStat;

public interface CalculateurDeScore {
	public List<OccurenceStat<String, Double>> ScoreFileCalcul(
			List<FullOccucranceStat<String, Double, String>> Filescore, int size);

}
