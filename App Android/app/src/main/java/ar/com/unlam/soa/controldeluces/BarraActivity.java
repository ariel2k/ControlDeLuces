package ar.com.unlam.soa.controldeluces;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class BarraActivity extends AppCompatActivity {

    private SeekBar barraRoja, barraVerde, barraAzul;
    private TextView txtRojo, txtVerde, txtAzul;
    private int rojo, verde, azul;
    private LinearLayout activityLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barra);

        barraRoja = (SeekBar) findViewById(R.id.barraRoja);
        barraVerde = (SeekBar) findViewById(R.id.barraVerde);
        barraAzul = (SeekBar) findViewById(R.id.barraAzul);

        txtRojo = (TextView) findViewById(R.id.txtRojo);
        txtVerde = (TextView) findViewById(R.id.txtVerde);
        txtAzul = (TextView) findViewById(R.id.txtAzul);

        activityLinear = (LinearLayout) findViewById(R.id.activityBarra);



        barraRoja.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int seekBarProgressRojo = 0;
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarProgressRojo = progress;
                txtRojo.setText("Rojo: " + seekBarProgressRojo + " / " + seekBar.getMax());
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                rojo = seekBarProgressRojo;
                mandarColores();
            }
        });

        barraVerde.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int seekBarProgressVerde = 0;
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarProgressVerde = progress;
                txtVerde.setText("Verde: " + seekBarProgressVerde + " / " + seekBar.getMax());
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                verde = seekBarProgressVerde;
                mandarColores();
            }
        });

        barraAzul.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int seekBarProgressAzul = 0;
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarProgressAzul = progress;
                txtAzul.setText("Azul: " + seekBarProgressAzul + " / " + seekBar.getMax());
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                azul = seekBarProgressAzul;
                mandarColores();
            }
        });

    }

    void mandarColores() {
        activityLinear.setBackgroundColor(Color.rgb(rojo,verde,azul));
        // Creo la ruta para el rest
        String url = "http://" + IP.getInstance().getIp() + "/colores/" + rojo + "/" + verde + "/" + azul;
        AsyncHttpClient cli = new AsyncHttpClient();
        cli.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                /// Toast.makeText(PaletaActivity.this, "Color cambiado con exito!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //  Toast.makeText(PaletaActivity.this, "Fallo al cambiar el color!", Toast.LENGTH_LONG).show();;
            }

            @Override
            public void onStart() {
            }

            @Override
            public void onRetry(int retryNro) {
            }
        });
    }
}
