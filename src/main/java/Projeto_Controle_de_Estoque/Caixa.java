package Projeto_Controle_de_Estoque;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Caixa extends ControleDeEstoque {

    private List<CadastroDeProduto> listaDeSaida = new ArrayList<>();

    @Override
    public void addProduto(CadastroDeProduto produto) {
        setProdutos(produto);
    }

    @Override
    public void removerProduto(int index) {
        produtos.remove(index);

    }

    @Override
    public void removerProdutoQtdNull() {
        produtos.removeIf( x -> x.getQuantity() == 0);
    }

    @Override
    public void removerProduto(String cod, int qtd) {

        if(qtd > getProdutos().stream().filter(f -> f.getCod().equalsIgnoreCase(cod)).flatMapToInt(x -> IntStream.of(x.getQuantity())).sum()){
            System.err.println("Quantidade inválida para remover!");
        }
        else {
            for (int i = 0; i < getProdutos().size(); i++) {

                if (getProdutos(i).getCod().equalsIgnoreCase(cod)) {

                    if (getProdutos(i).getQuantity() - qtd < 0) {

                        qtd = qtd - getProdutos(i).getQuantity();
                        getProdutos(i).setQuantity(0);

                    } else if (getProdutos(i).getQuantity() - qtd >= 0) {

                        getProdutos(i).setQuantity(getProdutos(i).getQuantity() - qtd);

                        break;
                    }
                }
            }
        }
        removerProdutoQtdNull();
        dadosEstoque();
    }


    @Override
    public void removerProduto(int index,int qtd) {

        getProdutos(index).setQuantity(getProdutos(index).getQuantity()-qtd);
        listaDeSaida.add(getProdutos(index));
        //Atualizando os dados do estoque
    }

    public List<CadastroDeProduto> getListaDeSaida() {
        return listaDeSaida;
    }

    public void CriarArquivo() {

        File file = new File("C:\\Users\\isaqu\\Desktop\\Dados_Do_Estoque.txt");


        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(file))) {

             csvWriter.writeAll(getDadosEstoque());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }

    }
