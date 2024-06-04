package Trieur;

import java.util.Comparator;

import tokenizers.OccurenceStat;

public class ComparateurScore implements Comparator<OccurenceStat<String, Double>> {

	@Override
	public int compare(OccurenceStat<String, Double> o1, OccurenceStat<String, Double> o2) {

		return Double.compare(o2.getValue(), o1.getValue());
	}

}
