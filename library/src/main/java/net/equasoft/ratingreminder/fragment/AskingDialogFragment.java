package net.equasoft.ratingreminder.fragment;

import net.equasoft.ratingreminder.R;
import net.equasoft.ratingreminder.dialog.BasicDialogBuilder;
import net.equasoft.ratingreminder.dialog.ImageDialogBuilder;
import net.equasoft.ratingreminder.io.PrefsTools;
import net.equasoft.ratingreminder.type.RatingDialogType;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class AskingDialogFragment extends DialogFragment {

    private TextView askTextView;
    private String appName;
    private RatingDialogType dialogType;
    private OnRating onRating;

    public interface OnRating {
        void OnRatingOk();

        void OnRatingCancel();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = null;

        switch (dialogType) {
        case IMAGE_DIALOG:
            dialog = new ImageDialogBuilder(getActivity()).create();
            break;

        case BASIC_DIALOG:
            dialog = new BasicDialogBuilder(getActivity()).create();
            break;

        default:
            dialog = new BasicDialogBuilder(getActivity()).create();
            break;
        }

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        AlertDialog dialog = (AlertDialog) getDialog();

        if (appName != null) {
            askTextView = (TextView) dialog.findViewById(R.id.askRatingText);
            askTextView.setText(getString(R.string.rating_ask, appName));
        }

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                onRating.OnRatingOk();
                AskingDialogFragment.this.getDialog().cancel();
            }
        });

        dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                onRating.OnRatingCancel();
                AskingDialogFragment.this.getDialog().cancel();
            }
        });

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                PrefsTools.setBool(getActivity(), PrefsTools.PREFS_RATED, true);
                onRating.OnRatingCancel();
                AskingDialogFragment.this.getDialog().cancel();
            }
        });

    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public RatingDialogType getDialogType() {
        return dialogType;
    }

    public void setDialogType(RatingDialogType dialogType) {
        this.dialogType = dialogType;
    }

    public void setOnRating(OnRating onRating) {
        this.onRating = onRating;
    }
}
