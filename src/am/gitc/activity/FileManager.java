package am.gitc.activity;

import am.gitc.R;
import am.gitc.util.ListAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class FileManager extends Activity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private File[] fileArray ;
    private ListAdapter listAdapter;
    private static ArrayList<File> files;
    private ArrayList<File> parentDir;
    File sdDir = android.os.Environment.getExternalStorageDirectory();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_manager_layout);

        files = new ArrayList<File>();
        parentDir = new ArrayList<File>();
        listView = (ListView) findViewById(R.id.myList);
        fileArray = sdDir.listFiles();

        for (int i = 0; i < fileArray.length; i++) {
            files.add(fileArray[i]);
        }

        listAdapter = new ListAdapter(files, this);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(this);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (parentDir.size() != 0) {

            fileArray = parentDir.get(parentDir.size() - 1).listFiles();
            parentDir.remove(parentDir.size() - 1);
            if (fileArray.length > 0) {
                files.clear();
                for (int j = 0; j < fileArray.length - 1; j++) {
                    files.add(fileArray[j]);
                }
                listAdapter.notifyDataSetChanged();

            } else {
                fileArray = sdDir.listFiles();
                for (int j = 0; j < fileArray.length - 1; j++) {
                    files.add(fileArray[j]);
                }

                listAdapter.notifyDataSetChanged();

            }
        } else Toast.makeText(this, "size =0", Toast.LENGTH_SHORT).show();

        return true;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        parentDir.add(files.get(i));
        Toast.makeText(this, files.get(i).getName(), Toast.LENGTH_SHORT).show();
        fileArray = files.get(i).listFiles();
        files.clear();
         if(fileArray==null){
             Toast.makeText(this, "No acsess", Toast.LENGTH_SHORT).show();
         } else
        if (fileArray.length >= 0) {

            for (int j = 0; j < fileArray.length; j++) {
                files.add(fileArray[j]);
            }

            listAdapter.notifyDataSetChanged();

        } else {
            Toast.makeText(this, "No files", Toast.LENGTH_SHORT).show();
        }

    }
}