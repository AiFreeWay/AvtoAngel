package upplic.com.angelavto.presentation.models;

import java.util.Collections;
import java.util.List;

import upplic.com.angelavto.presentation.adapters.Expannable;

public class AppMenuItem implements Expannable<AppMenuItem> {

    private String title;
    private int drawable;
    private List<AppMenuItem> insertedMenu = Collections.emptyList();

    public AppMenuItem() {
    }

    public AppMenuItem(String title, int drawable) {
        this.title = title;
        this.drawable = drawable;
    }

    public AppMenuItem(String title, int drawable, List<AppMenuItem> insertedMenu) {
        this.title = title;
        this.drawable = drawable;
        this.insertedMenu = insertedMenu;
    }

    @Override
    public List<AppMenuItem> getExpannableList() {
        return insertedMenu;
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

    public void setInsertedMenu(List<AppMenuItem> insertedMenu) {
        if (insertedMenu != null)
            this.insertedMenu = insertedMenu;
    }
}
