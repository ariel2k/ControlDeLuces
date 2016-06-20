package ar.com.unlam.soa.controldeluces;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

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
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(PaletaActivity.this, "" + getColorImagen(position),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
