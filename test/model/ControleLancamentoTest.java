package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
            writer.print("Categoria;Tipo;Data;Valor\n");
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
    
    @Test(expected=IllegalArgumentException.class)
    public void testAddDespesaValorNegativo() throws Exception{
        Despesa d = new Despesa(-100.00, LocalDate.now(), TipoDespesa.ALIMENTACAO);
    }
    
    @Test(expected=DateTimeException.class)
    public void testAddReceitaDataErrada() throws Exception{
        Receita r = new Receita(100.00, LocalDate.of(2023, 15, 06), TipoReceita.OUTRAS_RECEITAS);
    }
    
    private String lerConteudoDoArquivo() {
        try (Scanner scanner = new Scanner(new File("trabalho.csv"))) {
            StringBuilder conteudo = new StringBuilder();
            while (scanner.hasNextLine()) {
                conteudo.append(scanner.nextLine());
            }
            return conteudo.toString();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Arquivo nao Encontrado.");
        }
    }
    
    @Test
    public void calcularSaldoAddReceita(){
        Receita r = new Receita(100.00, LocalDate.of(2023, 12, 06), TipoReceita.OUTRAS_RECEITAS);
        ControleLancamento.addListaLancamentos(r);
        assertEquals(100.0, ControleLancamento.calcularSaldo(),0.001);
    }
    
    @Test
    public void calcularSaldoAddDespesa(){
        Despesa d = new Despesa(1000.00, LocalDate.of(2023, 12, 06), TipoDespesa.ALIMENTACAO);
        ControleLancamento.addListaLancamentos(d);
        assertEquals(-1000.0, ControleLancamento.calcularSaldo(),0.001);
    }
    
    @Test
    public void calcularSaldoAddDespesaReceita(){
        Despesa d = new Despesa(1000.00, LocalDate.of(2023, 12, 06), TipoDespesa.ALIMENTACAO);
        ControleLancamento.addListaLancamentos(d);
        Receita r = new Receita(100.00, LocalDate.of(2023, 12, 06), TipoReceita.OUTRAS_RECEITAS);
        ControleLancamento.addListaLancamentos(r);
        assertEquals(-900.0, ControleLancamento.calcularSaldo(),0.001);
    }
    
    @Test
    public void calcularSaldoAteHojeReceita(){
        Receita r = new Receita(100.00, LocalDate.of(2023, 9, 06), TipoReceita.OUTRAS_RECEITAS);
        ControleLancamento.addListaLancamentos(r);
        Receita r2 = new Receita(100.00, LocalDate.now().plusDays(5), TipoReceita.OUTRAS_RECEITAS);
        ControleLancamento.addListaLancamentos(r2);
        assertEquals(100, ControleLancamento.calcularSaldoAteHj(),0.001);
    }
    
    @Test
    public void calcularSaldoAteHojeDespesa(){
        Despesa d = new Despesa(1000.00, LocalDate.of(2023, 9, 06), TipoDespesa.ALIMENTACAO);
        ControleLancamento.addListaLancamentos(d);
        Despesa d2 = new Despesa(1000.00, LocalDate.now().plusDays(5), TipoDespesa.ALIMENTACAO);
        ControleLancamento.addListaLancamentos(d2);
        assertEquals(-1000.0, ControleLancamento.calcularSaldoAteHj(),0.001);
    }
    
    @Test
    public void calcularSaldoAcum(){
        Despesa d = new Despesa(500.00, LocalDate.of(2023, 9, 06), TipoDespesa.ALIMENTACAO);
        ControleLancamento.addListaLancamentos(d);
        Receita r = new Receita(100.00, LocalDate.now().plusDays(5), TipoReceita.OUTRAS_RECEITAS);
        ControleLancamento.addListaLancamentos(r);
        assertEquals(-500.0, ControleLancamento.calcularSaldoAcumulado(LocalDate.of(2023, Month.NOVEMBER, 19)),0.001);
    }

}