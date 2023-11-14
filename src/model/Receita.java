package model;

import java.time.LocalDate;

public class Receita extends Lancamento{
    
    private TipoReceita tipoReceita;

    public Receita(Double valor, LocalDate dataLancamento, TipoReceita tipoReceita) {
        super(valor, dataLancamento);
        this.tipoReceita = tipoReceita;
    }

    public TipoReceita getTipoReceita() {
        return tipoReceita;
    }

    public void setTipoReceita(TipoReceita tipoReceita) {
        this.tipoReceita = tipoReceita;
    }

}
