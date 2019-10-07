/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matias Herrera Caceres
 */
public class ArbolBin {

    NodoB padre;
    NodoB raiz;

    //Constructor
    public ArbolBin() {
        raiz = null;
    }

    //Insercion de un elemento en el arbol
    public void insertaNodo(int Elem) {
        if (raiz == null) {
            raiz = new NodoB(Elem);
        } else {
            raiz.insertar(Elem);
        }
    }

    //Preorden Recursivo del arbol
    public NodoB preorden(NodoB Nodo) {
        if (Nodo != null)
        {
            System.out.print(Nodo.dato + " ");
            preorden(Nodo.Hizq);
            preorden(Nodo.Hder);
        }
        return Nodo;
    }

    //PostOrden recursivo del arbol
    public NodoB postOrden(NodoB Nodo) {
        if (Nodo != null)
        {
            postOrden(Nodo.Hizq);
            postOrden(Nodo.Hder);
            System.out.print(Nodo.dato + " ");
        }
        return Nodo;
    }

    //Inorden Recursivo del arbol
    public NodoB inorden(NodoB Nodo)
    {    
        if (Nodo != null)
        {
            inorden(Nodo.Hizq);
            System.out.print(Nodo.dato + " ");
            inorden(Nodo.Hder);
        }
        return Nodo;
    }

//cantidad de niveles q	ue posee el arbol
    public int altura(NodoB Nodo) {
        if (Nodo == null) {
            return -1;
        } else {
            return 1 + Math.max(altura(Nodo.Hizq), altura(Nodo.Hder));
        }
    }
//cantidad de elementos que posee el arbol	

    public int tamaño(NodoB Nodo) {
        if (Nodo == null) {
            return 0;
        } else {
            return 1 + tamaño(Nodo.Hizq) + tamaño(Nodo.Hder);
        }
    }
    //Busca un elemento en el arbol

    public void buscar(int Elem, NodoB A) {
        if (A == null | A.dato == Elem) {
            System.out.print(A.dato + " ");
            return;
        } else {
            if (Elem > A.dato) {
                buscar(Elem, A.Hder);
            } else {
                buscar(Elem, A.Hizq);
            }
        }
    }
    
    /**
     * Método para eliminar un dato si es hoja
     * @param nodo : que será el punto de partida para revisar de ahí para abajo
     * @param dato : que, será lo que se eliminará siempre y cuando sea una hoja
     */
    public void eliminarHoja(NodoB nodo, int dato)
    {
        if(nodo!= null)
        {
            if(nodo.dato == dato)
            {
                if(nodo.Hder == null && nodo.Hizq == null)
                {
                    System.out.println("\nEs Hoja");
                    
                    if(nodo != raiz)
                    {
                        if(nodo.dato<padre.dato)
                        {
                            padre.Hizq = null;
                        }
                        else
                        {
                            padre.Hder = null;
                        }
                    }
                    else
                    {
                        raiz = null;
                    }
                }
                else
                {
                    System.out.println("\nNo Es Hoja");
                }
            }
            else
            {
                padre = nodo;
                eliminarHoja(nodo.Hizq, dato);
                eliminarHoja(nodo.Hder, dato);
            }
        }
    }
    
    public NodoB eliminarRaizUnHijo(NodoB nodo, int dato)
    {
        if (nodo != null) 
        {
            if (nodo.dato == dato) 
            {
                if(nodo.Hder == null && nodo.Hizq != null)
                {
                    nodo = nodo.Hizq;
                }
                else if(nodo.Hder != null && nodo.Hizq == null)
                {
                    nodo = nodo.Hder;
                }
                else
                {
                    System.out.println("No se puede eliminar porque no tiene hijos, o tiene 2 hijos");
                }   
            }
            else 
            {
                nodo.Hizq = eliminarRaizUnHijo(nodo.Hizq, dato);
                nodo.Hder = eliminarRaizUnHijo(nodo.Hder, dato);
            }
        }
        return nodo;
    }
    
    //---------------------------------- Puntos de Taller ------------------------------------//
    public void longitudHasta(int dato, int cont, NodoB ac)
    {
        if(ac != null)
        {
            if(ac.dato != dato)
            {
                int aux = cont+1;
                longitudHasta(dato, aux, ac.Hizq);
                aux = cont+1;
                longitudHasta(dato, aux, ac.Hder);
            }
            else if(ac.dato == dato)
            {
                System.out.println("Longitud: "+(cont-1));
            }
        }
    }
    
    public NodoB llenarArbol(NodoB nodo, int n, int nivel)
    {
        if(nodo != null)
        {
            nodo.Hder = llenarArbol(nodo.Hder, n, nivel + 1);
            nodo.Hizq = llenarArbol(nodo.Hizq, n, nivel + 1);
        }
        else if(nivel <= n)
        {
                nodo = new NodoB(0);
                nodo.Hizq = llenarArbol(nodo.Hizq, n, nivel + 1);
                nodo.Hder = llenarArbol(nodo.Hder, n, nivel + 1);
        }
        return nodo;
    }
    
    public NodoB eliminarHojasRango(NodoB ac, int x, int rango)
    {
        if(ac!=null)
        {
            if(x<rango)
            {
                eliminarHojasRango(ac.Hder, x+1, rango);
                eliminarHojasRango(ac.Hizq, x+1, rango);
            }
            else if(x == rango)
            {
                ac.Hder = null;
                ac.Hizq = null;
            }
        }
        return ac;
    }
    
    public void estaClave(NodoB ac, int dato)
    {
        if(ac.Hder == null && ac.Hizq == null)
        {
            if(ac.dato == dato)
            {
                System.out.println("El dato \""+dato+ "\", sí es una Hoja");
            }
        }
        else if (ac.Hder != null || ac.Hizq != null)
        {
            estaClave(ac.Hizq, dato);
            estaClave(ac.Hder, dato);
        }
    }
    
    public boolean antecesores(NodoB ac, int dato)
    {
        boolean is = false;
        if(ac!=null)
        {
            if(ac.dato<dato)
            {
                is = antecesores(ac.Hder, dato);
                if(is == false)
                {
                    is = antecesores(ac.Hizq, dato);
                }
            }
            else if(ac.dato == dato)
            {
                System.out.println("Sí Son Antecesores");
            }
        }
        return is;
    }
    
    public static void main(String[] args)
    {
        ArbolBin a = new  ArbolBin();
        a.insertaNodo(5);
        a.insertaNodo(3);
        a.insertaNodo(6);
        a.insertaNodo(1);
        a.insertaNodo(2);
        a.insertaNodo(4);
        a.insertaNodo(7);
        a.preorden(a.raiz);
        System.out.println("");
        a.longitudHasta(2, 1, a.raiz);
        a.llenarArbol(a.raiz, a.altura(a.raiz), 0);
        System.out.println("-----------------\n");
        a.preorden(a.raiz);
        System.out.println("\n-----------------\n");
        a.eliminarHojasRango(a.raiz, 0, 2);
        a.preorden(a.raiz);
        System.out.println("");
        a.estaClave(a.raiz, 7);
        System.out.println(a.antecesores(a.raiz, 7));
    }
}