package net.equasoft.demoapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.equasoft.ratingreminder.RatingReminder;
import net.equasoft.ratingreminder.type.AlgoType;
import net.equasoft.ratingreminder.type.RatingDialogType;
import net.equasoft.ratingreminder.type.StoreType;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RatingReminder object
        RatingReminder reminder = new RatingReminder(this);
        // Give a name that will be used in dialogs for your app
        reminder.setAppName(getString(R.string.app_name));
        //Select an algo
        reminder.setAlgoType(AlgoType.NO_GAP_ALGO); // NO_GAP_ALOG triggers every time
        // Select a dialog type
        reminder.setDialogType(RatingDialogType.BASIC_DIALOG); // basic dialog uses default system display

        // Configure a store
        ArrayList<StoreType> storeTypes = new ArrayList<>();
        storeTypes.add(StoreType.GOOGLE_PLAYSTORE);
        reminder.setStoreTypes(storeTypes);

        // Trigger the dialog if algo decides to
        reminder.process();

    }
}
