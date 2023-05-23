package Entitys;


public class BitacoraAll {
    private int id_producto;
    private String nombre_producto;
    private String fecha;

    public BitacoraAll() {
    }

    public BitacoraAll( int id_producto, String nombre_producto, String fecha) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.fecha = fecha;
    }
    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }
}
