/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.time.LocalDate;
import model.ControleLancamento;
import model.Receita;
import model.TipoDespesa;
import model.TipoReceita;

/**
 *
 * @author pedro
 */
public class Testes {
    public static void main(String[] args) {
        ControleLancamento control = new ControleLancamento();
        Receita receita = new Receita(200.0, LocalDate.now(), TipoReceita.OUTRAS_RECEITAS);
        
        control.addListaLancamentos(receita);
    }
}
