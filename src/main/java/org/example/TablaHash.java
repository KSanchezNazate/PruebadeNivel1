package org.example;

import java.util.ArrayList;
import java.util.LinkedList;

public class TablaHash {

    private ArrayList<LinkedList<String>> tabla;
    private int tamano;

    public void TablaHash(int tamano) {
        this.tamano = 0;
        tabla = new ArrayList<>(tamano);
        for (int i = 0; i < tamano; i++) {
            tabla.add(new LinkedList<>());
        }
    }

    //agrega una palabra al diccionario
    public void agregar(String agregarnuevo) {
        int posicion = funcion_hash(agregarnuevo, tamano);
        LinkedList<String> lista = tabla.get(posicion);
        if (lista.contains(agregarnuevo)) {
            return; // El elemento ya se encuentra en la tabla
        }
        lista.add(agregarnuevo);
    }

    //busca una palabra en el diccionario
    public String quitar(String palabra) {
        int posicion = funcion_hash(palabra, tamano);
        LinkedList<String> lista = tabla.get(posicion);
        if (lista.contains(palabra)) {
            lista.remove(palabra);
            return palabra;
        }
        return null; // El elemento no se encontró en la tabla
    }

    //lee el archivo csv y agrega las palabras al diccionario
    public int buscar(String palabra) {
        int posicion = funcion_hash(palabra, tamano);
        LinkedList<String> lista = tabla.get(posicion);
        if (lista.contains(palabra)) {
            return posicion;
        }
        return -1; // El elemento no se encontró en la tabla
    }

    //imprime la tabla hash
    private int funcion_hash(String dato, int tamano_tabla) {
        // Implementación de la función hash utilizando el primer carácter del dato
        int i = (int) dato.charAt(0);
        return i % tamano_tabla;
    }

    private int sondeo(int posicion, int tamano_tabla) {
        // Implementación de la función de sondeo utilizando la técnica lineal
        return (posicion + 1) % tamano_tabla;
    }

    //imprime la tabla hash
    public int cantidad_elementos() {
        int contador = 0;
        for (LinkedList<String> lista : tabla) {
            contador += lista.size();
        }
        return contador;
    }

}