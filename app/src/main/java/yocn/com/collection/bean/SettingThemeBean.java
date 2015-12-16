package yocn.com.collection.bean;

/**
 * Created by JD on 2015/12/11 0011.
 */
public class SettingThemeBean {
    private String title;
    private int color;

    public SettingThemeBean(String title, int color) {
        this.title = title;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "SettingThemeBean{" +
                "title='" + title + '\'' +
                ", color=" + color +
                '}';
    }
}
