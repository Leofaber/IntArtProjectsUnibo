package it.unibo.ai.didattica.mulino.client;

import it.unibo.ai.didattica.mulino.domain.State;
import aima.core.search.adversarial.AdversarialSearch;
import aima.core.search.adversarial.Game;
import aima.core.search.adversarial.IterativeDeepeningAlphaBetaSearch;

@SuppressWarnings("hiding")
public class MulinoIterativeDeepeningAlphaBetaSearch<State,String,Checker> extends
		IterativeDeepeningAlphaBetaSearch<State,String,Checker> implements AdversarialSearch<State, String>{

	public MulinoIterativeDeepeningAlphaBetaSearch(
			Game<State, String, Checker> game, double utilMin, double utilMax,
			int time) {
		super(game, utilMin, utilMax, time);
	}
	
	public String makeDecision(State state) {
		return super.makeDecision(game.getInitialState());
	}
	

	
}
