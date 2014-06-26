package plp.orientadaObjetos1.expressao.binaria;

import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.orientadaObjetos1.expressao.Expressao;
import plp.orientadaObjetos1.expressao.valor.Valor;
import plp.orientadaObjetos1.expressao.valor.ValorBooleano;
import plp.orientadaObjetos1.expressao.valor.ValorInteiro;
import plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;
import plp.orientadaObjetos1.util.Tipo;
import plp.orientadaObjetos1.util.TipoClasse;
import plp.orientadaObjetos1.util.TipoPrimitivo;


/**
 * Um objeto desta classe representa uma expressao de Menor Igual (esq <= dir) entre
 * Expressoes cuja avaliacao resulta num mesmo valor primitivo.
 */
public class ExpLE extends ExpBinaria{

    /**
     * Controi uma expressao de Menor Igual (esq <= dir) com as sub-expressoes especificadas.
     * Assume-se que estas sub-expressoes resultam num mesmo valor primitivo
     * quando avaliadas.
     * @param esq expressao da esquerda
     * @param dir expressao da direita
     */
    public ExpLE(Expressao esq, Expressao dir){
        super(esq, dir, "<=");
    }

    /**
     * Retorna o valor da expressao de Menor Igual (esq <= dir)
     */
    public Valor avaliar(AmbienteExecucaoOO1 ambiente)
        throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
        ObjetoNaoDeclaradoException, ClasseNaoDeclaradaException {
        return verificarMenorIgual(ambiente);
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
        if (super.checaTipo(ambiente)) {
           if(getEsq().getTipo(ambiente) instanceof TipoClasse) {
            result = getDir().getTipo(ambiente).equals(TipoClasse.TIPO_NULL) ||
                     getEsq().getTipo(ambiente).equals(getDir().getTipo(ambiente));
           }
           else {
            result = getEsq().getTipo(ambiente).equals(getDir().getTipo(ambiente));
           }
        }
        return result;
    }

    /**
     * Retorna os tipos possiveis desta expressao.
     *
     * @param ambiente o ambiente de compila??o.
     * @return os tipos possiveis desta expressao.
     */
    public Tipo getTipo(AmbienteCompilacaoOO1 ambiente) {
        return TipoPrimitivo.TIPO_BOOLEANO;
    }

    /**
     * Retorna o valor booleano que representa o resultado da comparacao de
     * Menor Igual (esq <= dir) entre duas expressoes
     * @param ambiente ? o Ambiente de Execu??o
     * @return o valor inteiro que representa o resultado da concatenacao de dois Strings
     * @throws ClasseNaoDeclaradaException 
     */
    private ValorBooleano verificarMenorIgual(AmbienteExecucaoOO1 ambiente)
            throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
                   ObjetoNaoDeclaradoException, ClasseNaoDeclaradaException{
        
    	int v1 = ((ValorInteiro)getEsq().avaliar(ambiente)).valor();
        int v2 = ((ValorInteiro)getDir().avaliar(ambiente)).valor();
       
        boolean compara = v1 <= v2 ? true : false;

        return new ValorBooleano(compara);
    }
}