package collections;

public class Menus {
   public String Idrestaurant;
   public String nombre;
   public String precio;
   public String descripcion;
   public String foto;
   public int id;

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

    public String getPrecio() {
        return this.precio;
    }

    public void setPrecio(String precio) {
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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
