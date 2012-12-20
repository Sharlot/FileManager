package am.gitc.util;

import am.gitc.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;


public class ListAdapter extends BaseAdapter {

    private ArrayList<File> files;
    private Context context;
    private LayoutInflater layoutInflater;

    public ListAdapter(ArrayList<File> files, Context context) {
        this.files = files;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return files.size();
    }

    public Object getItem(int i) {
        return files.get(i);
    }

    public long getItemId(int i) {
        return i;
    }

    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = null;
        if (view == null) {
            v = layoutInflater.inflate(R.layout.list_item, null);
        } else {
            v = view;
        }
        TextView fileName = (TextView) v.findViewById(R.id.file_name);
        ImageView imageView = (ImageView) v.findViewById(R.id.file_image);
        fileName.setText(files.get(position).getName());
        if (files.get(position).isDirectory()) {
            imageView.setImageResource(R.drawable.folder);

        } else {
            imageView.setImageResource(R.drawable.file);
        }


        return v;
    }
}
