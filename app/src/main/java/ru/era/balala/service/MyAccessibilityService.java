package ru.era.balala.service;

import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

public class MyAccessibilityService extends android.accessibilityservice.AccessibilityService {

    final String LOG_TAG = "mLog";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        Log.d(LOG_TAG, "MyAccessibilityService: onKeyEvent: action = " + event.getAction() +
                "; key code = " + event.getKeyCode() +
                "; scan code = " + event.getScanCode() +
                "; meta state = " + event.getMetaState() +
                "; key = " + event.getNumber());
//        int keyCode = event.getKeyCode();
//        switch (keyCode) {
//            case 8:
//                Intent intent8 = getPackageManager().getLaunchIntentForPackage("com.bambuna.podcastaddict");
//                intent8.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent8);
//                break;
//            case 9:
//                Intent intent9 = getPackageManager().getLaunchIntentForPackage("com.pasha.kissfm");
//                intent9.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent9);
//                break;
//            case 10:
//                Intent intent10 = getPackageManager().getLaunchIntentForPackage("com.google.android.apps.maps");
//                intent10.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent10);
//                break;
//        }
        return super.onKeyEvent(event);
    }
}
