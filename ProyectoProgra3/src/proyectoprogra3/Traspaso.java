package proyectoprogra3;

public class Traspaso {
    private final String placa;
    private final String dpiAnt;
    private final String nombreAnt;
    private final String fecha;
    private final String dpiNew;
    private final String nombreNew;

    public Traspaso(String placa, String dpiAnt, String nombreAnt, String fecha, String dpiNew, String nombreNew) {
        this.placa = placa;
        this.dpiAnt = dpiAnt;
        this.nombreAnt = nombreAnt;
        this.fecha = fecha;
        this.dpiNew = dpiNew;
        this.nombreNew = nombreNew;
    }

    public String getPlaca() {
        return placa;
    }

    public String getDpiAnt() {
        return dpiAnt;
    }

    public String getNombreAnt() {
        return nombreAnt;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDpiNew() {
        return dpiNew;
    }

    public String getNombreNew() {
        return nombreNew;
    }
}