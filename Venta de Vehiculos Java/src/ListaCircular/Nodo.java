package ListaCircular;

public class Nodo<K>
{
    public K dato;
    public Nodo sig, ant;
    
    public Nodo(K dato)
    {
        this.dato = dato;
    }
}