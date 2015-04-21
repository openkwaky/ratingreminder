package net.equasoft.ratingreminder.algo;

import net.equasoft.ratingreminder.io.PrefsTools;
import net.equasoft.ratingreminder.log.DebugLog;
import android.content.Context;

public class DoubleGapAlgo implements IAlgo {

    private Context mContext;
    private int gap;

    /**
     * Test if the number of app start matches with the limit fixed in the
     * SharedPreferences value. 
     * If yes, we calculate the next limit to trigger the dialog fragment
     */
    @Override
    public boolean isDialogShowable() {
        int nbStart = PrefsTools.getInteger(mContext, PrefsTools.PREFS_START_COUNT);
        // Store nbStart incremented by 1
        PrefsTools.setInteger(mContext, PrefsTools.PREFS_START_COUNT, ++nbStart);
        int nbLimit = PrefsTools.getInteger(mContext, PrefsTools.PREFS_LIMIT);
        if (nbLimit == 0) {
            nbLimit = gap;
            PrefsTools.setInteger(mContext, PrefsTools.PREFS_LIMIT, nbLimit);
        }

        DebugLog.d("DoubleGapAlgo", "gap : " + gap + " / nbStart : " + nbStart +
                 " / nbLimit : " + nbLimit);

        if (nbStart>=nbLimit){
            PrefsTools.setInteger(mContext, PrefsTools.PREFS_LIMIT, 2*nbLimit + gap);
            return true;
        }

        return false;
    }

    public DoubleGapAlgo(Context mContext, int gap) {
        super();
        this.mContext = mContext;
        this.gap = gap;
    }

}
