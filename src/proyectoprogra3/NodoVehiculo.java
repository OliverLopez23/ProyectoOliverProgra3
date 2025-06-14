package proyectoprogra3;

public class NodoVehiculo {
    String placa; // Clave para ordenar el árbol
    String dpi;
    String nombre;
    String marca;
    String modelo;
    int ano;
    int multas;
    int traspasos;
    String departamento;
    NodoVehiculo hijoIzquierdo;
    NodoVehiculo hijoDerecho;
    int altura; // Para árbol AVL

    public NodoVehiculo(String placa, String dpi, String nombre, String marca, String modelo, 
                        int ano, int multas, int traspasos, String departamento) {
        this.placa = placa;
        this.dpi = dpi;
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.multas = multas;
        this.traspasos = traspasos;
        this.departamento = departamento;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
        this.altura = 1; // Inicializar altura para AVL
    }
}