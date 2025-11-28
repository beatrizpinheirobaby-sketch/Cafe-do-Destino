package Model;

public class LatteModel extends ProdutoModel {

    public LatteModel() {
        super("Latte", 12.0, "Café com leite cremoso");
    }

    public LatteModel(String nome, double preco, String descricao) {
        super(nome, preco, descricao);
    }

    @Override
    public void preparar() {
        System.out.println("Preparando Latte\n\nLatte pronto!\n");
    }
}
