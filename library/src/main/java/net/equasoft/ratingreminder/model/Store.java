package net.equasoft.ratingreminder.model;

import net.equasoft.ratingreminder.R;
import net.equasoft.ratingreminder.type.StoreType;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.widget.Toast;

public class Store {
    public static final String STORE_GOOGLE_PLAY_PKG = "com.android.vending";
    public static final String STORE_KURIO_PKG = "com.kurio.appstore";
    public static final String STORE_AMAZON_PKG = "com.amazon.venezia";
    public static final String STORE_YANDEX_PKG = "com.yandex.store";

    public static final String STORE_GOOGLE_PLAY_URL = "market://details?id=";
    public static final String STORE_KURIO_URL = "kurio://details/";
    public static final String STORE_AMAZON_URL = "amzn://apps/android?p=";
    public static final String STORE_YANDEX_URL = "yastore://details?id=";

    StoreType type;
    boolean installed;
    String pkg;
    String url;
    int titleStringId;
    int logoId;

    public Store(Context mContext, StoreType type) {
        super();
        buildStore(mContext, type);
    }

    public boolean isPackageInstalled(Context mContext, String packageName) {
        try {
            mContext.getPackageManager().getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            return false;
        }
        return true;
    }

    public int getTitleStringId() {
        return titleStringId;
    }

    public int getLogoId() {
        return logoId;
    }

    public void goRating(Activity mActivity) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(getUrl() + mActivity.getPackageName()));
            mActivity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(mActivity, R.string.rating_store_not_found, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Build a new Store instance using parameters Test if the specified store
     * is installed on the device
     * 
     * @param type StoreType Enum value
     */
    private void buildStore(Context mContext, StoreType type) {
        this.type = type;

        switch (type) {
        case AMAZON_STORE:
            this.pkg = STORE_AMAZON_PKG;
            this.url = STORE_AMAZON_URL;
            this.titleStringId =  R.string.amazon_store_title;
            this.logoId = R.drawable.amazon_store_icon;
            break;

        case KURIO_STORE:
            this.pkg = STORE_KURIO_PKG;
            this.url = STORE_KURIO_URL;
            this.titleStringId =  R.string.kurio_store_title;
            this.logoId = R.drawable.kurio_store_icon;
            break;

        case YANDEX_STORE:
            this.pkg = STORE_YANDEX_PKG;
            this.url = STORE_YANDEX_URL;
            this.titleStringId =  R.string.yandex_store_title;
            this.logoId = R.drawable.yandex_store_icon;
            break;

        default:

            this.pkg = STORE_GOOGLE_PLAY_PKG;
            this.url = STORE_GOOGLE_PLAY_URL;
            this.titleStringId =  R.string.google_store_title;
            this.logoId = R.drawable.google_store_icon;
            break;
        }

        setInstalled(isPackageInstalled(mContext, getPkg()));
    }

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

}
