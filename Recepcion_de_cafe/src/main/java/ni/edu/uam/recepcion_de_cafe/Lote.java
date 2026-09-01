package ni.edu.uam.recepcion_de_cafe;

public class Lote {
    private int id;
    private String productor;
    private double pesoKg;
    private String variedad;

    public Lote(int id, String productor, double pesoKg, String variedad) {
        this.id = id;
        this.productor = productor;
        this.pesoKg = pesoKg;
        this.variedad = variedad;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getProductor() { return productor; }
    public void setProductor(String productor) { this.productor = productor; }

    public double getPesoKg() { return pesoKg; }
    public void setPesoKg(double pesoKg) { this.pesoKg = pesoKg; }

    public String getVariedad() { return variedad; }
    public void setVariedad(String variedad) { this.variedad = variedad; }
}
