/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import model.ControleLancamento;
import model.Lancamento;
import model.Receita;
import model.TipoDespesa;
import model.TipoReceita;

/**
 *
 * @author pedro
 */
public class Testes {
    public static void main(String[] args) {
        ArrayList<Lancamento> lista = ControleLancamento.getListaLancamentos();
        
        System.out.println(lista);
    }
}
