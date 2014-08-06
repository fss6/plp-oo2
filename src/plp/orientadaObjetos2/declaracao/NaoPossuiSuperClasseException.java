package plp.orientadaObjetos2.declaracao;

import plp.expressions2.expression.Id;

public class NaoPossuiSuperClasseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NaoPossuiSuperClasseException(Id id){
		super("Classe " + id + " não possui superclasse.");
	}

}
