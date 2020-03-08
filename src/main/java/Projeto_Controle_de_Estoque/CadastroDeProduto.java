package Projeto_Controle_de_Estoque;

import java.util.Objects;

public class CadastroDeProduto {

    private String cod;
    private String name;
    private Double price;
    private int quantity;

    public CadastroDeProduto(String cod, String name, Double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.cod = cod;
    }


    public String getCod(){
        return this.cod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCod(String cod) { this.cod = cod; }

    public String toString(){

        return "Cod: " + getCod() + " Name: " + getName() + " " + " Price: R$ " + getPrice() + " Qtd: " + getQuantity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CadastroDeProduto)) return false;
        CadastroDeProduto produto = (CadastroDeProduto) o;
        return Objects.equals(getCod(), produto.getCod());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCod());
    }
}
