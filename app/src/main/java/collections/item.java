package collections;



public class item {

    public String title ;
    public String description ;
    public String precio;
    public String url ;
    public int id ;
    public void setTitle(String title) {
        this . title = title;
    }
    public void setDescription(String description) {
        this . description = description;
    }
    public void setUrl(String url) {
        this . url = url;
    }
    public void setId( int id) {
        this . id = id;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getPrecio() {
        return this.precio;
    }

    public int getId() {
        return this . id ;
    }
    public String getTitle() {
        return this . title ;
    }
    public String getDescription() {
        return this . description ;
    }
    public String getUrl() {
        return this . url ;
    }

}
