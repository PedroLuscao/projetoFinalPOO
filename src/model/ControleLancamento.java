package model;

import java.util.ArrayList;

public class ControleLancamento {
    
    private ArrayList<Lancamento> listaLancamentos = new ArrayList<>();

    public ControleLancamento() {

    }

    public ArrayList<Lancamento> getListaLancamentos() {
        return listaLancamentos;
    }

    public void addListaLancamentos(Lancamento l){
        listaLancamentos.add(l);
    }

    public double calcularSaldo(){
        return 0;
    }

    public double calcularSaldoAteHj(){
        return 0;
    }

}
