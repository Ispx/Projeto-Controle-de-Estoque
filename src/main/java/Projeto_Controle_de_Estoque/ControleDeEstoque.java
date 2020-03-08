package Projeto_Controle_de_Estoque;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class ControleDeEstoque implements Remover, Entrada{

    private List<String[]> dadosEstoque;
    private Set<CadastroDeProduto> setCod = new HashSet<>();
    protected List<CadastroDeProduto> produtos = new ArrayList<>();

    public ControleDeEstoque(){}

    private void resetDadosEstoque(){
        /*Para não gerar uma sobrecarregar de referências de objetos na memória, primeiramento eu anulo os valores presente no objeto
        dadosEstoque, em seguida eu instâncio uma nova referência de objeto na memória.
        */
        dadosEstoque = null;
        dadosEstoque = new ArrayList<>();
    }
    public void setSetCod(){
        setCod.addAll(getProdutos());
    }
    public void dadosEstoque(){

        //Adicionandos os códigos cadastrados no objeto setCod
        setSetCod();
        //Resetando os valores do objeto dadosEstoque para adicionar os valores atualizados através do método seguinte
        resetDadosEstoque();
        //Adicionando os produtos cadastrados na Lista DadosEstoque

        setCod.forEach(x -> setDadosEstoque(new String[]{
                "Cod: " + x.getCod() + " Nome:  " + x.getName() +

                " - Preço médio: " +

                String.format("%.2f",getProdutos().stream().filter(f1 -> f1.getCod().equalsIgnoreCase(x.getCod())).mapToDouble(m1 -> m1.getQuantity() * m1.getPrice()).sum()/
                        getProdutos().stream().filter(f2 -> f2.getCod().equalsIgnoreCase(x.getCod())).mapToInt(CadastroDeProduto::getQuantity).sum()) +

                " - Qtd: " +
                getProdutos().stream().filter(f3 -> f3.getCod().equalsIgnoreCase(x.getCod())).mapToInt(CadastroDeProduto::getQuantity).sum() +

                " - Total R$ " +
                getProdutos().stream().filter(f4 -> f4.getCod().equalsIgnoreCase(x.getCod())).mapToDouble(m4 -> m4.getPrice() * m4.getQuantity()).sum()})

        );
    }

    public void printDados(){
        System.out.println("========================= DADOS DO ESTOQUE =================================");

        //Atualizando os dados do estoque
        dadosEstoque();
        //Flattening a lista de vetores para apenas lista
        List<String> elements = getDadosEstoque().stream().flatMap(Stream::of).collect(Collectors.toList());

        elements.forEach(System.out::println);

    }

    public CadastroDeProduto getProdutos(int i){ return produtos.get(i); }
    public List<CadastroDeProduto> getProdutos(){ return produtos; }
    public void setProdutos(CadastroDeProduto produtos) {
        this.produtos.add(produtos);
    }

    public List<String[]> getDadosEstoque() { return dadosEstoque; }
    public void setDadosEstoque(String[] dadosEstoque) {
        this.dadosEstoque.add(dadosEstoque);
    }

    public Set getSetCod(){
        return setCod;
    }

}
