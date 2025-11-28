package Controler;

import Model.ComandaModel;
import Model.ProdutoModel;
import Model.ClienteModel;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComandaController {

    private ComandaModel comanda;

    public ComandaController() {}

    public ComandaController(ClienteModel cliente) {
        this.comanda = new ComandaModel(cliente);
    }

    public void adicionarItem(ProdutoModel item) {
        comanda.adicionarItem(item);
    }

    public void definirPagamento(String forma) {
        comanda.setFormaPagamento(forma);
    }

    public ComandaModel getComanda() {
        return comanda;
    }

    public void salvarComanda(String caminhoArquivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(caminhoArquivo, true))) {

            pw.println("CLIENTE;" + comanda.getCliente().getNome());

            for (ProdutoModel item : comanda.getItens()) {
                pw.println(item.getNome() + ";" + item.getPreco());
            }

            pw.println("TOTAL;" + comanda.getTotal());
            pw.println("PAGAMENTO;" + comanda.getFormaPagamento());
            pw.println("-----");

        } catch (Exception e) {
            System.out.println("Erro ao salvar comanda: " + e.getMessage());
        }
    }

    public Map<String, Double> gerarResumoPagamentos(String caminhoArquivo) {

        Map<String, Double> mapa = new HashMap<>();

        File file = new File(caminhoArquivo);
        if (!file.exists()) return mapa;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String linha;
            String formaAtual = null;
            double totalAtual = 0;

            while ((linha = br.readLine()) != null) {

                if (linha.startsWith("TOTAL;")) {
                    totalAtual = Double.parseDouble(linha.split(";")[1]);
                }

                if (linha.startsWith("PAGAMENTO;")) {
                    formaAtual = linha.split(";")[1].trim();

                    mapa.put(formaAtual, mapa.getOrDefault(formaAtual, 0.0) + totalAtual);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao gerar relatório: " + e.getMessage());
        }

        return mapa;
    }

    public double calcularTotalGeral(Map<String, Double> mapa) {
        return mapa.values().stream().mapToDouble(Double::doubleValue).sum();
    }


    public void limparArquivo(String caminhoArquivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(caminhoArquivo))) {
        } catch (Exception e) {
            System.out.println("Erro ao limpar arquivo: " + e.getMessage());
        }
    }
}
