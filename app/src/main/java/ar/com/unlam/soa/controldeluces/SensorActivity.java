package ar.com.unlam.soa.controldeluces;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener{

    TextView lblValorSensor;
    TextView lblNombresSensores;
    LinearLayout ln;
    SensorManager sm;
    Sensor sensor;

    int TipoSensor = Sensor.TYPE_ORIENTATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        lblValorSensor = (TextView) findViewById(R.id.lblValorSensor);
        ln = (LinearLayout) findViewById(R.id.linearSensor);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(TipoSensor);
        List<Sensor> listaSensores = sm.getSensorList(Sensor.TYPE_ALL);

        
        listaSensores = sm.getSensorList(TipoSensor);
        if (!listaSensores.isEmpty()) {
            Sensor acelerometerSensor = listaSensores.get(0);
            sm.registerListener(this, acelerometerSensor,
                    SensorManager.SENSOR_DELAY_UI);}

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            switch(event.sensor.getType()) {
                case Sensor.TYPE_ORIENTATION:
                    String cadena = "Ángulo: "+ ((int) event.values[0]);
                    lblValorSensor.setText(cadena);
                    break;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
