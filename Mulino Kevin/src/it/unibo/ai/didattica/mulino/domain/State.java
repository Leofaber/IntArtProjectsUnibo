package it.unibo.ai.didattica.mulino.domain;

import java.io.Serializable;
import java.util.HashMap;

import it.unibo.ai.didattica.mulino.customExceptions.InvalidPositionException;
import it.unibo.ai.didattica.mulino.domain.State.Checker;

public class State implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum Phase {
	    FIRST ("First"),
	    SECOND ("Second"),
	    FINAL ("Final");
	    private final String name;       
	    private Phase(String s) { name = s; }
	    public boolean equalsName(String otherName){ return (otherName == null)? false:name.equals(otherName); }
	    public String toString(){ return name; }
	}
	
	
	public enum Checker {
		EMPTY ('O'),
		WHITE ('W'),
		BLACK ('B');
		private final char checker;       
	    private Checker(char s) { checker = s; }
	    public boolean equalsChecker(char otherChecker){ return otherChecker == checker; }
	    public String toString(){ return ""+checker; }
		
	}
	
	// the board with the checkers over it
	private HashMap<String,Checker> board = new HashMap<>();
	
	// current phase of the game
	private Phase currentPhase = Phase.FIRST;

	// positions on the board
	public final String[] positions = {
			"a1", "a4", "a7"
			, "b2", "b4", "b6"
			, "c3", "c4", "c5"
			, "d1", "d2", "d3", "d5", "d6", "d7"
			, "e3", "e4", "e5"
			, "f2", "f4", "f6"
			, "g1", "g4", "g7"
	};
	
	private int whiteCheckers = 9;
	private int blackCheckers = 9;
	
	private int whiteCheckersOnBoard = 0;
	private int blackCheckersOnBoard = 0;
	
	public State() {
		// init the board
		for (String s : positions)
			board.put(s, Checker.EMPTY);
	}
		
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("7 " + board.get("a7") + "--------" + board.get("d7") + "--------" + board.get("g7") + "\n");
		result.append("6 |--" + board.get("b6") + "-----" + board.get("d6") + "-----" + board.get("f6") + "--|\n");
		result.append("5 |--|--" + board.get("c5") + "--" + board.get("d5") + "--" + board.get("e5") + "--|--|\n");
		result.append("4 " + board.get("a4") + "--" + board.get("b4") + "--" + board.get("c4") + "     " + board.get("e4") + "--" + board.get("f4") + "--" + board.get("g4") +"\n");
		result.append("3 |--|--" + board.get("c3") + "--" + board.get("d3") + "--" + board.get("e3") + "--|--|\n");
		result.append("2 |--" + board.get("b2") + "-----" + board.get("d2") + "-----" + board.get("f2") + "--|\n");
		result.append("1 " + board.get("a1") + "--------" + board.get("d1") + "--------" + board.get("g1") + "\n");
		result.append("  a  b  c  d  e  f  g\n");
		result.append("Phase: " + currentPhase.toString() + ";\n");
		result.append("White Checkers: " + whiteCheckers + ";\n");
		result.append("Black Checkers: " + blackCheckers + ";\n");
		result.append("White Checkers On Board: " + whiteCheckersOnBoard + ";\n");
		result.append("Black Checkers On Board: " + blackCheckersOnBoard + ";\n");
		return result.toString();
	}
	
	
	
	
	
	// getters and setters
	public HashMap<String, Checker> getBoard() { return board; }
	public void setBoard(HashMap<String, Checker> hashMap) { this.board = hashMap; }

	public String[] getPositions() { return positions; }
	
	public Phase getCurrentPhase() { return currentPhase; }
	public void setCurrentPhase(Phase currentPhase) { this.currentPhase = currentPhase; }
	
	public int getWhiteCheckers() { return whiteCheckers; }
	public void setWhiteCheckers(int whiteCheckers) { this.whiteCheckers = whiteCheckers; }

	public int getBlackCheckers() { return blackCheckers; }
	public void setBlackCheckers(int blackCheckers) { this.blackCheckers = blackCheckers; }
	
	public int getWhiteCheckersOnBoard() { return whiteCheckersOnBoard; }
	public void setWhiteCheckersOnBoard(int whiteCheckersOnBoard) { this.whiteCheckersOnBoard = whiteCheckersOnBoard; }

	public int getBlackCheckersOnBoard() { return blackCheckersOnBoard; }
	public void setBlackCheckersOnBoard(int blackCheckersOnBoard) { this.blackCheckersOnBoard = blackCheckersOnBoard; }
	
	
	
	public State clone() {
		// generate the new State
		State result = new State();
				
		// replicate the current board
		result.getBoard().putAll(this.getBoard());

		// update the checkers available to the players
		result.setWhiteCheckers(this.getWhiteCheckers());
		result.setBlackCheckers(this.getBlackCheckers());
		result.setWhiteCheckersOnBoard(this.getWhiteCheckersOnBoard());
		result.setBlackCheckersOnBoard(this.getBlackCheckersOnBoard());
		
		// update the phase
		result.setCurrentPhase(this.getCurrentPhase());
		
		return result;
	}
	
	
	
	public static void main (String[] args) {
		State aState = new State();
		System.out.println(aState.toString());
	}


	

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  FUNZIONI CUSTOM BARONCELLI CALABRIA ZAINI 						   *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	public int[] rowMorrisBlack = new int[8];
	public int[] columnMorrisBlack = new int[8];
	public int[] rowMorrisWhite = new int[8];
	public int[] columnMorrisWhite = new int[8];
	public int[] rowEmpty = new int[8];
	public int[] columnEmpty = new int[8];
	//le due funzioni sono senza dubbio migliorabili, ma almeno ne abbiamo un abbozzo
	
public void setArrayOfMorris() {
		
		//ho preso la lunghezza di un solo array, tanto sono tutti lunghi uguali
		for (int t=0;t<rowMorrisBlack.length;t++){	
			rowMorrisBlack[t]=0;
			columnMorrisBlack[t]=0;
			rowMorrisWhite[t]=0;
			columnMorrisWhite[t]=0;
			rowEmpty[t]=0;
			columnEmpty[t]=0;
		}
			
		for (String s : this.positions) {
			char first = s.charAt(0);
			char second = s.charAt(1);
			//cerco tris in orizzontale
			//incremento nel relativo array se trovo la pedina bianca, nera o empty
			switch (second) {
			
			// se mi trovo nella riga 'a', vado a controllare se nella mia casella
			// della board il valore corrisponde a c. se � cos� allora incremento
			// il mio array.
				case '1' : 	if (this.board.get(s) == Checker.EMPTY)
								rowEmpty[0]++;
							else if (this.board.get(s) == Checker.WHITE)
								rowMorrisWhite[0]++;
							else
								rowMorrisBlack[0]++;
							break;
				case '2':	if (this.board.get(s) == Checker.EMPTY)
								rowEmpty[1]++;
							else if (this.board.get(s) == Checker.WHITE)
								rowMorrisWhite[1]++;
							else
								rowMorrisBlack[1]++;
							break;
				case '3':	if (this.board.get(s) == Checker.EMPTY)
								rowEmpty[2]++;
							else if (this.board.get(s) == Checker.WHITE)
								rowMorrisWhite[2]++;
							else
								rowMorrisBlack[2]++;
							break;
				case '4':	if (first < 'd') {
					
								if (this.board.get(s) == Checker.EMPTY)
									rowEmpty[3]++;
								else if (this.board.get(s) == Checker.WHITE)
									rowMorrisWhite[3]++;
								else
									rowMorrisBlack[3]++;
								break;
							} else {
								
								if (this.board.get(s) == Checker.EMPTY)
									rowEmpty[4]++;
								else if (this.board.get(s) == Checker.WHITE)
									rowMorrisWhite[4]++;
								else
									rowMorrisBlack[4]++;
								break;
							}
				case '5':	if (this.board.get(s) == Checker.EMPTY)
								rowEmpty[5]++;
							else if (this.board.get(s) == Checker.WHITE)
								rowMorrisWhite[5]++;
							else
								rowMorrisBlack[5]++;
							break;
				case '6':	if (this.board.get(s) == Checker.EMPTY)
								rowEmpty[6]++;
							else if (this.board.get(s) == Checker.WHITE)
								rowMorrisWhite[6]++;
							else
								rowMorrisBlack[6]++;
							break;
				case '7':	if (this.board.get(s) == Checker.EMPTY)
								rowEmpty[7]++;
							else if (this.board.get(s) == Checker.WHITE)
								rowMorrisWhite[7]++;
							else
								rowMorrisBlack[7]++;
							break;
			}
			
			switch (first) {
			// se mi trovo nella colonna 1, vado a controllare se nella mia casella
			// della board il valore corrisponde a c. se � cos� allora incremento
			// il mio array.
				case 'a' : 	if (this.board.get(s) == Checker.EMPTY)
								columnEmpty[0]++;
							else if (this.board.get(s) == Checker.WHITE)
								columnMorrisWhite[0]++;
							else
								columnMorrisBlack[0]++;
							break;
				case 'b' :	if (this.board.get(s) == Checker.EMPTY)
								columnEmpty[1]++;
							else if (this.board.get(s) == Checker.WHITE)
								columnMorrisWhite[1]++;
							else
								columnMorrisBlack[1]++;
							break;
				case 'c': 	if (this.board.get(s) == Checker.EMPTY)
								columnEmpty[2]++;
							else if (this.board.get(s) == Checker.WHITE)
								columnMorrisWhite[2]++;
							else
								columnMorrisBlack[2]++;
							break;
				case 'd': 	if (Character.getNumericValue(second) < 4) {
								if (this.board.get(s) == Checker.EMPTY)
									columnEmpty[3]++;
								else if (this.board.get(s) == Checker.WHITE)
									columnMorrisWhite[3]++;
								else
									columnMorrisBlack[3]++;
								break;
							} else {
								if (this.board.get(s) == Checker.EMPTY)
									columnEmpty[4]++;
								else if (this.board.get(s) == Checker.WHITE)
									columnMorrisWhite[4]++;
								else
									columnMorrisBlack[4]++;
								break;
							}
				case 'e': 	if (this.board.get(s) == Checker.EMPTY)
								columnEmpty[5]++;
							else if (this.board.get(s) == Checker.WHITE)
								columnMorrisWhite[5]++;
							else
								columnMorrisBlack[5]++;
							break;
				case 'f': 	if (this.board.get(s) == Checker.EMPTY)
								columnEmpty[6]++;
							else if (this.board.get(s) == Checker.WHITE)
								columnMorrisWhite[6]++;
							else
								columnMorrisBlack[6]++;
							break;
				case 'g': 	if (this.board.get(s) == Checker.EMPTY)
								columnEmpty[7]++;
							else if (this.board.get(s) == Checker.WHITE)
								columnMorrisWhite[7]++;
							else
								columnMorrisBlack[7]++;
							break;
			}
		}
	}

	public int[] getMorrisArray(String colour, String verso) {
		switch (colour) {
		case "BLACK":	if (verso.equalsIgnoreCase("ROW"))
							return rowMorrisBlack;
						else
							return columnMorrisBlack;
		case "WHITE":	if (verso.equalsIgnoreCase("ROW"))
							return rowMorrisWhite;
						else
							return columnMorrisWhite;
		case "EMPTY":	if (verso.equalsIgnoreCase("ROW"))
							return rowEmpty;
						else
							return columnEmpty;
		default:
						System.out.println("[getMorrisArray] ERRORE");
		}
		//non dovrei mai arrivarci
		return null;
	}
	
	
	/*
	 *  		 "a1"-------------------"a4"-----------------"a7" 
			      |                      |                    |
			      |    					 |					  |
			 	  |		"b2"------------"b4"------------b6"   |
				  |      |               |              !     |
	 			  |		 |		         |              |     |  
				  |		 |	  "c3"------"c4"-----"c5"   |     |
				  |		 |		|                  |    |     |
			      | 	 |		|                  |    |     |
				 "d1"---"d2"--"d3"               "d5"--"d6"--"d7"
				  |      |		|                  |    |     |
				  |		 |		|				   |    |     |
				  |		 |	  "e3"----- "e4"-----"e5"   |     |
				  |		 |				 |				|     |
				  |		 |	 			 |	 			|     |
				  |		"f2"------------"f4"-----------"f6"   |
				  |                      |                    |
				  |						 |					  |
				"g1"--------------------"g4"-----------------"g7"
	 * 
	 */
	
	/*
	 * DATA UNA POSIZIONE, RITORNA LA LISTA DELLE POSIZIONI RAGGIUNGIBILI
	 * By Leo
	 */
	public String[] getAdjacentPositions(String pos) throws InvalidPositionException{
		
		switch(pos){
			case "a1":
				return new String[] {"a4","d1"};
			
			case "a4":
				return new String[] {"a1","a7","b4"};
			
			case "a7":
				return new String[] {"a4","d7"};
				 
			case "b2":
				return new String[] {"b4","d2"};
				 
			case "b4":
				return new String[] {"b2","a4","b6","c4"};
				 
			case "b6":
				return new String[] {"b4","d6"};
				 
			case "c3":
				return new String[] {"c4","d3"};
				 
			case "c4":
				return new String[] {"c3","c5","b4"};
				 
			case "c5":
				return new String[] {"c4","d5"};
				 
			case "d1":
				return new String[] {"a1","d2","g1"};
				 
			case "d2":
				return new String[] {"d1","b2","d3","f2"};
				 
			case "d3":
				return new String[] {"c3","e3","d2"};
				 
			case "d5":
				return new String[] {"c5","d6","e5"};
				 
			case "d6":
				return new String[] {"d5","b6","d7","f6"};
				 
			case "d7":
				return new String[] {"a7","d6","g7"};
				 
			case "e3":
				return new String[] {"d3","e4"};
				 
			case "e4":
				return new String[] {"e3","f4","e5"};
				 
			case "e5":
				return new String[] {"e4","d5"};
				 
			case "f2":
				return new String[] {"d2","f4"};
				 
			case "f4":
				return new String[] {"f2","e4","f6"};
				 
			case "f6":
				return new String[] {"f4","d6"};
				 
			case "g1":
				return new String[] {"d1","g4"};
				 
			case "g4":
				return new String[] {"g1","f4","g7"};
				 
			case "g7":
				return new String[] {"g4","d7"};
				 
			default:
				throw new InvalidPositionException(pos);
		}
		
		
	}
	
	public int[] getIndexOfLinkedColumnsToRow(int rowIndex){
		switch(rowIndex){
		case 1:
			return new int[] {1,4,8};
		case 2:
			return new int[] {2,4,7};
		case 3:
			return new int[] {3,4,6};
		case 4:
			return new int[] {1,2,3};
		case 5:
			return new int[] {6,7,8};
		case 6:
			return new int[] {3,5,6};
		case 7:
			return new int[] {2,5,7};
		case 8:
			return new int[] {1,5,8};
		default:
			return new int[] {};
		}
	}
	
	public int[] getIndexOfLinkedRowToColumn(int columnIndex){
		switch(columnIndex){
		case 1:
			return new int[] {1,4,8};
		case 2:
			return new int[] {2,4,7};
		case 3:
			return new int[] {3,4,6};
		case 4:
			return new int[] {1,2,3};
		case 5:
			return new int[] {6,7,8};
		case 6:
			return new int[] {3,5,6};
		case 7:
			return new int[] {2,5,7};
		case 8:
			return new int[] {1,5,8};
		default:
			return new int[] {};
		}
	}
/*
 *  public int[] rowMorrisBlack = new int[8];
	public int[] columnMorrisBlack = new int[8];
	public int[] rowMorrisWhite = new int[8];
	public int[] columnMorrisWhite = new int[8];
 */
	public double getNumberOfMorris(Checker color){

		if(color == Checker.BLACK){
			int morrisBlack = 0;
			for (int i:rowMorrisBlack)
				if (i == 3)
					morrisBlack++;
			for (int i:columnMorrisBlack)
				if (i == 3)
					morrisBlack++;
			
 			return morrisBlack;
		}
	 	else {
			
			int morrisWhite = 0;
			for (int i:rowMorrisWhite)
				if (i == 3)
					morrisWhite++;
			for (int i:columnMorrisWhite)
				if (i == 3)
					morrisWhite++;
 			return morrisWhite;
			
	    }
	}
}
