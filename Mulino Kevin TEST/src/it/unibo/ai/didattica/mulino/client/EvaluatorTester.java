package it.unibo.ai.didattica.mulino.client;

import it.unibo.ai.didattica.mulino.actions.Phase1;
import it.unibo.ai.didattica.mulino.actions.Phase1Action;
import it.unibo.ai.didattica.mulino.domain.State;
import it.unibo.ai.didattica.mulino.domain.State.Checker;

public class EvaluatorTester {

	public EvaluatorTester() {
		
	}

	public static void main(String[] args) {
		State state=new State();
		Phase1Action action;
		try{						
			action = new Phase1Action();
			action.setPutPosition("d1");
			state=Phase1.applyMove(state, action, Checker.BLACK);
			System.out.println(state.toString());
			HeuristicEvaluator eval=new HeuristicEvaluator(state, Checker.BLACK);
			System.out.println("TRIS NERI-TRIS BIANCHI: "+ eval.numberOfMorris());
			System.out.println("DOUBLE NERI-DOUBLE BIANCHI: "+ eval.doubleMorris());
			System.out.println("PEZZI NERI-PEZZI BIANCHI: "+eval.numberOfPieces());

		}catch(Exception e){
			System.out.println("Exception: "+e);
		}
		
		try{						
			action = new Phase1Action();
			action.setPutPosition("d2");
			state=Phase1.applyMove(state, action, Checker.BLACK);
			System.out.println(state.toString());
			HeuristicEvaluator eval=new HeuristicEvaluator(state, Checker.BLACK);
			System.out.println("TRIS NERI-TRIS BIANCHI: "+ eval.numberOfMorris());
			System.out.println("DOUBLE NERI-DOUBLE BIANCHI: "+ eval.doubleMorris());
			System.out.println("PEZZI NERI-PEZZI BIANCHI: "+eval.numberOfPieces());

		}catch(Exception e){
			System.out.println("Exception: "+e);
		}
		try{						
			action = new Phase1Action();
			action.setPutPosition("a7");
			state=Phase1.applyMove(state, action, Checker.WHITE);
			System.out.println(state.toString());
			HeuristicEvaluator eval=new HeuristicEvaluator(state, Checker.BLACK);
			System.out.println("TRIS NERI-TRIS BIANCHI: "+ eval.numberOfMorris());
			System.out.println("DOUBLE NERI-DOUBLE BIANCHI: "+ eval.doubleMorris());
			System.out.println("PEZZI NERI-PEZZI BIANCHI: "+eval.numberOfPieces());

		}catch(Exception e){
			System.out.println("Exception: "+e);
		}
		try{						
			action = new Phase1Action();
			action.setPutPosition("d3");
			action.setRemoveOpponentChecker("a7");
			state=Phase1.applyMove(state, action, Checker.BLACK);
			System.out.println(state.toString());
			HeuristicEvaluator eval=new HeuristicEvaluator(state, Checker.BLACK);
			System.out.println("TRIS NERI-TRIS BIANCHI: "+ eval.numberOfMorris());
			System.out.println("DOUBLE NERI-DOUBLE BIANCHI: "+ eval.doubleMorris());
			System.out.println("PEZZI NERI-PEZZI BIANCHI: "+eval.numberOfPieces());

		}catch(Exception e){
			System.out.println("Exception: "+e);
		}
		
		try{						
			action = new Phase1Action();
			action.setPutPosition("g1");
			state=Phase1.applyMove(state, action, Checker.WHITE);
			System.out.println(state.toString());
			HeuristicEvaluator eval=new HeuristicEvaluator(state, Checker.BLACK);
			System.out.println("TRIS NERI-TRIS BIANCHI: "+ eval.numberOfMorris());
			System.out.println("DOUBLE NERI-DOUBLE BIANCHI: "+ eval.doubleMorris());
			System.out.println("PEZZI NERI-PEZZI BIANCHI: "+eval.numberOfPieces());

		}catch(Exception e){
			System.out.println("Exception: "+e);
		}

		
	}

}
