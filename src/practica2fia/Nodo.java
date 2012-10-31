/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2fia;

import java.util.ArrayList;

/**
 *
 * @author victor
 */
public class Nodo implements Comparable {

    int f;
    int g;
    int h;
    int x, y;
    int tamano, destino;
    Nodo padre;
    ArrayList<Nodo> hijos;

    public Nodo(int _x, int _y, Nodo _padre, int _tamano, int _destino) {
        padre = _padre;
        tamano = _tamano;
        destino = _destino;
        x = _x;
        y = _y;
        if (padre == null) {
            g = 0;
        } else {
            g = padre.g + 1;
        }

        h = Math.abs(destino - x) + Math.abs(tamano - 1 - y);
        f = g + h;

        hijos = new ArrayList<Nodo>();
    }

    public boolean esMeta(int destino) {
        return x == destino && y == tamano - 1;
    }

    public void generarHijos(int[][] mundo, int destino) {
        if (y < tamano) {
            Nodo aux = new Nodo(x, y + 1, this, tamano, destino);
            if (mundo[x][y + 1] == 0 || aux.esMeta(destino)) { // Derecha
                hijos.add(aux);
            }
        }
        if (x > 0) {
            if (mundo[x - 1][y] == 0) { // Abajo
                hijos.add(new Nodo(x - 1, y, this, tamano, destino));
            }
        }
        if (x < tamano) {
            if (mundo[x + 1][y] == 0) { // Arriba
                hijos.add(new Nodo(x + 1, y, this, tamano, destino));
            }
        }
        if (y > 0) {
            if (mundo[x][y - 1] == 0) { // Izquierda
                hijos.add(new Nodo(x, y - 1, this, tamano, destino));
            }
        }

    }

    public boolean equals(Nodo n) {
        return x == n.x && y == n.y;
    }

    @Override
    public boolean equals(Object n) {
        Nodo aux = (Nodo) n;
        return x == aux.x && y == aux.y;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.x;
        hash = 17 * hash + this.y;
        return hash;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Object o) {
        Nodo n = (Nodo) o;
        if (this.f < n.f) {
            return -1;
        } else if (this.f == n.f) {
            return 0;
        } else {
            return 1;
        }
    }
}
