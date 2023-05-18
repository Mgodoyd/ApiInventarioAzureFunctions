package Entitys;

public class Productgt {
    private int id_producto;
    private int id_usuario;
    private int id_ubicacion;
    private String nombbre;
    private int precio;
    private Byte[] img;
    private int stock;
    private int stock_minimo;

    public Productgt() {
    }

    public Productgt(int id_producto, int id_usuario, int id_ubicacion, String nombbre, int precio, Byte[] img, int stock, int stock_minimo) {
        this.id_producto = id_producto;
        this.id_usuario = id_usuario;
        this.id_ubicacion = id_ubicacion;
        this.nombbre = nombbre;
        this.precio = precio;
        this.img = img;
        this.stock = stock;
        this.stock_minimo = stock_minimo;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_ubicacion() {
        return id_ubicacion;
    }

    public void setId_ubicacion(int id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    public String getNombre() {
        return nombbre;
    }

    public void setNombre(String nombre) {
        this.nombbre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Byte[] getImg() {
        return img;
    }

    public void setImg(Byte[] img) {
        this.img = img;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock_minimo() {
        return stock_minimo;
    }

    public void setStock_minimo(int stock_minimo) {
        this.stock_minimo = stock_minimo;
    }
}
