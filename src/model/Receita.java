package model;

import java.time.LocalDate;

public class Receita extends Lancamento{
    
    private TipoReceita tipoReceita;
    private double valor;

    public Receita(double valor, LocalDate dataLancamento, TipoReceita tipoReceita) {
        super(valor, dataLancamento);
        this.tipoReceita = tipoReceita;
    }

    public TipoReceita getTipoReceita() {
        return tipoReceita;
    }

    public void setTipoReceita(TipoReceita tipoReceita) {
        this.tipoReceita = tipoReceita;
    }
    
    @Override
    public void setValor(double valor){
        if (valor <= 0){
            throw new IllegalArgumentException("Valor de receita invalido.");
        }
        
        this.valor = valor;
    }

}
