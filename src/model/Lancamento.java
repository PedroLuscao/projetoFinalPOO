package model;

import java.time.LocalDate;

/**
 * Classe responsavel para armazenar os atributos de um lancamento.
 * Um lancamento pode tanto uma despesa, quanto uma receita.
 * @author Felipe
 */

public abstract class Lancamento {
    
    /**
     * Atributo responsavel para armazenar a data do lancamento.
     */
    private LocalDate dataLancamento;
    
    /**
     * Atributo responsavel para armazenar o valor do lancamento.
     */
    
    private double valor;

    /**
     * Construtor resposavel para criar uma instância de Lancamento.
     * 
     * @param valor           O valor do lançamento.
     * @param dataLancamento  A data do lançamento.
     */
    
    public Lancamento(double valor, LocalDate dataLancamento) {
        setDataLancamento(dataLancamento);
        setValor(valor);
    }
    
    /**
     * Metodo responsavel para obter a data do lancamento.
     * @return Retorna a data do lancamento.
     */
    
    public LocalDate getDataLancamento() {
        return dataLancamento;
    }
    
    /**
     * Metodo responsavel para definir a data do lancamento.
     * @param dataLancamento A nova data do lancamento.
     */
    
    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
    
    /**
     * Metodo responsavel para obter o valor do lancamento.
     * @return Retorna o valor do lancamento.
     */
    
    public double getValor() {
        return valor;
    }
    
    /**
     * Metodo responsavel para definir o valor do lancamento.
     * @param valor O novo valor do lancamento.
     * @throws IllegalArgumentException Se o valor for igual a 0 ou negativo.
     */
    
    public void setValor(double valor) {
        if (valor <= 0){
            throw new IllegalArgumentException("Valor negativo para receitas");
        }
        this.valor = valor;
    }

}
