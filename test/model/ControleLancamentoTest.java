package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Felipe
 */
public class ControleLancamentoTest {

    ControleLancamento cl = new ControleLancamento();

    @Test
    public void CalcularSaldo() {

        assertEquals(2977.00, cl.calcularSaldo(), 0.000);

    }

    @Test
    public void CalcularSaldoAteHj() {

        assertEquals(1777.00, cl.calcularSaldoAteHj(), 0.000);

    }

    @Test
    public void testGetListaLancamentos() {

        assertEquals(7, cl.getListaLancamentos().size());

    }

    @Test
    public void testAddListaLancamentos() {

        Lancamento l = new Receita(150.0, LocalDate.of(2023, 10, 19), TipoReceita.SALARIO);
        cl.addListaLancamentos(l);

        boolean teste = false;

        try (Scanner scanner = new Scanner(new File("trabalho.csv"))) {
            while (scanner.hasNextLine()) {
                if (scanner.nextLine().equals("RECEITA;SALARIO;" + LocalDate.of(2023, 10, 19) + ";150.0")) {
                    teste = true;
                }
            }
        } catch (FileNotFoundException e) {

        }

        assertTrue(teste);
    }

}
