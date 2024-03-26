package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.List;

/**
 * Interface defining a pathfinding algorithm.
 * @param <E> Type des éléments du graphe.
 */
public interface AlgorithmeChemin<E> {
    /**
     * Find the shortest path between two nodes in a graph.
     * @param graphe it is the graph where the nodes are.
     * @param depart it is the node where the path starts.
     * @param arrivee it is the node where the path ends.
     * @return the shortest path between the two nodes.
     */
    List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee);

}