package model;

import java.time.LocalDate;
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
        double total = 0;
        
        for (Lancamento l : listaLancamentos){
            if (l instanceof Receita){
                total += l.getValor();
            }
            
            if (l instanceof Despesa){
                total -= l.getValor();
            }
        }
        
        return total;
    }

    public double calcularSaldoAteHj(){
        double total = 0;
        
        for (Lancamento l : listaLancamentos){
            if (l instanceof Receita && l.getDataLancamento().isBefore(LocalDate.now())){
                total += l.getValor();
            }
            
            if (l instanceof Despesa && l.getDataLancamento().isBefore(LocalDate.now())){
                total -= l.getValor();
            }
        }
        
        return total;
    }

}
