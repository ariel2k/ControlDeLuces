package ar.com.unlam.soa.controldeluces;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

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
                    AsyncHttpClient cli = new AsyncHttpClient();
                    String url = "http://" + IP.getInstance().getIp() + "/angulo/" + ((int) event.values[0]);
                    cli.get(url, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        }
                    });
                    break;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
