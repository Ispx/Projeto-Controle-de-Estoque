package Classes;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class CriarArquivo  {

    public void CriarArquivo() {

        File file = new File("C:\\Users\\isaqu\\Desktop\\Dados_Do_Estoque.txt");

        Set<Produto> set = new HashSet<>();

        //getProdutos().forEach(x -> set.add(x));

        List<String[]> values = new ArrayList<>();
/*

        //Armazenando os valores dentro de uma lista de vetores
        set.forEach(x ->

                values.add(new String[]{

                        "Cod: " + x.getCod() + " Nome:  " + x.getName() +

                        " - Preço médio: " +

                        String.format("%.2f",getProdutos().stream().filter(f1 -> f1.getCod().equalsIgnoreCase(x.getCod())).mapToDouble(m1 -> m1.getQuantity() * m1.getPrice()).sum()/
                        getProdutos().stream().filter(f2 -> f2.getCod().equalsIgnoreCase(x.getCod())).mapToInt(m2 -> m2.getQuantity()).sum()) +

                        " - Qtd: " +
                        getProdutos().stream().filter(f3 -> f3.getCod().equalsIgnoreCase(x.getCod())).mapToInt(m3 -> m3.getQuantity()).sum() +

                        " - Total R$ " +
                        getProdutos().stream().filter(f4 -> f4.getCod().equalsIgnoreCase(x.getCod())).mapToDouble(m4 -> m4.getPrice() * m4.getQuantity()).sum()
                        }


        ));

        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(file))) {


            getProdutos().forEach(p -> {

                csvWriter.writeAll(values);


            });



        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }






*/







    }
}
