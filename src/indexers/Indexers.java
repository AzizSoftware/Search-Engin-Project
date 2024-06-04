package indexers;

import java.util.List;

import tokenizers.FullOccucranceStat;
import tokenizers.OccurenceStat;

public interface Indexers {
	void indexer(List<OccurenceStat<String, Double>> statistics, String path);

	List<FullOccucranceStat<String, Double, String>> getFileStatistics(List<String> requete);

	void Supprimer(List<String> paths);

	void displayIndexContent();
}
