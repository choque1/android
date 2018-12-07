package collections;

public class item {

        public String nombre ;
        public String description ;
        public String precio;
        public String url ;

        public int id ;
        public void setNombre(String title) {
            this . nombre = nombre;
        }
        public void setDescription(String description) {
            this . description = description;
        }
        public void setUrl(String url) {
            this . url = url;
        }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setId(int id) {
            this . id = id;
        }
        public int getId() {
            return this . id ;
        }
        public String getNombre() {
            return this . nombre ;
        }
        public String getDescription() {
            return this . description ;
        }
        public String getUrl() {
            return this . url ;
        }

    public String getPrecio() {
        return this.precio;
    }
}
