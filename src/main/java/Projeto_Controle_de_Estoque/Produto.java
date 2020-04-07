package Projeto_Controle_de_Estoque;

import java.util.Objects;

public class Produto {

    private String name;
    private double price;
    private int quantity;
    private byte categoria;

    public Produto(String name, Double price, int quantity, byte categoria) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.categoria = categoria;
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public byte getCategoriaId() {
        return categoria;
    }

    public String toString(){

        return "Categoria: " + getCategoriaId() + " Name: " + getName() + " " + " Price: R$ " + getPrice() + " Qtd: " + getQuantity();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return Objects.equals(getCategoriaId(), produto.getCategoriaId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoriaId());
    }
}
