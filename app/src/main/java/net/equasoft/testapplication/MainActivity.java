package net.equasoft.testapplication;

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

        RatingReminder reminder = new RatingReminder(this);
        reminder.setAppName(getString(R.string.app_name));
        reminder.setAlgoType(AlgoType.NO_GAP_ALGO);
        reminder.setDialogType(RatingDialogType.BASIC_DIALOG);

        ArrayList<StoreType> storeTypes = new ArrayList<>();
        storeTypes.add(StoreType.GOOGLE_PLAYSTORE);
        reminder.setStoreTypes(storeTypes);

        reminder.process();

    }
}
