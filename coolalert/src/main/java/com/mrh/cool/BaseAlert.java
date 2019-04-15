package com.mrh.cool;

import android.view.View;

public abstract class BaseAlert {
    public abstract void setBackground(int color);
    public abstract void show();
    public abstract void dismiss();
    public abstract void setCancelable(boolean cancelable);
    public abstract View getView(int viewId);
}
