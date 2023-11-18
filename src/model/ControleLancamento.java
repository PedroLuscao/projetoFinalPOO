package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class ControleLancamento {

    public ControleLancamento() {

    }

    public ArrayList<Lancamento> getListaLancamentos() {
        ArrayList<Lancamento> listaLancamentos = new ArrayList<>();
        
        try(Scanner lancamentosTexto = new Scanner (new File("trabalho.csv"), "UTF-8")){
            lancamentosTexto.nextLine();
            while(lancamentosTexto.hasNext()){
                String textoCompleto = lancamentosTexto.nextLine();
                String[] split = textoCompleto.split(";");
                if(split[0].equals("RECEITA")){
                    listaLancamentos.add(new Receita(Double.parseDouble(split[3]), LocalDate.parse(split[2], DateTimeFormatter.ofPattern("dd/MM/yyyy")), TipoReceita.valueOf(split[1])));
                }
                
                if(split[0].equals("DESPESA")){
                    listaLancamentos.add(new Despesa(Double.parseDouble(split[3]), LocalDate.parse(split[2], DateTimeFormatter.ofPattern("dd/MM/yyyy")), TipoDespesa.valueOf(split[1])));
                }
            }
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Arquivo Não Encontrado.");
        }
        
        return listaLancamentos;
    }

    public static void addListaLancamentos(Lancamento l){
        String[] texto = new String[4];
        
        if (l instanceof Receita){
            texto[0] = "RECEITA";
            texto[1] = ((Receita)l).getTipoReceita().name();
            texto[2] = l.getDataLancamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            texto[3] = String.valueOf(l.getValor());
        }
        
        if (l instanceof Despesa){
            texto[0] = "DESPESA";
            texto[1] = ((Despesa)l).getTipoDespesa().name();
            texto[2] = l.getDataLancamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            texto[3] = String.valueOf(l.getValor());
        }
        
        
        File arquivo = new File("trabalho.csv");
        try(FileOutputStream fos = new FileOutputStream(arquivo, true);PrintWriter arquivoTexto = new PrintWriter(fos)){
            
            arquivoTexto.println(texto[0] + ";" + texto[1] + ";" + texto[2] + ";" + texto[3]);
            
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Arquivo Não Encontrado.");
        } catch (IOException ex) {
            throw new IllegalArgumentException("Erro ao fazer a escrita do arquivo.");
        }
    }

    public double calcularSaldo(){
        double total = 0;
        
        for (Lancamento l : getListaLancamentos()){
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
        
        for (Lancamento l : getListaLancamentos()){
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
