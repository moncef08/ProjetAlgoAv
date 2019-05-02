package com.company;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Optimale {
	List<Point> points;
	int size;
	static double C = 1.5;
	static double O = Double.POSITIVE_INFINITY;
	
	public Optimale(){
		points = Parser.recuperePoints();
		size = points.size();
	}
	
	public Optimale(int n){
		
		points = new ArrayList<>();
		double min=1;
		double max=n;

	    for(int i=1; i<=n; i++) {
			double x = (int) (Math.random() * ((max - min) + 1)) + min;

			points.add(new Point(i,x));
		}
		size = n;

	}
	
	public double distance(int i, int j){
		double distance = 0;
		Ligne L = new Ligne(points.get(i), points.get(j));
		for(int k=i+1; k<j; k++){
			distance = distance + L.dist(points.get(k));
		}
		return distance;
	}
	
	/*public double solution_1(int i, List<Point> p){
		double result = Double.POSITIVE_INFINITY;
		if(i == size-1){
			result = 0;
			p.add(points.get(size-1));
			for(int j=0; j<(p.size()-1);j++){
				result = result + distance((int)p.get(j).getx()-1, (int)p.get(j+1).getx()-1);
			}
			p.remove(p.size()-1);
			// System.out.println("result --------------------------*" + result);
			return result + Cout*p.size();
		}
		// Etape 1:
		if(i != 0)
			result = solution_1(i+1, p);
		
		// Etape 2:
		p.add(points.get(i));
		result = Math.min(result, solution_1(i+1, p));
		p.remove(p.size()-1);
		return result;
	}*/
	
	public void solution_Essais_successifs(int i, int last, double somme){
		
		// Etape finale:
		if(i == size-1){
			O = Math.min(O, somme + distance(last, i) + C) ;
			return ;
		}
		
		// Etape 1:
		solution_Essais_successifs(i+1, last, somme);
		
		// Etape 2:
		double check = 0;
		check = somme + distance(last, i) + C;
		solution_Essais_successifs(i+1, i, check);
	
	}
	
	public void solution_elagage(int i, int last, double somme){
		
		// Etape finale:
		if(i == size-1){
			O = Math.min(O, somme + distance(last, i) + C) ;
			return ;
		}
		
		// Etape 1:
		solution_elagage(i+1, last, somme);
		
		// Etape 2:
		double check = 0;
		check = somme + distance(last, i) + C;
		if(check < O)
			solution_elagage(i+1, i, check);
	
	}
	
	public List<Double[]> approx_opt(){
		// Programmation dynamique
		List<Double[]> T = new ArrayList<>();
		T.add(new Double[]{1.0,0.0});
		
		for(int i=1; i<size; i++){
			double min = Double.POSITIVE_INFINITY;
			double last = 0;
			for(int j=0; j < i; j++){
				if(min > T.get(j)[1] + distance(j, i) + C){
					min = T.get(j)[1] + distance(j, i) + C;
					last = j;
				}
			}
			T.add(new Double[]{last, min});
		}
		return T;
	}
	
	public LinkedList<Point> backward(List<Double[]> T){
		double size_loc = T.size() - 1;
		LinkedList<Point> p = new LinkedList<Point>();
		p.add(points.get((int)size_loc));
		while(size_loc > 0){
			size_loc = T.get((int)size_loc)[0];
			p.addFirst(points.get((int)size_loc));
		}
		return p;
	}
	
	public static void main(String args[]) {
		int n =32;						      //on choisit le nombre de points qu'on veut (inutile si on utilise le Deuxieme constructeur
		                                      //qui récupère un fichier de données
		Optimale p = new Optimale(n);
		//Optimale p = new Optimale();       // à décommenter si on veut introduire un jeu de test et non générer aléatoirement les pts
		List<Point> l = new ArrayList<>();
		double value = 0;
		System.out.println("n = " + n);


		long startTime = System.currentTimeMillis();
		O = Double.POSITIVE_INFINITY;
		p.solution_Essais_successifs(1, 0, 0);
		long stopTime = System.currentTimeMillis();
		System.out.println("La solution sans élagage ==> score=" + O + " |Time : " + (stopTime - startTime) + " ms");
		
		startTime = System.currentTimeMillis();
		O = Double.POSITIVE_INFINITY;
	    p.solution_elagage(1, 0, 0);
		stopTime = System.currentTimeMillis();
		System.out.println("La solution avec élagage ==> score= " + O + " |Time : " + (stopTime - startTime) + " ms");
		
		startTime = System.currentTimeMillis();
		List<Double[]> T = p.approx_opt();
		LinkedList<Point> set = p.backward(T);
		stopTime = System.currentTimeMillis();
		System.out.println("Backward sequence " + set.toString());
		System.out.println("Programmatin dynamique ====> score=" + T.get(T.size()-1)[1]+ " |Time : " + (stopTime - startTime) + " ms");


		Set <Ligne> lignes = new HashSet<Ligne>();
		Set<Point> setP = new HashSet<Point>(p.points); //ensemble de tous les points

		Set<Point> setF = new HashSet<Point>(set);      //ensemble des points qui constituent la ligne brisée optimale
		int i=0;
		System.out.println(set.size());
		while (i<set.size()-1){                     //boucle qui relie tous les points de la ligne brisée optimale pour les afficher
		 lignes.add(new Ligne (set.get(i),set.get(i+1)));
		 i++;


		}
		Visu v1 = new Visu(setP,lignes,"Solution de score "+T.get(T.size()-1)[1]); //affichage de la solution optimale+ score optimal

	}

}