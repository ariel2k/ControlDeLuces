package ar.com.unlam.soa.controldeluces;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class SensorActivity extends AppCompatActivity implements SensorEventListener ,View.OnClickListener{

    TextView lblValorSensor;
    LinearLayout ln;
    SensorManager sm;
    Sensor sensor;
    private boolean leer = false;
    Button btnUso;
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
        btnUso = (Button) findViewById(R.id.btnUso);
        btnUso.setOnClickListener(this);
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
                    String url = "http://" + IP.getInstance().getIp() + "/angulo/";
                    if(leer) {
                        url += ((int) event.values[0]);
                    }
                    else {
                        url += 180;
                    }
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

    @Override
    public void onClick(View v) {
        if(this.leer == false) {
            this.leer = true;
            findViewById(R.id.btnUso).setBackgroundColor(Color.rgb(0, 255, 0));
        }
        else{
            this.leer = false;
            findViewById(R.id.btnUso).setBackgroundColor(Color.rgb(255,0,0));
        }
    }
}
