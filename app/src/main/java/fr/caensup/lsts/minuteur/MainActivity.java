package fr.caensup.lsts.minuteur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnTime;
    
    private Button btnStop;

    private TextView tvTime;
    private TextView tvTime2;
    private TextView tvTime3;
    private boolean started;

    private TimeToGoReceiver ttgr1, ttgr2, ttgr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStart( v );
            }
        });

        btnTime = findViewById( R.id.btnTime );
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int timeToGo = ServiceMinuteur.getTimeToGo();
                String timeToGoStr = "temps restant : " + timeToGo;
                tvTime.setText( timeToGoStr );

            }
        });

        tvTime = findViewById( R.id.tvTime );

        btnStop = findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStop( v );
            }
        });

        tvTime2 = findViewById( R.id.tvTime2);
        tvTime3 = findViewById( R.id.tvTime3);

    }

    public void onClickStart(View v){
        Intent intent = new Intent(this, ServiceMinuteur.class);
        intent.putExtra("time", 180 );
        started = startService(intent) != null;
    }

    public void onClickStop(View v){
        started = !stopService(new Intent(this, ServiceMinuteur.class));
    }

    @Override
    protected void onResume( ) {
        super.onResume();
        IntentFilter filter= new IntentFilter("time.action.TIMETOGO");
        ttgr1 = new TimeToGoReceiver( "Receiver 1", tvTime);
        ttgr2 = new TimeToGoReceiver( "Receiver 2", tvTime2);
        ttgr3 = new TimeToGoReceiver( "Receiver 3", tvTime3);

        getApplicationContext().registerReceiver(ttgr1, filter);
        getApplicationContext().registerReceiver(ttgr2, filter);
        getApplicationContext().registerReceiver(ttgr3, filter);



    }

    @Override
    public void onPause() {
        super.onPause();
        getApplicationContext().unregisterReceiver(ttgr1);
        getApplicationContext().unregisterReceiver(ttgr2);
        getApplicationContext().unregisterReceiver(ttgr3);
    }


}