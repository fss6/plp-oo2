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
import plp.orientadaObjetos1.expressao.valor.ValorRef;
import plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;
import plp.orientadaObjetos1.memoria.DefClasse;
import plp.orientadaObjetos1.memoria.Objeto;
import plp.orientadaObjetos1.util.Tipo;
import plp.orientadaObjetos1.util.TipoClasse;
import plp.orientadaObjetos1.util.TipoPrimitivo;
import plp.orientadaObjetos2.memoria.DefClasseOO2;


/**
 * Um objeto desta classe representa uma expressao de Igualdade entre
 * Expressoes cuja avaliacao resulta num mesmo valor primitivo.
 */
public class ExpInstanceOf extends ExpBinaria{

    /**
     * Controi uma expressao de Igualdade com as sub-expressoes especificadas.
     * Assume-se que estas sub-expressoes resultam num mesmo valor primitivo
     * quando avaliadas.
     * @param esq expressao da esquerda
     * @param dir expressao da direita
     */
    public ExpInstanceOf(Expressao esq, Expressao dir){
        super(esq, dir, "instanceof");
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
    	
    	LeftExpression expClasse = (LeftExpression) getDir();
    	//Levanta excecao caso a classe nao esteja declarada
    	DefClasse classe = ambiente.getDefClasse(expClasse.getId());
		
 
        if(getEsq().getTipo(ambiente) instanceof TipoClasse && classe != null) 
        	result = true;
            
        return result;
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
        
    	boolean compara = false;
        
        ValorRef vr = (ValorRef) getEsq().avaliar(ambiente); // recupera o id do objeto
        Objeto objeto =  ambiente.getObjeto(vr);// recupera o objeto
	    Id idObjeto = objeto.getClasse(); // recupera o tipo do objeto
	    
	    LeftExpression classe   = (LeftExpression) getDir();
	    DefClasseOO2 defClasse = (DefClasseOO2) ambiente.getDefClasse(idObjeto);
	    Id idSuperClasse = defClasse.getNomeSuperClasse();
	    
	    //Verifica se o objeto (expEsq) e da instancia da classe (expDir)
	    if (idObjeto.equals(classe))
	        compara = true;
	    
	    //Entra caso o objeto possua superclasse e se a condicao anterior
	    //nao tenha sido satisfeita.
	    while(idSuperClasse != null && compara != true){
	    	System.out.println("entrei");
	    	//Verifica se o objeto e da instancia das superclasses
	    	if(idSuperClasse.equals(classe)){
	    		compara = true;
	    		//idSuperClasse = null;
	    	}else{//caso contrario chama a superclasse da superclasse (se existir)
	    		defClasse = (DefClasseOO2) ambiente.getDefClasse(idSuperClasse);
	    	    idSuperClasse = defClasse.getNomeSuperClasse();
	    	}
	    }
	        
     
        return new ValorBooleano(compara);
    }
}