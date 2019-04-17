package com.company;

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

        if (A.getx()==B.gety()){
            System.out.println("l'equation de cette droite est : x= "+A.getx());
            T[0]=A.getx();
            return(T);
        }
        else{
            double coeffDir=((B.gety()-A.gety())/(B.getx()-A.getx()));
            double p=A.gety() - coeffDir * A.getx();
            System.out.println("l'equation de cette droite est : y= "+coeffDir+"x+"+p);
            T[0]=coeffDir;
            T[1]=p;
            return (T);

        }
    }


}