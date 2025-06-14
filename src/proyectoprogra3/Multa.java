package proyectoprogra3;

public class Multa {
    private final String placa;
    private final String fecha;
    private final String descripcion;
    private final double monto;

    public Multa(String placa, String fecha, String descripcion, double monto) {
        this.placa = placa;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;
    }

    public String getPlaca() {
        return placa;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getMonto() {
        return monto;
    }
}