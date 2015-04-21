package net.equasoft.ratingreminder.dialog;

import net.equasoft.ratingreminder.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

public class BasicDialogBuilder extends AlertDialog.Builder {

    public BasicDialogBuilder(Activity a) {
        super(a);
        
        this.setTitle(R.string.rating_ask_basic_title);
        LayoutInflater inflater = a.getLayoutInflater();
        this.setView(inflater.inflate(R.layout.basic_dialog_fragment, null));
        this.setPositiveButton(R.string.rating_basic_ok, null);
        this.setNegativeButton(R.string.rating_no, null);
        this.setNeutralButton(R.string.rating_later, null);
    }
}
