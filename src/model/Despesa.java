package model;

import java.time.LocalDate;

/**
 * Classe responsavel para armazenar os atributos de uma Despesa
 * Despesa é um lancamento
 * @author Felipe
 */

public class Despesa extends Lancamento{
    
    /**
     * Atributo responsavel para armazenar o tipo dessa despesa
     */
    private TipoDespesa tipoDespesa;

    /**
     * Construtor responsavel para criar uma instancia de despesa
     * @param valor O valor da despesa
     * @param dataLancamento A data da despesa
     * @param tipoDespesa O tipo da despesa
     */
    
    public Despesa(double valor, LocalDate dataLancamento, TipoDespesa tipoDespesa) {
        super(valor, dataLancamento);
        setTipoDespesa(tipoDespesa);
    }

    /**
     * Metodo responsavel para obter o tipo da despesa
     * @return Retorna o tipo da despesa
     */
    
    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    /**
     * Metodo responsavel para definir o tipo da despesa
     * @param tipoDespesa O novo tipo da despesa
     */
    
    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

}
