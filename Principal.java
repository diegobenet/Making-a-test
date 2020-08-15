/**************************************************************

Autor: Diego Elizondo Benet
Fecha:24 Septiembre 2019
Objetivo:Crear una biblioteca con Libros.
***************************************************************/
import java.io.*;
import java.util.*;

//inicio del programa
class Principal{
//clase main
public static void main(String arg[]){
   
   Calendar c=new GregorianCalendar();
   int inicioS=c.get(Calendar.SECOND);
   int inicioH=c.get(Calendar.HOUR);
   int inicioM=c.get(Calendar.MINUTE);
   int inicioT=(inicioH*3600)+(inicioM*60)+inicioS;
   
   Pregunta[] pregunta= new Pregunta[4]; //crea arreglo de objeto
   char[] respC = new char [4];  //Crea arreglo para las respuestas correctas
   char[] resp = new char [4];   //Crea arreglo para las respuestas del usuario
   String desc[] = new String [4];  //Crea arreglo para las descripciones
   
   int i=0;
   while(i<=3){
      System.out.println("Descripcion de pregunta "+(i+1)); //Pide la descripcion de la pregunta
      desc[i]=Lectura.readString();
      System.out.println("Respuesta de pregunta "+(i+1)+" con v/f"); //Pide la Respuesta de la pregunta
      resp[i]=Lectura.readChar();
      System.out.println("respuesta correcta de pregunta "+(i+1)+" v/f"); //Pide la respuesta correcta.
      respC[i]=Lectura.readChar();
      pregunta[i]	= new Pregunta(desc[i],resp[i]); //crea objetos pregunta
      i++;
   }
      Examen e1 = new Examen(pregunta,respC); //Crea el objeto examen
      e1.capturarCalif();
      System.out.println(e1);
      System.out.println("\n\n Tiempo que se tardo: "+e1.tiempoTardado(inicioT));
}
}
//se crea la clase examen
class Examen{
private Pregunta[] preg;
private char[] respC;

//metodo Constructor
public Examen(Pregunta[] preg, char[] respC){
   setPreg(preg);
   setRespC(respC);
}
//Metodos Sets y Gets...
public void setPreg(Pregunta[] preg){
   this.preg=preg;
}
public Pregunta[] getPreg(){
   return preg;
}
public void setRespC(char[] respC){
   this.respC=respC;
}
public char[] getRespC(){
   return respC;
}
//Metodo para generar las Calificaciones
public int capturarCalif(){
char r1 = Character.toLowerCase(preg[0].getRespuesta()); //convierte las respuestas a minusculas
char r2 = Character.toLowerCase(preg[1].getRespuesta());
char r3 = Character.toLowerCase(preg[2].getRespuesta());
char r4 = Character.toLowerCase(preg[3].getRespuesta());

respC[0] = Character.toLowerCase(respC[0]);            //convierte las respuestas correctas a minusculas
respC[1] = Character.toLowerCase(respC[1]);
respC[2] = Character.toLowerCase(respC[2]);
respC[3] = Character.toLowerCase(respC[3]);

int calif=0;      //Genera calificacion
if(r1==respC[0])
   calif=25;
if(r2==respC[1])
   calif+=25;
if(r3==respC[2])
   calif+=25;
if(r4==respC[3])
   calif+=25;
return calif;
}
public int preguntaRescate(){
int y =0;
String[] rescate = new String[3];
rescate[0]="Los barcos flotan en el agua?";
rescate[1]="Pluto es un planeta?";
rescate[2]="Los pinguinos pueden volar?";
char[] respuestaCorrecta ={'v','f','f'};


int x = (int)(Math.random()*3+1);
System.out.println("math random:"+x);
System.out.println("respuesta de rescate : "+rescate[(x-1)]+" (v/f)");
char respuesta=Lectura.readChar();
respuesta=Character.toLowerCase(respuesta);

if(x==1 && respuesta == respuestaCorrecta[0]){
         y=1;
 }
   
if(x==2 && respuesta==respuestaCorrecta[1]){
         y=2;
   }
if(x==3 && respuesta==respuestaCorrecta[2]){
         y=3;
   }
   
return y;
}

public String tiempoTardado(int inicioT){
   Calendar c2=new GregorianCalendar();
   int finS=c2.get(Calendar.SECOND);
   int finH=c2.get(Calendar.HOUR);
   int finM=c2.get(Calendar.MINUTE);
   int finalT=(finH*3600)+(finM*60)+finS;
   
   int tiempoTotal=finalT-inicioT;
   int hora = tiempoTotal/3600;
   int minuto = (tiempoTotal-(3600*hora))/60;
   int seg = tiempoTotal-((hora*3600)+(minuto*60));
   
   
return "Tiempo en el examen: "+hora+"h "+minuto+"m "+seg+"s";
}

//metodo toString para desplegar resultados.
public String toString(){
   int cont=0; //contador para contar la palabra mas grande.
   String[] p = {preg[0].getDesc(),preg[1].getDesc(),preg[2].getDesc(),preg[3].getDesc()}; //arreglo para poner las descripciones de las preguntas.
   
   while(cont<=preg[0].getDesc().length() || cont<=preg[1].getDesc().length() || cont<=preg[2].getDesc().length() || cont<=preg[3].getDesc().length()){ //Loop para contar la palabra mas grande.
      cont++;
   }
   for(int i=0;i<=3;i++){ //Loop para crear los espacios correspondientes en las descripciones
      int espacios = cont; //Variable espacios para no cambiar el contador.
      int e = p[i].length(); //Variable e para que se quede estatica.
      while(e<=espacios){
         p[i]+=" ";
         espacios--;
      }
   }
   String calif=""; //Crea espacios para desplegar la calificacion
   int espacios = cont; //Variable espacios para no cambiar el contador.
   while(13<=espacios){ //loop para crear los espacios de calificacion:
      calif=" "+calif;
      espacios--;
   }
      return "\n"+p[0]+preg[0].getRespuesta()+"     "+respC[0]+"\n"+p[1]+preg[1].getRespuesta()+"     "+respC[1]+"\n"+p[2]+preg[2].getRespuesta()+"     "+
            respC[2]+"\n"+p[3]+preg[3].getRespuesta()+"     "+respC[3]+"\n"+calif+"Calificacion:"+capturarCalif()+"  puntos extra por pregunta de rescate:"+preguntaRescate();
}
}
//clase Pregunta
class Pregunta{
   String descripcion;
   char respuesta;
//metodo constructor de pregunta
public Pregunta(String descripcion, char respuesta){
   setDesc(descripcion);
   setResp(respuesta);
}
//sets y gets..
public void setDesc(String descripcion){
   this.descripcion=descripcion;
}
public String getDesc(){
   return descripcion;
}
public void setResp(char respuesta){
   this.respuesta=respuesta;
}
public char getRespuesta(){
   return respuesta;
}
}
class Lectura{

//INT
public static int readInt(){
   String g;
   DataInputStream w=new DataInputStream(System.in);
   try{
      g=w.readLine();
   }
   catch(IOException e){g="0";}
   
   int g2=Integer.parseInt(g);
   return g2;   
}

//BYTE
public static byte readByte(){
   String g;
   DataInputStream w=new DataInputStream(System.in);
   try{
      g=w.readLine();
   }
   catch(IOException e){g="0";}
   
   byte g2=Byte.parseByte(g);
   return g2;   
}

//SHORT
public static short readShort(){
   String g;
   DataInputStream w=new DataInputStream(System.in);
   try{
      g=w.readLine();
   }
   catch(IOException e){g="0";}
   
   short g2=Short.parseShort(g);
   return g2;   
}

//LONG
public static long readLong(){
   String g;
   DataInputStream w=new DataInputStream(System.in);
   try{
      g=w.readLine();
   }
   catch(IOException e){g="0";}
   
   long g2=Long.parseLong(g);
   return g2;   
}

//FLOAT
public static float readFloat(){
   String g;
   DataInputStream w=new DataInputStream(System.in);
   try{
      g=w.readLine();
   }
   catch(IOException e){g="0";}
   
   float g2=Float.parseFloat(g);
   return g2;   
   }
   
//DOUBLE
public static double readDouble(){
   String g;
   DataInputStream w=new DataInputStream(System.in);
   try{
      g=w.readLine();
   }
   catch(IOException e){g="0";}
   
   double g2=Double.parseDouble(g);
   return g2;   
}

//STRING
public static String readString(){
   String g;
   DataInputStream w=new DataInputStream(System.in);
   try{
      g=w.readLine();
   }
   catch(IOException e){g=" ";}
  
   return g;   
}

//CHAR
public static char readChar(){
   String g;
   DataInputStream w=new DataInputStream(System.in);
   try{
      g=w.readLine();
   }
   catch(IOException e){g=" ";}
   
   char g2= g.charAt(0);
   return g2;   
}}
