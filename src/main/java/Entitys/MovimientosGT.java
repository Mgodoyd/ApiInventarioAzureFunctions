package Entitys;

public class MovimientosGT {
    public int id_movimiento;
    public int id_producto;
    public int id_usuario;
    public int id_ubicacion;
    public int cantidad_movidad;
    public String ubicacion_almacen_anterior;
    public String ubicacion_almacen_nuevo;


    public MovimientosGT() {
    }


    public MovimientosGT(int id_movimiento, int id_producto, int id_usuario, int id_ubicacion, int cantidad_movidad, String ubicacion_almacen_anterior, String ubicacion_almacen_nuevo) {
        this.id_movimiento = id_movimiento;
        this.id_producto = id_producto;
        this.id_usuario = id_usuario;
        this.id_ubicacion = id_ubicacion;
        this.cantidad_movidad = cantidad_movidad;
        this.ubicacion_almacen_anterior = ubicacion_almacen_anterior;
        this.ubicacion_almacen_nuevo = ubicacion_almacen_nuevo;
    }


    public int getId_movimiento() {
        return id_movimiento;
    }

    public void setId_movimiento(int id_movimiento) {
        this.id_movimiento = id_movimiento;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto= id_producto;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario= id_usuario;
    }

    public int getId_ubicacion() {
        return id_ubicacion;
    }


    public void setId_ubicacion(int id_ubicacion) {
        this.id_ubicacion= id_ubicacion;
    }

    public int getCantidad_movidad() {
        return cantidad_movidad;
    }

    public void setCantidad_movidad(int cantidad_movidad) {
        this.cantidad_movidad= cantidad_movidad;
    }

    public String getUbicacion_almacen_anterior() {
        return ubicacion_almacen_anterior;
    }

    public void setUbicacion_almacen_anterior(String ubicacion_almacen_anterior) {
        this.ubicacion_almacen_anterior= ubicacion_almacen_anterior;
    }

    public String getUbicacion_almacen_nuevo() {
        return ubicacion_almacen_nuevo;
    }

    public void setUbicacion_almacen_nuevo(String ubicacion_almacen_nuevo) {
        this.ubicacion_almacen_nuevo= ubicacion_almacen_nuevo;
    }
    
}
