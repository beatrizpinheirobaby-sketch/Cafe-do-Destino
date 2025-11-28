package View;

import Model.ComandaModel;
import Model.ProdutoModel;

import java.util.List;
import java.util.Map;

public class ComandaView {

    public void exibirComanda(ComandaModel comanda) {
        System.out.println("");
        System.out.println("===== COMANDA DO CLIENTE =====");
        System.out.println("Cliente: " + comanda.getCliente().getNome());
        System.out.println("-------------------------------");

        List<ProdutoModel> itens = comanda.getItens();
        for (ProdutoModel p : itens) {
            System.out.println("- " + p.getNome() + " R$ " + p.getPreco());
        }

        System.out.println("-------------------------------");
        System.out.println("TOTAL: R$ " + comanda.getTotal());
        System.out.println("PAGAMENTO: " + comanda.getFormaPagamento());
        System.out.println("===============================");
        System.out.println("");
    }


    public void exibirRelatorioCaixa(Map<String, Double> pagamentos, double totalGeral) {

        System.out.println("\n======== RELATÓRIO DE CAIXA ========");

        for (String forma : pagamentos.keySet()) {
            System.out.printf("%s: R$ %.2f%n", forma, pagamentos.get(forma));
        }

        System.out.println("------------------------------------");
        System.out.printf("TOTAL GERAL: R$ %.2f%n", totalGeral);
        System.out.println("====================================\n");
    }
}
