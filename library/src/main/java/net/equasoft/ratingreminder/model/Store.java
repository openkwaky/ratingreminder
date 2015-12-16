package net.equasoft.ratingreminder.model;

import net.equasoft.ratingreminder.type.StoreType;

public class Store {

    private StoreType type;
    private boolean installed;
    private String pkg;
    private String url;
    private int titleStringId;
    private int logoId;

    public StoreType getType() {
        return type;
    }

    public void setType(StoreType type) {
        this.type = type;
    }

    public boolean isInstalled() {
        return installed;
    }

    public void setInstalled(boolean installed) {
        this.installed = installed;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTitleStringId() {
        return titleStringId;
    }

    public void setTitleStringId(int titleStringId) {
        this.titleStringId = titleStringId;
    }

    public int getLogoId() {
        return logoId;
    }

    public void setLogoId(int logoId) {
        this.logoId = logoId;
    }
}
