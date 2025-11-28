package Model;

import java.util.ArrayList;
import java.util.List;

public class ComandaModel {

    private ClienteModel cliente;
    private List<ProdutoModel> itens;
    private double total;
    private String formaPagamento;

    public ComandaModel(ClienteModel cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<ProdutoModel>();
        this.total = 0.0;
        this.formaPagamento = "Pendente";
    }

    public void adicionarItem(ProdutoModel p) {
        itens.add(p);
        total += p.getPreco();
    }

    public double getTotal() {
        return total;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public List<ProdutoModel> getItens() {
        return itens;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }
}
