package ru.era.balala.listapp;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class LoadApplicationsAsync extends AsyncTask<Void, Void, Void> {

    private List<ApplicationInfo> applist;
    private final PackageManager packageManager;
    private final ICallback callback;

    public LoadApplicationsAsync(PackageManager packageManager, ICallback callback) {
        this.packageManager = packageManager;
        this.callback = callback;
    }

    public interface ICallback {
        void returnList(List<ApplicationInfo> applist);

        void onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        callback.returnList(applist);
        super.onPostExecute(result);
    }

    @Override
    protected void onPreExecute() {
        callback.onPreExecute();
        super.onPreExecute();
    }

    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {

        List<ApplicationInfo> appList = new ArrayList();

        for (ApplicationInfo info : list) {
            try {
                if (packageManager.getLaunchIntentForPackage(info.packageName) != null) {
                    appList.add(info);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return appList;
    }
}