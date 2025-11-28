package Model;

import java.util.ArrayList;
import java.util.List;

public class CardapioModel {

    private static List<ProdutoModel> listaProduto;

    public CardapioModel() {
        listaProduto = new ArrayList<>();
        listaProduto.add(new ExpresoModel("Café Expresso", 7.5, "Cafe preto coado sem ou com açucar"));
        listaProduto.add(new MochaModel("Mocha", 15.5, "Cafe com leite vaporizado e chocolate"));
        listaProduto.add(new LatteModel("Latte", 12.0, "Café com leite cremoso"));
    }

    public List<ProdutoModel> getListaProduto() {
        return listaProduto;
    }

    public static void exibirProdutos() {
        for (int i = 0; i < listaProduto.size(); i++) {
            ProdutoModel produto = listaProduto.get(i);
            System.out.println((i + 1) + ". " + produto.getNome() + " R$ " + produto.getPreco());
            System.out.println("Descrição: " + produto.getDescricao() + "\n");
        }
    }
}
