package upplic.com.angelavto.domain.models;


public class Product {

    private String title;
    private String subtitle;
    private String description;
    private String image;

    public Product() {
    }

    public Product(String title, String subtitle, String description, String image) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
