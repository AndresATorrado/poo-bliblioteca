/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import tiendadelibros.controlador.Controlador;
import tiendadelibros.modelo.TiendaDeLibros;
import tiendadelibros.vista.Index;

/**
 *
 * @author Jean
 */
public class main {
    public static void main(String[] args) {
        TiendaDeLibros tienda = new TiendaDeLibros();
        Index vista = new Index();
        Controlador control = new Controlador(vista, tienda);
    }
}
