package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.ArrayList;
import java.util.List;
/**
 * Noeud represent a node in a graph.
 * @param <E> Type of the value of the node.
 */
public class Noeud<E> {
    /**
     * Value of the node.
     */
    private E valeur;
    /**
     * List of the neighbors of the node.
     */
    private List<Noeud<E>> voisins;
    /**
     * Constructor of the class Noeud.
     * @param valeur it is the value of the node.
     */
    public Noeud(E valeur) {
        this.valeur = valeur;
        this.voisins = new ArrayList<>();
    }
    /**
     * Get the value of the node.
     * @return the value of the node.
     */
    public E getValeur() {
        return this.valeur;
    }
    /**
     * Get the neighbors of the node.
     * @return the neighbors of the node.
     */
    public List<Noeud<E>> getVoisins() {
        return this.voisins;
    }
    /**
     * Add a neighbor to the node.
     * @param voisin it is the neighbor to add.
     */
    public void ajouterVoisin(Noeud<E> voisin) {
        this.voisins.add(voisin);
    }
}