package servicerest;


import Conexiones.ProductDao;
import Conexiones.ProductDao1;
import Conexiones.ProductDaoidjt;
import Conexiones.ProductDaoidgt;
import Entitys.Product;
import Entitys.Product1;
import Entitys.Productgt;
import Entitys.Productjt;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author godoy
 */
public class ProductService {
    private ProductDao productdao;
    private ProductDao1 productdao1;
    private ProductDaoidjt idjuti;
    private ProductDaoidgt idguate;
    
    public ProductService(){
        productdao = new ProductDao();
        productdao1= new ProductDao1();
        idjuti = new ProductDaoidjt();
        idguate= new ProductDaoidgt();
         
    }
    
    // para obtener la listas de los 2 almacenes
    public List<Product> getAll() throws ClassNotFoundException, IOException {
       
            return productdao.getAll();
             
    }
    
     public List<Product1> getAll1() throws ClassNotFoundException, IOException {
       
            return productdao1.getAll();
    }
     //para obtener solo los productos de Jutiapa
      public List<Productjt> getAll2(int id) throws ClassNotFoundException {
       
            return idjuti.getAll2(id);
    }
     //para obtener solo los productos de Guatemala
     public List<Productgt> getAll3(int id) throws ClassNotFoundException {
       
            return idguate.getAll3(id);
    }
    
    public Product get(Product product){
        return (Product) productdao.get(product.getId_producto(),product.getId_usuario(),product.getId_ubicacion(),product.getNombre(),product.getPrecio(),product.getImg(),product.getStock(),product.getStock_minimo());
    }
    
   public Product get(Product1 product){
        return (Product) productdao.get(product.getId_producto(),product.getId_usuario(),product.getId_ubicacion(),product.getNombre(),product.getPrecio(),product.getImg(),product.getStock(),product.getStock_minimo());
    }
    
    

}
