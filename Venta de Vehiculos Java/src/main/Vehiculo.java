package main;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.*;

public class Vehiculo implements Comparable, Serializable
{
    private final String marca, modelo, año, cilindraje;
    double precio;
    private int ejes, cantidad, tipo, color;
    private ImageIcon imagen;
    public ArrayList <ImageIcon> imagenes;
    
    public final static int BLANCO = 1;
    public final static int NEGRO = 2;
    public final static int GRIS = 3;
    public final static int ROJO = 4;
    public final static int AZUL = 5;
    public final static int VERDE = 6;
    
    public final static int AUTOMOVIL = 1;
    public final static int MOTOCICLETA = 2;
    public final static int CAMION = 3;
    
    private final double precio_inicial;
    
    public Vehiculo(String marca, String modelo, String año, double precio, int ejes, int tipo, String cilindraje, int color, String url)
    {
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.precio = precio;
        this.ejes = ejes;
        this.cilindraje = cilindraje;
        this.precio_inicial = precio;
        this.imagenes = new ArrayList<>();
        this.cantidad = 1;
        
        setColor(color);
        setImagen(url);
        setTipo(tipo);
    }
    
    public void agregarImagen(ImageIcon image, Component c)
    {
        boolean exist = false;
        
        for(ImageIcon i: imagenes)
        {
            if(i.getDescription().equalsIgnoreCase(image.getDescription()))
            {
                exist = true;
            }
        }
        
        if(!exist)
        {
            imagenes.add(image);
            JOptionPane.showMessageDialog(c, "Imagen Agregada Correctamente");
        }
        else
        {
            JOptionPane.showMessageDialog(c, "Imagen Ya Existente","Agregar Imagen",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void agregarImagenFile(ImageIcon image)
    {
        boolean exist = false;
        
        for(ImageIcon i: imagenes)
        {
            if(i.getDescription().equalsIgnoreCase(image.getDescription()))
            {
                exist = true;
            }
        }
        
        if(!exist)
        {
            imagenes.add(image);
        }
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getAño() {
        return año;
    }

    public String getCilindraje() {
        return cilindraje;
    }

    public double getPrecio() {
        return precio;
    }

    public int getEjes() {
        return ejes;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getTipo() {
        return tipo;
    }
    
    public String getColor()
    {
        String valueColor;
        switch(color)
        {
            case Vehiculo.BLANCO: valueColor = "Blanco";
            break;
            
            case Vehiculo.NEGRO: valueColor = "Negro";
            break;
            
            case Vehiculo.GRIS: valueColor = "Gris";
            break;
            
            case Vehiculo.ROJO: valueColor = "Rojo";
            break;
            
            case Vehiculo.AZUL: valueColor = "Azul";
            break;
            
            case Vehiculo.VERDE: valueColor = "Verde";
            break;
            
            default: valueColor = "";
        }
        
        return valueColor;
    }
    
    public ImageIcon getImageDefault()
    {
        return imagen;
    }
    
    public final void setImagen(String url)
    {
        this.imagen = new ImageIcon(url);
        this.imagen.setDescription(url);
        imagenes.add(imagen);
    }
    
    public final void setTipo(int t)
    {
        if(t<1 || t>3)
        {
            do
            {
                JOptionPane.showMessageDialog(null, "Tipo de Vehículo Inválido", "Error en creación", JOptionPane.ERROR_MESSAGE);
                t = Integer.parseInt(JOptionPane.showInputDialog("Digite un Tipo"));
            }
            while(t<1 || t >3);
        }
        this.tipo = t;
    }
    
    /**
     * Seleccione un color determinado entre 1 y 6 siendo:
     * 1. Blanco
     * 2. Negro
     * 3. Gris
     * 4. Rojo
     * 5. Azul
     * 6. Verde
     * @param color 
     */
    public final void setColor(int color)
    {
        if(color<1 || color >6)
        {
            do
            {
                JOptionPane.showMessageDialog(null, "Tipo de Color Inválido", "Error en creación", JOptionPane.ERROR_MESSAGE);
                color = Integer.parseInt(JOptionPane.showInputDialog("Digite Código de un color Válido:\n"
                        + "1: Blanco\n"
                        + "2: Negro \n"
                        + "3: Gris \n"
                        + "4: Rojo \n"
                        + "5: Azul \n"
                        + "6: verde"));
            }
            while(color<1 || color >6);
        }
        else
        {
            this.color = color;
        }
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public void descuento(double porcentaje)
    {
        precio -= precio*porcentaje/100;
    }
    
    public void precioNormal()
    {
        this.precio = precio_inicial;
    }

    @Override
    public int compareTo(Object t) 
    {
        Vehiculo v = (Vehiculo) t;
        if(v.getPrecio() > this.getPrecio())
        {
            return -1;
        }
        else if(this.getPrecio() > v.getPrecio())
        {
            return 1;
        }
        return 0;
    }
}