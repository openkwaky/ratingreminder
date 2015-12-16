package net.equasoft.ratingreminder.factory;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import net.equasoft.ratingreminder.R;
import net.equasoft.ratingreminder.model.Store;
import net.equasoft.ratingreminder.type.StoreType;

/**
 * Created by kwaky on 16/12/15.
 */
public class StoreFactory {

    public static final String STORE_GOOGLE_PLAY_PKG = "com.android.vending";
    public static final String STORE_KURIO_PKG = "com.kurio.appstore";
    public static final String STORE_AMAZON_PKG = "com.amazon.venezia";
    public static final String STORE_YANDEX_PKG = "com.yandex.store";

    public static final String STORE_GOOGLE_PLAY_URL = "market://details?id=";
    public static final String STORE_KURIO_URL = "kurio://details/";
    public static final String STORE_AMAZON_URL = "amzn://apps/android?p=";
    public static final String STORE_YANDEX_URL = "yastore://details?id=";

    /**
     * Build a new Store instance using parameters
     * Test if the specified store is installed on the device
     *
     * @param type StoreType Enum value
     */
    public static Store buildStore(Context mContext, StoreType type){
        Store store = new Store();

        store.setType(type);

        switch (type) {
            case AMAZON_STORE:
                store.setPkg(StoreFactory.STORE_AMAZON_PKG);
                store.setUrl(StoreFactory.STORE_AMAZON_URL);
                store.setTitleStringId(R.string.amazon_store_title);
                store.setLogoId(R.drawable.amazon_store_icon);
                break;

            case KURIO_STORE:
                store.setPkg(StoreFactory.STORE_KURIO_PKG);
                store.setUrl(StoreFactory.STORE_KURIO_URL);
                store.setTitleStringId(R.string.kurio_store_title);
                store.setLogoId(R.drawable.kurio_store_icon);
                break;

            case YANDEX_STORE:
                store.setPkg(StoreFactory.STORE_YANDEX_PKG);
                store.setUrl(StoreFactory.STORE_YANDEX_URL);
                store.setTitleStringId(R.string.yandex_store_title);
                store.setLogoId(R.drawable.yandex_store_icon);
                break;

            default:

                store.setPkg(StoreFactory.STORE_GOOGLE_PLAY_PKG);
                store.setUrl(StoreFactory.STORE_GOOGLE_PLAY_URL);
                store.setTitleStringId(R.string.google_store_title);
                store.setLogoId(R.drawable.google_store_icon);
                break;
        }

        store.setInstalled(isPackageInstalled(mContext, store.getPkg()));

        return store;
    }

    public static boolean isPackageInstalled(Context mContext, String packageName) {
        try {
            mContext.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        return true;
    }

    public static void goRating(Activity mActivity, Store store) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(store.getUrl() + mActivity.getPackageName()));
            mActivity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(mActivity, R.string.rating_store_not_found, Toast.LENGTH_SHORT).show();
        }

    }


}
