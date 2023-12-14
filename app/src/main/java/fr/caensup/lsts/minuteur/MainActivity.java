package fr.caensup.lsts.minuteur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnTime;
    
    private Button btnStop;

    private TextView tvTime;
    private boolean started;

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

        
    }

    public void onClickStart(View v){
        Intent intent = new Intent(this, ServiceMinuteur.class);
        intent.putExtra("time", 180 );
        started = startService(intent) != null;
    }

    public void onClickStop(View v){
        started = !stopService(new Intent(this, ServiceMinuteur.class));
    }


}