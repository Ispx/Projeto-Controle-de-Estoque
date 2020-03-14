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
    public void addProduto(CadastroDeProduto produto){

        if(getProdutos().size() == 0){
            setProdutos(produto);
        }
        else{

            try {
                for (int i = 0; i < this.getProdutos().size(); i++) {

                    if (!this.getProdutos(i).getName().equalsIgnoreCase(produto.getName()) && this.getProdutos(i).getCod().equalsIgnoreCase(produto.getCod())) {
                        throw new IllegalArgumentException("Nome inválido para código já cadastrado!");
                    }
                    else{
                        setProdutos(produto);
                    }

                }
            }catch (IllegalArgumentException e ){
                System.err.println(e.getMessage());

            }

        }
    }



    @Override
    public void atualizar() {
        produtos.removeIf( x -> x.getQuantity() == 0);
    }

    @Override
    public void removerProduto(String cod, int qtd){
        try {
            if (qtd > getProdutos().stream().
                    filter(f -> f.getCod().equalsIgnoreCase(cod)).
                    flatMapToInt(x -> IntStream.of(x.getQuantity())).sum()) {
                throw new IllegalArgumentException("Quantidade inválida para remover!");
            } else {
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
            atualizar();
            dadosEstoque();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<CadastroDeProduto> getListaDeSaida() {
        return listaDeSaida;
    }

    public void CriarArquivo() {

        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(new File("C:\\Users\\isaqu\\Desktop\\Dados_Do_Estoque.txt")))) {

             csvWriter.writeAll(getDadosEstoque());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }

}
