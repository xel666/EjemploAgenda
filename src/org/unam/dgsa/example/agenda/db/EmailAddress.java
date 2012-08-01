package org.unam.dgsa.example.agenda.db;

/**
 * Created with IntelliJ IDEA.
 * User: xel666
 * Date: 1/08/12
 * Time: 05:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class EmailAddress {
    private int id;
    private Contacto contacto;
    private String value;

    public EmailAddress() {
    }

    public EmailAddress(Contacto contacto, String value) {
        this.contacto = contacto;
        this.value = value;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailAddress that = (EmailAddress) o;

        if (id != that.id) return false;
        if (!contacto.equals(that.contacto)) return false;
        if (!value.equals(that.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + contacto.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
