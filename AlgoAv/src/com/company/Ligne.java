package com.company;

import java.util.ArrayList;

public class Ligne {
    private Point point1;
    private Point point2;

    public Ligne(Point p1, Point p2 ){
        point1=p1;
        point2=p2;
    }

    Point getp1(){return point1;}
    Point getp2(){return point2;}

    public double[] Equation_Droite(){
        double T[] = new double[2];
        Point A=this.getp1();
        Point B=this.getp2();

        if (A.getx()==B.getx()){ // Coquille.
            // System.out.println("L'equation de cette droite est : x = "+A.getx());
            T[0]=A.getx();
            T[1]=Double.POSITIVE_INFINITY;
            return(T);
        }
        else{
            double coeffDir=((B.gety()-A.gety())/(B.getx()-A.getx()));
            double p=A.gety() - coeffDir * A.getx();
            // System.out.println("L'equation de cette droite est : y = "+coeffDir+" x + "+p);
            T[0]=coeffDir;
            T[1]=p;
            return (T);

        }
    }
    public double dist(Point point){
        double T[] = new double[2];
        T = this.Equation_Droite();
        double xP=point.getx();
        double yP=point.gety(); 
        double distance=0;
        if(T[1] == Double.POSITIVE_INFINITY)
        	distance = Math.abs(T[0] - xP);
        else 
        	distance = Math.abs(T[0]*xP-yP+T[1])/Math.sqrt(Math.pow(T[0],2)+1);
        // System.out.println("La distance est : "+distance);
        return distance;

    }
    public static void main(String[] args) {

        Point p1=new Point(1,1);
        Point p2=new Point(2,1);
        Point p3=new Point(3,3);
        Point p4=new Point(4,5);
        Point p5= new Point(5,3);
        Point p6=new Point(6,4);
        Point p7=new Point(7,3);
        Point p8= new Point(8,4);

        Ligne L1 = new Ligne(p1,p4);
        Ligne L2 = new Ligne(p4,p7);
        Ligne L3 = new Ligne(p7,p8);

        //double somme=L2.dist(p3)+L2.dist(p4)+L2.dist(p5)+L2.dist(p6)+L2.dist(p7) + 3*2;
        double somme1=L1.dist(p2)+L1.dist(p3)+L2.dist(p5)+L2.dist(p6);

        L1.Equation_Droite();
        System.out.println("score="+somme1);

    }

}