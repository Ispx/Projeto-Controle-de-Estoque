package Projeto_Controle_de_Estoque;

public interface Remover {


    void removerProduto(int index);
    void removerProdutoQtdNull();
    void removerProduto(int index, int qtd);
    void removerProduto(String cod, int qtd);


}
