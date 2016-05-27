package ar.com.unlam.soa.controldeluces;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSensor, btnAlarma, btnPaleta, btnSensores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSensor = (Button) findViewById(R.id.btnSensor);
        btnPaleta = (Button) findViewById(R.id.btnPaleta);
        btnAlarma = (Button) findViewById(R.id.btnAlarma);
        btnSensores = (Button) findViewById(R.id.btnSensores);

        btnSensor.setOnClickListener(this);
        btnPaleta.setOnClickListener(this);
        btnAlarma.setOnClickListener(this);
        btnSensores.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btnSensor:
                intent = new Intent(MainActivity.this, SensorActivity.class);
                startActivity(intent);
                break;
            case R.id.btnPaleta:
                intent = new Intent(MainActivity.this, PaletaActivity.class);
                startActivity(intent);
                break;
            case R.id.btnAlarma:
                intent = new Intent(MainActivity.this, AlarmaActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSensores:
                intent = new Intent(MainActivity.this, SensoresActivity.class);
                startActivity(intent);
                break;
        }
    }
}
