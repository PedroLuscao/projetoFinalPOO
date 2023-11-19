package model;

import java.time.LocalDate;

/**
 * Classe responsavel para armazenar os atributos de uma Receita
 * Receita é um lancamento
 * @author Felipe
 */

public class Receita extends Lancamento{
    
    /**
     * Atributo responsavel para armazenar o tipo dessa receita
     */
    
    private TipoReceita tipoReceita;
    
    /**
     * Construtor responsavel para criar uma instancia de receita
     * @param valor O valor da receita
     * @param dataLancamento A data da receita
     * @param tipoReceita O tipo da receita
     */
    
    public Receita(double valor, LocalDate dataLancamento, TipoReceita tipoReceita) {
        super(valor, dataLancamento);
        this.tipoReceita = tipoReceita;
    }

    /**
     * Metodo responsavel para obter o tipo da receita
     * @return Retorna o tipo da receita
     */
    
    public TipoReceita getTipoReceita() {
        return tipoReceita;
    }

    /**
     * Metodo responsavel para definir o tipo da receita
     * @param tipoReceita O novo tipo da receita
     */
    
    public void setTipoReceita(TipoReceita tipoReceita) {
        this.tipoReceita = tipoReceita;
    }

}
