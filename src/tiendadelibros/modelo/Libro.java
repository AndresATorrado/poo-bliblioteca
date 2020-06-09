/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendadelibros.modelo;

import java.util.ArrayList;

/**
 *
 * @author Jean
 */
public class Libro {
    private String isbn;
    private String titulo;
    private double precioVenta;
    private double precioCompra;
    private int cantidadActual;
    private String rutaImagen;
    private ArrayList<Transaccion> transacciones;

    public Libro() {
        
    }
    
    public Libro(String isbn, String titulo, double precioVenta, double precioCompra, String rutaImagen) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.rutaImagen = rutaImagen;
        transacciones = new ArrayList<>();
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(int cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }
    
    public boolean vender(int cantidad, String nombre) {
        return true;
    }
    
    public int darTotalDeEjemplaresVendidos() {
        int totalVendidos = 0;
        for (Transaccion transaccion : transacciones) {
            if ("Venta".equals(transaccion.getTipo().getNombre())) {
                totalVendidos++;
            }
        }
        return totalVendidos;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
