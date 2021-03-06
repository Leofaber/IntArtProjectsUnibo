package it.unibo.ai.didattica.mulino.iothincook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import it.unibo.ai.didattica.mulino.domain.State.Checker;
import it.unibo.ai.didattica.mulino.iothincook.MulinoState;
 import aima.core.search.adversarial.Game;
/*
 * S0: The initial state, which specifies how the game is set up at the
 * start.
 * PLAYER(s): Defines which player has the move in a state.
 * ACTIONS(s): Returns the set of legal moves in a state.
 * RESULT(s, a): The transition model, which defines the result of a move.
 * TERMINAL-TEST(s): A terminal test, which is true when the game is over
 * and false TERMINAL STATES otherwise. States where the game has ended are
 * called terminal states.</li>
 * UTILITY(s, p): A utility function, defines the final numeric value for a game 
 * that ends in terminal state s for a player p.
 */
public class MulinoGame implements Game<MulinoState, String, Checker> {
	private MulinoState currentState;
	
	public MulinoGame(MulinoState state) {
		currentState=state;
	}
	
	@Override
	public MulinoState getInitialState() {
		return currentState.clone();
	}

	@Override
	public Checker[] getPlayers() {
		Checker c[] ={Checker.WHITE, Checker.BLACK};
		return c;
	}

	@Override
	public Checker getPlayer(MulinoState state) {		
//		switch(state.getCurrentPhase()){
//		case FIRST:
//			return state.getBlackCheckers()>state.getWhiteCheckers() ? Checker.BLACK : Checker.WHITE;
//		case SECOND:
//			//TODO
//			return null;
//		case FINAL:
//			//TODO
//			return null;
//		default:
//			return null;
//		}
		return state.getCurrentPlayer();
				
	}

	@Override
	public boolean isTerminal(MulinoState state) {
//		switch (state.getCurrentPhase()){
//		case FIRST: 
			return state.getBlackCheckers()==0 && state.getWhiteCheckers()==0;
//		case SECOND:
//			return state.getBlackCheckersOnBoard()==3 || state.getWhiteCheckersOnBoard()==3;
//		case FINAL:
//			return state.getBlackCheckersOnBoard()<3 || state.getWhiteCheckersOnBoard()<3;
//		default:
//			return false;
//		}		
	}

	@Override
	public double getUtility(MulinoState state, Checker player) {
		HeuristicEvaluator evaluator=new HeuristicEvaluator(state, player);
		return evaluator.evaluate();
	}

	@Override
	public MulinoState getResult(MulinoState state, String action) {
		MulinoState newState = state.clone();

			String putAction=action.substring(0,2);
			String removeAction;
			switch (getPlayer(state)){
			case BLACK:
				newState.getBoard().put(putAction, Checker.BLACK);
				newState.setBlackCheckersOnBoard(newState.getBlackCheckersOnBoard()+1);
				newState.setBlackCheckers(newState.getBlackCheckers()-1);
				newState.setCurrentPlayer(Checker.WHITE);
				if(action.length()==4){
					removeAction=action.substring(2,4);
					newState.getBoard().put(removeAction, Checker.EMPTY);
					newState.setWhiteCheckersOnBoard(newState.getWhiteCheckersOnBoard()-1);
				}
				break;
			case WHITE:
				newState.getBoard().put(putAction, Checker.WHITE);
				newState.setWhiteCheckersOnBoard(newState.getWhiteCheckersOnBoard()+1);
				newState.setWhiteCheckers(newState.getWhiteCheckers()-1);
				newState.setCurrentPlayer(Checker.BLACK);
				if(action.length()==4){
					removeAction=action.substring(2,4);
					newState.getBoard().put(removeAction, Checker.EMPTY);
					newState.setBlackCheckersOnBoard(newState.getBlackCheckersOnBoard()-1);
				}
				break;
			case EMPTY:
				break;
			default:
				break;
			}
	
			
		return newState;
	}

	@Override
	public List<String> getActions(MulinoState state) {
		List<String> result= new ArrayList<String>();
  		
		state.setArrayOfMorris();
	 
		
		switch (getPlayer(state)){
			case BLACK:
				for (String s : state.getPositions())
					if(state.getBoard().get(s)==Checker.EMPTY){
						if(closedMill(state, s, Checker.BLACK)){
							for (String oppCheck : opponentCheckers(state, Checker.WHITE))
								//se opcheck � in un tris (avversario) non pu� essere rimossa
								if(state.isDeletableChecker(Checker.WHITE,oppCheck))								
									result.add(s+oppCheck);
						}else
							result.add(s);
					}
				break;
			case WHITE:
				for (String s : state.getPositions())
					if(state.getBoard().get(s)==Checker.EMPTY){
						if(closedMill(state, s, Checker.WHITE)){
							for (String oppCheck : opponentCheckers(state, Checker.BLACK))
								//se opcheck � in un tris (avversario) non pu� essere rimossa
								if(state.isDeletableChecker(Checker.BLACK,oppCheck))
									result.add(s+oppCheck);
						}else
							result.add(s);
					}
				break;
			default:
				break;
			}
	
		
		return result;
	}	
	
	
	//Verifica se l'azione action genera un nuovo tris
	public static boolean closedMill(MulinoState state, String action,Checker player){
		Double oldNumMorris, newNumMorris=0.0;
//		state.setArrayOfMorris();
		MulinoState newState=state.clone();
	//	System.out.println(newState.toString());
		//HashMap<String, Checker> newBoard=newState.getBoard();
		newState.setArrayOfMorris();
//		newState.setCurrentPlayer(player);
		oldNumMorris=newState.getNumberOfMorris(player);
	//	System.out.println("OLD NUM MORRIS: "+ oldNumMorris);
		
		newState.getBoard().put(action, player);		
		switch(player){
		case WHITE:
			newState.setWhiteCheckers(newState.getWhiteCheckers()-1);
			newState.setWhiteCheckersOnBoard(newState.getWhiteCheckersOnBoard()+1);
//			newState.setCurrentPlayer(Checker.BLACK);
			break;
		case BLACK:
			newState.setBlackCheckers(newState.getBlackCheckers()-1);
			newState.setBlackCheckersOnBoard(newState.getBlackCheckersOnBoard()+1);
//			newState.setCurrentPlayer(Checker.WHITE);
			break;
		case EMPTY:
			break;
		default:
			break;
		} 
	//	System.out.println(newState.toString());
		newState.setArrayOfMorris();			
		newNumMorris=newState.getNumberOfMorris(player);
//		switch(player){
//		case WHITE:
//			newState.setCurrentPlayer(Checker.BLACK);
//			break;
//		case BLACK:
//			newState.setCurrentPlayer(Checker.WHITE);
//			break;
//		}

	//	System.out.println("NEW NUM MORRIS: "+ newNumMorris);
		
 		
		return newNumMorris-oldNumMorris>0;
	}
	
	//genera la lista dei checker del giocatore player
	public static List<String> opponentCheckers(MulinoState state, Checker player){
		List<String> result = new ArrayList<String>();
		for(Entry<String,Checker> entry: state.getBoard().entrySet())
			if(entry.getValue()==player)
				result.add(entry.getKey());
		return result;
	}
}
