package Controler;

import Model.ClienteModel;
import View.ClienteView;
import java.util.List;

public class ClienteController {

    private ClienteView clienteView;

    public ClienteController() {
        this.clienteView = new ClienteView();
    }

    public ClienteModel cadastrarCliente() {
        String nome = clienteView.lerNome();
        String cpf = clienteView.lerCpf();
        String telefone = clienteView.lerTelefone();

        ClienteModel cliente = new ClienteModel(
                nome.trim(),
                cpf.trim(),
                telefone.trim()
        );

        clienteView.mostrarClienteEncontrado(cliente);

        return cliente;
    }

    public ClienteModel buscar(List<ClienteModel> clientes, String nome) {

        if (nome == null) return null;

        // Normaliza entrada do usuário
        nome = nome.trim().toLowerCase();

        for (ClienteModel c : clientes) {
            if (c.getNome() != null) {

                String nomeCliente = c.getNome().trim().toLowerCase();

                if (nomeCliente.equals(nome)) {
                    return c;
                }
            }
        }
        return null;
    }
}
