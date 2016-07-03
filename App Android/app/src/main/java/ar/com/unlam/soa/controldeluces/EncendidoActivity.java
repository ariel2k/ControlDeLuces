package ar.com.unlam.soa.controldeluces;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class EncendidoActivity extends Activity implements SensorEventListener {

    private TextView txtX;
    private TextView txtY;
    private TextView txtZ;
    private TextView max;
    private int estado = 1;
    private float curX;
    private float curY;
    private float curZ;
    private float prevX;
    private float prevY;
    private float prevZ;

    private long last_update;
    private long last_movement;

    private float max_movement = 0;

    private boolean mostrarDatos = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encendido2);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txtX = (TextView)findViewById(R.id.txtX);
        txtY = (TextView)findViewById(R.id.txtY);
        txtZ = (TextView)findViewById(R.id.txtZ);
        max = (TextView)findViewById(R.id.max);

        max.setText("MAX: "+max_movement);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensors.size() > 0) {
            sm.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    protected void onStop() {
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sm.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            if(mostrarDatos){
                long current_time = event.timestamp;

                curX = event.values[0];
                curY = event.values[1];
                curZ = event.values[2];

                if (prevX == 0 && prevY == 0 && prevZ == 0) {
                    last_update = current_time;
                    last_movement = current_time;
                    prevX = curX;
                    prevY = curY;
                    prevZ = curZ;
                }

                long time_difference = current_time - last_update;
                Log.i("4a4s1d4", "time_difference: "+time_difference+" - current_time: "+current_time+" - last_update: "+last_update);
                if (time_difference > 50000000) {
                    Log.i("4a4s1d4", "*************");
                    float movement = Math.abs((curX + curY + curZ) - (prevX - prevY - prevZ)) / time_difference;
                    if(movement > max_movement) {
                        max_movement = movement;
                        max.setText("MAX: "+max_movement);
                    }
                    if(movement > 0.000001){
                        String url = "http://" + IP.getInstance().getIp() + "/estado/";
                        AsyncHttpClient cli = new AsyncHttpClient();
                        if(this.estado == 1) {
                            url += "off";
                            this.estado=0;
                        }
                        else {
                            url += "on";
                            this.estado=1;
                        }
                        cli.get(url, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                Toast.makeText(EncendidoActivity.this, "Estado cambiado!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                Toast.makeText(EncendidoActivity.this, "Fallo al cambiar el estado!!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    //si es mayor a este valor 1.0E-6, activa
                /*int limit = 1500;
                float min_movement = 1E-6f;
                if (movement > min_movement) {
                    if (current_time - last_movement >= limit) {
                        Toast.makeText(getApplicationContext(), "Hay movimiento de " + movement, Toast.LENGTH_SHORT).show();
                    }
                    Log.i("4a4s1d4", "Hay movimiento de " + movement);
                    last_movement = current_time;
                }*/
                    prevX = curX;
                    prevY = curY;
                    prevZ = curZ;
                    last_update = current_time;
                }

                //if(mostrarDatos){
                txtX.setText("Acelerómetro X: " + curX);
                txtY.setText("Acelerómetro Y: " + curY);
                txtZ.setText("Acelerómetro Z: " + curZ);
                //}
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
