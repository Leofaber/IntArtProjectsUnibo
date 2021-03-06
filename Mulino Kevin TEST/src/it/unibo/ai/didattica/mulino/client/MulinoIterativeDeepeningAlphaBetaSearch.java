package it.unibo.ai.didattica.mulino.client;

import java.util.Comparator;
import java.util.List;

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
		super.setLogEnabled(true);
	}
	
	@Override
	public String makeDecision(State state) {
		return super.makeDecision(game.getInitialState());
	}
	
	@Override
	public List<String> orderActions(State state, List<String> actions,
			Checker player, int depth) {
		
		actions.sort(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
		
		return actions;
	}
}