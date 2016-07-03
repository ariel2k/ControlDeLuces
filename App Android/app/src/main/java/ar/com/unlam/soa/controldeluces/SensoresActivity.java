package ar.com.unlam.soa.controldeluces;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SensoresActivity extends AppCompatActivity implements SensorEventListener {

    TextView nombreSensores;
    TextView valoresSensores;

    float orientacion1  = 0, orientacion2  = 0, orientacion3 = 0,
            acelerometro1  = 0, acelerometro2  = 0, acelerometro3 = 0,
            magnetico1  = 0,magnetico2 = 0, magnetico3 = 0,
            temperatura1 = 0,temperatura2 = 0, temperatura3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);

        nombreSensores = (TextView) findViewById(R.id.lblNombreSensores);
        valoresSensores = (TextView) findViewById(R.id.lblValoresSensores);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for(Sensor sensor: listaSensores) {
            log(sensor.getName() + " - " + sensor.getMaximumRange());
        }



        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
        if (!listaSensores.isEmpty()) {
            Sensor orientationSensor = listaSensores.get(0);
            sensorManager.registerListener(this, orientationSensor,SensorManager.SENSOR_DELAY_UI);}

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (!listaSensores.isEmpty()) {
            Sensor acelerometerSensor = listaSensores.get(0);
            sensorManager.registerListener(this, acelerometerSensor,SensorManager.SENSOR_DELAY_UI);}

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);
        if (!listaSensores.isEmpty()) {
            Sensor magneticSensor = listaSensores.get(0);
            sensorManager.registerListener(this, magneticSensor,SensorManager.SENSOR_DELAY_UI);}

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_TEMPERATURE);
        if (!listaSensores.isEmpty()) {
            Sensor temperatureSensor = listaSensores.get(0);
            sensorManager.registerListener(this, temperatureSensor,SensorManager.SENSOR_DELAY_UI);}
    }

    private void log(String string) {
        nombreSensores.append(string + "\n");
    }

    private void valores(float valorSensor, int tipoSensor, int nroSensor){
        String cadena = "";
        switch (tipoSensor){
            case Sensor.TYPE_ORIENTATION:
                if(nroSensor == 0)
                    orientacion1 = valorSensor;
                else if (nroSensor == 1)
                    orientacion2 = valorSensor;
                else if (nroSensor == 2)
                    orientacion3 = valorSensor;
                break;
            case Sensor.TYPE_ACCELEROMETER:
                if(nroSensor == 0)
                    acelerometro1 = valorSensor;
                else if (nroSensor == 1)
                    acelerometro2 = valorSensor;
                else if (nroSensor == 2)
                    acelerometro3 = valorSensor;
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                if(nroSensor == 0)
                    magnetico1 = valorSensor;
                else if (nroSensor == 1)
                    magnetico2 = valorSensor;
                else if (nroSensor == 2)
                    magnetico3 = valorSensor;
                break;
            default:
                if(nroSensor == 0)
                    temperatura1 = valorSensor;
                else if (nroSensor == 1)
                    temperatura2 = valorSensor;
                else if (nroSensor == 2)
                    temperatura3 = valorSensor;
        }

        cadena += "Valores sensores:" +
                "\nOrientacion 1: " + orientacion1 +
                "\nOrientacion 2: " + orientacion2 +
                "\nOrientacion 3: " + orientacion3 +
                "\nAcelerometro 1: " + acelerometro1 +
                "\nAcelerometro 2: " + acelerometro2 +
                "\nAcelerometro 3: " + acelerometro3 +
                "\nMagnetico 1: " + magnetico1 +
                "\nMagnetico 2: " + magnetico2 +
                "\nMagnetico 3: " + magnetico3 +
                "\nTemperatura 1: " + temperatura1 +
                "\nTemperatura 2: " + temperatura2 +
                "\nTemperatura 3: " + temperatura3;

        valoresSensores.setText(cadena);
    }

    @Override
    public void onSensorChanged(SensorEvent evento) {

        synchronized (this) {
            switch(evento.sensor.getType()) {
                case Sensor.TYPE_ORIENTATION:
                    for (int i=0 ; i<evento.values.length ; i++) {
                        valores(evento.values[i], Sensor.TYPE_ORIENTATION, i);
                    }
                    break;
                case Sensor.TYPE_ACCELEROMETER:

                    for (int i=0 ; i<evento.values.length ; i++) {
                        valores(evento.values[i], Sensor.TYPE_ACCELEROMETER, i);
                    }
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:

                    for (int i=0 ; i<evento.values.length ; i++) {
                        valores(evento.values[i], Sensor.TYPE_MAGNETIC_FIELD, i);
                    }
                    break;
                default:
                    for (int i=0 ; i<evento.values.length ; i++) {
                        valores(evento.values[i], Sensor.TYPE_TEMPERATURE, i);
                    }
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
