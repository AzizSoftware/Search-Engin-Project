package domain;

import indexers.*;
import readers.*;
import tokenizers.*;
import cleaners.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Trieur.*;
import Affichers.*;
import Scorer.*;
import analyzers.*;

public class MoteurDeRecherche {
	private DocumentReader lecteur;
	private List<TextCleaner> TextCleaners;
	private Indexers index;
	private Anlayzer analyseur;
	private CalculateurDeScore scoreCalculator;
	private Afficheur afficheur;
	private Historique historique;
	private ScoreSorter sorter;

	private List<String> indexedPaths = new ArrayList<>();

	public MoteurDeRecherche(
			DocumentReader lecteur, List<TextCleaner> textCleaners, Indexers index,
			Anlayzer analyseur,
			CalculateurDeScore scoreCalculator, Afficheur afficheur, Historique historique, ScoreSorter sorter) {
		this.lecteur = lecteur;
		TextCleaners = textCleaners;
		this.index = index;
		this.analyseur = analyseur;
		this.scoreCalculator = scoreCalculator;
		this.afficheur = afficheur;
		this.historique = historique;
		this.sorter = sorter;
	}

	public MoteurDeRecherche() {
		this.historique = new Historique();
		this.lecteur = new LecteurDocument();
		TextCleaners = new ArrayList<>();
		TextCleaners.add(new LowerCase());
		TextCleaners.add(new Ponctuation());
		TextCleaners.add(new EmptyWord());
		TextCleaners.add(new Numbers());
		TextCleaners.add(new Abreviation());
		this.index = new IndexUsingMaps();
		this.analyseur = new AnalyseurLineByLine();
		this.scoreCalculator = new ScoreCalculatorOccV1();
		this.sorter = new ScoreSorter();
		this.afficheur = new Afficheur();

	}

	public void SetHist(Historique historique) {
		this.historique = historique;

	}

	public void index(String path) {
		long startTime = System.nanoTime();
		File filePath = new File(path);
		if (filePath.exists()) {
			if (filePath.isDirectory()) {
				System.out.println(path + " is a directory");
				indexDirectory(filePath); // Passer l'objet File du répertoire
			} else {
				System.out.println(path + " is a file");
				indexSingleDocument(filePath.getAbsolutePath());
			}
		} else {
			System.out.println(path + " does not exist");
		}
		// After indexing is complete for all files and directories, display the index
		// content
		long endTime = System.nanoTime(); // Mesure du temps de fin d'indexation
		long elapsedTime = endTime - startTime;
		System.out.println("Temps d'indexation (nanosecondes) de l'indexation est  : " + elapsedTime);
		// index.displayIndexContent();
	}

	protected void indexSingleDocument(String path) {
		List<String> text = lecteur.lireDocument(path);
		if (!indexedPaths.contains(path)) {
			indexedPaths.add(path);
			for (TextCleaner Tc : TextCleaners) {
				text = Tc.cleanText(text);
			}
			List<OccurenceStat<String, Double>> list = analyseur.OccurenceCalculator(text);
			index.indexer(list, path); // Indexation du fichier
		} else {
			System.out.println("path already indexed");
		}
	}

	protected void indexDirectory(File directory) {
		File[] files = directory.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.isDirectory()) {
					indexDirectory(f); // Parcours récursif des sous-répertoires
				} else if (f.isFile() && f.getName().endsWith(".txt")) { // Vérifier si c'est un fichier texte
					indexSingleDocument(f.getAbsolutePath()); // Indexation du fichier
				}
			}
		}
	}

	public void chercherMots(String requete, Integer nombrePath) {
		long startTime = System.nanoTime();
		List<String> maRequete = Arrays.asList(requete.split("\\s+"));
		for (TextCleaner Tc : TextCleaners) {
			maRequete = Tc.cleanText(maRequete);
		}
		List<OccurenceStat<String, Double>> lScore = scoreCalculator.ScoreFileCalcul(index.getFileStatistics(maRequete),
				maRequete.size());
		long endTime = System.nanoTime(); // Mesure du temps de fin d'indexation
		long elapsedTime = endTime - startTime;
		historique.ajouterResultat(requete, lScore);
		lScore = sorter.sortByScoreDescending(lScore);
		afficheur.AfficherRsultat(lScore, nombrePath);
		System.out.println("Temps  de recherche (nanoseconde)  : " + elapsedTime);

	}

	public void afficherHistorique() {
		if (historique != null) {
			historique.afficherHistorique();
		} else {
			System.out.println("L'historique n'est pas initialisé.");
		}
	}

	public void modifierIndex(List<String> paths) {
		if (index != null) {
			index.Supprimer(paths);
		} else {
			System.out.println("L'index n'est pas initialisé.");
		}
	}

}
