package observer;

import competition.Competition;
import competition.Tournament;
import observer.BookMaker;
import observer.Journalist;

import util.Util;

public class MainObserver {
	
	public static void main(String[] args) {
		
		System.out.println("*****************************************************************");
		System.out.println("\t\t  THE BEGINNING OF THE TOURNAMENT !\n");
		System.out.println("*****************************************************************\n");
		
		Util.initListCompetitors();
		Util.createCompetitors(4);
		
		System.out.println("\n\n");	
		Competition tournament = new Tournament(Util.competitors);
		
		Util.displayCompetitors();
		
		tournament.play();
		tournament.ranking();
		
		Journalist j =  new Journalist();
		BookMaker bm = new BookMaker();
		
		tournament.addCompetitionListener(j);
		tournament.addCompetitionListener(bm);

		tournament.competitionPlayed();
		
		System.out.println("\n\n#################################################################");
		System.out.println("#################################################################");
		System.out.println("#################################################################");
	
		
		
		
		
		
		
	}

	
	

}