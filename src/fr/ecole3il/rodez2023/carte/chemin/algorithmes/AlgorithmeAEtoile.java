package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.*;

public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {

    // Heuristic function (Estimated cost from node n to the goal node)
    protected double h(Noeud<E> n, Noeud<E> goal) {
        // Implement the heuristic function here
        return 0;
    }

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Double> g = new HashMap<>();
        Map<Noeud<E>, Double> f = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> precedent = new HashMap<>();
        Set<Noeud<E>> explored = new HashSet<>();
        PriorityQueue<Noeud<E>> queue = new PriorityQueue<>(Comparator.comparingDouble(f::get));

        for (Noeud<E> node : graphe.getNoeuds()) {
            f.put(node, Double.POSITIVE_INFINITY);
            g.put(node, Double.POSITIVE_INFINITY);
            precedent.put(node, null);
        }
        g.put(depart, 0.0);
        f.put(depart, h(depart, arrivee));
        queue.add(depart);

        while (!queue.isEmpty()) {
            Noeud<E> current = queue.poll();
            if (current.equals(arrivee))
                break;
            explored.add(current);

            for (Noeud<E> voisin : graphe.getVoisins(current)) {
                if (explored.contains(voisin))
                    continue;

                double tentativeG = g.get(current) + graphe.getCoutArete(current, voisin);
                if (tentativeG < g.get(voisin)) {
                    precedent.put(voisin, current);
                    g.put(voisin, tentativeG);
                    f.put(voisin, g.get(voisin) + h(voisin, arrivee));
                    if (!queue.contains(voisin))
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