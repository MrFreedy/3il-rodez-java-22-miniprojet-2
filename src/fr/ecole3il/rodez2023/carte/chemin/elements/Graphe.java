package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.*;
/**
 * Graphe represent the graph where the nodes are.
 * @param <E> Type of the elements of the graph.
 */
public class Graphe<E> {
    /**
     * Map of the graph where the key is a node and the value is a map of the neighbors of the node and the cost of the edge.
     */
    private Map<Noeud<E>, Map<Noeud<E>, Double>> graphe;

    /**
     * Constructor of the class Graphe.
     */
    public Graphe() {
        this.graphe = new HashMap<>();
    }
    /**
     * Add a node to the graph.
     * @param noeud it is the node to add.
     */
    public void ajouterNoeud(Noeud<E> noeud) {
        this.graphe.putIfAbsent(noeud, new HashMap<>());
    }
    /**
     * Add an edge to the graph.
     * @param depart it is the node where the edge starts.
     * @param arrivee it is the node where the edge ends.
     * @param cout it is the cost of the edge.
     */
    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        ajouterNoeud(depart);
        ajouterNoeud(arrivee);
        this.graphe.get(depart).put(arrivee, cout);
    }
    /**
     * Get the cost of an edge.
     * @param depart it is the node where the edge starts.
     * @param arrivee it is the node where the edge ends.
     * @return the cost of the edge.
     */
    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        return this.graphe.getOrDefault(depart, new HashMap<>()).getOrDefault(arrivee, -1.0);
    }
    /**
     * Get the nodes of the graph.
     * @return the nodes of the graph.
     */
    public List<Noeud<E>> getNoeuds() {
        return new ArrayList<>(this.graphe.keySet());
    }
    /**
     * Get the neighbors of a node.
     * @param noeud it is the node.
     * @return the neighbors of the node.
     */
    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        if (!this.graphe.containsKey(noeud))
            return new ArrayList<>();
        return new ArrayList<>(this.graphe.get(noeud).keySet());
    }
}