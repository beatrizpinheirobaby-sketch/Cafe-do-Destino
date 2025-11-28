package View;

import Controler.ClienteController;
import Controler.ComandaController;
import Controler.FuncionarioController;
import Model.CardapioModel;
import Model.ClienteModel;
import Model.ComandaModel;
import Model.FuncionarioModel;
import Model.ProdutoModel;

import java.io.*;
import java.util.*;

public class Main {

    private static final String ARQUIVO_CLIENTES = "src/clientes.txt";
    private static final String ARQUIVO_COMANDAS = "src/comandas.txt";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        FuncionarioModel funcionario = new FuncionarioModel(
                "Pedro",
                "741589621430",
                1,
                1518.00
        );

        FuncionarioController funcionarioController = new FuncionarioController();
        ClienteController clienteController = new ClienteController();

        List<ClienteModel> clientes = carregarClientesDoArquivo();

        boolean turnoAtivo = true;

        System.out.println("🌸 Bem-vindo à Cafeteria 'Com Dorama'! 🌸");
        System.out.println("As portas se abrem, o aroma de café fresco preenche o ar...\n");

        System.out.println("👨‍🍳 Funcionário responsável pelo turno:");
        funcionario.exibirDados();
        System.out.println("-------------------------------------------");

        while (turnoAtivo) {

            System.out.println("\n📖 O que acontece agora?");
            System.out.println("1 - Um novo cliente entra na cafeteria");
            System.out.println("2 - Procurar um cliente já conhecido");
            System.out.println("3 - Registrar um pedido e acompanhar o preparo");
            System.out.println("4 - Encerrar o turno e contar a história do dia");
            System.out.print("Escolha: ");
            int opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {

                case 1: {
                    System.out.println("\n✨ A porta toca um pequeno sino... um cliente acabou de entrar.");
                    ClienteModel novo = clienteController.cadastrarCliente();
                    clientes.add(novo);
                    salvarClientesNoArquivo(clientes);
                    System.out.println("O cliente sorri e agradece por ser cadastrado.");
                    break;
                }

                case 2: {
                    System.out.println("\n🔎 O atendente olha ao redor...");
                    System.out.print("Nome do cliente que você está procurando: ");
                    String nome = sc.nextLine();

                    ClienteModel encontrada = clienteController.buscar(clientes, nome);

                    if (encontrada != null) {
                        System.out.println("\n📘 O atendente reconhece o cliente imediatamente:");
                        new ClienteView().mostrarClienteEncontrado(encontrada);
                    } else {
                        System.out.println("😕 O atendente pensa por um momento... mas não encontra esse nome.");
                    }
                    break;
                }

                case 3: {
                    if (clientes.isEmpty()) {
                        System.out.println("💔 Não há clientes cadastrados ainda.");
                        continue;
                    }

                    System.out.println("\n🚪 Um cliente se aproxima do balcão.");
                    System.out.print("Quem é o cliente? ");
                    String nome = sc.nextLine();

                    ClienteModel cliente = clienteController.buscar(clientes, nome);

                    if (cliente == null) {
                        System.out.println("O atendente não reconhece o cliente e se desculpa educadamente.");
                        continue;
                    }

                    System.out.println("\n😊 O atendente sorri e chama Pedro para acompanhar o cliente.");
                    funcionarioController.levarCliente();

                    ComandaController comandaController = new ComandaController(cliente);
                    CardapioModel cardapio = new CardapioModel();

                    int escolha;
                    do {
                        System.out.println("\n📜 *Cardápio com cheirinho de café quente*");
                        CardapioModel.exibirProdutos();
                        System.out.println("0 - Finalizar pedido");
                        System.out.print("O que o cliente deseja? ");

                        escolha = sc.nextInt();
                        sc.nextLine();

                        if (escolha >= 1 && escolha <= cardapio.getListaProduto().size()) {

                            ProdutoModel item = cardapio.getListaProduto().get(escolha - 1);

                            System.out.println("\n☕ O barista Pedro começa a preparar o pedido...");
                            funcionarioController.preparo(escolha);

                            comandaController.adicionarItem(item);

                            System.out.println("🍽 O item foi adicionado à comanda.");
                        }

                    } while (escolha != 0);

                    System.out.println("\n💳 O cliente informa a forma de pagamento:");
                    System.out.println("1 - Dinheiro");
                    System.out.println("2 - Débito");
                    System.out.println("3 - Crédito");
                    System.out.println("4 - Pix");
                    int pg = sc.nextInt();
                    sc.nextLine();

                    String forma;
                    switch (pg) {
                        case 1: forma = "Dinheiro"; break;
                        case 2: forma = "Débito"; break;
                        case 3: forma = "Crédito"; break;
                        default: forma = "Pix"; break;
                    }

                    comandaController.definirPagamento(forma);
                    comandaController.salvarComanda(ARQUIVO_COMANDAS);

                    funcionarioController.entregar();

                    System.out.println("\n☑ Pedido finalizado!");
                    new ComandaView().exibirComanda(comandaController.getComanda());

                    System.out.println("\n👀 Pedro pergunta educadamente:");
                    System.out.println("1 - Cliente insatisfeito");
                    System.out.println("2 - Cliente muito feliz");
                    int fb = sc.nextInt();
                    funcionarioController.receberFeedback(fb);

                    break;
                }

                case 4: {
                    turnoAtivo = false;

                    System.out.println("\n🌙 O sol começa a se pôr...");
                    System.out.println("A cafeteria está ficando vazia, e é hora de fechar o caixa.");

                    ComandaController relatorio = new ComandaController();

                    Map<String, Double> resumo = relatorio.gerarResumoPagamentos(ARQUIVO_COMANDAS);
                    double totalGeral = relatorio.calcularTotalGeral(resumo);

                    ComandaView view = new ComandaView();
                    view.exibirRelatorioCaixa(resumo, totalGeral);

                    relatorio.limparArquivo(ARQUIVO_COMANDAS);

                    System.out.println("\n📦 Todas as comandas do dia foram guardadas.");
                    System.out.println("As luzes se apagam. O turno chegou ao fim.");
                    break;
                }

                default:
                    System.out.println("❌ O atendente não entendeu essa ação.");
                    break;
            }
        }

        System.out.println("\n🌌 A cafeteria fecha suas portas...");
        System.out.println("Até amanhã! ☕💕");
    }

    // ----------------------- MÉTODOS DE ARQUIVO -----------------------

    private static List<ClienteModel> carregarClientesDoArquivo() {

        List<ClienteModel> lista = new ArrayList<>();
        File file = new File(ARQUIVO_CLIENTES);

        if (!file.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String linha;

            while ((linha = br.readLine()) != null) {
                if (!linha.contains(";")) continue;

                String[] dados = linha.split(";");
                if (dados.length != 3) continue;

                String nome = dados[0].trim();
                String cpf = dados[1].trim();
                String telefone = dados[2].trim();

                lista.add(new ClienteModel(nome, cpf, telefone));
            }

        } catch (Exception e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
        }

        return lista;
    }

    private static void salvarClientesNoArquivo(List<ClienteModel> clientes) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_CLIENTES))) {

            for (ClienteModel c : clientes) {
                pw.println(c.getNome() + ";" + c.getCpf() + ";" + c.getTelefone());
            }

        } catch (Exception e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }
}
