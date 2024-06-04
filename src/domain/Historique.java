package domain;

import java.util.ArrayList;
import java.util.List;

import tokenizers.OccurenceStat;

public class Historique {
    private List<OccurenceStat<String, List<OccurenceStat<String, Double>>>> historique;

    public Historique() {
        this.historique = new ArrayList<>();
    }

    public void ajouterResultat(String requete, List<OccurenceStat<String, Double>> resultatRecherche) {
        OccurenceStat<String, List<OccurenceStat<String, Double>>> resultat = new OccurenceStat<>(requete,
                resultatRecherche);
        historique.add(resultat);
    }

    public List<OccurenceStat<String, List<OccurenceStat<String, Double>>>> getHistorique() {
        return historique;
    }

    public void afficherHistorique() {
        System.out.println("Historique content ");
        for (OccurenceStat<String, List<OccurenceStat<String, Double>>> resultat : historique) {
            System.out.println("Requete : " + resultat.getKey());
            System.out.println("Resultats de la recherche : ");
            List<OccurenceStat<String, Double>> resultList = resultat.getValue();
            for (OccurenceStat<String, Double> occurence : resultList) {
                System.out.println("  Path : " + occurence.getKey());
                System.out.println("  Score : " + occurence.getValue());
            }
            System.out.println(); // Ajouter une ligne vide pour séparer les résultats
        }
    }

}
