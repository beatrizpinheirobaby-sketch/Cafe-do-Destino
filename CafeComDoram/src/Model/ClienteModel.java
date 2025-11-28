package Model;

public class ClienteModel extends PessoaModel {

    private String telefone;

    public ClienteModel() {}

    public ClienteModel(String nome, String cpf, String telefone) {
        super(nome.trim(), cpf.trim());
        this.telefone = telefone.trim();
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone.trim();
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Telefone: " + telefone);
    }
}
