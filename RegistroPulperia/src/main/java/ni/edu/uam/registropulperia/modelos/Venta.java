package ni.edu.uam.registropulperia.modelos;

public class Venta {
    private int cantidad;
    private double precio;
    private double total;

    public Venta() {}

    public Venta(int cantidad, double precio) {
        setCantidad(cantidad);
        setPrecio(precio);
    }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) {
        if (cantidad > 0) {
            this.cantidad = cantidad;
            this.total = cantidad * precio;
        } else {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
    }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) {
        if (precio > 0) {
            this.precio = precio;
            this.total = cantidad * precio;
        } else {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
    }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public double getImpuesto() {
        return total * 0.15;
    }

    public double getTotalConImpuesto() {
        return total + getImpuesto();
    }

    @Override
    public String toString() {
        return String.format("Cantidad: %d | Precio: C$ %.2f | Total: C$ %.2f | ITBMS: C$ %.2f",
                cantidad, precio, total, getImpuesto());
    }
}