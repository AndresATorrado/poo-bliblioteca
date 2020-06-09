/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendadelibros.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import tiendadelibros.modelo.Libro;
import tiendadelibros.vista.Index;
import tiendadelibros.modelo.TiendaDeLibros;
import tiendadelibros.modelo.Transaccion;
import tiendadelibros.vista.Abastecer;
import tiendadelibros.vista.Registrar;
import tiendadelibros.vista.Vender;

/**
 *
 * @author Jean
 */
public class Controlador implements ActionListener {

    private Index vista;
    private Abastecer abastecer;
    private TiendaDeLibros tienda;
    private Registrar registrar;
    private Vender vender;

    public Controlador() {
    }

    public Controlador(Index vista, TiendaDeLibros tienda) {
        this.vista = vista;
        this.tienda = tienda;
        this.abastecer = new Abastecer();
        this.registrar = new Registrar();
        this.vender = new Vender();
        iniciar();
    }

    public void iniciar() {
        this.vista.btnAbastecer.addActionListener(this);
        this.vista.btnBuscarISBN.addActionListener(this);
        this.vista.btnBuscarTitulo.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnMasBarato.addActionListener(this);
        this.vista.btnMasCaro.addActionListener(this);
        this.vista.btnMasVendido.addActionListener(this);
        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnVender.addActionListener(this);
        this.abastecer.btnAceptar.addActionListener(this);
        this.registrar.btnRegistrar.addActionListener(this);
        this.vender.btnAceptar.addActionListener(this);
        actualizarCaja();
        if (!this.tienda.getCatalogo().isEmpty()) {
            Libro mostrar = this.tienda.getCatalogo().get(0);
            pintarLibro(mostrar);
        }

        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.btnAbastecer) {
            this.abastecer.txtISBN.setText(this.vista.txtISBN.getText());
            this.abastecer.txtNombre.setText(this.vista.txtTitulo.getText());
            this.abastecer.setVisible(true);
        } else if (e.getSource() == this.abastecer.btnAceptar) {
            if (!"".equals(this.abastecer.txtCantidad.getText())) {
                int cantidad = Integer.parseInt(this.abastecer.txtCantidad.getText());
                String titulo = this.abastecer.txtNombre.getText();
                String isbn = this.abastecer.txtISBN.getText();
                boolean abastecido = this.tienda.abastecer(isbn, titulo, cantidad);
                if (abastecido) {
                    JOptionPane.showMessageDialog(null, "Éxito");
                    Libro lAbastecido = this.tienda.buscarLibroPorISBN(isbn);
                    pintarLibro(lAbastecido);
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado el libro que ha ingresado");
                }
                this.abastecer.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
            }
        } else if (e.getSource() == this.vista.btnBuscarISBN) {
            String isbn = JOptionPane.showInputDialog("ISBN");
            Libro encontrado = this.tienda.buscarLibroPorISBN(isbn);
            if (encontrado != null) {
                pintarLibro(encontrado);
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado el libro");
            }
        } else if (e.getSource() == this.vista.btnBuscarTitulo) {
            String titulo = JOptionPane.showInputDialog("Título");
            Libro encontrado = this.tienda.buscarLibroPorTitulo(titulo);
            if (encontrado != null) {
                pintarLibro(encontrado);
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado el libro");
            }
        } else if (e.getSource() == this.vista.btnEliminar) {
            int opcion = JOptionPane.showInternalConfirmDialog(null, "Está seguro que desea eliminar el Libro con ISBN " + this.vista.txtISBN.getText());
            if (opcion == 0) {
                boolean eliminado = this.tienda.eliminarLibro(this.vista.txtISBN.getText());
                if (eliminado) {
                    JOptionPane.showMessageDialog(null, "Libro eliminado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha podido realizar la operación");
                }
            }
        } else if (e.getSource() == this.vista.btnMasBarato) {
            Libro masBarato = this.tienda.darLibroMasEconomico();
            JOptionPane.showMessageDialog(null, "Éxito");
            pintarLibro(masBarato);
        } else if (e.getSource() == this.vista.btnMasBarato) {
            Libro masCaro = this.tienda.darLibroMasCostoso();
            JOptionPane.showMessageDialog(null, "Éxito");
            pintarLibro(masCaro);
        } else if (e.getSource() == this.vista.btnMasVendido) {
            Libro masVendido = this.tienda.darLibroMasVendido();
            JOptionPane.showMessageDialog(null, "Éxito");
            pintarLibro(masVendido);
        } else if (e.getSource() == this.vista.btnRegistrar) {
            this.registrar.setVisible(true);
        } else if (e.getSource() == this.registrar.btnRegistrar) {
            if (!"".equals(this.registrar.txtISBN.getText()) && !"".equals(this.registrar.txtImagen.getText())
                    && !"".equals(this.registrar.txtPrecioCompra.getText()) && !"".equals(this.registrar.txtPrecioVenta.getText())
                    && !"".equals(this.registrar.txtTitulo.getText())) {
                String isbn = this.registrar.txtISBN.getText();
                String titulo = this.registrar.txtTitulo.getText();
                double precioCompra = Double.parseDouble(this.registrar.txtPrecioCompra.getText());
                double precioVenta = Double.parseDouble(this.registrar.txtPrecioVenta.getText());
                String ruta = this.registrar.txtImagen.getText();
                Libro registrado = this.tienda.registrarLibro(isbn, titulo, precioVenta, precioCompra, ruta);
                if (registrado != null) {
                    pintarLibro(registrado);
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha podido realizar la operación");
                }
                this.registrar.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
            }
        } else if (e.getSource() == this.vista.btnVender) {
            this.vender.txtISBN.setText(this.vista.txtISBN.getText());
            this.vender.txtNombre.setText(this.vista.txtTitulo.getText());
            this.vender.setVisible(true);
        } else if (e.getSource() == this.vender.btnAceptar) {
            if (!"".equals(this.vender.txtCantidad.getText())) {
                int cantidad = Integer.parseInt(this.vender.txtCantidad.getText());
                String titulo = this.vender.txtNombre.getText();
                String isbn = this.vender.txtISBN.getText();
                boolean vendido = this.tienda.vender(isbn, titulo, cantidad);
                if (vendido) {
                    JOptionPane.showMessageDialog(null, "Éxito");
                    Libro lVendido = this.tienda.buscarLibroPorISBN(isbn);
                    pintarLibro(lVendido);
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha podido realizar la transacción");
                }
                this.vender.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
            }
        }
        actualizarCaja();
        pintarBodega();
    }

    public void pintarLibro(Libro encontrado) {
        this.vista.txtISBN.setText(encontrado.getIsbn());
        this.vista.txtTitulo.setText(encontrado.getTitulo());
        this.vista.txtPrecioCompra.setText(encontrado.getPrecioCompra() + "");
        this.vista.txtPrecioVenta.setText(encontrado.getPrecioVenta() + "");
        this.vista.txtUnidades.setText(encontrado.getCantidadActual() + "");
        this.vista.txtISBN1.setText(encontrado.getIsbn());
        this.vista.txtTitulo1.setText(encontrado.getIsbn());
        this.vista.txtPrecioCompra1.setText(encontrado.getPrecioCompra() + "");
        this.vista.txtPrecioVenta1.setText(encontrado.getPrecioVenta() + "");
        pintarTransacciones(encontrado);
    }

    public void pintarTransacciones(Libro encontrado) {
        String sTransacciones = "";
        ArrayList<Transaccion> transacciones = encontrado.getTransacciones();
        for (Transaccion transaccion : transacciones) {
            sTransacciones += transaccion.getFecha() + "-" + transaccion.getCantidad() + "-" + transaccion.getTipo().getNombre() + "\n";
        }
        this.vista.txtAreaTransacciones.setText(sTransacciones);
    }

    public void pintarBodega() {
        String sBodega = "";
        for (Libro libro : this.tienda.getCatalogo()) {
            sBodega += libro.getTitulo() + "-" + libro.getIsbn() + "-" + libro.getCantidadActual() + "\n";
        }
        this.vista.txtAreaBodega.setText(sBodega);
    }

    public void actualizarCaja() {
        this.vista.txtCaja.setText(this.tienda.getCaja() + "$");
    }

}
