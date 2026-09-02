package ni.edu.uam.registropulperia.modelos;



public class Producto {
    private String codigo;
    private String nombre;
    private String categoria;
    private double precio;
    private int cantidad;

    public Producto() {}

    public Producto(String codigo, String nombre, String categoria, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        setPrecio(precio);
        setCantidad(cantidad);
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) {
        if (precio > 0) {
            this.precio = precio;
        } else {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
    }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) {
        if (cantidad >= 0) {
            this.cantidad = cantidad;
        } else {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
    }

    public String getPrecioFormateado() {
        return String.format("C$ %.2f", precio);
    }

    public boolean hayStock() {
        return this.cantidad > 0;
    }

    public void restarStock(int cantidadVender) {
        if (cantidadVender <= this.cantidad) {
            this.cantidad -= cantidadVender;
        } else {
            throw new IllegalArgumentException("Stock insuficiente");
        }
    }

    @Override
    public String toString() {
        return String.format("Código: %s | Nombre: %s | Categoría: %s | Precio: %s | Cantidad: %d",
                codigo, nombre, categoria, getPrecioFormateado(), cantidad);
    }
}