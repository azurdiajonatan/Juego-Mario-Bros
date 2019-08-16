
package proyectofinal;

import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;


public class JParser {
 
    //variables para los datos de los objetos, Enemigos, bloques
        int complefila = 0;
        int enemigofila = 0;
        int bloquefila = 0;
    //
        
    //Llamar clases
    String solo;
    JAnalizador nuevo = new JAnalizador(solo);
    ProyectoFinal nuevo2 = new ProyectoFinal();
    JJuego manejador = new JJuego();
    String retornarerrores = "";
    int filax=0, filay =0;
    //
    
    String concatenar = "";
    int numPreanalisis;
    JTemporal preanalisis;
    ArrayList<JTemporal> tokens;
    int l;
    
    //Matriz
    static String [][] Guardar = new String[100][4];
    static String [][] Objetos = new String[100][6];
    int linea = 0;
    int posicion = 0;
    //Fin matriz
    
    //Ladrones
    String Etiqueta_Principal = "";
    String Etiqueta_E1 = "";
    String Etiqueta_EN = "";
    String Etiqueta_E2 = "";
    String Etiqueta_Final = "";
    //Fin
    
    //vector
    String [] Verificar = new String[5];
    String Concatenar_Datos = "";    
    //
    
    public void Parsear(ArrayList <JTemporal> lista, int contador){
        tokens = lista;
        numPreanalisis = 0;
        preanalisis = tokens.get(0);
        //lista.add("","Ultimo")
        l = contador;
        
        while(l > 0){
         S();  
         numPreanalisis++;
         l--;
         if(numPreanalisis>0 && numPreanalisis<contador){
          preanalisis=tokens.get(numPreanalisis);}
        }
        
        System.out.println("Cadena reconocida");
    }
    
    //Con este inicia el proceso
    void S(){
      I();
      J();
    }
    //
    
    //
    void I(){
       if(preanalisis.token.equalsIgnoreCase("PrincipalAbierta")){
           Etiqueta_Principal = preanalisis.lexema;
           Guardar[linea][0] = Etiqueta_Principal;
           match(preanalisis.token);
           
       }else{
           
           System.out.println("Se esperaba " + "Etiqueta Principal Abierta");
           retornarerrores = "Se esperaba " + "Etiqueta Principal Abierta" ;
           nuevo.Agregar_Error(retornarerrores, "Error Sintactico", filax++, filay++);
       }     
    }
    //
    
    void J(){
      E1();
      EN();
      E2();
      if(preanalisis.token.equalsIgnoreCase("PrincipalCerrada")){
          Etiqueta_Final = preanalisis.lexema;
          match(preanalisis.token);
      }else{
          J();      
      }
    }
    
                    void E1(){
                       if(preanalisis.token.equalsIgnoreCase("NormalA")){
                           Etiqueta_E1 = preanalisis.lexema;
                            Guardar[linea][0] = Etiqueta_Principal;
                           Guardar[linea][1] = Etiqueta_E1;
                           match(preanalisis.token);
                       }else{
                           System.out.println("Se esperaba " + "Etiqueta Abierta");
                           retornarerrores = "Se esperaba " + "Etiqueta Abierta" ;
                           nuevo.Agregar_Error(retornarerrores, "Error Sintactico", filax++, filay++);
                       }
                    }
                
                    void EN(){
                        if(preanalisis.token.equalsIgnoreCase("Cadena")){
                           Etiqueta_EN = preanalisis.lexema;
                           Guardar[linea][0] = Etiqueta_Principal;
                           Guardar[linea][2] = Etiqueta_EN;
                            match(preanalisis.token);
                        }else if(preanalisis.token.equalsIgnoreCase("NumeroEntero")){
                           Etiqueta_EN = preanalisis.lexema;
                           Guardar[linea][0] = Etiqueta_Principal;
                           Guardar[linea][2] = Etiqueta_EN;
                            match(preanalisis.token);
                        }else if(preanalisis.token.equalsIgnoreCase("Ruta")){
                           Etiqueta_EN = preanalisis.lexema;
                           Guardar[linea][0] = Etiqueta_Principal;
                           Guardar[linea][2] = Etiqueta_EN;
                            match(preanalisis.token);
                        }else{
                            System.out.println("Se esperaba " + "Ruta-Numero-Cadena");
                            retornarerrores = "Se esperaba " + "Ruta/Numero/Cadena" ;
                            nuevo.Agregar_Error(retornarerrores, "Error Sintactico", filax++, filay++);
                        }
                    }
                    
                    void E2(){                       
                        if(preanalisis.token.equalsIgnoreCase("NormalZ")){
                           Etiqueta_E2 = preanalisis.lexema;
                           Guardar[linea][0] = Etiqueta_Principal;
                           Guardar[linea][3] = Etiqueta_E2;
                           linea++;
                            match(preanalisis.token);
                        }else{
                            System.out.println("Se esperaba " + "Etiqueta Cerrada");
                            retornarerrores = "Se esperaba " + "Etiqueta Cerrada" ;
                            nuevo.Agregar_Error(retornarerrores, "Error Sintactico", filax++, filay++);
                        }
                    }
                    
    
                    
    
    void match(String tipo){
    
        if(!tipo.equalsIgnoreCase(preanalisis.token)){
            System.out.println("Error sintactico se esperaba " + tipo);
           nuevo.Agregar_Error(preanalisis.token, "Error Sintactico", filax++, filay++);
        }
       
        if(!preanalisis.token.equalsIgnoreCase("PrincipalCerrada")){
            numPreanalisis++;
            preanalisis = tokens.get(numPreanalisis);
            l--;
        }       
    }
    
    //solo para mostrar datos
    public void Mostrar_Datos(){
        for(int i=0;i<Guardar.length;i++){
            for(int j=0;j<Guardar[i].length;j++){
                System.out.print("[" + Guardar[i][j] + "]");
            }
            System.out.println("");
        }

    }
    //Fin de mostrar datos
    

    public void Contenedor(){
        String tipo = "";
        String nombre =  "";
        String imagen = "";
        String posicionx = "";
        String posiciony = "";
        String vida = "";
        String dano = "";
        
        int recibirx = 0;
        int recibiry = 0;
        
        int especialm = 0;
        String remplazo = "";
        
        //para imagenes

        
        //Fin de para imagenes
        
        int seguidor = 0; //variable para sumar filas
        int contador = 1; // vriable para enemigo,personaje,complemento
        int contador2 = 0;
        while(Guardar[seguidor][0]!=null){
            if(Guardar[seguidor][0].equals("Personaje") && contador!=6){
                tipo = "Personaje";
                nombre = Guardar[seguidor][2];
                seguidor++;
                contador++;
                vida = Guardar[seguidor][2];
                seguidor++;
                contador++;
                imagen = Guardar[seguidor][2];
                seguidor++;
                contador++;
                posicionx =  Guardar[seguidor][2];
                seguidor++;
                contador++;
                posiciony = Guardar[seguidor][2];
                contador++;
                if(contador == 6){
                    contador = 0;
                    
                }
                concatenar += tipo +" "+" Nombre>:"+nombre+" Vida>:"+vida+" Imagen>:"+imagen+" X>:"+posicionx+" Y>:"+posiciony + "\n";
                Objetos[especialm][0] = tipo;
                Objetos[especialm][1] = nombre;
                Objetos[especialm][2] = vida;
                Objetos[especialm][3] = imagen;
                Objetos[especialm][4] = posicionx;
                Objetos[especialm][5] = posiciony;
                
                recibirx = Integer.parseInt(posicionx);
                recibiry = Integer.parseInt(posiciony);
                remplazo = imagen.replace("'", "");
                nuevo2.GuardaTodo[recibirx][recibiry] = remplazo;
                //para mover personaje
                nuevo2.recibir = remplazo;
                nuevo2.n = recibirx;
                nuevo2.m = recibiry;
                nuevo2.vida = Integer.parseInt(vida);
                // fin de mover
                
            }else if(Guardar[seguidor][0].equals("Bloque") && contador2!=4){
                tipo = "Bloque";
                posicionx = Guardar[seguidor][2];
                seguidor++;
                contador2++;
                posiciony = Guardar[seguidor][2];
                seguidor++;
                contador2++;
                imagen =  Guardar[seguidor][2];
                contador2++;
                if(contador2 == 4){
                    contador = 0;                  
                }
                 concatenar += tipo+" Imagen>:"+imagen+" X>:"+posicionx+" Y>:"+posiciony +"\n";
                 Objetos[especialm][0] = tipo;
                 Objetos[especialm][3] = imagen;
                 Objetos[especialm][4] = posicionx;
                 Objetos[especialm][5] = posiciony;
                 
                recibirx = Integer.parseInt(posicionx);
                recibiry = Integer.parseInt(posiciony);
                remplazo = imagen.replace("'", "");
                nuevo2.GuardaTodo[recibirx][recibiry] = remplazo;
                nuevo2.Bloques[bloquefila][0] = posicionx;
                nuevo2.Bloques[bloquefila][1] = posiciony;
                bloquefila++;
                
            }else if(Guardar[seguidor][0].equals("Enemigo") && contador!=6){
                tipo = "Enemigo";
                nombre = Guardar[seguidor][2];
                seguidor++;
                contador++;
                imagen = Guardar[seguidor][2];
                seguidor++;
                contador++;
                posicionx = Guardar[seguidor][2];
                seguidor++;
                contador++;
                posiciony =  Guardar[seguidor][2];
                seguidor++;
                contador++;
                dano = Guardar[seguidor][2];
                contador++;
                if(contador == 6){
                    contador = 0;            
                }
                concatenar += tipo +" "+" Nombre>:"+nombre+" Dano>:"+dano+" Imagen>:"+imagen+" X>:"+posicionx+" Y>:"+posiciony + "\n";      
                Objetos[especialm][0] = tipo;
                Objetos[especialm][1] = nombre;
                Objetos[especialm][2] = dano;
                Objetos[especialm][3] = imagen;
                Objetos[especialm][4] = posicionx;
                Objetos[especialm][5] = posiciony;
                
                recibirx = Integer.parseInt(posicionx);
                recibiry = Integer.parseInt(posiciony);
                remplazo = imagen.replace("'", "");
                
                nuevo2.GuardaTodo[recibirx][recibiry] = remplazo;
                
                //para obetener los datos de enemigos
                nuevo2.Enemigos[enemigofila][0] = posicionx;
                nuevo2.Enemigos[enemigofila][1] = posiciony;
                nuevo2.Enemigos[enemigofila][2] = dano;
                enemigofila++;
                //
                
            }else if(Guardar[seguidor][0].equals("Salida")&& contador!=4){
                tipo = "Salida";
                posicionx = Guardar[seguidor][2];
                seguidor++;
                contador2++;
                posiciony = Guardar[seguidor][2];
                seguidor++;
                contador2++;
                imagen =  Guardar[seguidor][2];
                contador2++;
                if(contador2 == 4){
                    contador = 0;                  
                }
                 concatenar += tipo+" Imagen>:"+imagen+" X>:"+posicionx+" Y>:"+posiciony +"\n";
                 Objetos[especialm][0] = tipo;
                 Objetos[especialm][3] = imagen;
                 Objetos[especialm][4] = posicionx;
                 Objetos[especialm][5] = posiciony;
                 
                recibirx = Integer.parseInt(posicionx);
                recibiry = Integer.parseInt(posiciony);
                remplazo = imagen.replace("'", "");
                nuevo2.GuardaTodo[recibirx][recibiry] = remplazo;
                nuevo2.a = recibirx;
                nuevo2.b = recibiry;
               
            }else if(Guardar[seguidor][0].equals("Complemento")&& contador!=6){
                tipo = "Complemento";
                nombre = Guardar[seguidor][2];
                seguidor++;
                contador++;
                imagen = Guardar[seguidor][2];
                seguidor++;
                contador++;
                vida = Guardar[seguidor][2];
                seguidor++;
                contador++;
                posicionx =  Guardar[seguidor][2];
                seguidor++;
                contador++;
                posiciony = Guardar[seguidor][2];
                contador++;
                if(contador == 6){
                    contador = 0;            
                }
                concatenar += tipo +" "+" Nombre>:"+nombre+" vida>:"+vida+" Imagen>:"+imagen+" X>:"+posicionx+" Y>:"+posiciony + "\n";      
                Objetos[especialm][0] = tipo;
                Objetos[especialm][1] = nombre;
                Objetos[especialm][2] = vida;
                Objetos[especialm][3] = imagen;
                Objetos[especialm][4] = posicionx;
                Objetos[especialm][5] = posiciony;
                
                recibirx = Integer.parseInt(posicionx);
                recibiry = Integer.parseInt(posiciony);
                remplazo = imagen.replace("'", "");
                nuevo2.GuardaTodo[recibirx][recibiry] = remplazo;
                
                //para guardar datos
                nuevo2.Complementos[complefila][0] = posicionx;
                nuevo2.Complementos[complefila][1] = posiciony;
                nuevo2.Complementos[complefila][2] = vida;
                complefila++;
                //fin
                
            }
           seguidor++;
           especialm++;
        }
        System.out.println(concatenar);
    }
    
    
        //Solo para mostrar datos de 
    public void Mostrar_Objetos(){
         for(int x=0;x<Objetos.length;x++){
            for(int y=0;y<Objetos[x].length;y++){
                System.out.print("[" + Objetos[x][y] + "]");
            }
            System.out.println("");
        }
    }
    //Fin de mostrar datos por objetos
    
    
    

}
