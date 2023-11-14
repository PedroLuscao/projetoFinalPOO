package model;

import java.time.LocalDate;

public abstract class Lancamento {
    
    private LocalDate dataLancamento;
    private Double valor;

    public Lancamento(Double valor, LocalDate dataLancamento) {
        setDataLancamento(dataLancamento);
        setValor(valor);
    }
    
    public LocalDate getDataLancamento() {
        return dataLancamento;
    }
    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }

    

}
