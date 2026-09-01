package ni.edu.uam.tiendadeartesanias;

public class Productos {
    private String id;
    private String nombre;
    private String categoria;
    private double precio;
    private String rutaImagen; // Guarda el nombre/ruta del archivo de la imagen

    public Productos(String id, String nombre, String categoria, double precio, String rutaImagen) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.rutaImagen = rutaImagen;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public double getPrecio() { return precio; }
    public String getRutaImagen() { return rutaImagen; }
}