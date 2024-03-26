## Réponses aux questions

**Question :** Quelle structure de données pourrait être utilisée pour stocker les relations entre les nœuds du graphe et les informations associées à ces relations, comme les coûts des arêtes ?<br>
**Réponse :** Une map. 

**Question :** Pourquoi pensez-vous que les classes `Noeud` et `Graphe` ont été définies avec des paramètres génériques ?<br>
**Réponse :** Les paramètres génériques permettent de créer des classes qui peuvent être utilisées avec différents types de données, ce qui les rend plus flexibles et réutilisables.


**Question :** Pourquoi pensez-vous que la création d'une interface est une bonne pratique dans ce contexte ?<br><br>
**Réponse:** L'interface `AlgorithmeChemin` est une bonne pratique car elle permet de définir un contrat pour les classes qui implémentent des algorithmes de recherche de chemin dans un graphe. Cela permet de définir une structure commune pour ces classes, ce qui facilite leur utilisation et leur interchangeabilité. En utilisant une interface, on peut s'assurer que les classes qui implémentent l'interface fournissent les méthodes requises, ce qui rend le code plus robuste et plus facile à maintenir.

## Feedback
**Difficulté rencontrée :**

La gestion des listes n'est pas un domaine je suis totatement à l'aise. Pour moi j'utilise que les ArrayList. Mais ici on a utilisé des LinkedList et des PriorityQueue. Cela m'a pris un peu de temps pour comprendre comment les utiliser correctement mais j'ai compris et je suis content.<br>
De plus, petite anecdote, j'ai réalisé un test technique chez Airbus la semaine dernière l'après midi du partiel de Java et le test portait sur un problème de performance lié à l'utilisation des ArrayList au lieu de HasMap. J'étais content d'avoir pu répondre correctement à la question grâce au partiel de Java 😁