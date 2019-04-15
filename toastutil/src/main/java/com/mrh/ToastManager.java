package com.mrh;

import android.content.Context;
import android.widget.Toast;

public class ToastManager {
    private static Context context;

    public static void init(Context context){
        ToastManager.context = context;
    }

    public static void show(String text){
        if (text == null)
            return;
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
}
