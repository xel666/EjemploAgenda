package org.unam.dgsa.example.agenda.db;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: xel666
 * Date: 1/08/12
 * Time: 05:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class Contacto {
    private int id;
    private String nombre;
    private String apellido;
    private Set<Telefono> telefonos;
    private Set<EmailAddress> direcciones;

    public Contacto() {
        telefonos=new HashSet<Telefono>();
        direcciones=new HashSet<EmailAddress>();
    }

    public Contacto(String nombre, String apellido) {
        this();
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Set<EmailAddress> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(Set<EmailAddress> direcciones) {
        this.direcciones = direcciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(Set<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contacto contacto = (Contacto) o;

        if (id != contacto.id) return false;
        if (apellido != null ? !apellido.equals(contacto.apellido) : contacto.apellido != null) return false;
        if (nombre != null ? !nombre.equals(contacto.nombre) : contacto.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellido != null ? apellido.hashCode() : 0);
        return result;
    }
}
