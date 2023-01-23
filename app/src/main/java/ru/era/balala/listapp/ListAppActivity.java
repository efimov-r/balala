package ru.era.balala.listapp;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import ru.era.balala.R;

public class ListAppActivity extends ListActivity {

    private ProgressDialog progressDialog;
    private PackageManager packageManager;
    private AppAdapter listadapter;

    public static Intent buildIntent(Context context) {
        return new Intent(context, ListAppActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        packageManager = getPackageManager();

        new LoadApplicationsAsync(packageManager, new LoadApplicationsAsync.ICallback() {
            @Override
            public void returnList(List<ApplicationInfo> applist) {
                listadapter = new AppAdapter(ListAppActivity.this, R.layout.list_item, applist);
                setListAdapter(listadapter);

                progressDialog.dismiss();
            }

            @Override
            public void onPreExecute() {
                progressDialog = ProgressDialog.show(ListAppActivity.this, null, "Loading apps info...");
            }
        }).execute();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ApplicationInfo app = listadapter.getItem(position);

        try {
            Intent intent = packageManager.getLaunchIntentForPackage(app.packageName);

            if (intent != null) {
                startActivity(intent);
            }
        } catch (Exception e) {
            Toast.makeText(ListAppActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
