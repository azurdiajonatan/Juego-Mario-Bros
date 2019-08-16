package proyectofinal;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import static proyectofinal.JParser.Objetos;

public class ProyectoFinal {

 static int datox=0;
 static int datoy=0;
 static String [][] GuardaTodo;
        static int n =0; //esta es para recibir la posicion en x del personaje
        static int m =0; //esta es para recibir la posicion en x del personaje
        static int vida = 0; //esta es para recibir la vida del personaje
        static int a =0; //esta es para recibir la posicion en x de la salida
        static int b =0; //esta es para recibir la posicion en x de la salida
 static String recibir = ""; //esta es para recibir la imagen del personaje
 
 static String [][] Complementos = new String [20][3]; // esta matriz es para los objetos y obtener su imagen y vida
 static String [][] Enemigos = new String [20][3]; //esta matriz es para los objetos y obtener su imagen y dano
 static String [][] Bloques = new String [20][2];
 
    public static void main(String[] args) {
        
        
        
            try {
            // Modificamos la apariencia.
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
       
        JInicio interfaz = new JInicio();
        datox = Integer.parseInt(JOptionPane.showInputDialog("Tablero" + "Ingrese la cantidad de Filas"));
        datoy = Integer.parseInt(JOptionPane.showInputDialog("Tablero" + "Ingrese la cantidad de Columnas"));
        GuardaTodo = new String[datox][datoy];
        interfaz.setVisible(true);
        
         
    }
    
    
}
