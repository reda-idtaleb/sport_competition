package competition;


import competition.*;
import selection.BestOfGroup;
import selection.TwoBestsPlusTwoBestsOfThirds;
import util.Util;

public class Main {

	public static void main(String[] args) {
		/**
		Util.createCompetitors();
		Competition tournament = new Tournament(Util.competitors);
		Util.displayCompetitors();
		
		System.out.println("*****************************************************************");
		
		System.out.println("\t\t  THE BEGINNING OF THE TOURNAMENT !\n");
		System.out.println("*****************************************************************\n");
		
		tournament.play();
		tournament.ranking();
		
		System.out.println("\n\n#################################################################");
		System.out.println("#################################################################");
		System.out.println("#################################################################");
		
		// initialisation de la liste des competiteurs pour jouer la league
		Util.initListCompetitors();
		Util.createCompetitors();
		 
		System.out.println("\n\n");	
		Competition league = new League(Util.competitors);
		
		Util.displayCompetitors();
		
		System.out.println("*****************************************************************");
		System.out.println("\t\t  THE BEGINNING OF THE LEAGUE !\n");
		System.out.println("*****************************************************************\n");
		
		league.play();
		league.ranking();
		
		
		System.out.println("\n\n#################################################################");
		System.out.println("#################################################################");
		System.out.println("#################################################################");
		**/
		
		// exemple pour la première selection
		System.out.println("*****************************************************************");
		System.out.println("\t\t  THE BEGINNING OF THE MASTER !\n");
		System.out.println("*****************************************************************\n");
		System.out.println("\t\t Exemple 1: Selection BestOfGroups");
		
		Util.initListCompetitors();
		Util.createCompetitors(16);
		
		System.out.println("\n\n");	
		Competition master1 = new Master(Util.competitors, new BestOfGroup(), 4);
		
		Util.displayCompetitors();
		
		master1.play();
		master1.ranking();
		
		// exemple pour la deuxième selection
		System.out.println("\n*****************************************************************");
		System.out.println("\t\t  THE BEGINNING OF THE MASTER !\n");
		System.out.println("*****************************************************************\n");
		System.out.println("\t Exemple 2: Selection TwoBestsPlusTwoBestsOfThirds");
		
		Util.initListCompetitors();
		Util.createCompetitors(24);
		
		System.out.println("\n\n");	
		Competition master2 = new Master(Util.competitors, new TwoBestsPlusTwoBestsOfThirds(), 3);
		
		Util.displayCompetitors();
		
		master2.play();
		master2.ranking();
	}

}
