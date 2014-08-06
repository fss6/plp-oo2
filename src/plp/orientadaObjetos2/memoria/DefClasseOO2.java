package plp.orientadaObjetos2.memoria;

import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.orientadaObjetos1.comando.Comando;
import plp.orientadaObjetos1.declaracao.procedimento.DecProcedimento;
import plp.orientadaObjetos1.declaracao.procedimento.ListaDeclaracaoParametro;
import plp.orientadaObjetos1.declaracao.variavel.DecVariavel;
import plp.orientadaObjetos1.excecao.declaracao.ClasseJaDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ObjetoJaDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.orientadaObjetos1.excecao.execucao.EntradaInvalidaException;
import plp.orientadaObjetos1.expressao.leftExpression.Id;
import plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;
import plp.orientadaObjetos1.memoria.DefClasse;
import plp.orientadaObjetos2.declaracao.DecConstrutor;

public class DefClasseOO2 extends DefClasse {

	/**
	 * Nome super classe
	 */
	private Id nomeSuperClasse;
	
	/**
	 * Construtor
	 */
	private DecConstrutor construtor;

	public DefClasseOO2(Id idClasse, Id nomeSuperClasse,DecVariavel decVariavel,
			DecConstrutor construtor, DecProcedimento decProcedimento) {
		super(idClasse, decVariavel, decProcedimento);
		this.nomeSuperClasse = nomeSuperClasse;
		this.construtor = construtor;
	}

	public DecConstrutor getConstrutor() {
		return construtor;
	}

	public void setConstrutor(DecConstrutor construtor) {
		this.construtor = construtor;
	}

	/**
	 * @return the nomeSuperClasse
	 */
	public Id getNomeSuperClasse() {
		return nomeSuperClasse;
	}

	/**
	 * @param nomeSuperClasse the nomeSuperClasse to set
	 */
	public void setNomeSuperClasse(Id nomeSuperClasse) {
		this.nomeSuperClasse = nomeSuperClasse;
	}
}
