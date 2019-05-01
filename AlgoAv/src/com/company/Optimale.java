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
	static double Cout = 1.5;
	static double O = Double.POSITIVE_INFINITY;
	
	public Optimale(){
		points = Parser.recuperePoints();
		size = points.size();
	}
	
	public Optimale(int n){
		
		points = new ArrayList<>();
		for(int i=1; i<=n; i++)
			points.add(new Point(i, (i*9)%n));
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
	
	public double solution_1(int i, List<Point> p){
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
	}
	
	public void solution_2(int i, int last, double somme){
		
		// Etape finale:
		if(i == size-1){
			O = Math.min(O, somme + distance(last, i) + Cout) ;
			return ;
		}
		
		// Etape 1:
		solution_elagage(i+1, last, somme);
		
		// Etape 2:
		double check = 0;
		check = somme + distance(last, i) + Cout;
		solution_2(i+1, i, check);
	
	}
	
	public void solution_elagage(int i, int last, double somme){
		
		// Etape finale:
		if(i == size-1){
			O = Math.min(O, somme + distance(last, i) + Cout) ;
			return ;
		}
		
		// Etape 1:
		solution_elagage(i+1, last, somme);
		
		// Etape 2:
		double check = 0;
		check = somme + distance(last, i) + Cout;
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
				if(min > T.get(j)[1] + distance(j, i) + Cout){
					min = T.get(j)[1] + distance(j, i) + Cout;
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
		int n = 30;
		Optimale p = new Optimale(n);
		List<Point> l = new ArrayList<>();
		double value = 0;
		System.out.println("n = " + n);
		// System.out.println("La distance totale : " + p.distance(0,3));
		/*
		long startTime = System.currentTimeMillis();
		value = p.solution_1(0, l);
		long stopTime = System.currentTimeMillis();
		System.out.println("La solution optimale 1 ====> " + "Time : " + (stopTime - startTime));
		*/
		long startTime = System.currentTimeMillis();
		O = Double.POSITIVE_INFINITY;
		p.solution_2(1, 0, 0);
		long stopTime = System.currentTimeMillis();
		System.out.println("La solution optimale 2 ====> " + O + " Time : " + (stopTime - startTime) + " ms");
		
		startTime = System.currentTimeMillis();
		O = Double.POSITIVE_INFINITY;
		p.solution_elagage(1, 0, 0);
		stopTime = System.currentTimeMillis();
		System.out.println("La solution avec elagage ==> " + O + " Time : " + (stopTime - startTime) + " ms");
		
		startTime = System.currentTimeMillis();
		List<Double[]> T = p.approx_opt();
		LinkedList<Point> set = p.backward(T);
		stopTime = System.currentTimeMillis();
		System.out.println("Backward sequence " + set.toString());
		System.out.println("Programmatin dynamique ====> " + T.get(T.size()-1)[1]+ " Time : " + (stopTime - startTime) + " ms");
	}

}