package ar.com.unlam.soa.controldeluces;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PaletaActivity extends AppCompatActivity {

    ListView listaPaleta;
    String[] paleta = new String[] {"Blanco", "Negro", "Azul", "Amarillo", "Verde", "Rojo", "Naranja", "Marron"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paleta);

        listaPaleta = (ListView) findViewById(R.id.listaPaleta);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, paleta);
        listaPaleta.setAdapter(adapter);

        listaPaleta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Color cambiado", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
