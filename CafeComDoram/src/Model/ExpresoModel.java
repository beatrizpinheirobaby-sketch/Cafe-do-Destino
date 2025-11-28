package Model;

public class ExpresoModel extends ProdutoModel {

    public ExpresoModel() {
        super("Café Expresso", 7.5, "Café preto coado sem ou com açúcar");
    }

    public ExpresoModel(String nome, double preco, String descricao) {
        super(nome, preco, descricao);
    }

    @Override
    public void preparar() {
        System.out.println("Preparando expresso...\n\nCafé pronto!\n");
    }
}

