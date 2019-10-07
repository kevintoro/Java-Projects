package ListaCircular;

import javax.swing.ImageIcon;

public class ListaCircularImagenes
{
    Nodo inicio;
    Nodo fin;
    Nodo ac;
    
    public ListaCircularImagenes()
    {
        inicio = fin = null;
    }
    
    public void agregar(ImageIcon icon)
    {
        Nodo c = new Nodo(icon);
        
        if(inicio == null)
        {
            inicio = fin = ac = c;
        }
        else
        {
            fin.sig = c;
            c.sig = inicio;
            c.ant = fin;
            fin = c;
            inicio.ant = fin;
        }
    }
    
    public Nodo siguiente()
    {
        if(inicio != fin)
        {
            ac = ac.sig;
        }
        return ac;
    }
    
    public Nodo anterior()
    {
        if(inicio != fin)
        {
            ac = ac.ant;
        }
        return ac;
    }
    
    public int getTamanio()
    {
        if(inicio!=null)
        {
            Nodo temp = inicio;
            int cont = 1;
            while(temp!=fin)
            {
                cont++;
                temp = temp.sig;
            }
            
            return cont;
        }
        return 0;
    }
}