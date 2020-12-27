package android.cs.pusan.ac.week2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor stepCountSensor;
    public AlarmManager am;
    public EditText message;
    public EditText month;
    public EditText day;
    public EditText year;
    public EditText hour;
    public EditText minute;
    public Button save;
    public ImageButton ArmButton;
    public ImageButton StretchButton;
    public ImageButton StmchButton;
    public ImageButton LegButton;
    public TextView walkView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArmButton = (ImageButton) findViewById(R.id.ArmButton);
        StretchButton = (ImageButton) findViewById(R.id.StretchButton);
        StmchButton = (ImageButton) findViewById(R.id.StmchButton);
        LegButton = (ImageButton) findViewById(R.id.LegButton);
        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        message = (EditText) findViewById(R.id.message);
        month = (EditText) findViewById(R.id.month);
        day = (EditText) findViewById(R.id.day);
        year = (EditText) findViewById(R.id.year);
        hour = (EditText) findViewById(R.id.hour);
        minute = (EditText) findViewById(R.id.minute);
        save = (Button) findViewById(R.id.save);
        walkView = (TextView) findViewById(R.id.walkView);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (stepCountSensor == null) {
            Toast.makeText(this, "No Step Detect Sensor", Toast.LENGTH_SHORT).show();
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
                intent.putExtra("message", message.getText().toString());
                PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

                Calendar cal = Calendar.getInstance();
                cal.set(Integer.parseInt(year.getText().toString()),
                        Integer.parseInt(month.getText().toString()) - 1,
                        Integer.parseInt(day.getText().toString()),
                        Integer.parseInt(hour.getText().toString()),
                        Integer.parseInt(minute.getText().toString()));
                cal.set(Calendar.SECOND, 0);

                am.set(AlarmManager.RTC, cal.getTimeInMillis(), sender);
            }
        });

        ArmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=%ED%8C%94+%EC%9A%B4%EB%8F%99"));
                startActivity(intent);
            }
        });

        StretchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=%EC%8A%A4%ED%8A%B8%EB%A0%88%EC%B9%AD"));
                startActivity(intent);
            }
        });


        StmchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=%EB%B3%B5%EA%B7%BC"));
                startActivity(intent);
            }
        });

        LegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=%EB%8B%A4%EB%A6%AC+%EC%9A%B4%EB%8F%99"));
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, stepCountSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            walkView.setText(String.valueOf(event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
