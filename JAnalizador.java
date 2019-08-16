package proyectofinal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class JAnalizador {

    public String Cadena_Entrada;
    public String Retornar_Token;
    public String Retornar_Error;
    
    //Listas
    static ArrayList<JToken> Lista_Tokens = new ArrayList<JToken>();
    static ArrayList<JError> Lista_Errores = new ArrayList<JError>();
           ArrayList<JTemporal> Lista_Temporal = new ArrayList<>();
    //Fin de Listas
    
    //Vectores
    String [] Etiquetas_Abiertas = new String[11];
    String [] Etiquetas_Cerradas = new String[11];
    //Fin de vectores
    
    int fila = 0;
    int columna = 0;
   
    
public JAnalizador(String Cadena_Entrada){
    this.Cadena_Entrada = Cadena_Entrada;
}
    
public void Analizador(){
    
    //Etiquetas
    Etiquetas_Abiertas[0] = "Personaje"; 
    Etiquetas_Abiertas[1] = "Nombre";
    Etiquetas_Abiertas[2] = "Vida";
    Etiquetas_Abiertas[3] = "Imagen";
    Etiquetas_Abiertas[4] = "X";
    Etiquetas_Abiertas[5] = "Y";
    Etiquetas_Abiertas[6] = "Bloque";
    Etiquetas_Abiertas[7] = "Salida";
    Etiquetas_Abiertas[8] = "Enemigo";
    Etiquetas_Abiertas[9] = "Daño";
    Etiquetas_Abiertas[10] = "Complemento";
    //Fin etiquetas
    
    //Etiqueta cerrada
    Etiquetas_Cerradas[0] = "/Personaje"; 
    Etiquetas_Cerradas[1] = "/Nombre";
    Etiquetas_Cerradas[2] = "/Vida";
    Etiquetas_Cerradas[3] = "/Imagen";
    Etiquetas_Cerradas[4] = "/X";
    Etiquetas_Cerradas[5] = "/Y";
    Etiquetas_Cerradas[6] = "/Bloque";
    Etiquetas_Cerradas[7] = "/Salida";
    Etiquetas_Cerradas[8] = "/Enemigo";
    Etiquetas_Cerradas[9] = "/Daño";
    Etiquetas_Cerradas[10] = "/Complemento";
    //Fin etiqueta
    
    int contador = 0;

    char comilla = (char)39;
    
    String lexema = ""; //concatena letras
    int tamano = Cadena_Entrada.length();
    int posicion = -1;
    int estado  = 0;
    
    while(posicion<tamano-1){
        posicion++;
        char caracter = Cadena_Entrada.charAt(posicion);
        switch(estado){
           
            case 0: //Estado 0               
                if(caracter == '<'){
                    lexema += "<";
                    Agregar_Token(lexema, fila++, columna++, "Abrirllave");
                    //Agregar_Temporal(lexema, "Abrirllave");
                    lexema = "";
                }else if(caracter == '>') {
                    lexema += ">";
                    Agregar_Token(lexema, fila++, columna++, "Cerrarllave");
                    //Agregar_Temporal(lexema, "Cerrarllave");
                    lexema = "";
                }else if(Character.isWhitespace(caracter)){
                    //No hacer nada
                }else if(Character.isLetter(caracter)){
                    lexema += caracter;
                    estado = 1;
                }else if(Character.isDigit(caracter)){
                    lexema+= caracter;
                    estado = 2;
                }else if(caracter == '/'){
                    lexema += caracter;
                    estado = 3;
                }else if(caracter == '"'){
                    lexema += caracter;
                    estado = 4;
                }else if(caracter == comilla){
                    lexema += caracter;
                    estado = 6;
                }else{
                    System.out.println("Error " + caracter);
                    lexema = Character.toString(caracter);
                    Agregar_Error(lexema,"Caracter no valido", fila++,columna++);
                    lexema = "";
                } 
                break; //Fin del estado 0
            
                
            case 1:
                if(Character.isLetter(caracter)){
                    lexema += caracter;
                    estado = 1;
                }else {
                    
                    if(lexema.equalsIgnoreCase(Etiquetas_Abiertas[0])){
                        Agregar_Token(lexema, fila++, columna++, "PersonajeInicio");
                        Agregar_Temporal(lexema, "PrincipalAbierta");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else if(lexema.equalsIgnoreCase(Etiquetas_Abiertas[1])){
                        Agregar_Token(lexema, fila++, columna++, "NombreInicio");
                        Agregar_Temporal(lexema, "NormalA");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else if(lexema.equalsIgnoreCase(Etiquetas_Abiertas[2])){
                        Agregar_Token(lexema, fila++, columna++, "VidaInicio");
                        Agregar_Temporal(lexema, "NormalA");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else if(lexema.equalsIgnoreCase(Etiquetas_Abiertas[3])){
                        Agregar_Token(lexema, fila++, columna++, "ImagenInicio");
                        Agregar_Temporal(lexema, "NormalA");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else if(lexema.equalsIgnoreCase(Etiquetas_Abiertas[4])){
                        Agregar_Token(lexema, fila++, columna++, "XInicio");
                        Agregar_Temporal(lexema, "NormalA");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else if(lexema.equalsIgnoreCase(Etiquetas_Abiertas[5])){
                        Agregar_Token(lexema, fila++, columna++, "YInicio");
                        Agregar_Temporal(lexema, "NormalA");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else if(lexema.equalsIgnoreCase(Etiquetas_Abiertas[6])){
                        Agregar_Token(lexema, fila++, columna++, "BloqueInicio");
                        Agregar_Temporal(lexema, "PrincipalAbierta");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else if(lexema.equalsIgnoreCase(Etiquetas_Abiertas[7])){
                        Agregar_Token(lexema, fila++, columna++, "SalidaInicio");
                        Agregar_Temporal(lexema, "PrincipalAbierta");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else if(lexema.equalsIgnoreCase(Etiquetas_Abiertas[8])){
                        Agregar_Token(lexema, fila++, columna++, "EnemigoInicio");
                        Agregar_Temporal(lexema, "PrincipalAbierta");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else if(lexema.equalsIgnoreCase(Etiquetas_Abiertas[9])){
                        Agregar_Token(lexema, fila++, columna++, "DañoInicio");
                        Agregar_Temporal(lexema, "NormalA");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else if(lexema.equalsIgnoreCase(Etiquetas_Abiertas[10])){
                        Agregar_Token(lexema, fila++, columna++, "ComplementoInicio");
                        Agregar_Temporal(lexema, "PrincipalAbierta");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else{
                        Agregar_Token(lexema, fila++, columna++, "Palabra");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }
                    
                }
                
                break;
                
            case 2:
                if(Character.isDigit(caracter)){
                    lexema += caracter;
                    estado = 2;
                }else{
                    Agregar_Token(lexema, fila++, columna++, "NumeroEntero");
                    Agregar_Temporal(lexema, "NumeroEntero");
                        lexema = "";
                        estado = 0;
                        posicion--;
                }
                break;
                
                
            case 3:
               if(Character.isLetter(caracter)){
                   lexema += caracter;
                   estado = 3;
               }else{                  
                    if(lexema.equalsIgnoreCase(Etiquetas_Cerradas[0])){
                        Agregar_Token(lexema, fila++, columna++, "PersonajeFin");
                        Agregar_Temporal(lexema, "PrincipalCerrada");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else if(lexema.equalsIgnoreCase(Etiquetas_Cerradas[1])){
                        Agregar_Token(lexema, fila++, columna++, "NombreFin");
                        Agregar_Temporal(lexema, "NormalZ");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else if(lexema.equalsIgnoreCase(Etiquetas_Cerradas[2])){
                        Agregar_Token(lexema, fila++, columna++, "VidaFin");
                        Agregar_Temporal(lexema, "NormalZ");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else if(lexema.equalsIgnoreCase(Etiquetas_Cerradas[3])){
                        Agregar_Token(lexema, fila++, columna++, "ImagenFin");
                        Agregar_Temporal(lexema, "NormalZ");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else if(lexema.equalsIgnoreCase(Etiquetas_Cerradas[4])){
                        Agregar_Token(lexema, fila++, columna++, "XFin");
                        Agregar_Temporal(lexema, "NormalZ");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else if(lexema.equalsIgnoreCase(Etiquetas_Cerradas[5])){
                        Agregar_Token(lexema, fila++, columna++, "YFin");
                        Agregar_Temporal(lexema, "NormalZ");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else if(lexema.equalsIgnoreCase(Etiquetas_Cerradas[6])){
                        Agregar_Token(lexema, fila++, columna++, "BloqueFin");
                        Agregar_Temporal(lexema, "PrincipalCerrada");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else if(lexema.equalsIgnoreCase(Etiquetas_Cerradas[7])){
                        Agregar_Token(lexema, fila++, columna++, "SalidaFin");
                        Agregar_Temporal(lexema, "PrincipalCerrada");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else if(lexema.equalsIgnoreCase(Etiquetas_Cerradas[8])){
                        Agregar_Token(lexema, fila++, columna++, "EnemigoFin");
                        Agregar_Temporal(lexema, "PrincipalCerrada");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else if(lexema.equalsIgnoreCase(Etiquetas_Cerradas[9])){
                        Agregar_Token(lexema, fila++, columna++, "DañoFin");
                        Agregar_Temporal(lexema, "NormalZ");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else if(lexema.equalsIgnoreCase(Etiquetas_Cerradas[10])){
                        Agregar_Token(lexema, fila++, columna++, "ComplementoFin");
                        Agregar_Temporal(lexema, "PrincipalCerrada");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }else{
                        Agregar_Token(lexema, fila++, columna++, "Palabra");
                        lexema = "";
                        estado = 0;
                        posicion--;
                    }                  
               }               
                break;
                
            case 4:
                if(caracter != '"'){
                    lexema += caracter;
                    estado = 4;
                }else if(caracter == '"') {
                    lexema += caracter;
                    estado = 5;
                }
                break;
              
            case 5:
                Agregar_Token(lexema, fila++, columna++, "Cadena");
                Agregar_Temporal(lexema, "Cadena");
                        lexema = "";
                        estado = 0;
                        posicion--;
                break;
                
            case 6:
                if(caracter != comilla){
                    lexema += caracter;
                    estado = 6;
                }else if(caracter == comilla){
                    lexema += caracter;
                    estado = 7;
                }
                break;
                
                
            case 7:
                  Agregar_Token(lexema, fila++, columna++, "Ruta");
                  Agregar_Temporal(lexema, "Ruta");
                        lexema = "";
                        estado = 0;
                        posicion--;
                break;
        }
    }
   
}


public void Agregar_Token(String lexema, int fila, int columna, String token){
    JToken agregar = new JToken(lexema,fila,columna,token);
    Lista_Tokens.add(agregar);
}

public void Agregar_Error(String lexema, String descripcion, int fila, int columna){
    JError agregar = new JError(lexema,descripcion,fila,columna);
    Lista_Errores.add(agregar);
}

public void Agregar_Temporal(String lexema, String token){
    JTemporal agregar = new JTemporal(lexema,token);
    Lista_Temporal.add(agregar);
}


//Metodo del reporte
public void GenerarReporteTokens(){
            try{
                FileWriter sw = new FileWriter("Reportedetokens.html");
                BufferedWriter redactor = new BufferedWriter(sw);
                String a1 = "<!DOCTYPE html>";
                String a2 = "<html lang = 'es'>";
                String a3 = "<head>";
                String a4 = "<title> " + " LPE " + "</title>";
                String a5 = "</head>";
                String a6 = "<body>";
                String a7 = "<header>";
                String a8 = "<h1 style=" + "text-align:center;" + ">" + "Reporte de Tokens" + " </h1>";
                String a9 = "<p style=" + "text-align:center;" + ">" + "Este reporte detalla todos los tokens encontrados en la consola" + " </p>";
                String a10 = "<table border = 5 " + "align = " + "center" + ">";
                String a11 = "<tr>";
                String a12 = "<th >" + "No." + "</th>";
                String a13 = "<th >" + "Lexema" + "</th>";
                String a14 = "<th >" + "Fila" + "</th>";
                String a15 = "<th >" + "Columna" + "</th>";
                String a16 = "<th >" + "Token" + "</th>";
                String a17 = "</tr>";
                String a18 = "<tr>";
                String a19 = "</tr>";
                String a20 = "</table>";
                String a21 = "</header>";
                String a22 = "</body>";
                String a23 = "</html>";

                redactor.write(a1);
                redactor.write(a2);
                redactor.write(a3);
                redactor.write(a4);
                redactor.write(a5);
                redactor.write(a6);
                redactor.write(a7);
                redactor.write(a8);
                redactor.write(a9);
                redactor.write(a10);
                redactor.write(a11);
                redactor.write(a12);
                redactor.write(a13);
                redactor.write(a14);
                redactor.write(a15);
                redactor.write(a16);
                redactor.write(a17);

                for (int k = 0; k < Lista_Tokens.size(); k++)
                {
                    JToken reconocer = Lista_Tokens.get(k);
                    Retornar_Token = "<td>" + k + "</td>" + "<td>" + reconocer.getLexema() + "</td>" + "<td>" + reconocer.getFila() + "</td>" + "<td>" + reconocer.getColumna() + "</td>" + "<td>" + reconocer.getToken() + "</td>";
                    redactor.write(a18);
                    redactor.write(Retornar_Token);
                    redactor.write(a19);
                }
                redactor.write(a20);
                redactor.write(a21);
                redactor.write(a22);
                redactor.write(a23);
                redactor.close();

            }
            catch (Exception e)
            {

            }
        }
//Fin del metodo del reporte


public void ReporErrores(){
            try{
                FileWriter sw = new FileWriter("Reportedeerrores.html");
                BufferedWriter redactor = new BufferedWriter(sw);
                String a1 = "<!DOCTYPE html>";
                String a2 = "<html lang = 'es'>";
                String a3 = "<head>";
                String a4 = "<title> " + " LPE " + "</title>";
                String a5 = "</head>";
                String a6 = "<body>";
                String a7 = "<header>";
                String a8 = "<h1 style=" + "text-align:center;" + ">" + "Reporte de Tokens" + " </h1>";
                String a9 = "<p style=" + "text-align:center;" + ">" + "Este reporte detalla todos los errores encontrados en la consola" + " </p>";
                String a10 = "<table border = 5 " + "align = " + "center" + ">";
                String a11 = "<tr>";
                String a12 = "<th >" + "No." + "</th>";
                String a13 = "<th >" + "Error" + "</th>";
                String a14 = "<th >" + "Descripcion" + "</th>";
                String a15 = "<th >" + "Columna" + "</th>";
                String a16 = "<th >" + "Token" + "</th>";
                String a17 = "</tr>";
                String a18 = "<tr>";
                String a19 = "</tr>";
                String a20 = "</table>";
                String a21 = "</header>";
                String a22 = "</body>";
                String a23 = "</html>";

                redactor.write(a1);
                redactor.write(a2);
                redactor.write(a3);
                redactor.write(a4);
                redactor.write(a5);
                redactor.write(a6);
                redactor.write(a7);
                redactor.write(a8);
                redactor.write(a9);
                redactor.write(a10);
                redactor.write(a11);
                redactor.write(a12);
                redactor.write(a13);
                redactor.write(a14);
                redactor.write(a15);
                redactor.write(a16);
                redactor.write(a17);

                for (int k = 0; k < Lista_Errores.size(); k++)
                {
                    JError reconocer = Lista_Errores.get(k);
                    Retornar_Error = "<td>" + k + "</td>" + "<td>" + reconocer.getCaracter() + "</td>" + "<td>" + reconocer.getDescripcion() + "</td>" + "<td>" + reconocer.getFila()  +"</td>" + "<td>" + reconocer.getColumna()   + "</td>";
                    redactor.write(a18);
                    redactor.write(Retornar_Error);
                    redactor.write(a19);
                }
                redactor.write(a20);
                redactor.write(a21);
                redactor.write(a22);
                redactor.write(a23);
                redactor.close();

            }
            catch (Exception e)
            {

            }
        }//Fin del metodo


}
