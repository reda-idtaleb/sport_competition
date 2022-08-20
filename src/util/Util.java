package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import competitor.Competitor;

public class Util {
	public static List<Competitor> competitors;
	
	public Util() {
		
	}

	public static void initListCompetitors() {
		competitors = new ArrayList<Competitor>();
	}
	
	public static void displayCompetitors() {
		System.out.println("*****************************************************************");
		System.out.println("\t\t*** THE LIST OF THE COMPETITORS *** ");
		System.out.println("*****************************************************************\n");
		System.out.println("-----------------------------------------------------------------");
		int j = 1;
		for(int i = 0; i < competitors.size(); i++) {
			if (i == 4*j ){
				System.out.println("|");
				System.out.println("-----------------------------------------------------------------");
				j += 1;
			}
			System.out.print("|\t" + Util.competitors.get(i) + "\t");
			if(i == competitors.size()-1) {
				System.out.print("|");
			}
		}
		System.out.println(" \n-----------------------------------------------------------------\n");
	}
	
	public static List<Competitor> createCompetitors(int nbCompetitors) {
		
		initListCompetitors();
		
		for(int i = 0; i < nbCompetitors; i++) {
			Competitor c = new Competitor("C" + (i+1));
			competitors.add(c);
		}
		
		return  competitors;
	}

	/**
	 * @param list 
	 * @return
	 */
	public static int getRandomElementFromList(List<Integer> list) {
		Random rand = new Random();
		int index = rand.nextInt(list.size());
		return list.get(index);
	}
	
	/**
	 * get a list of the all dividers of a number n
	 * @param n an integer n
	 * @return a list of all the dividers of an integer n 
	 */
	public static List<Integer> dividers(int n) {
		List<Integer> dividers = new ArrayList<Integer>();
        for( int i=2;i<n ;i++){
            if (n % i == 0) {
            	dividers.add(i);
            }
        }
        return dividers;
	}

	/**
	 * tests if a number is a power of 2
	 * @param x an integer
	 * @return True if it is otherwise false
	 */
	public static boolean isPowerOfTwo(int x){
	    return (x != 0) && ((x & (x - 1)) == 0);
	}


}
