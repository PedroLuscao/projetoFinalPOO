package model;

import java.time.LocalDate;

public abstract class Lancamento {
    
    private LocalDate dataLancamento;
    private double valor;

    public Lancamento(double valor, LocalDate dataLancamento) {
        setDataLancamento(dataLancamento);
        setValor(valor);
    }
    
    public LocalDate getDataLancamento() {
        return dataLancamento;
    }
    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        if (valor <= 0){
            throw new IllegalArgumentException("Valor negativo para receitas");
        }
        this.valor = valor;
    }

    

}
