package Controler;

import Model.ExpresoModel;
import Model.LatteModel;
import Model.MochaModel;

import java.util.Scanner;

public class FuncionarioController {
    Scanner sc = new Scanner(System.in);

    public void levarCliente () {
        System.out.println("levando cliente até uma mesa\n");
    }

    public void preparo (int n) {
        if (n == 1) {
            ExpresoModel cafe = new ExpresoModel();
            cafe.preparar();
        }
        if (n == 2) {
            MochaModel mochaModel = new MochaModel();
            mochaModel.preparar();
        }
        if (n == 3) {
            LatteModel latteModel = new LatteModel();
            latteModel.preparar();
        }
    }

    public void entregar () {
        System.out.println("pedido entregue para cliente\n");
    }

    public void receberFeedback (int n) {
        if (n == 1) {
            System.out.println("refazer pedido\n");
        } else {
            System.out.println("tenha um bom cafe\n");
        }
    }
}
