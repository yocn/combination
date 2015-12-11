package yocn.com.collection.bean;

/**
 * Created by JD on 2015/12/11 0011.
 */
public class SettingBean {
    private String title;

    public SettingBean(String title) {
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
        return "SettingBean{" +
                "title='" + title + '\'' +
                '}';
    }
}
