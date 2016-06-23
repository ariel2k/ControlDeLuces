package ar.com.unlam.soa.controldeluces;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class AlarmaActivity extends AppCompatActivity {
    Button guardar;
    TimePicker timePickerMotherFucker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma);

        guardar = (Button) findViewById(R.id.btnSetearAlarma);
        timePickerMotherFucker = (TimePicker) findViewById(R.id.timePicker);

        guardar.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                String url = "http://" + IP.getInstance().getIp() + "/despertador/" + timePickerMotherFucker.getHour() + "/" + timePickerMotherFucker.getMinute();
                AsyncHttpClient cli = new AsyncHttpClient();
                cli.get(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Toast.makeText(AlarmaActivity.this, "Alarma Configurada!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(AlarmaActivity.this, "No se pudo configurar la alarma", Toast.LENGTH_LONG).show();;
                    }
                });
            }
        });
    }
}
