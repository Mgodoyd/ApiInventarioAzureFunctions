package Entitys;

import java.util.List;


public class Contenedor {
     
    private List<Product> products ;
    private List<Product1> products2 ;

    public Contenedor() {
    }

    public Contenedor(List<Product> products, List<Product1> products2) {
        this.products = products;
        this.products2 = products2;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product1> getProducts2() {
        return products2;
    }

    public void setProducts2(List<Product1> products2) {
        this.products2 = products2;
    }

   
}