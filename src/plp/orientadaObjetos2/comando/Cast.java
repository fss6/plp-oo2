package plp.orientadaObjetos2.comando;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import javax.swing.JOptionPane;

import plp.orientadaObjetos1.comando.Comando;
import plp.orientadaObjetos1.excecao.declaracao.ClasseJaDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ObjetoJaDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.VariavelJaDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.execucao.EntradaInvalidaException;
import plp.orientadaObjetos1.expressao.leftExpression.Id;
import plp.orientadaObjetos1.expressao.leftExpression.LeftExpression;
import plp.orientadaObjetos1.expressao.valor.Valor;
import plp.orientadaObjetos1.expressao.valor.ValorBooleano;
import plp.orientadaObjetos1.expressao.valor.ValorRef;
import plp.orientadaObjetos1.util.Tipo;
import plp.orientadaObjetos1.expressao.binaria.ExpInstanceOf;
import plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;
import plp.orientadaObjetos1.memoria.Objeto;
import plp.orientadaObjetos2.declaracao.NaoPossuiSuperClasseException;
import plp.orientadaObjetos2.memoria.AmbienteCompilacaoOO2;
import plp.orientadaObjetos2.memoria.AmbienteExecucaoOO2;
import plp.orientadaObjetos2.util.SuperClasseMap;

/**
 * Comando de cast de objeto e atribuição deste a uma expressão esquerda.
 */
public class Cast implements Comando {

	/**
	 * Lado esquerdo do cast.
	 */
	private LeftExpression av;

	/**
	 * Identificador do objeto a ser atribuido.
	 */
	private Id idObjeto;

	/**
	 * Tipo da classe para fazer cast.
	 */
	private Tipo classeCast;

	/**
	 * Construtor.
	 * 
	 * @param av
	 *            Lado esquerdo da atribuição.
	 * @param classe
	 *            Identificador com o nome da classe.
	 * @param classeCast
	 *            Tipo da classe para ser dado o cast.
	 */
	public Cast(LeftExpression av, Id idObjeto, Tipo classeCast) {
		this.av = av;
		this.idObjeto = idObjeto;
		this.classeCast = classeCast;
	}

	/**
	 * Execução de cast de um objeto a uma left expression.
	 * 
	 * @param ambiente
	 *            O ambiente contendo o mapeamento entre identificadores e
	 *            valores.
	 * @return o ambiente de execução atualizado.
	 */
	public AmbienteExecucaoOO2 executar(AmbienteExecucaoOO2 ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
			ObjetoJaDeclaradoException, ObjetoNaoDeclaradoException {

		ExpInstanceOf expInstanceOf = new ExpInstanceOf(idObjeto,
				classeCast.getTipo());
		
		if (!((ValorBooleano) expInstanceOf.avaliar(ambiente)).valor()) {
			throw new ClassCastException("'" + idObjeto
					+ " não é uma instancia de " + classeCast + "'");
		}
		
		
		
		System.out.println("Executar cast");
		
//		Valor valorObjeto = ambiente.getValor(idObjeto);
//		Valor valorAv = ambiente.getValor(av.getId());
//		
//		Objeto objeto = ambiente.getMapObjetos().get(valorObjeto);
//		Objeto objetoAv = ambiente.getMapObjetos().get(valorAv);
//		
//		objetoAv.setEstado(objeto.getEstado());
				
		
		

		return ambiente;
	}

	/**
	 * Verifica se a atribuição é possível comparando os tipos do objeto e da
	 * left expression bem como verificando se esta também é filha da classe
	 * representada por <code>classeCast</code>.
	 * 
	 * @param ambiente
	 *            O ambiente de compilação, com o mapeamento entre
	 *            identificadores e tipos.
	 */
	public boolean checaTipo(AmbienteCompilacaoOO2 ambiente)
			throws VariavelNaoDeclaradaException, ClasseJaDeclaradaException,
			ClasseNaoDeclaradaException {

		System.out.println("Checa tipo do cast");

		AmbienteCompilacaoOO2 ambienteOO = (AmbienteCompilacaoOO2) ambiente;
		
		return av.checaTipo(ambienteOO)
				&& eValido(ambienteOO, classeCast.getTipo());

		// boolean result = av.checaTipo(ambiente) &&
		// ambiente.getTipo(av.getId()).
		// equals(classeCast);
		//
		// if (!result) {
		// return result;
		// }
		//
		// return eValido(ambienteOO, classeCast.getTipo());
	}

	/**
	 * Verifica recusivamente se o a classe do objeto representado por
	 * <code>idObjeto</code> tem como subclasse <code>classeCast</code>.
	 * 
	 * @param ambiente
	 *            O ambiente de compilação idClasse O id da classe.
	 */
	private boolean eValido(AmbienteCompilacaoOO2 ambiente, Id idClasse)
			throws VariavelNaoDeclaradaException {

		System.out.println("É válido do cast");

		try {
			Id tipoObjeto = ambiente.getTipo(idObjeto).getTipo();
			Id tipoCast = idClasse;
			boolean hierarquia = false;
			//Se o tipoObjeto e uma subclasse do tipoCast, entao o cast e valido, embora desnecessario
			//Se o tipoCast e uma subclasse do tipoObjeto, entao o cast pode ser valido, deve ser verificado em tempo de execucao
			if (eSubClasse(tipoObjeto, tipoCast, ambiente) || eSubClasse(tipoCast, tipoObjeto, ambiente)) {
				hierarquia = true;
			}

			System.out.println("Tipo objeto " + tipoObjeto.toString());
			System.out.println("Tipo cast " + tipoCast.toString()+"\n"+hierarquia);
			
			//Verifica se o tipoCast e do mesmo tipo da leftExpression
			return (hierarquia && eSubClasse(tipoCast, av.getTipo(ambiente).getTipo(), ambiente));
				
			
		} catch (ClasseNaoDeclaradaException e) {
			return false;
		}
	}

	private boolean eSubClasse(Id tipoObj, Id tipoClasse,
			AmbienteCompilacaoOO2 ambiente) throws ClasseNaoDeclaradaException {
		try {
			if (tipoObj.toString().equals(tipoClasse.toString())) {
				return true;
			} else {
				return eSubClasse(ambiente.getSuperClasse(tipoObj)
						.getSuperClasse(), tipoClasse, ambiente);
			}
		} catch (NullPointerException e) {
			// SuperClasse nao foi encontrada
			return false;
		}
	}

	@Override
	public AmbienteExecucaoOO1 executar(AmbienteExecucaoOO1 ambiente)
			throws plp.expressions2.memory.VariavelJaDeclaradaException,
			plp.expressions2.memory.VariavelNaoDeclaradaException,
			ObjetoJaDeclaradoException, ObjetoNaoDeclaradoException,
			ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ClasseJaDeclaradaException,
			ClasseNaoDeclaradaException, EntradaInvalidaException {

		try {
			return executar((AmbienteExecucaoOO2) ambiente);
		} catch (VariavelJaDeclaradaException | VariavelNaoDeclaradaException e) {
			e.printStackTrace();
			return ambiente;
		}
	}

	@Override
	public boolean checaTipo(AmbienteCompilacaoOO1 ambiente)
			throws plp.expressions2.memory.VariavelNaoDeclaradaException,
			plp.expressions2.memory.VariavelJaDeclaradaException,
			ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ClasseJaDeclaradaException,
			ClasseNaoDeclaradaException {
		// TODO Auto-generated method stub

		try {
			return checaTipo((AmbienteCompilacaoOO2) ambiente);
		} catch (VariavelNaoDeclaradaException e1) {
			e1.printStackTrace();
			return false;
		}

		// AmbienteCompilacaoOO2 ambienteOO = (AmbienteCompilacaoOO2)ambiente;
		//
		// try {
		// return av.checaTipo(ambienteOO) && eValido(ambienteOO,
		// classeCast.getTipo());
		// } catch (VariavelNaoDeclaradaException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// throw new plp.expressions2.memory.VariavelNaoDeclaradaException(new
		// plp.expressions2.expression.Id(""));
		// }

	}

}