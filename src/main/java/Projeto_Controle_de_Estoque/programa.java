package Projeto_Controle_de_Estoque;

import java.util.Scanner;

/*
Criar um método para apresentar uma listagem dos x produtos mais vendidos.
Apresentar a quantidade de produtos vendidos de determinado elemento e o total;


 */
public class programa {
    public static void main(String[] args){


        Caixa caixa = new Caixa();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n");
            System.out.println("1 - Adicionar produto:" + "\n" + "2 - Retirar produto:" + "\n" + "3 - Dados do estoque:" + "\n" + "4 - Produtos mais vendidos: (Em Desenvolvimento)" + "\n" + "5 - Sair do programa:");

            int input = sc.nextInt();
            if (input==5){break;}
            switch (input) {
                case 1:
                    System.out.println("Informe a quantidade de produtos a ser adicionado:");
                    int inputQtd = sc.nextInt();
                    for (int i = 0; i <= inputQtd; i++) {
                        System.out.println();
                        System.out.print("Código:");
                        String cod = sc.next();
                        System.out.print("Nome: ");
                        String nome = sc.next();
                        System.out.print("Preço: R$ ");
                        Double preco = sc.nextDouble();
                        System.out.print("Quantidade: ");
                        int qtd = sc.nextInt();
                        caixa.addProduto(new CadastroDeProduto(cod, nome, preco, qtd));
                    }
                    break;
                case 2:
                    caixa.printDados();
                    System.out.println();
                    System.out.println("Informe o código do produto:");
                    String cod = sc.next();
                    System.out.println("Informe a quantidade:");
                    int qtd2 = sc.nextInt();
                    caixa.removerProduto(cod,qtd2);

                    break;

                case 3:

                    caixa.printDados();
                    System.out.println("============================================================================");
                    System.out.println("Exportar dados ? s/n");

                    if(sc.next().equalsIgnoreCase("s")){
                        caixa.CriarArquivo();
                        System.out.println("Arquivo 'Dados_Do_Estoque' criado!");
                        System.out.println("Caminho: C:\\Users\\isaqu\\Desktop\\ ");

                        break;
                    }

                    break;

                case 4:
                    System.out.println("Produtos mais vendidos");
                   caixa.getListaDeSaida().
                            forEach(f -> System.out.println("Cod: " + f.getCod() + " Nome: " + f.getName() + " Preço Unitário: R$ " + f.getPrice() + " Total: " + f.getQuantity()));
                    break;

                default:
                    System.err.println("Opção invalida!");


            }


        }


        System.out.println("******************* PROGRAMA ENCERRADO *******************");
        sc.close();
    }
}
