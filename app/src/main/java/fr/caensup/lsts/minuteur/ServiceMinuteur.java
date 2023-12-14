package fr.caensup.lsts.minuteur;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ServiceMinuteur extends Service {

    private static Thread countDown = null;
    public static int timeToGo;

    public ServiceMinuteur() {
    }


    public int onStartCommand(Intent intent, int flags, int startId) {
        if (countDown == null) {
            countDown = new Thread(new CountDown(intent.getIntExtra("time", 180))); // un thread local
            countDown.start();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        countDown.interrupt();
        countDown = null;
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    static public int getTimeToGo() {
        return timeToGo;
    }

    public class CountDown implements Runnable {


        public CountDown(int time) {
            timeToGo = time;
        }

        @Override
        public void run() {
            while (timeToGo > 0 && !countDown.isInterrupted()) {
                try {
                    Thread.sleep(1000);
                    timeToGo--;
                    // Envoi en broadcast sur les bradcastreceiver registrés
                    Intent notif= new Intent("time.action.TIMETOGO");
                    notif.putExtra("timetogo", timeToGo);
                    sendBroadcast(notif);

                } catch (Exception e) {
                    return;
                }
            }

        }

    }


}