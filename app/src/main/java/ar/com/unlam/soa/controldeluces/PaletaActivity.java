package ar.com.unlam.soa.controldeluces;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class PaletaActivity extends AppCompatActivity {
    // Colores de cada imagen, se corresponden en posicion
    private String[] mRGB = {
            // Fila1
            "ff0000",
            "065208",
            "320cf5",
            // Fila 2
            "f76f6f",
            "6eb970",
            "6650d8",
            // Fila 3
            "871f1f",
            "bef4c0",
            "a7a2bd",
            // Fila 4
            "e2c0c0",
            "dae8db",
            "57c6f0"
    };
    public String getColorImagen(Integer n) {
        return mRGB[n];
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paleta);
        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new ImageAdapter(this));

        // Para cada click de color
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(PaletaActivity.this, IP.getInstance().getIp(), Toast.LENGTH_LONG);
                // Variable de color
                String color = getColorImagen(position);
                String r, g, b;
                r = Integer.valueOf(color.substring(0, 2), 16).toString();
                g = Integer.valueOf(color.substring(2, 4), 16).toString();
                b = Integer.valueOf(color.substring(4, 6), 16).toString();
                // Creo la ruta para el rest
                String url = "http://" + IP.getInstance().getIp() + "/colores/" + r + "/" + g + "/" + b;
                Toast.makeText(PaletaActivity.this, url,Toast.LENGTH_LONG).show();
                AsyncHttpClient cli = new AsyncHttpClient();
                cli.get(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Toast.makeText(PaletaActivity.this, "Color cambiado con exito!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(PaletaActivity.this, "Fallo al cambiar el color!", Toast.LENGTH_LONG).show();;
                    }
                    @Override
                    public void onStart() {

                    }
                    @Override
                    public void onRetry(int retryNro) {

                    }
                });
            }
        });

    }
}
