
Projet Algorithmique Avancée 2019 
Binôme: Moncef REJEB SFAR et Mahdi BELHAOUZ

Notre code source présente 6 fichiers .java
Hormis la classe Point.java, tous les autres fichiers possèdent leur propre main et peuvent donc être testés séparément.

Classe ligne :
On peut tester les fonctions Equation_Droite() qui permet de donner
l'équation d'une ligne et la fonction dist(Point) qui calcule la distance d'un point à une droite

Classe Visu : On peut tester le traçage d'un ensemble de points et les lignes qu'on crée


 Classe Optimale:
 Cette classe contient les 3 algorithmes demandées. D'abord, elle contient deux constructeurs.
 L'un permet de générer aléatoirement un ensemble de n points (optimale(n))et un deuxième  qui
 récupère un fichier test.txt contenant l'ensemble des points. Donc avant de tester dans le main,
 il faudra décommenter soit la ligne 137 soit la ligne 138. L'exécution affiche dans la console le
 nom de la méthode, le score trouvé et le temps d'exécution. De plus, en faisant appel à la classe
 Visu, on construit, à l'exécution, l'ensemble de points, la ligne brisée optimale et affiche le graphe.

Remarque:
Si vous voulez tester des n très grands, il faut décommenter la solution sans élagage(ligne 151) qui met énormément
de temps à trouver la ligne brisée optimale si n dépasse 34.