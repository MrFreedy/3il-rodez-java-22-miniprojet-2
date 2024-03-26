package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.elements.Tuile;

import java.util.ArrayList;
import java.util.List;
/**
 * Adapter class to use the pathfinding algorithms with the map.
 */
public class AdaptateurAlgorithme {
    /**
     * Find the shortest path between two points on the map.
     * @param algorithme it is the pathfinding algorithm to use.
     * @param carte it is the map where the path is.
     * @param xDepart it is the x coordinate of the starting point.
     * @param yDepart it is the y coordinate of the starting point.
     * @param xArrivee it is the x coordinate of the ending point.
     * @param yArrivee it is the y coordinate of the ending point.
     * @return the shortest path between the two points.
     */
    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee){
        Graphe<Case> graphe = creerGraphe(carte);

        Tuile tuileDepart = carte.getTuile(xDepart, yDepart);
        Case depart = new Case(tuileDepart, xDepart, yDepart);

        Tuile tuileArrivee = carte.getTuile(xArrivee, yArrivee);
        Case arrivee = new Case(tuileArrivee, xArrivee, yArrivee);

        List<Noeud<Case>> chemin = algorithme.trouverChemin(graphe, new Noeud<>(depart), new Noeud<>(arrivee));

        return afficherChemin(chemin);
    }
    /** Create a graph from a map.
     * @param carte it is the map where the nodes are.
     * @return the graph created.
     */
    static Graphe<Case> creerGraphe(Carte carte){
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();
        for(int x = 0; x < largeur; x++){
            for(int y = 0; y < hauteur; y++){
                Tuile tuileCourante = carte.getTuile(x, y);
                Case caseCourante = new Case(tuileCourante, x, y);
                graphe.ajouterNoeud(new Noeud<>(caseCourante));
                ajouterAretesVoisines(graphe, caseCourante, x, y, largeur, hauteur);
            }
        }
        return graphe;
    }
    /** Add edges to the graph for the neighbors of a node.
     * @param graphe it is the graph where the nodes are.
     * @param currentCase it is the node where the edges are added.
     * @param x it is the x coordinate of the node.
     * @param y it is the y coordinate of the node.
     * @param largeur it is the width of the map.
     * @param hauteur it is the height of the map.
     */
    static void ajouterAretesVoisines(Graphe<Case> graphe, Case currentCase, int x, int y, int largeur, int hauteur){

    }

    /**
     * Calculate the cost of an edge between two nodes.
     * @param from it is the starting node.
     * @param to it is the ending node.
     * @return the cost of the edge.
     */
    static double calculerCout(Case from, Case to){
        if(from == null || to == null)
            return Double.POSITIVE_INFINITY;
        return 1;
    }

    /**
     * Display the path found.
     * @param chemin it is the path found.
     * @return the path found.
     */
    static Chemin afficherChemin(List<Noeud<Case>> chemin){
        if (chemin.isEmpty()) {
            System.out.println("No path found!");
            return new Chemin(new ArrayList<>());
        }

        System.out.print("Path: ");
        List<Case> cheminCases = new ArrayList<>();
        for (Noeud<Case> noeud : chemin){
            Case caseNode = noeud.getValeur();
            cheminCases.add(caseNode);
            System.out.print(" -> " + caseNode.toString());
        }
        System.out.println();

        return new Chemin(cheminCases);
    }

}