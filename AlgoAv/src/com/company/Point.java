package com.company;

/**
 *
 * @author Equipe pédagogique d'algorithmique avancées 2013-2014
 *
 */
public class Point {
    /**
     * Un point est composé d'une abscisse de type flottant et d'une ordonnée de type double
     */
    private double x, y;

    /**
     * Constructeur de la classe Point.
     * @param abs Valeur de l'abscisse du point à créer.
     * @param ord Valeur de l'ordonnée du point à créer.
     */
    public Point(double abs, double ord){
        x = abs;
        y = ord;
    }


    /**
     * Récupération de la valeur de l'abscisse du point.
     * @return la valeur de l'abscisse du point.
     */
    public double getx(){return x;}

    /**
     * Récupération de la valeur de l'ordonnée du point.
     * @return la valeur de l'ordonnée du point.
     */
    public double gety(){return y;}


    /**
     * Réinitialisation de l'abscisse.
     * @param xIn La nouvelle valeur de l'abscisse.
     */
    public void setx(double xIn){x=xIn;}

    /**
     * Réinitialisation de l'ordonnée.
     * @param yIn La nouvelle valeur de l'ordonnée.
     */
    public void sety(double yIn){x=yIn;}

    /**
     * Réinitialisation du point.
     * @param abs La nouvelle valeur de l'abscisse.
     * @param ord La nouvelle valeur de l'ordonnée.
     */
    public void initialise(double abs, double ord) {
        x = abs;
        y = ord;
    }

    @Override
    public String toString(){
        return "("+x+","+y+")";
    }

    /**
     * Deux points sont égaux s'ils ont la même valeur pour abscisse et la même valeur pour ordonnée.
     * @param obj Objet avec lequel comparer le point courant.
     * @return vrai si les points sont égaux, faux sinon.
     */
    @Override
    public boolean equals(Object obj){
        if (obj==this){return true;}
        if (!(obj instanceof Point)){return false;}
        Point p= (Point) obj;
        return ((x == p.x) && (y == p.y));
    }


    /**
     * Affichage de la valeur de this.toString sur la sortie standard.
     */
    public void affiche() {
        System.out.println(this.toString());
    }
}
