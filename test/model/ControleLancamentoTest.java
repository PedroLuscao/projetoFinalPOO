package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Felipe
 */
public class ControleLancamentoTest {

 
    @Before
    public void limparArquivoAntes() {
        limparArquivo();
    }

    
    @After
    public void limparArquivoDepois() {
        limparArquivo();
    }

   
    private void limparArquivo() {
        try (PrintWriter writer = new PrintWriter("trabalho.csv")) {
            writer.print("Categoria;Tipo;Data;Valor");
        } catch (FileNotFoundException e) {
            
        }
    }
    
    @Test
    public void testAddListaLancamentosReceita() {
        ControleLancamento.addListaLancamentos(new Receita(100.0, LocalDate.now(), TipoReceita.SALARIO));
        String conteudoDoArquivo = lerConteudoDoArquivo();
        assertTrue(conteudoDoArquivo.contains("RECEITA;SALARIO;" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ";100.0"));
    }
    
    @Test
    public void testAddListaLancamentosDespesa() {
        ControleLancamento.addListaLancamentos(new Despesa(100.0, LocalDate.now(), TipoDespesa.OUTRAS_DESPESAS));
        String conteudoDoArquivo = lerConteudoDoArquivo();
        assertTrue(conteudoDoArquivo.contains("DESPESA;OUTRAS_DESPESAS;" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ";100.0"));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testAddReceitaValorNegativo() throws Exception{
        Receita r = new Receita(-100.00, LocalDate.now(), TipoReceita.OUTRAS_RECEITAS);
        
    }
    
    @Test(expected=DateTimeException.class)
    public void testAddReceitaDataErrada() throws Exception{
        Receita r = new Receita(-100.00, LocalDate.of(2023, 15, 06), TipoReceita.OUTRAS_RECEITAS);
    }
    
     private String lerConteudoDoArquivo() {
        try (Scanner scanner = new Scanner(new File("trabalho.csv"))) {
            StringBuilder conteudo = new StringBuilder();
            while (scanner.hasNextLine()) {
                conteudo.append(scanner.nextLine());
            }
            return conteudo.toString();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Arquivo NÃ£o Encontrado.");
        }
    }

}