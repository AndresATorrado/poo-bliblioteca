/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendadelibros.modelo;

/**
 *
 * @author Jean
 */
public enum Tipo {
    VENTA("Venta"),
    ABASTECIMIENTO("Abastecimiento");
    
    private final String nombre;

    private Tipo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
}
