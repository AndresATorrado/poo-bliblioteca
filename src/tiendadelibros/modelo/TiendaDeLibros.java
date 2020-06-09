/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendadelibros.modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jean
 */
public class TiendaDeLibros {

    private double caja;
    private ArrayList<Libro> catalogo;

    public TiendaDeLibros() {
        this.caja = 1000000;
        this.catalogo = new ArrayList<>();
    }

    public ArrayList<Libro> getCatalogo() {
        return this.catalogo;
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        for (Libro libro : catalogo) {
            if (titulo.equals(libro.getTitulo())) {
                return libro;
            }
        }
        return null;
    }

    public Libro buscarLibroPorISBN(String isbn) {
        for (Libro libro : catalogo) {
            if (isbn.equals(libro.getIsbn())) {
                return libro;
            }
        }
        return null;
    }

    public Libro registrarLibro(String isbn, String nombre, double precioVenta, double precioCompra, String rutaImagen) {
        boolean repetido = false;
        for (Libro libro : catalogo) {
            if (isbn.equals(libro.getIsbn()) || nombre.equals(libro.getTitulo())) {
                repetido = true;
                break;
            }
        }
        if (!repetido) {
            Libro nuevoLibro = new Libro(isbn, nombre, precioVenta, precioCompra, rutaImagen);
            catalogo.add(nuevoLibro);
            return nuevoLibro;
        }
        return null;
    }

    public boolean eliminarLibro(String isbn) {
        for (Libro libro : (ArrayList<Libro>) catalogo.clone()) {
            if (isbn.equals(libro.getIsbn())) {
                catalogo.remove(libro);
                return true;
            }
        }
        return false;
    }

    public boolean abastecer(String isbn, String titulo, int cantidad) {
        for (Libro libro : catalogo) {
            if (isbn.equals(libro.getIsbn()) && titulo.equals(libro.getTitulo())) {
                libro.setCantidadActual(libro.getCantidadActual() + cantidad);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String sdfString = sdf.format(new Date());
                Transaccion transaccion = new Transaccion(Tipo.ABASTECIMIENTO, cantidad, sdfString);
                libro.getTransacciones().add(transaccion);
                this.caja = this.caja - (libro.getPrecioCompra()*cantidad);
                return true;
            }
        }
        return false;
    }

    public boolean vender(String isbn, String titulo, int cantidad) {
        for (Libro libro : catalogo) {
            if (isbn.equals(libro.getIsbn()) && titulo.equals(libro.getTitulo())) {
                if (libro.getCantidadActual() >= cantidad) {
                    libro.setCantidadActual(libro.getCantidadActual() - cantidad);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    String sdfString = sdf.format(new Date());
                    Transaccion transaccion = new Transaccion(Tipo.VENTA, cantidad, sdfString);
                    libro.getTransacciones().add(transaccion);
                    this.caja = this.caja + (libro.getPrecioVenta()*cantidad);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public Libro darLibroMasCostoso() {
        Libro masCaro = catalogo.get(0);
        for (Libro libro : catalogo) {
            if (masCaro.getPrecioVenta() < libro.getPrecioVenta()) {
                masCaro = libro;
            }
        }
        return masCaro;
    }

    public Libro darLibroMasEconomico() {
        Libro masBarato = catalogo.get(0);
        for (Libro libro : catalogo) {
            if (masBarato.getPrecioVenta() > libro.getPrecioVenta()) {
                masBarato = libro;
            }
        }
        return masBarato;
    }

    public Libro darLibroMasVendido() {
        Libro masVendido = catalogo.get(0);
        for (Libro libro : catalogo) {
            if (masVendido.darTotalDeEjemplaresVendidos() < libro.darTotalDeEjemplaresVendidos()) {
                masVendido = libro;
            }
        }
        return masVendido;
    }

    public int darCantidadTransaccionesAbastecimiento(String abastecimiento) {
        return 0;
    }

    public int darTotalDeEjemplaresParaLaVenta() {
        int disponiblesParaVenta = 0;
        for (Libro libro : catalogo) {
            if (libro.getCantidadActual() >= 1) {
                disponiblesParaVenta++;
            }
        }
        return disponiblesParaVenta;
    }

    public boolean hayLibroSinTransacciones() {
        boolean haySinTransacciones = false;
        for (Libro libro : catalogo) {
            if (libro.getTransacciones().isEmpty()) {
                haySinTransacciones = true;
                break;
            }
        }
        return haySinTransacciones;
    }

    public int darTotalDeEjemplaresVendidos() {
        int totalVendidos = 0;
        for (Libro libro : catalogo) {
            totalVendidos += libro.darTotalDeEjemplaresVendidos();
        }
        return totalVendidos;
    }

    public String metodo1() {
        String haySinTransacciones = hayLibroSinTransacciones() ? "Sí" : "No";
        return "Total de libros disponibles para la venta: " + darTotalDeEjemplaresParaLaVenta()
                + "– Hay algún libro sin transacciones " + haySinTransacciones;
    }

    public String metodo2() {
        return "Total de ejemplares vendidos en la tienda " + darTotalDeEjemplaresVendidos();
    }

    public double getCaja() {
        return caja;
    }

    public void setCaja(double caja) {
        this.caja = caja;
    }
    
}
