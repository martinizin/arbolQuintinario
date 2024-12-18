import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class Arbol {
    private Nodo raiz;
    private ArrayList<Nodo> nodos;
    private int numNodos;

    public Arbol() {
        raiz = null;
        nodos = new ArrayList<>();
        numNodos = 5;
    }
//boolean izq1, izq2, centro, der1. der2
    //boo
//    public void anadirNodo(Nodo nodo, Nodo padre, boolean esIzq) {
//        if (padre == null) {
//            if (raiz == null) {
//                raiz= nodo;
//            } else {
//                throw new IllegalArgumentException("La raíz ya existe");
//            }
//        } else {
//            if (esIzq) {
//                if (padre.izq1 == null) {
//                    padre.izq1 = nodo;
//                } else {
//                    throw new IllegalArgumentException("Hoja Izq ya existe");
//                }
//            } else {
//                if (padre.der1 == null) {
//                    padre.der1 = nodo;
//                } else {
//                    throw new IllegalArgumentException("Hoja Der ya existe");
//                }
//                else{
//                    if(padre.der2 == null){
//                        padre.der2 = nodo;
//                    }
//                }
//
//            }
//        }
//        nodos.add(nodo);
//    }

    public boolean anadirNodo(int x, int y, String etiqueta, String etiquetaPadre) {
        if (raiz == null) {
            raiz = new Nodo(x, y, etiqueta); // Si no hay raíz, se crea el primer nodo
            return true;
        }
        return anadirRecursivo(raiz, x, y, etiqueta, etiquetaPadre);
    }

    private boolean anadirRecursivo(Nodo actual, int x, int y, String etiqueta, String etiquetaPadre) {
        if (actual.etiqueta.equals(etiquetaPadre)) {
            if (actual.izq1 == null) {
                actual.izq1 = new Nodo(x, y, etiqueta);
                return true;
            } else if (actual.izq2 == null) {
                actual.izq2 = new Nodo(x, y, etiqueta);
                return true;
            } else if (actual.centro == null) {
                actual.centro = new Nodo(x, y, etiqueta);
                return true;
            } else if (actual.der1 == null) {
                actual.der1 = new Nodo(x, y, etiqueta);
                return true;
            } else if (actual.der2 == null) {
                actual.der2 = new Nodo(x, y, etiqueta);
                return true;
            }
            return false; // No hay espacio disponible en este nodo.
        }
        // Recursión: buscamos el nodo padre en los subárboles
        if (actual.izq1 != null && anadirRecursivo(actual.izq1, x, y, etiqueta, etiquetaPadre)) {
            return true;
        }
        if (actual.izq2 != null && anadirRecursivo(actual.izq2, x, y, etiqueta, etiquetaPadre)) {
            return true;
        }
        if (actual.centro != null && anadirRecursivo(actual.centro, x, y, etiqueta, etiquetaPadre)) {
            return true;
        }
        if (actual.der1 != null && anadirRecursivo(actual.der1, x, y, etiqueta, etiquetaPadre)) {
            return true;
        }
        if (actual.der2 != null && anadirRecursivo(actual.der2, x, y, etiqueta, etiquetaPadre)) {
            return true;
        }
        return false; // No se encontró el nodo padre.
    }


        public ArrayList<Nodo> getNodos() {
            return nodos;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public String getEtiquetaNodoSiguiente() {
        return String.valueOf((char) ('A' + numNodos++));
    }

    public String bfs() {
        if (raiz== null) return "";

        StringBuilder resultado = new StringBuilder();
        Queue<Nodo> queue = new LinkedList<>();
        queue.add(raiz);

        while (!queue.isEmpty()) {
            Nodo nodo = queue.poll();
            resultado.append(nodo.etiqueta).append(" ");
            if (nodo.izq1 != null) queue.add(nodo.izq1);
            if (nodo.der1 != null) queue.add(nodo.der1);
        }

        return resultado.toString().trim();
    }

    public String dfs() {
        if (raiz== null) return "";

        StringBuilder resultado = new StringBuilder();
        Stack<Nodo> stack = new Stack<>();
        stack.push(raiz);

        while (!stack.isEmpty()) {
            Nodo nodo = stack.pop();
            resultado.append(nodo.etiqueta).append(" ");
            if (nodo.der1 != null) stack.push(nodo.der1);
            if (nodo.izq1 != null) stack.push(nodo.izq1);
        }

        return resultado.toString().trim();
    }

    public String preorden() {
        return preordenImpresion(raiz).trim();
    }

    private String preordenImpresion(Nodo nodo) {
        if (nodo == null) return "";
        return nodo.etiqueta + " " + preordenImpresion(nodo.izq1) + preordenImpresion(nodo.der1);
    }

    public String inorden() {
        return inordenImpresion(raiz).trim();
    }

    private String inordenImpresion(Nodo nodo) {
        if (nodo == null) return "";
        return inordenImpresion(nodo.izq1) + nodo.etiqueta + " " + inordenImpresion(nodo.der1);
    }

    public String postorden() {
        return postordenImpresion(raiz).trim();
    }

    private String postordenImpresion(Nodo nodo) {
        if (nodo == null) return "";
        return postordenImpresion(nodo.izq1) + postordenImpresion(nodo.der1) + nodo.etiqueta + " ";
    }

    public Object[][] getMatrizAdyacencia() {
        int tam = nodos.size();
        Object[][] matriz = new Object[tam][tam];
        Map<String, Integer> etiquetaAIndice = new HashMap<>();

        for (int i = 0; i < tam; i++) {
            etiquetaAIndice.put(nodos.get(i).etiqueta, i);
            for (int j = 0; j < tam; j++) {
                matriz[i][j] = 0;
            }
        }

        for (Nodo nodo : nodos) {
            int desdeIndice = etiquetaAIndice.get(nodo.etiqueta);
            if (nodo.izq1 != null) {
                int hastaIndice = etiquetaAIndice.get(nodo.izq1.etiqueta);
                matriz[desdeIndice][hastaIndice] = 1;
            }
            if (nodo.der1 != null) {
                int hastaIndice = etiquetaAIndice.get(nodo.der1.etiqueta);
                matriz[desdeIndice][hastaIndice] = 1;
            }
        }

        return matriz;
    }
}
