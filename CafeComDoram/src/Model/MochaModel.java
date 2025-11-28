package Model;

public class MochaModel extends ProdutoModel{
    public MochaModel() {
        super("Mocha", 15.5, "Cafe com leite vaporizado e chocolate");
    }

    public MochaModel(String nome, double preco, String descricao) {
        super(nome, preco, descricao);
    }
    @Override
    public void preparar() {
        System.out.println("Preparando Mocha\n\nMocha pronto\n");
    }
}
