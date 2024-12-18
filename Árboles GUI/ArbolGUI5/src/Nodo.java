public class Nodo {
    int x, y;
    String etiqueta;
   Nodo izq1, der1, izq2, der2, centro;
//cambiar el constructor y agregamos nodos


    public Nodo(int x, int y, String etiqueta) {
        this.x = x;
        this.y = y;
        this.etiqueta = etiqueta;
        this.izq1 = null;
        this.der1 = null;
        this.izq2 = null;
        this.der2 = null;
        this.centro = null;
    }

    @Override
    public String toString() {
        return etiqueta + " (" + x + ", " + y + ")";
    }
}
