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

/**
 * Classe responsavel por controlar os lancamentos, sendo adicionar eles ao arquivo, fazer a leitura do arquivo, calcular o saldo da conta,
 * calcular o saldo da conta ate hj, e calcular o saldo ate aquele lancamento especifico.
 * @author Felipe
 */

public class ControleLancamento {

    /**
     * Construtor responsavel para criar uma instancia de Controle de Lancamento.
     */
    
    public ControleLancamento() {

    }

    /**
     * Metodo responsavel para fazer a leitura do arquivo CSV
     * @return Retorna uma lista com o tipo, o valor, e a data do lancamento.
     */
    public static ArrayList<Lancamento> getListaLancamentos() {
        ArrayList<Lancamento> listaLancamentos = new ArrayList<>();
        
        try(Scanner lancamentosTexto = new Scanner (new File("trabalho.csv"), "UTF-8")){
            lancamentosTexto.nextLine();
            while(lancamentosTexto.hasNext()){
                String textoCompleto = lancamentosTexto.nextLine();
                String[] split = textoCompleto.split(";");
                if(split[0].equals("RECEITA")){
                    Receita r = new Receita(Double.parseDouble(split[3]), LocalDate.parse(split[2], DateTimeFormatter.ofPattern("dd/MM/yyyy")), TipoReceita.valueOf(split[1]));
                    listaLancamentos.add(r);
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

    /**
     * Metodo responsavel para adicionar um objeto do tipo lancamento no arquivo, esvrendo ele no arquivo com os seus atributos especificos.
     * @param l Parametro do tipo lancamento, que vai ser adicionado ao arquivo.
     */
    
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

    
    /**
     * Metodo responsavel para calcular o saldo da conta, somando as receitas e subtraindo as despesas do valor final.
     * @return Retorna o saldo total da conta
     */
    public static double calcularSaldo(){
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
    
    /**
     * Metodo responsavel para calcular o saldo acumulado ate a data especificada, assim fazendo a atualizacao constante do saldo a cada lancamento feito.
     * @param data Paremetro que sera passado para verificar todos os saldos antes da data passada no parametro.
     * @return Retorna o valor do saldo ate a data especificada.
     */
    
    public static double calcularSaldoAcumulado(LocalDate data){
        double total = 0;
        
        for (Lancamento l : getListaLancamentos()){
            if (l instanceof Receita && (l.getDataLancamento().isBefore(data) || l.getDataLancamento().isEqual(data))){
                total += l.getValor();
            }
            
            if (l instanceof Despesa && (l.getDataLancamento().isBefore(data) || l.getDataLancamento().isEqual(data))){
                total -= l.getValor();
            }
        }
        
        return total;
    }

    
    /**
     * Metodo responsavel para calcular o saldo da conta ate o dia de hoje, fazendo verificacoes das datas dos lancamentos.
     * @return Retorna o calculo do saldo ate a data de hoje
     */
    public static double calcularSaldoAteHj(){
        double total = 0;
        
        for (Lancamento l : getListaLancamentos()){
            if (l instanceof Receita && (l.getDataLancamento().isBefore(LocalDate.now()) || l.getDataLancamento().isEqual(LocalDate.now()))){
                total += l.getValor();
            }
            
            if (l instanceof Despesa && (l.getDataLancamento().isBefore(LocalDate.now()) || l.getDataLancamento().isEqual(LocalDate.now()))){
                total -= l.getValor();
            }
        }
        
        return total;
    }

}
