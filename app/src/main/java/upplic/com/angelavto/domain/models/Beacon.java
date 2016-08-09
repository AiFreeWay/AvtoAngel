package upplic.com.angelavto.domain.models;


public class Beacon {

    private int id;
    private String imgUrl;
    private String title;

    public Beacon() {
    }

    public Beacon(int id, String imgUrl, String title) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imgUrl;
    }

    public void setImagUrle(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
