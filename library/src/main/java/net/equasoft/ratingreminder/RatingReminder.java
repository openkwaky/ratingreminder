package net.equasoft.ratingreminder;

import java.util.ArrayList;

import net.equasoft.ratingreminder.algo.DoubleGapAlgo;
import net.equasoft.ratingreminder.algo.IAlgo;
import net.equasoft.ratingreminder.algo.NoGapAlgo;
import net.equasoft.ratingreminder.algo.RegularAlgo;
import net.equasoft.ratingreminder.dialog.StoreSelectionDialogBuilder;
import net.equasoft.ratingreminder.fragment.AskingDialogFragment;
import net.equasoft.ratingreminder.fragment.AskingDialogFragment.OnRating;
import net.equasoft.ratingreminder.io.PrefsTools;
import net.equasoft.ratingreminder.model.Store;
import net.equasoft.ratingreminder.type.AlgoType;
import net.equasoft.ratingreminder.type.RatingDialogType;
import net.equasoft.ratingreminder.type.StoreType;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

public class RatingReminder {

    private FragmentActivity mActivity;
    private int gap = 3;
    private String appName;
    private RatingDialogType dialogType = RatingDialogType.BASIC_DIALOG;
    private AlgoType algoType = AlgoType.DOUBLE_GAP_ALGO;
    private ArrayList<StoreType> storeTypes = new ArrayList<StoreType>();
    private ArrayList<Store> stores = new ArrayList<Store>();

    /**
     * Default Constructor. FragmentActivity parameter needed to display
     * DialogFragment
     * 
     * @param mActivity
     *            : the Activity object who created this instance
     */
    public RatingReminder(FragmentActivity mActivity) {
        super();
        this.mActivity = mActivity;
    }

    public void process() {

        // Check if user has posted a rating yet
        boolean rated = PrefsTools.getBool(mActivity, PrefsTools.PREFS_RATED);

        if (!rated) {

            initStores();

            IAlgo algo = buildAlgo(mActivity, algoType);
            if (algo.isDialogShowable()) {
                showAskingDialogFragment();
            }
        }

    }

    /**
     * Create list of <code>Store</code> using the <code>StoreType</code> list
     * specified by <code>setStoreTypes(ArrayList<StoreType> storeTypes)</code>
     */
    private void initStores() {
        stores.clear();
        for (StoreType _t : storeTypes) {
            addToStoresList(new Store(mActivity, _t));
        }

        if (stores.size() == 0) {
            stores.add(new Store(mActivity, StoreType.GOOGLE_PLAYSTORE));
        }
    }

    private void addToStoresList(Store store) {
        if (store.isInstalled()) {
            stores.add(store);
        }
    }

    /**
     * Select the correct Algo class according the field algoType to generate an
     * IAlgo instance
     * 
     * @return IAlgo instance of the correct type
     */
    private IAlgo buildAlgo(Context mContext, AlgoType algoType) {
        IAlgo _algo = null;
        switch (algoType) {
        case NO_GAP_ALGO:
            _algo = new NoGapAlgo();
            break;

        case REGULAR_ALGO:
            _algo = new RegularAlgo(mContext, gap);
            break;

        case DOUBLE_GAP_ALGO:
            _algo = new DoubleGapAlgo(mContext, gap);
            break;

        default:
            break;
        }

        return _algo;
    }

    private void showAskingDialogFragment() {
        AskingDialogFragment dialogFragment = new AskingDialogFragment();
        dialogFragment.setAppName(appName);
        dialogFragment.setDialogType(dialogType);
        dialogFragment.setOnRating(new OnRating() {

            @Override
            public void OnRatingOk() {
                if (stores.size() == 1) {
                    PrefsTools.setBool(mActivity, PrefsTools.PREFS_RATED, true);
                    stores.get(0).goRating(mActivity);
                } else {
                    showStoreSelectionDialogFragment();
                }
            }

            @Override
            public void OnRatingCancel() {

            }
        });

        dialogFragment.show(mActivity.getSupportFragmentManager(), "rating");
    }

    private void showStoreSelectionDialogFragment() {
        Dialog dialog = new StoreSelectionDialogBuilder(mActivity, stores).create();
        dialog.show();
    }

    public String getAppName() {
        return appName;
    }

    /**
     * Specify the name of the app to be displayed in the dialog
     * 
     * @param appName
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    public RatingDialogType getDialogType() {
        return dialogType;
    }

    /**
     * Specify a pre-defined dialog type
     * <p>
     * There are 2 dialog types :<br>
     * <ul>
     * <li><code>RatingDialogType.BASIC_DIALOG</code> define a very simple alert
     * dialog</li>
     * <li><code>RatingDialogType.IMAGE_DIALOG</code> define an alert dialog
     * decorated with an image</li>
     * </ul>
     * 
     * @param dialogType
     */
    public void setDialogType(RatingDialogType dialogType) {
        this.dialogType = dialogType;
    }

    public int getGap() {
        return gap;
    }

    /**
     * This method sets the field "gap" an int value that is used by the chosen
     * algo to trigger a dialog display <br>
     * This field is useless when the algo AlgoType.NO_GAP_ALGO is selected
     * 
     * @param gap
     */
    public void setGap(int gap) {
        this.gap = gap;
    }

    public AlgoType getAlgoType() {
        return algoType;
    }

    /**
     * Specify a pre-defined algorithm that will decide to trigger or not the
     * alert dialog each time RatingReminder is executed
     * <p>
     * There are 3 algorithm :<br>
     * <ul>
     * <li><code>AlgoType.NO_GAP_ALGO</code> this algo returns always true, see
     * <code>NoGapAlgo</code> class</li>
     * <li><code>AlgoType.REGULAR_ALGO</code> this algo returns true each time
     * the app started a number of times equals to "gap", see
     * <code>RegularAlgo</code> class</li>
     * <li><code>AlgoType.DOUBLE_GAP_ALGO</code> this algo returns true each
     * time the app started a number of times equals to "gap", "gap" being
     * multiplied by 2 at each hit, see <code>DoubleGapAlgo</code> class</li>
     * </ul>
     * 
     * @param algoType
     */
    public void setAlgoType(AlgoType algoType) {
        this.algoType = algoType;
    }

    public ArrayList<StoreType> getStoreTypes() {
        return storeTypes;
    }

    public void setStoreTypes(ArrayList<StoreType> storeTypes) {
        this.storeTypes = storeTypes;
    }

}
