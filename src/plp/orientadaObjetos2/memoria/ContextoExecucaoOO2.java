package plp.orientadaObjetos2.memoria;

import java.util.ArrayList;
import java.util.HashMap;

import plp.expressions2.expression.Id;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.expressao.valor.Valor;
import plp.orientadaObjetos1.expressao.valor.ValorNull;
import plp.orientadaObjetos1.memoria.ContextoExecucaoOO1;
import plp.orientadaObjetos1.memoria.DefClasse;
import plp.orientadaObjetos1.memoria.colecao.ListaValor;
import plp.orientadaObjetos2.util.SuperClasseMap;

public class ContextoExecucaoOO2 extends ContextoExecucaoOO1 implements AmbienteExecucaoOO2 {
	
	private ArrayList<SuperClasseMap> arraySuperClasse;

	public ContextoExecucaoOO2() {
		super();
		arraySuperClasse = new ArrayList <SuperClasseMap> ();
	}
	
	public ContextoExecucaoOO2(AmbienteExecucaoOO2 ambiente) throws VariavelJaDeclaradaException {
		super(ambiente);
		arraySuperClasse = ((AmbienteExecucaoOO2) ambiente).getMapSuperClasse();
		HashMap<Id, Valor> aux = new HashMap<Id, Valor>();
		aux.put(new Id("super"), new ValorNull());
		getPilha().push(aux);
	}
	
	public ContextoExecucaoOO2(ListaValor entrada) throws VariavelJaDeclaradaException {
		super(entrada);
		arraySuperClasse = new ArrayList <SuperClasseMap> ();
		HashMap<Id, Valor> aux = new HashMap<Id, Valor>();
		aux.put(new Id("super"), new ValorNull());
		getPilha().push(aux);
	}

	@Override
	public ContextoExecucaoOO2 getContextoIdValor() throws VariavelJaDeclaradaException {
		ContextoExecucaoOO2 ambiente = new ContextoExecucaoOO2(this.getEntrada());
		ambiente.setPilha( getPilha() );
		ambiente.setSaida( getSaida() );
		return ambiente;
	}
	
	public void mapSuperClasse(Id classe, Id superClasse) throws ClasseNaoDeclaradaException {
		DefClasse defClasse = getDefClasse(superClasse);
		if (defClasse != null) {
			arraySuperClasse.add(new SuperClasseMap( 
					(plp.orientadaObjetos1.expressao.leftExpression.Id)classe, 
					(plp.orientadaObjetos1.expressao.leftExpression.Id)superClasse ));	
		}
	}

	public SuperClasseMap getSuperClasse(Id classe) throws ClasseNaoDeclaradaException {
		for(int i = 0; i < arraySuperClasse.size(); i++){
			String nomeClasse = arraySuperClasse.get(i).getClasse().toString();
			
			if (nomeClasse.equalsIgnoreCase( classe.toString() )) {
				return arraySuperClasse.get(i);
			}
		}
		return null;
	}	
	
	public ArrayList<SuperClasseMap> getMapSuperClasse() {
		return arraySuperClasse;
	}
}
