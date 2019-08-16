package proyectofinal;

public class JError {

String caracter;
String descripcion;
int fila;
int columna;

public JError(String caracter, String descripcion, int fila, int columna){
    this.caracter = caracter;
    this.descripcion = descripcion;
    this.fila = fila;
    this.columna = columna;
}

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
   
}
