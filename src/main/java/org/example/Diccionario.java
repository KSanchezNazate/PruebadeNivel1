package org.example;

import java.util.HashMap;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Diccionario {

    private HashMap<Character, LinkedList<String>> tablaHash;

    public Diccionario() {
        tablaHash = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            tablaHash.put(c, new LinkedList<String>());
        }
    }
    //agrega una palabra al diccionario
    public void agregarPalabra(String palabra) {
        char primeraLetra = palabra.charAt(0);
        LinkedList<String> palabrasConLetra = tablaHash.get(primeraLetra);
        if (palabrasConLetra == null) {
            palabrasConLetra = new LinkedList<String>();
            tablaHash.put(primeraLetra, palabrasConLetra);
        }
        palabrasConLetra.add(palabra);
    }
    //busca una palabra en el diccionario
    public boolean buscarPalabra(String palabra) {
        char primeraLetra = palabra.charAt(0);
        LinkedList<String> palabrasConLetra = tablaHash.get(primeraLetra);
        return palabrasConLetra.contains(palabra);
    }

    //lee el archivo csv y agrega las palabras al diccionario
    public void leerDiccionario(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String palabra = datos[0];
                agregarPalabra(palabra);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //imprime la tabla hash
    public void imprimirTablaHash() {
        for (HashMap.Entry<Character, LinkedList<String>> entry : tablaHash.entrySet()) {
            char letra = entry.getKey();
            LinkedList<String> palabrasConLetra = entry.getValue();
            System.out.print(letra + ": ");
            if (palabrasConLetra != null) {
                for (String palabra : palabrasConLetra) {
                    System.out.print(palabra + " ");
                }
            }
            System.out.println();
        }
    }

    //método main para probar el diccionario
    public static void mainParaDiccionario() {
        Diccionario diccionario = new Diccionario();
        diccionario.leerDiccionario("palabras.csv");
        diccionario.imprimirTablaHash();
    }
}
