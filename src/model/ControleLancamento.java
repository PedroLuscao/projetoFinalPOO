package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControleLancamento {
    
    private ArrayList<Lancamento> listaLancamentos = new ArrayList<>();

    public ControleLancamento() {

    }

    public ArrayList<Lancamento> getListaLancamentos() {
        return listaLancamentos;
    }

    public void addListaLancamentos(Lancamento l){
        
        try(Scanner lancamentosTexto = new Scanner (new File("Trabalho.csv", "UTF-8"))){
            lancamentosTexto.nextLine();
            while(true){
                String textoCompleto = lancamentosTexto.nextLine();
                String[] split = textoCompleto.split(";");
                if(split[0].equals("RECEITA")){
                    listaLancamentos.add(new Receita(Double.parseDouble(split[3]), LocalDate.parse(split[2]), TipoReceita.valueOf(split[1])));
                }
                
                if(split[0].equals("DESPESA")){
                    listaLancamentos.add(new Despesa(Double.parseDouble(split[3]), LocalDate.parse(split[2]), TipoDespesa.valueOf(split[1])));
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControleLancamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
