package plp.orientadaObjetos1.expressao.binaria;

import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.orientadaObjetos1.expressao.Expressao;
import plp.orientadaObjetos1.expressao.leftExpression.Id;
import plp.orientadaObjetos1.expressao.leftExpression.LeftExpression;
import plp.orientadaObjetos1.expressao.valor.Valor;
import plp.orientadaObjetos1.expressao.valor.ValorBooleano;
import plp.orientadaObjetos1.expressao.valor.ValorConcreto;
import plp.orientadaObjetos1.expressao.valor.ValorRef;
import plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;
import plp.orientadaObjetos1.memoria.Objeto;
import plp.orientadaObjetos1.util.Tipo;
import plp.orientadaObjetos1.util.TipoClasse;
import plp.orientadaObjetos1.util.TipoPrimitivo;


/**
 * Um objeto desta classe representa uma expressao de Igualdade entre
 * Expressoes cuja avaliacao resulta num mesmo valor primitivo.
 */
public class ExpEquals extends ExpBinaria{

    /**
     * Controi uma expressao de Igualdade com as sub-expressoes especificadas.
     * Assume-se que estas sub-expressoes resultam num mesmo valor primitivo
     * quando avaliadas.
     * @param esq expressao da esquerda
     * @param dir expressao da direita
     */
    public ExpEquals(Expressao esq, Expressao dir){
        super(esq, dir, "==");
    }

    /**
     * Retorna o valor da expressao de Igualdade
     */
    public Valor avaliar(AmbienteExecucaoOO1 ambiente)
        throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
        ObjetoNaoDeclaradoException, ClasseNaoDeclaradaException {
        return verificarIgualdade(ambiente);
    }

    /**
     * Realiza a verificacao de tipos desta expressao.
     *
     * @param ambiente o ambiente de compila��o.
     * @return <code>true</code> se os tipos da expressao s�o v�lidos;
     *          <code>false</code> caso contrario.
     * @exception VariavelNaoDeclaradaException se existir um identificador
     *          nao declarado no ambiente.
     * @exception VariavelNaoDeclaradaException se existir um identificador
     *          declarado mais de uma vez no mesmo bloco do ambiente.
     */
    public boolean checaTipo(AmbienteCompilacaoOO1 ambiente)
        throws VariavelNaoDeclaradaException, ClasseNaoDeclaradaException {
        boolean result = false;
        
        /*if (super.checaTipo(ambiente)) {
           if(getEsq().getTipo(ambiente) instanceof TipoClasse ||
        		   getDir().getTipo(ambiente) instanceof TipoClasse) {
            result = true;
            System.out.println("Checa tipo! True");
           }
           else {
        	   System.out.println("Checa tipo! False");
            result = false;
           }
        }*/
     
        return true;
    }

    /**
     * Retorna os tipos possiveis desta expressao.
     *
     * @param ambiente o ambiente de compila��o.
     * @return os tipos possiveis desta expressao.
     */
    public Tipo getTipo(AmbienteCompilacaoOO1 ambiente) {
        return TipoPrimitivo.TIPO_BOOLEANO;
    }

    /**
     * Retorna o valor booleano que representa o resultado da comparacao de igualdade
     * de duas expressoes
     * @param ambiente � o Ambiente de Execu��o
     * @return o valor inteiro que representa o resultado da concatenacao de dois Strings
     * @throws ClasseNaoDeclaradaException 
     */
    private ValorBooleano verificarIgualdade(AmbienteExecucaoOO1 ambiente)
            throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
                   ObjetoNaoDeclaradoException, ClasseNaoDeclaradaException{
        
    	System.out.println("Verifica Igualdade!");
    	//Valor v1 = getEsq().avaliar(ambiente);
        //Valor v2 = getDir().avaliar(ambiente);
        
        ValorRef vr1 = (ValorRef) getEsq().avaliar(ambiente);  // recupera o id do objeto
        Objeto objeto =  ambiente.getObjeto(vr1);               // recupera o objeto
	    Id idClasse = objeto.getClasse();                      // recupera o tipo do objeto
	    System.out.println("ID: " + idClasse);
        
	    ValorRef vr2 = (ValorRef) getDir().avaliar(ambiente);  // recupera o id do objeto
        Objeto objeto2 =  ambiente.getObjeto(vr2);               // recupera o objeto
	    Id idClasse2 = objeto2.getClasse();                      // recupera o tipo do objeto
	    System.out.println("ID2: " + idClasse2);
        
        //LeftExpression idObjeto = (LeftExpression) getEsq();
		//LeftExpression classe   = (LeftExpression) getDir();
		
        boolean compara;
        /*if (v1 instanceof ValorConcreto && v2 instanceof ValorConcreto){
            compara = ((ValorConcreto)v1).equals((ValorConcreto)(v2));
        } else {
            compara =  v1.equals(v2); 
        }*/
        if (vr1.toString().equals(vr2.toString())){
            compara = true;
        } else {
            compara =  false;
            System.out.println("Estou aqui: " + false);
        }
        
     
        return new ValorBooleano(compara);
    }
}