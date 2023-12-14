package fr.caensup.lsts.minuteur;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

public class TimeToGoReceiver extends BroadcastReceiver  {
    private String name;
    private TextView tv;

    public TimeToGoReceiver( String name , TextView tv ) {
        this.name = name;
        this.tv = tv;
    }

    public TimeToGoReceiver( TextView tv ) {
        this( "Anonymous" , tv );
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final int timeToGo = intent.getIntExtra("timetogo", 0);
        String result = name + " : " + timeToGo;
        tv.setText( result );
    }
}
