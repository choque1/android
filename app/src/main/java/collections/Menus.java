package collections;

public class Menus {
   public String Idrestaurant;
   public String nombre;
   public Double precio;
   public String descripcion;
   public String foto;
   public String id;

    public String getIdrestaurant() {
        return this.Idrestaurant;
    }

    public void setIdrestaurant(String idrestaurant) {
        Idrestaurant = idrestaurant;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
