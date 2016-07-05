package upplic.com.angelavto.presentation.models;

public class AppMenuItem {

    private String title;
    private int drawable;

    public AppMenuItem() {
    }

    public AppMenuItem(String title, int drawable) {
        this.title = title;
        this.drawable = drawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
