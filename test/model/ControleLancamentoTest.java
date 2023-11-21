package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
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
        assertEquals(1, ControleLancamento.getListaLancamentos().size());
    }
    
    @Test
    public void testAddListaLancamentosDespesa() {
        ControleLancamento.addListaLancamentos(new Despesa(100.0, LocalDate.now(), TipoDespesa.OUTRAS_DESPESAS));
        assertEquals(1, ControleLancamento.getListaLancamentos().size());
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
    public void calcularSaldoAteHjDespesaReceita(){
        Receita r = new Receita(100.00, LocalDate.of(2023, 9, 06), TipoReceita.OUTRAS_RECEITAS);
        ControleLancamento.addListaLancamentos(r);
        Receita r2 = new Receita(100.00, LocalDate.now().plusDays(5), TipoReceita.OUTRAS_RECEITAS);
        ControleLancamento.addListaLancamentos(r2);
        Despesa d = new Despesa(100.00, LocalDate.of(2023, 9, 06), TipoDespesa.ALIMENTACAO);
        ControleLancamento.addListaLancamentos(d);
        Despesa d2 = new Despesa(100.00, LocalDate.now().plusDays(5), TipoDespesa.ALIMENTACAO);
        ControleLancamento.addListaLancamentos(d2);
        assertEquals(0, ControleLancamento.calcularSaldoAteHj(),0.001);
    }
    
    @Test
    public void calcularSaldoAcum(){
        Despesa d = new Despesa(500.00, LocalDate.of(2023, 9, 06), TipoDespesa.ALIMENTACAO);
        ControleLancamento.addListaLancamentos(d);
        Receita r = new Receita(100.00, LocalDate.now().plusDays(5), TipoReceita.OUTRAS_RECEITAS);
        ControleLancamento.addListaLancamentos(r);
        assertEquals(-500.0, ControleLancamento.calcularSaldoAcumulado(LocalDate.of(2023, Month.NOVEMBER, 19)),0.001);
    }
    
    @Test
    public void testCalcularSaldoAposVariasTransacoes() {   
        ControleLancamento.addListaLancamentos(new Receita(500.0, LocalDate.now(), TipoReceita.FERIAS));
        ControleLancamento.addListaLancamentos(new Despesa(200.0, LocalDate.now(), TipoDespesa.ENTRETENIMENTO));
        ControleLancamento.addListaLancamentos(new Receita(300.0, LocalDate.now(), TipoReceita.OUTRAS_RECEITAS));
        assertEquals(600.0, ControleLancamento.calcularSaldo(), 0.001);
    }
    
    @Test
    public void testCalcularSaldoAteHjAposVariasTransacoes() {   
        ControleLancamento.addListaLancamentos(new Receita(500.0, LocalDate.now(), TipoReceita.FERIAS));
        ControleLancamento.addListaLancamentos(new Despesa(200.0, LocalDate.now(), TipoDespesa.ENTRETENIMENTO));
        ControleLancamento.addListaLancamentos(new Receita(300.0, LocalDate.now(), TipoReceita.OUTRAS_RECEITAS));
        ControleLancamento.addListaLancamentos(new Receita(500.0, LocalDate.now().plusDays(1), TipoReceita.FERIAS));
        ControleLancamento.addListaLancamentos(new Despesa(200.0, LocalDate.now().plusDays(1), TipoDespesa.ENTRETENIMENTO));
        ControleLancamento.addListaLancamentos(new Receita(300.0, LocalDate.now().plusDays(1), TipoReceita.OUTRAS_RECEITAS));
        assertEquals(600.0, ControleLancamento.calcularSaldoAteHj(), 0.001);
    }
    
    @Test
    public void testCalcularSaldoAcumuladoAposVariasTransacoes() {   
        ControleLancamento.addListaLancamentos(new Receita(100.0, LocalDate.of(2023,1,22), TipoReceita.FERIAS));
        ControleLancamento.addListaLancamentos(new Despesa(200.0, LocalDate.of(2023,2,12), TipoDespesa.ENTRETENIMENTO));
        ControleLancamento.addListaLancamentos(new Receita(300.0, LocalDate.of(2023,3,26), TipoReceita.OUTRAS_RECEITAS));
        ControleLancamento.addListaLancamentos(new Receita(400.0, LocalDate.of(2023,4,11), TipoReceita.FERIAS));
        ControleLancamento.addListaLancamentos(new Despesa(500.0, LocalDate.of(2023,5,3), TipoDespesa.ENTRETENIMENTO));
        ControleLancamento.addListaLancamentos(new Receita(600.0, LocalDate.of(2023,6,24), TipoReceita.OUTRAS_RECEITAS));
        assertEquals(100.0, ControleLancamento.calcularSaldoAcumulado(LocalDate.of(2023,5,3)), 0.001);
    }
    
    @Test
    public void testCalcularSaldoSemTransacoes() {
        assertEquals(0.0, ControleLancamento.calcularSaldo(), 0.001);
    }
    
    @Test
    public void testCalcularSaldoAteHjSemTransacoes() {
        assertEquals(0.0, ControleLancamento.calcularSaldoAteHj(), 0.001);
    }
    
    @Test
    public void testAddListaLancamentosAposVariasDespesas() {   
        ControleLancamento.addListaLancamentos(new Despesa(200.0, LocalDate.of(2023,2,12), TipoDespesa.ENTRETENIMENTO));
        ControleLancamento.addListaLancamentos(new Despesa(500.0, LocalDate.of(2023,5,3), TipoDespesa.ALIMENTACAO));
        ControleLancamento.addListaLancamentos(new Despesa(500.0, LocalDate.of(2023,5,3), TipoDespesa.RESIDENCIA));
        ControleLancamento.addListaLancamentos(new Despesa(450.0, LocalDate.of(2023,5,3), TipoDespesa.SAUDE));
        assertEquals(4, ControleLancamento.getListaLancamentos().size(), 0.001);
    }
    
    @Test
    public void testAddListaLancamentosAposVariasReceitas() {   
        ControleLancamento.addListaLancamentos(new Receita(200.0, LocalDate.of(2023,2,12), TipoReceita.DECIMO_TERCEIRO));
        ControleLancamento.addListaLancamentos(new Receita(500.0, LocalDate.of(2023,5,3), TipoReceita.FERIAS));
        ControleLancamento.addListaLancamentos(new Receita(500.0, LocalDate.of(2023,5,3), TipoReceita.OUTRAS_RECEITAS));
        ControleLancamento.addListaLancamentos(new Receita(450.0, LocalDate.of(2023,5,3), TipoReceita.SALARIO));
        assertEquals(4, ControleLancamento.getListaLancamentos().size(), 0.001);
    }
    
    @Test
    public void testAddListaLancamentosAposVariosLancamentos() {   
        ControleLancamento.addListaLancamentos(new Despesa(200.0, LocalDate.of(2023,2,12), TipoDespesa.ENTRETENIMENTO));
        ControleLancamento.addListaLancamentos(new Despesa(500.0, LocalDate.of(2023,5,3), TipoDespesa.ALIMENTACAO));
        ControleLancamento.addListaLancamentos(new Despesa(500.0, LocalDate.of(2023,5,3), TipoDespesa.RESIDENCIA));
        ControleLancamento.addListaLancamentos(new Despesa(450.0, LocalDate.of(2023,5,3), TipoDespesa.SAUDE));
        ControleLancamento.addListaLancamentos(new Receita(200.0, LocalDate.of(2023,2,12), TipoReceita.DECIMO_TERCEIRO));
        ControleLancamento.addListaLancamentos(new Receita(500.0, LocalDate.of(2023,5,3), TipoReceita.FERIAS));
        ControleLancamento.addListaLancamentos(new Receita(500.0, LocalDate.of(2023,5,3), TipoReceita.OUTRAS_RECEITAS));
        ControleLancamento.addListaLancamentos(new Receita(450.0, LocalDate.of(2023,5,3), TipoReceita.SALARIO));
        assertEquals(8, ControleLancamento.getListaLancamentos().size(), 0.001);
    }
}