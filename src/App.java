import domain.*;
import indexers.*;
import readers.*;
import cleaners.*;
import analyzers.*;
import Scorer.*;
import Affichers.*;
import Trieur.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		DocumentReader lecteur = null;
		List<TextCleaner> cleaners = new ArrayList<>();
		Anlayzer analyseur = null;
		Indexers index = null;
		MoteurDeRecherche engin = null;
		CalculateurDeScore scoreCalculator = null;
		Afficheur afficheur = new Afficheur();
		Historique historique = new Historique();
		Scanner scanner = new Scanner(System.in);
		ScoreSorter sorter = new ScoreSorter();
		boolean quitter = false;
		Scanner newScan = new Scanner(System.in);
		boolean firstexit = false;
		boolean exit = false;

		// boolean quitterSousMenu = false;
		// String pathDirectory = "C:\\Users\\zouag\\OneDrive\\Bureau\\corpus";

		while (!quitter) {
			if (!firstexit) {
				System.out.println("Bienvenue dans le moteur de recherche. Voici le menu :");
				// Menu global pour choisir l'action
				System.out.println("Veuilliez choisir le moteur de recherche souhaiter ");
				System.out.println("1. Moteur de recherche normale (combinaison)");
				System.out.println("2. Moteur de recherche optimale (default case)");
				System.out.println("3. Quitter");
				int choixAction = scanner.nextInt();

				switch (choixAction) {
					case 1:
						// Menu pour choisir le type de lecteur
						System.out.println("Choisissez le type de lecteur :");
						System.out.println("4. LecteurDocument");
						System.out.println("5. LecteurDocumentWord");
						int choixLecteur = scanner.nextInt();
						if (choixLecteur == 4) {
							lecteur = new LecteurDocument();
						} else if (choixLecteur == 5) {
							lecteur = new LecteurDocumentWord();
						} else {
							System.out.println("Choix invalide pour le lecteur.");
							break;
						}

						// Menu pour choisir les cleaners
						System.out.println(
								"Choisissez les cleaners (sélectionnez plusieurs en appuyant sur Entrée, 0 pour terminer) :");
						System.out.println("6. Abreviation");
						System.out.println("7. EmptyWord");
						System.out.println("8. LowerCase");
						System.out.println("9. Numbers");
						System.out.println("10. Ponctuation");
						System.out.println("11. Terminer la sélection");

						int choixCleaner;
						int cleanersChoisis = 0;
						while (true) {
							choixCleaner = scanner.nextInt();
							switch (choixCleaner) {
								case 6:
									cleaners.add(new Abreviation());
									cleanersChoisis++;
									break;
								case 7:
									cleaners.add(new EmptyWord());
									cleanersChoisis++;
									break;
								case 8:
									cleaners.add(new LowerCase());
									cleanersChoisis++;
									break;
								case 9:
									cleaners.add(new Numbers());
									cleanersChoisis++;
									break;
								case 10:
									cleaners.add(new Ponctuation());
									cleanersChoisis++;
									break;
								case 11:
									break;
								default:
									System.out.println("Choix invalide pour le cleaner.");
									break;
							}
							if (choixCleaner == 11 || cleanersChoisis == 5) {
								break;
							}
						}

						// Menu pour choisir le type d'analyseur
						System.out.println("Choisissez le type d'analyseur :");
						System.out.println("12. AnalyseurLineByLine");
						System.out.println("13. AnalyseurWordByWord");
						System.out.println("14. AnalyseurUniquesWords");
						System.out.println("15. AnalyseurOccDivLength");
						int choixAnalyseur = scanner.nextInt();
						switch (choixAnalyseur) {
							case 12:
								analyseur = new AnalyseurLineByLine();
								break;
							case 13:
								analyseur = new AnalyseurWordByWord();
								break;
							case 14:
								analyseur = new AnalyseurUniquesWords();
								break;
							case 15:
								// analyseur = new AnalyseurOccDivLength();
								break;
							default:
								System.out.println("Choix invalide pour l'analyseur.");
								break;
						}

						// Menu pour choisir le type d'index
						System.out.println("Choisissez le type d'index :");
						System.out.println("16. IndexUsingList");
						System.out.println("17. IndexUsingMaps");
						System.out.println("18. IndexUsingMaps2");
						int choixIndex = scanner.nextInt();
						switch (choixIndex) {
							case 16:
								index = new IndexUsingList();
								break;
							case 17:
								index = new IndexUsingMaps();
								break;
							case 18:
								index = new IndexUsingMaps2();
								break;
							default:
								System.out.println("Choix invalide pour l'index.");
								break;
						}

						// Menu pour choisir le calculateur de score
						System.out.println("Choisissez le calculateur de score :");
						System.out.println("19. CalculateurDeScoreOccPourcentage");
						System.out.println("20. ScoreCalculatorV1");
						System.out.println("21. ScoreCalculatorV2");
						System.out.println("22. ScoreCalculatorWordInFile");
						int choixScoreCalculator = scanner.nextInt();
						switch (choixScoreCalculator) {
							case 19:
								scoreCalculator = new CalculateurDeScoreOccPourcentage();
								break;
							case 20:
								scoreCalculator = new ScoreCalculatorOccV1();
								break;
							case 21:
								scoreCalculator = new ScoreCalculatorOccV2();
								break;
							case 22:
								scoreCalculator = new ScoreCalculatorWordInFile();
								break;
							default:
								System.out.println("Choix invalide pour le calculateur de score.");
								break;
						}
						engin = new MoteurDeRecherche(lecteur, cleaners, index, analyseur, scoreCalculator, afficheur,
								historique, sorter);

						firstexit = true;
						break;

					case 2:
						engin = new MoteurDeRecherche();
						firstexit = true;
						break;

					case 3:
						quitter = true;
						break;

					default:
						System.out.println("Choix invalide.");
						break;
				}

			} else {
				do {
					System.out.println("Que voulez-vous faire maintenant ?");
					System.out.println("4. Faire l'indexation de votre ensemble de fichiers ");
					System.out.println("0. Quitter");
					int choixAct = newScan.nextInt();
					switch (choixAct) {
						case 0:
							quitter = true;
							break;
						case 4:
							System.out.println("veuillez donner le path de votre grande repartoire ");
							scanner.nextLine();
							String pathDirectory = scanner.nextLine();
							engin.index(pathDirectory);
							boolean quitterSousMenu = false;

							while (!quitterSousMenu) {
								System.out.println("voulez vous quitter ou faire la recherche ?");
								System.out.println("veuilliez choisir votre choix s'il vous plait:");
								System.out.println("0. quitter ce menu");
								System.out.println("1. faire la recherche ");
								System.out.println("2. Accéder à l'historique de recherche");
								System.out.println("3. Déclarer les fichiers modifiés ou supprimer");
								int choice = scanner.nextInt();
								switch (choice) {
									case 0:
										quitterSousMenu = true;
										break;
									case 1:
										System.out.println("Entrez la requête : ");
										scanner.nextLine(); // Vider la ligne en attente
										String maRequete = scanner.nextLine();
										System.out.println("Donner le nombre de paths à afficher :");
										Integer nombrePaths = Integer.parseInt(scanner.nextLine());
										engin.chercherMots(maRequete, nombrePaths);
										break;
									case 2:
										engin.afficherHistorique();
										break;
									case 3:
										System.out.println("Vous avez supprimé ou modifié un ou plusieurs fichiers");
										System.out.println("1. Modifier un/plusieurs fichiers");
										System.out.println("2. Supprimer un/plusieurs fichiers ");
										System.out.println("0. Aucune modification");
										System.out.println("Veuillez choisir une des options :");
										int choix = newScan.nextInt();
										switch (choix) {
											case 1:
												// Modifier les fichiers
												List<String> listeOfPaths = new ArrayList<>();
												System.out.println(
														"Veuillez donner les chemins modifiés (séparés par des virgules) :");
												String inputPaths = scanner.next(); // Lire l'entrée de l'utilisateur
												String[] pathsArray = inputPaths.split(",");
												listeOfPaths.addAll(Arrays.asList(pathsArray));
												engin.modifierIndex(listeOfPaths);
												for (String path : listeOfPaths) {

													engin.index(path);
												}
												break;
											case 2:
												List<String> listeOfPaths2 = new ArrayList<>();
												System.out.println(
														"Veuillez donner les chemins modifiés (séparés par des virgules) :");
												String inputPaths2 = scanner.next();
												String[] pathsArray2 = inputPaths2.split(",");
												engin.modifierIndex(listeOfPaths2);

												break;
											case 0:
												exit = true;
												break;
											default:
												System.out.println("Choix invalide.");
												break;
										}
										break;
									default:
										System.out.println("choix invalide ");
										break;
								}

							}

						default:
							System.out.println("Choix invalide.");
							break;
					}
				} while (!quitter);

			}

		}

	}
}
