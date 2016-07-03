package ar.com.unlam.soa.controldeluces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSensor, btnAlarma, btnPaleta, btnSensores, btnConectar, btnColoresRGB,btnLatigo ;
    public static String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnColoresRGB = (Button) findViewById(R.id.btnColoresRGB);
        btnSensor = (Button) findViewById(R.id.btnSensor);
        btnPaleta = (Button) findViewById(R.id.btnPaleta);
        btnAlarma = (Button) findViewById(R.id.btnAlarma);
        btnConectar = (Button) findViewById(R.id.btnConectar);
        btnLatigo = (Button) findViewById(R.id.btnLatigo);

        btnColoresRGB.setOnClickListener(this);
        btnSensor.setOnClickListener(this);
        btnPaleta.setOnClickListener(this);
        btnAlarma.setOnClickListener(this);
        btnConectar.setOnClickListener(this);
        btnLatigo.setOnClickListener(this);
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
           /* case R.id.btnSensores:
                intent = new Intent(MainActivity.this, SensoresActivity.class);
                startActivity(intent);
                break;*/
            case R.id.btnConectar:
                IP i = IP.getInstance();
                i.setInstance(((EditText) findViewById(R.id.textIp)).getText().toString() + ":8080/arduino/rest");
                break;
            case R.id.btnColoresRGB:
                intent = new Intent(MainActivity.this, BarraActivity.class);
                startActivity(intent);
                break;
            case R.id.btnLatigo:
                intent = new Intent(MainActivity.this, EncendidoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
