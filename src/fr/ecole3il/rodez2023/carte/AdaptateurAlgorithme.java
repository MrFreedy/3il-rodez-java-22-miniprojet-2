package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.elements.Carte;

import java.util.ArrayList;
import java.util.List;

/**
 * AdaptateurAlgorithme est une classe utilitaire qui permet d'adapter un algorithme de recherche de chemin
 * pour trouver un chemin entre deux cases sur une carte.
 */
public class AdaptateurAlgorithme{
    /**
     * Trouve un chemin entre deux cases sur une carte en utilisant un algorithme de recherche de chemin.
     * @param algorithme L'algorithme de recherche de chemin à utiliser.
     * @param carte La carte sur laquelle trouver le chemin.
     * @param xDepart La coordonnée x de la case de départ.
     * @param yDepart La coordonnée y de la case de départ.
     * @param xArrivee La coordonnée x de la case d'arrivée.
     * @param yArrivee La coordonnée y de la case d'arrivée.
     * @return Un objet Chemin représentant le chemin trouvé entre les deux cases.
     */
    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee){
        Graphe<Case> graphe = creerGraphe(carte);
        Noeud<Case> noeudDepart = graphe.getNoeud(xDepart, yDepart);
        Noeud<Case> noeudArrivee = graphe.getNoeud(xArrivee, yArrivee);
        List<Noeud<Case>> cheminNoeuds = algorithme.trouverChemin(graphe, noeudDepart, noeudArrivee);

        afficherChemin(cheminNoeuds);

        List<Case> cheminCases = new ArrayList<>();
        for (Noeud<Case> noeud : cheminNoeuds) {
            cheminCases.add(noeud.getValeur());
        }

        return new Chemin(cheminCases);
    }

    /**
     * Crée un graphe à partir d'une carte.
     * @param carte La carte à utiliser pour créer le graphe.
     * @return Un objet Graphe représentant la carte sous forme de graphe.
     */
    static Graphe<Case> creerGraphe(Carte carte){
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();

        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Case caseActuelle = new Case(carte.getTuile(x, y), x, y);
                graphe.ajouterNoeud(new Noeud<>(caseActuelle));
            }
        }

        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Case caseActuelle = new Case(carte.getTuile(x, y), x, y);
                ajouterAretesVoisines(graphe, caseActuelle, x, y, largeur, hauteur);
            }
        }

        return graphe;
    }

    /**
     * Ajoute des arêtes entre un nœud et ses nœuds voisins dans un graphe.
     * @param graphe Le graphe dans lequel ajouter les arêtes.
     * @param currentCase La case actuelle pour laquelle ajouter des arêtes.
     * @param x La coordonnée x de la case actuelle.
     * @param y La coordonnée y de la case actuelle.
     * @param largeur La largeur de la carte.
     * @param hauteur La hauteur de la carte.
     */
    static void ajouterAretesVoisines(Graphe<Case> graphe, Case currentCase, int x, int y, int largeur, int hauteur){
        // Get the current node from the graph
        Noeud<Case> currentNode = null;
        for (Noeud<Case> noeud : graphe.getNoeuds()) {
            Case c = noeud.getValeur();
            if (c.equals(currentCase)) {
                currentNode = noeud;
                break;
            }
        }

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            if (newX >= 0 && newX < largeur && newY >= 0 && newY < hauteur) {

                Noeud<Case> neighborNode = graphe.getNoeud(newX, newY);
                Case neighborCase = neighborNode.getValeur();

                double cost = calculerCout(currentCase, neighborCase);

                graphe.ajouterArete(currentNode, neighborNode, cost);

                assert currentNode != null;
                currentNode.ajouterVoisin(neighborNode);
            }
        }
    }
    /**
     * Calcule le coût entre deux cases.
     * @param from La case de départ.
     * @param to La case d'arrivée.
     * @return Le coût entre les deux cases.
     */
    static double calculerCout(Case from, Case to){
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }

    /**
     * Affiche un chemin sur la console en affichant les coordonnées et le type de tuile de chaque case.
     * @param chemin Le chemin à afficher.
     */
    static void afficherChemin(List<Noeud<Case>> chemin){
        for (Noeud<Case> noeud : chemin) {
            Case caseActuelle = noeud.getValeur();
            System.out.println("Case: x = " + caseActuelle.getX() + ", y = " + caseActuelle.getY()+", tuile = " + caseActuelle.getTuile());
        }
    }
}