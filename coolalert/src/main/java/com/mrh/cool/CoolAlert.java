package com.mrh.cool;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class CoolAlert extends BaseAlert{
    private PopupWindow popupWindow;
    private View view;
    private LinearLayout container;
    private Context context;
    private boolean cancelable = true;

    public CoolAlert(Context context,int layoutId){
        this.context = context;
        popupWindow = new PopupWindow(context);
        view = LayoutInflater.from(context).inflate(layoutId,null,false);
        container = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.cool_alert_layout,null,false);
        View place1 = getPlaceView();
        View place2 = getPlaceView();
        container.addView(place1);
        container.addView(view);
        container.addView(place2);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#66000000")));
        popupWindow.setFocusable(true);
        popupWindow.setContentView(container);
    }

    private View getPlaceView(){
        View view = new View(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
        view.setLayoutParams(layoutParams);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click2cancel();
            }
        });
        return view;
    }

    private void click2cancel(){
        if (cancelable && popupWindow != null)
            popupWindow.dismiss();
    }

    @Override
    public void setCancelable(boolean cancelable){
        this.cancelable = cancelable;
    }

    @Override
    public View getView(int viewId){
        return view.findViewById(viewId);
    }

    @Override
    public void setBackground(int color){
        if (popupWindow != null)
            popupWindow.setBackgroundDrawable(new ColorDrawable(color));
    }

    @Override
    public void show(){
        if (popupWindow != null)
            popupWindow.showAsDropDown(view);
    }

    @Override
    public void dismiss(){
        if (popupWindow != null)
            popupWindow.dismiss();
    }

}
