package plp.orientadaObjetos2.memoria;

import java.util.ArrayList;

import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.expressao.leftExpression.Id;
import plp.orientadaObjetos1.memoria.ContextoCompilacaoOO1;
import plp.orientadaObjetos1.memoria.DefClasse;
import plp.orientadaObjetos1.memoria.colecao.ListaValor;
import plp.orientadaObjetos2.util.SuperClasseMap;

public class ContextoCompilacaoOO2 extends ContextoCompilacaoOO1 implements AmbienteCompilacaoOO2{
	
	private ArrayList<SuperClasseMap> arraySuperClasse;
	
	public ContextoCompilacaoOO2(ListaValor entrada) {
		super(entrada);		
		arraySuperClasse = new ArrayList <SuperClasseMap> ();
	}
	
	/**
	 * Mapeia o id da sub-classe em uma super-classe.
	 */
	public void mapSuperClasse(Id classe, Id superClasse) throws ClasseNaoDeclaradaException {
		DefClasse defClasse = getDefClasse(superClasse);
		if (defClasse != null) {
			arraySuperClasse.add(new SuperClasseMap( classe, superClasse ));	
		}
	}

	/**
	 * Dado o id de uma classe, 
	 * recupera a definicao da super-classe. 
	 */
	public SuperClasseMap getSuperClasse(Id classe) throws ClasseNaoDeclaradaException {
		for(int i=0; i < arraySuperClasse.size(); i++){
			String nomeClasse = arraySuperClasse.get(i).getClasse().toString();
			
			if(nomeClasse.equalsIgnoreCase( classe.toString() )) {
				return arraySuperClasse.get(i);
			}
		}
		return null;
	}

	@Override
	public void mapSuperClasse(plp.expressions2.expression.Id classe,
			plp.expressions2.expression.Id superClasse)
			throws ClasseNaoDeclaradaException {

		mapSuperClasse((Id)classe, (Id)superClasse);
		
	}

	@Override
	public SuperClasseMap getSuperClasse(plp.expressions2.expression.Id classe)
			throws ClasseNaoDeclaradaException {
		return getSuperClasse((Id)classe);
	}

}
