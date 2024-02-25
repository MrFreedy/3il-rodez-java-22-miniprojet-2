package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.*;

public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Noeud<E>> precedent = new HashMap<>();
        Map<Noeud<E>, Double> distance = new HashMap<>();
        PriorityQueue<Noeud<E>> queue = new PriorityQueue<>(Comparator.comparingDouble(distance::get));

        for (Noeud<E> node : graphe.getNoeuds()) {
            distance.put(node, Double.POSITIVE_INFINITY);
            precedent.put(node, null);
        }
        distance.put(depart, 0.0);
        queue.add(depart);

        while (!queue.isEmpty()) {
            Noeud<E> current = queue.poll();

            if (current.equals(arrivee))
                break;

            for (Noeud<E> voisin : graphe.getVoisins(current)) {
                double newDistance = distance.get(current) + graphe.getCoutArete(current, voisin);
                if (newDistance < distance.get(voisin)) {
                    queue.remove(voisin);
                    distance.put(voisin, newDistance);
                    precedent.put(voisin, current);
                    queue.add(voisin);
                }
            }
        }

        LinkedList<Noeud<E>> chemin = new LinkedList<>();
        Noeud<E> current = arrivee;
        while (current != null) {
            chemin.addFirst(current);
            current = precedent.get(current);
        }
        if (chemin.getFirst().equals(depart))
            return chemin;
        else
            return new LinkedList<>();  // No path found
    }
}