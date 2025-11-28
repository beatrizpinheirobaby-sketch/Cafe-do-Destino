package Model;

public class FuncionarioModel extends PessoaModel {
    private int matricula;
    private double salario;

    public FuncionarioModel(String nome, String cpf, int matricula, double salario) {
        super(nome, cpf);
        this.salario = salario;
        this.matricula = matricula;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
     public void exibirDados() {
        super.exibirDados();
        System.out.println("Matricula: " + matricula);
        System.out.println("Salario: " + salario);
    }
}
