/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.time.LocalDate;
import java.time.Month;
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
        System.out.println(ControleLancamento.calcularSaldoAcumulado(LocalDate.of(2023, Month.SEPTEMBER, 30))+"");
    }
}
