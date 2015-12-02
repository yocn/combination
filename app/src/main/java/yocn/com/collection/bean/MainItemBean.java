package yocn.com.collection.bean;

import android.app.Activity;

public class MainItemBean {

    private String title;
    private Class target;

    public MainItemBean(String title, Class target) {
        this.title = title;
        this.target = target;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTarget(Class target) {
        this.target = target;
    }

    public Class getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return "MainItemBean{" +
                "title='" + title + '\'' +
                ", target=" + target +
                '}';
    }
}
