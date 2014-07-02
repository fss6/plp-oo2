package plp.orientadaObjetos1.expressao.binaria;

import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.orientadaObjetos1.expressao.Expressao;
import plp.orientadaObjetos1.expressao.leftExpression.LeftExpression;
import plp.orientadaObjetos1.expressao.valor.Valor;
import plp.orientadaObjetos1.expressao.valor.ValorBooleano;
import plp.orientadaObjetos1.expressao.valor.ValorInteiro;
import plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;
import plp.orientadaObjetos1.util.Tipo;
import plp.orientadaObjetos1.util.TipoClasse;
import plp.orientadaObjetos1.util.TipoPrimitivo;

/**
 * Um objeto desta classe representa uma expressao de Soma.
 */
public class ExpInstanceOf extends ExpBinaria {

    /**
     * Controi uma expressao de Soma com as sub-expressoes especificadas.
     * Assume-se que estas sub-expressoes resultam em <code>ValorInteiro</code>
     * quando avaliadas.
     * @param esq expressao da esquerda
     * @param dir expressao da direita
     */
    public ExpInstanceOf(Expressao esq, Expressao dir) {
        super(esq, dir, "<=>");
    }

    /**
     * Retorna o valor da expressao de Soma
     */
    public Valor avaliar(AmbienteExecucaoOO1 ambiente)
        throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
               ObjetoNaoDeclaradoException, ClasseNaoDeclaradaException {
        return obterResultadoInstanceOf(ambiente);
    }

    /**
     * Realiza a verificacao de tipos desta expressao.
     *
     * @param ambiente o ambiente de compila??o.
     * @return <code>true</code> se os tipos da expressao s?o v?lidos;
     *          <code>false</code> caso contrario.
     * @exception VariavelNaoDeclaradaException se existir um identificador
     *          nao declarado no ambiente.
     * @exception VariavelNaoDeclaradaException se existir um identificador
     *          declarado mais de uma vez no mesmo bloco do ambiente.
     */
    public boolean checaTipo(AmbienteCompilacaoOO1 ambiente)
        throws VariavelNaoDeclaradaException, ClasseNaoDeclaradaException {
        
    	boolean result = false;
        /*if (super.checaTipo(ambiente) &&
           ((TipoPrimitivo)getEsq().getTipo(ambiente)).eInteiro() &&
            ((TipoPrimitivo)getDir().getTipo(ambiente)).eInteiro()) {
            result = true;
        }
        else {
            result = false;
        }*/
        LeftExpression Obj1 = (LeftExpression) getEsq();
		LeftExpression Obj2   = (LeftExpression) getDir();
		

		Tipo tipoObj1 = ambiente.getTipo(Obj1.getId());
		Tipo tipoObj2 = ambiente.getTipo(Obj2.getId());
		
		if (tipoObj1 instanceof TipoClasse && tipoObj2 instanceof TipoClasse)
			result = true;
	

		return (result);//((Object)tipoObj2).getClass().equals(tipoObj1));
        //return result;
    }

    /**
     * Retorna os tipos possiveis desta expressao.
     *
     * @param ambiente o ambiente de compila??o.
     * @return os tipos possiveis desta expressao.
     * @throws ClasseNaoDeclaradaException 
     * @throws VariavelNaoDeclaradaException 
     */
    public Tipo getTipo(AmbienteCompilacaoOO1 ambiente) throws VariavelNaoDeclaradaException, ClasseNaoDeclaradaException {
    	
    	LeftExpression Obj = (LeftExpression) getEsq();
        return Obj.getTipo(ambiente);
    }

    /**
     * Retorna o valor inteiro que representa o resultado da soma das duas express?es
     * @param ambiente ? o Ambiente de Execu??o
     * @return o valor inteiro que representa o resultado da soma das duas express?es
     * @throws ClasseNaoDeclaradaException 
     */
    private ValorBooleano obterResultadoInstanceOf(AmbienteExecucaoOO1 ambiente)
            throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
                   ObjetoNaoDeclaradoException, ClasseNaoDeclaradaException{
        return new ValorBooleano(true);
    }
}
