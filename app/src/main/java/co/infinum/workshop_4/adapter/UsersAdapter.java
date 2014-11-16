package co.infinum.workshop_4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import co.infinum.workshop_4.R;
import co.infinum.workshop_4.modules.User;


/**
 * Created by ivankocijan on 24.03.2014..
 */
public class UsersAdapter extends ArrayAdapter<User> {

    LayoutInflater inflater;

    public UsersAdapter (Context context, List<User> objects) {
        super(context, 0, objects);

        inflater = LayoutInflater.from(context);

    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_user, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.list_item_name);
        TextView surname = (TextView) convertView.findViewById(R.id.list_item_surname);
        TextView description = (TextView) convertView.findViewById(R.id.list_item_description);

        name.setText(getItem(position).getName());
        surname.setText(getItem(position).getSurname());
        description.setText(getItem(position).getDescription());

        return convertView;


    }

}
