package net.equasoft.ratingreminder.algo;

import net.equasoft.ratingreminder.io.PrefsTools;
import net.equasoft.ratingreminder.log.DebugLog;
import android.content.Context;

public class RegularAlgo implements IAlgo {

    Context mContext;
    private int gap;

    /**
     * Test if the number of app start matches with a multiple of the gap value.
     * If yes, return true
     */
    @Override
    public boolean isDialogShowable() {
        int nbStart = PrefsTools.getInteger(mContext, PrefsTools.PREFS_START_COUNT);
        // Store nbStart incremented by 1
        PrefsTools.setInteger(mContext, PrefsTools.PREFS_START_COUNT, ++nbStart);
        DebugLog.d("RegularAlgo", "gap : " + gap + " / nbStart : " + nbStart);
        if (nbStart%gap == 0){
            return true;
        }

        return false;
    }

    public RegularAlgo(Context mContext, int gap) {
        super();
        this.mContext = mContext;
        this.gap = gap;
    }

}
