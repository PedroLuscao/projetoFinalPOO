package model;

import java.time.LocalDate;

public class Despesa extends Lancamento{
    
    private TipoDespesa tipoDespesa;

    public Despesa(double valor, LocalDate dataLancamento, TipoDespesa tipoDespesa) {
        super(valor, dataLancamento);
        setTipoDespesa(tipoDespesa);
    }

    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

}
