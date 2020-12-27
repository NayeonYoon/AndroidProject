package android.cs.pusan.ac.week2;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {
    private final static int NOTICATION_ID = 222;

    public void onReceive(Context context, Intent intent) {

        String message = intent.getStringExtra("message");
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, "default")
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL);

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(NOTICATION_ID, builder.build());
    }
}
