package Entitys;

import java.util.List;

public class Contenedor2 {
       
    private List<BitacoraAll> products ;
    private List<BitacoraJtAll> products2 ;

    public Contenedor2() {
    }

    public Contenedor2(List<BitacoraAll> products, List<BitacoraJtAll> products2) {
        this.products = products;
        this.products2 = products2;
    }


    public List<BitacoraAll> getProducts() {
        return products;
    }

    public void setProducts(List<BitacoraAll> products) {
        this.products = products;
    }


    public List<BitacoraJtAll> getProducts2() {
        return products2;
    }

    public void setProducts2(List<BitacoraJtAll> products2) {
        this.products2 = products2;
    }


}
