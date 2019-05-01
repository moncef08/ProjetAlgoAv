package com.company;
import java.awt.*;
import java.awt.geom.*;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

@SuppressWarnings("serial")
public class Visu extends JFrame {

    // Ensemble de points à afficher
    Set <Point> points;

    // Ensemble de lignes à afficher, une ligne est caractérisée par deux points représentant ses extremités.
    Set <Ligne> lignes;

    // Description de la solution, incluant son score et toute autre information utile
    String descriptionSolution;

    public Visu(Set <Point> points, Set <Ligne> lignes, String descriptionSolution) {
        super();
        this.points = points;
        this.lignes = lignes;
        this.descriptionSolution = descriptionSolution;
        construitContenuFenetre();
    }

    public Visu(Set <Point> points, String descriptionSolution) {
        super();
        this.points = points;
        this.lignes = new HashSet<Ligne>();
        this.descriptionSolution = descriptionSolution;
        construitContenuFenetre();
    }


    public void construitContenuFenetre(){
        JFrame f = new JFrame();
        f.setTitle("Algorithmique avancée - Visualiseur points et lignes brisées");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création des composants et ajout au ContentPane du cadre
        JLabel label = new JLabel(descriptionSolution);
        Plot2d plot = new Plot2d(points,lignes);

        f.getContentPane().add(plot, BorderLayout.CENTER);
        f.getContentPane().add(label, BorderLayout.SOUTH);

        f.setSize(600,600);
        f.setLocation(200,200);
        f.setVisible(true);
    }
  /*  distance(entrée,i,j) :
    somme = 0
            for x in range (i+1, j) :
    somme + = distanceSegmentPoint (entrée[x],entrée[j], entrée[j])
    fin for
            return somme

    distanceSegmentPoint(point, i, j) :
    ortho = projeté(point, i, j)
    si (abscisse(ortho) > abscisse(i) et abscisse(ortho) < abscisse(j)) :
            return distance(point, ortho)
	return min (distance(point,i), distance(point,j))*/

   /* public float Approximation(int n , float C){
        float sde = 0;
        float T[] = new float[n];
        T[1]=0;
        for (int i=2 ;i<=n;i++){
            T[i]=999999;
            for (int j=1; j<=i-1){
                sde=0;
                for (int k=j;k<=i;k++){
                    sde=sde+Distance(j,i);

                }
                T[i]=Math.min(T[i],sde+C+T[j]);

            }
        }
        return(T[n]);

    }*/
   /* public float Distance(Point point,Point i,Point j){
        Point ortho=projete(point,i,j);
        if (ortho.getx()>i.getx() && ortho.getx()<j.getx()){
            return d
            somme=somme + distanceSec
        }

    }*/

    public static void main(String[] args) {

        // EXEMPLE d'UTILISATION DE LA CLASSE

        // Création des points
    	Point p1 = new Point (1.0,8);
        Point p2 = new Point (2.0,25);
        Point p3 = new Point (3.0,7);
        Point p4 = new Point (4,5.0);
        Point p5 = new Point (5.0,8);
        Point p6 = new Point (6.0,25);
        Point p7 = new Point (7.0,7);
        Point p8 = new Point (8,5);
        Set <Point> points = new HashSet<Point>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);
        points.add(p7);
        points.add(p8);
        // Les points peuvent être obtenus par lecture d'un fichier de données par :
        //Set  <Point> points_lus ;
        //points_lus = Parser.recuperePoints();


        // Création des lignes composanr la ligne brisée (A VOUS DE JOUER pour trouver la meilleure approximation)
        // Encore une fois, les structues données ici sont à adapter à votre programme.
        Set <Ligne> lignes = new HashSet<Ligne>();
        lignes.add(new Ligne (p1,p2));
        lignes.add(new Ligne (p2,p7));
        lignes.add(new Ligne (p7,p8));

        Double score_au_pif = 3.4;

        Visu v1 = new Visu(points,lignes,"Solution de score "+score_au_pif);
        // si pas de ligne à afficher :
        //new Visu(points_lus,"Solution de score"+score_au_pif);

    }
}