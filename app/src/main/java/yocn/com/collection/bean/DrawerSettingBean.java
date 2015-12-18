package yocn.com.collection.bean;

/**
 * Created by Yocn on 2015/12/11 0011.
 */
public class DrawerSettingBean {
    private String title;

    public DrawerSettingBean(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "DrawerSettingBean{" +
                "title='" + title + '\'' +
                '}';
    }
}
