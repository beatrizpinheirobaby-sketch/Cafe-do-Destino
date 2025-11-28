package View;

import java.util.Scanner;
import Model.ClienteModel;

public class ClienteView {

    private Scanner sc = new Scanner(System.in);

    public String lerNome() {
        System.out.print("Nome: ");
        return sc.nextLine();
    }

    public String lerCpf() {
        System.out.print("CPF: ");
        return sc.nextLine();
    }

    public String lerTelefone() {
        System.out.print("Telefone: ");
        return sc.nextLine();
    }

    public String lerNomeBusca() {
        System.out.print("Digite o nome para buscar: ");
        return sc.nextLine();
    }

    public void mostrarClienteEncontrado(ClienteModel cliente) {
        System.out.println("\n== Cliente ==");
        cliente.exibirDados();
    }
}
