package Controler;

import Model.ComandaModel;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CaixaController {

    private List<ComandaModel> comandas;

    public CaixaController() {
        comandas = new ArrayList<ComandaModel>();
    }

    public void registrarComanda(ComandaModel comanda) {
        comandas.add(comanda);
    }

    public double fecharCaixa() {
        double total = 0.0;
        for (int i = 0; i < comandas.size(); i++) {
            total += comandas.get(i).getTotal();
        }
        return total;
    }

    public List<ComandaModel> getComandas() {
        return comandas;
    }

    public void salvarRelatorio(String caminhoArquivo) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(caminhoArquivo, false));
            for (int i = 0; i < comandas.size(); i++) {
                ComandaModel c = comandas.get(i);
                pw.println("Cliente: " + c.getCliente().getNome());
                for (int j = 0; j < c.getItens().size(); j++) {
                    pw.println(c.getItens().get(j).getNome() + ";" + c.getItens().get(j).getPreco());
                }
                pw.println("TOTAL;" + c.getTotal());
                pw.println("PAGAMENTO;" + c.getFormaPagamento());
                pw.println("-----");
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar relatório do caixa: " + e.getMessage());
        } finally {
            if (pw != null) pw.close();
        }
    }
}
