package proyectofinal;


public class JToken {

String lexema;
int fila;
int columna;
String token;
    
public JToken(String lexema,int fila, int columna, String token){
    this.lexema = lexema;
    this.fila = fila;
    this.columna = columna;
    this.token = token;
}

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
