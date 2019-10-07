package main;

import java.awt.Component;
import java.util.*;
import javax.swing.JOptionPane;
public class VentaVehiculos
{
    private final ArrayList<Vehiculo> lista;
    
    public VentaVehiculos()
    {
        lista = new ArrayList<>();
    }

    public ArrayList<Vehiculo> getLista()
    {
        return lista;
    }
    
    public void venderVehiculo(Vehiculo vehiculo)
    {
        boolean elim = false;
        for(Vehiculo v: lista)
        {
            if(vehiculo == v)
            {
                if(v.getCantidad()>1)
                {
                    v.setCantidad(v.getCantidad()-1);
                    if(v.getCantidad() == 0)
                    {
                        elim = true;
                    }
                }
                else
                {
                    elim = true;
                }
            }
        }
        
        if(elim == true)
        {
            lista.remove(vehiculo);
        }
    }
    
    public void agregarVehiculo(Vehiculo nuevo, Component comp)
    {
        boolean exist = false;
        if(lista != null)
        {
            for(Vehiculo v: lista)
            {
                if(v.getMarca().equalsIgnoreCase(nuevo.getMarca()))
                {
                    if(v.getModelo().equalsIgnoreCase(nuevo.getModelo()))
                    {
                        if(v.getAño().equalsIgnoreCase(nuevo.getAño()))
                        {
                            if(v.getColor().equals(nuevo.getColor()))
                            {
                                JOptionPane.showMessageDialog(comp, "Vehículo Similar encontrado\n"
                                    + "(Cantidad de "+v.getMarca()+" - "+v.getModelo()+" aumentada)");
                                v.setCantidad(v.getCantidad()+1);
                                exist = true;
                                v.agregarImagen(nuevo.getImageDefault(), comp);
                            }
                        }
                    }
                }
            }
        
            if(exist == false)
            {
                lista.add(nuevo);
                JOptionPane.showMessageDialog(comp, "Vehículo Agregado Correctamente");
            }
        }
        else
        {
            lista.add(nuevo);
            JOptionPane.showMessageDialog(comp, "Vehículo Agregado Correctamente");
        }
    }
    
    public ArrayList<Vehiculo> getVehiculosEntre(int añoMin, int añoMax)
    {
        ArrayList<Vehiculo> listadoAño = new ArrayList<>();
        
        for(Vehiculo v: lista)
        {
            int año = Integer.parseInt(v.getAño());
            if(año >= añoMin && año<= añoMax)
            {
                listadoAño.add(v);
            }
        }
        return listadoAño;
    }
    
    public ArrayList<Vehiculo> getVehiculosCilindraje(int min, int max)
    {
        ArrayList<Vehiculo> listadoCilindraje = new ArrayList<>();
        for(Vehiculo vh: lista)
        {
            int c = Integer.parseInt(vh.getCilindraje());
            
            if(c>=min && c<=max)
            {
                listadoCilindraje.add(vh);
            }
        }
        return listadoCilindraje;
    }
    
    public ArrayList<Vehiculo> getMayorPotencia()
    {
        ArrayList<Vehiculo> potencia = new ArrayList<>();
        Vehiculo temp = null;
        for(Vehiculo vh: lista)
        {
            if(temp == null)
            {
                temp = vh;
                potencia.add(vh);
            }
            else
            {
                int cil1 = Integer.parseInt(vh.getCilindraje());
                int cil2 = Integer.parseInt(temp.getCilindraje());
                
                if(cil1>cil2)
                {
                    temp = vh;
                    potencia = new ArrayList<>();
                    potencia.add(vh);
                }
                else if(cil1 == cil2)
                {
                    potencia.add(vh);
                }
            }
        }
        return potencia;
    }
    
    public ArrayList<Vehiculo> getMasAntiguo()
    {
        ArrayList<Vehiculo> old = new ArrayList<>();
        Vehiculo temp = null;
        for(Vehiculo vh: lista)
        {
            if(temp == null)
            {
                temp = vh;
                old.add(vh);
            }
            else
            {
                int año1 = Integer.parseInt(vh.getAño());
                int año2 = Integer.parseInt(temp.getAño());
                
                if(año1<año2)
                {
                    temp = vh;
                    old = new ArrayList<>();
                    old.add(vh);
                }
                else if(año1 == año2)
                {
                    old.add(vh);
                }
            }
        }
        return old;
    }
    
    public ArrayList<Vehiculo> getEconomico()
    {
        ArrayList<Vehiculo> eco = new ArrayList<>();
        Vehiculo temp = null;
        for(Vehiculo vh: lista)
        {
            if(temp == null)
            {
                temp = vh;
                eco.add(vh);
            }
            else
            {
                if(vh.getPrecio() < temp.getPrecio())
                {
                    eco = new ArrayList<>();
                    temp = vh;
                    eco.add(vh);
                }
                else if(vh.getPrecio() == temp.getPrecio())
                {
                    eco.add(vh);
                }
            }
        }
        return eco;
    }
    
    public void cargaListado(Vehiculo nuevo)
    {
        boolean exist = false;
    
        if(lista != null)
        {
            for(Vehiculo v: lista)
            {
                if(v.getMarca().equalsIgnoreCase(nuevo.getMarca()))
                {
                    if(v.getModelo().equalsIgnoreCase(nuevo.getModelo()))
                    {
                        if(v.getAño().equalsIgnoreCase(nuevo.getAño()))
                        {
                            if(v.getColor().equals(nuevo.getColor()))
                            {
                                v.setCantidad(v.getCantidad()+1);
                                exist = true;
                                v.agregarImagenFile(nuevo.getImageDefault());
                            }
                        }
                    }
                }
            }
        
            if(exist == false)
            {
                lista.add(nuevo);
            }
        }
    }
}