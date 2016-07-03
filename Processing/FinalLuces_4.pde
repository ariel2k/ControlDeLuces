import processing.serial.*;
import cc.arduino.*;

static int red_pin = 10;
static int green_pin = 11;
static int blue_pin = 9;

Arduino arduino;

// ------- Get informacion servidor ------- //
String pathServer 	= "http://localhost:8080/arduino/";
String pathAngulo 	= "angulo.txt";
String pathColores 	= "colores.txt";
String pathAlarma 	= "despertador.txt";
String pathEstado 	= "estado.txt";
String caracterSeparacion = " ";

// ------- Set informacion servidor ------- //
String pathRestServer 	= "http://localhost:8080/arduino/rest/";
String pathRestEstado 	= "estado/";

// ------- Estados anteriores ------- //
String coloresAnterior 	= "";
String anguloAnterior 	= "";
String alarmaAnterior 	= "";
String estadoAnterior 	= "";
String[] cadenaRGBTotal;
String[] alarmaTotalSeparada;

// ------- Estados actuales ------- //
String estadoActual 	= "";
String coloresActual 	= "";
String alarmaActual 	= "";
String anguloActual 	= "";

// ------- Informacion Sistema de colores ------- //
int red 	= 0;
int green 	= 0;
int blue 	= 0;

int horaAlarma 		= 99;
int minutosAlarma 	= 99;
boolean yaSonoAlarma = false;
int anguloCelular = 0;

void setup(){
  size(300,250);
  textSize(26); 
	println("COM Ports");
	println(Arduino.list());
	println("=========");
	arduino=new Arduino(this, Arduino.list()[0], 57600);
	arduino.pinMode(red_pin, Arduino.OUTPUT);
	arduino.pinMode(green_pin, Arduino.OUTPUT);
	arduino.pinMode(blue_pin, Arduino.OUTPUT);
}

void draw(){
	leerArchivos(); 
	if (estaApagada()) {
		desactivarLuces();
	}else{
		mostrarColores();
	}
	
  revisarAlarma();
	background(red+anguloCelular,green+anguloCelular,blue+anguloCelular);
	text("Estado: " + estadoActual +
        "\nHora: " + hour() + ":" + minute() +
        "\nHora Alarma: " + horaAlarma + ":" + minutosAlarma +
        "\nColores: " + red + "," + green + "," + blue +
        "\nAngulo: "+ anguloCelular+"°" +
        "\nYa sono alarma: " + yaSonoAlarma,20,40);

}

//Lee los archivos y si hubo algun cambio, los guarda y asigna
void leerArchivos(){
  	estadoActual = leerServidor(pathEstado); 
  	coloresActual = leerServidor(pathColores);
  	alarmaActual = leerServidor(pathAlarma);
  	anguloActual = leerServidor(pathAngulo); 

  	if(huboCambio(estadoActual, estadoAnterior)){
  		estadoAnterior = estadoActual;
  	}

  	if(huboCambio(coloresActual, coloresAnterior) || red + green +blue == 0){
  		coloresAnterior = coloresActual;
  		cadenaRGBTotal = split(coloresActual, caracterSeparacion);
	    red = Integer.parseInt(cadenaRGBTotal[0]);
	    green = Integer.parseInt(cadenaRGBTotal[1]);
	    blue = Integer.parseInt(cadenaRGBTotal[2]);
  	}

  	if(huboCambio(alarmaActual, alarmaAnterior)){
  		alarmaAnterior = alarmaActual;
  		yaSonoAlarma = false;
  		alarmaTotalSeparada = split(alarmaActual, caracterSeparacion);
	    horaAlarma = Integer.parseInt(alarmaTotalSeparada[0]);
	    minutosAlarma = Integer.parseInt(alarmaTotalSeparada[1]);
  	}

  	if(huboCambio(anguloActual, anguloAnterior)){
  		anguloAnterior= anguloActual;
  		anguloCelular = Integer.parseInt(anguloActual) * 255 / 360;
  	}  	
}

String leerServidor(String path){
	try{
   		return join(loadStrings(pathServer + path),"");
	}
	catch (Exception e) {
		println("Error inesperado al leer archivo " + path + ".");
	}
	return null;
}

boolean huboCambio (String cadenaActual, String cadenaAnterior){
	return !cadenaActual.equals(cadenaAnterior);
}

boolean estaApagada(){
	return estadoAnterior.equals("off") 
      || estadoAnterior.equals("false") 
      || estadoAnterior.equals("apagado");
}

void mostrarColores(){
	arduino.analogWrite(red_pin, (red+anguloCelular));
	arduino.analogWrite(green_pin, (green+anguloCelular));
	arduino.analogWrite(blue_pin, (blue+anguloCelular));
}

void desactivarLuces(){
  	red 	= 0;
  	blue 	= 0;
  	green 	= 0;
}


void revisarAlarma(){
  	if( yaSonoAlarma == false && horaAlarma == hour() && minutosAlarma == minute()){
		yaSonoAlarma = true;
	    if(estaApagada()){
	        red = Integer.parseInt(cadenaRGBTotal[0]);
          green = Integer.parseInt(cadenaRGBTotal[1]);
          blue = Integer.parseInt(cadenaRGBTotal[2]);
          loadStrings(pathRestServer + pathRestEstado + "on");
          println("Entre a on "  + red + "," + green + "," + blue);
	    } else {
	      red   = 0;
          blue   = 0;
          green   = 0;
	        loadStrings(pathRestServer + pathRestEstado + "off");
          println("Entre a off"  + red + "," + green + "," + blue);
      } 
      println("me voy de la alarma");
      delay(100);
	}
}