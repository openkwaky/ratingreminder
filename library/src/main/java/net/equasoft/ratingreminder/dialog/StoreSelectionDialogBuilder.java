package net.equasoft.ratingreminder.dialog;

import java.util.ArrayList;
import java.util.List;

import net.equasoft.ratingreminder.R;
import net.equasoft.ratingreminder.io.PrefsTools;
import net.equasoft.ratingreminder.model.Store;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class StoreSelectionDialogBuilder extends AlertDialog.Builder {

    Activity mActivity;
    ArrayList<String> storeTitles;
    ArrayList<Store> stores;

    public StoreSelectionDialogBuilder(Activity a, ArrayList<Store> stores) {
        super(a);
        this.mActivity = a;
        this.stores = stores;
        this.setTitle(R.string.rating_store_selection_title);
        buildAdapter();
    }

    private void buildAdapter() {
        storeTitles = new ArrayList<String>();

        for (Store _s : stores) {
            storeTitles.add(mActivity.getString(_s.getTitleStringId()));
        }

        ListAdapter adapter = new StoreSelectionAdapter(mActivity, R.layout.dialog_store_list_item,
                storeTitles);
        this.setAdapter(adapter, new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PrefsTools.setBool(mActivity, PrefsTools.PREFS_RATED, true);
                stores.get(which).goRating(mActivity);
                dialog.dismiss();
            }
        });
    }

    private class StoreSelectionAdapter extends ArrayAdapter<String> {

        public StoreSelectionAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

        ViewHolder holder;

        class ViewHolder {
            ImageView icon;
            TextView title;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            final LayoutInflater inflater = (LayoutInflater) mActivity.getApplicationContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.dialog_store_list_item, null);

                holder = new ViewHolder();
                holder.icon = (ImageView) convertView.findViewById(R.id.icon);
                holder.title = (TextView) convertView.findViewById(R.id.title);
                convertView.setTag(holder);
            } else {
                // view already defined, retrieve view holder
                holder = (ViewHolder) convertView.getTag();
            }

            holder.title.setText(storeTitles.get(position));

            holder.icon.setImageResource(stores.get(position).getLogoId());
            return convertView;
        }
    }

}
