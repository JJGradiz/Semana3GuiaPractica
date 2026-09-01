package ni.edu.uam.registropulperia.modelos;

public class Venta {
    private int cantidad;
    private double precio;
    private double total;

    public Venta() {}

    public Venta(int cantidad, double precio) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = cantidad * precio;
    }

    // Getters y Setters
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return String.format("Cantidad: %d | Precio: C$ %.2f | Total: C$ %.2f",
                cantidad, precio, total);
    }
}
