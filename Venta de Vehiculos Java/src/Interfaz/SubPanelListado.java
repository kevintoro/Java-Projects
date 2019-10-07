package Interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import main.*;

public class SubPanelListado extends JPanel implements ActionListener
{
    JComboBox<String> marcas, modelos, colores;
    Main main;
    public SubPanelListado(Main main)
    {
        this.main = main;
        
        setLayout(new GridLayout(12, 1));
        TitledBorder border = BorderFactory.createTitledBorder("Vehículos a la Venta");
        border.setTitleColor(Color.BLUE);
        setBorder(border);
        
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel("Marcas Disponibles: "));
        marcas = new JComboBox<>();
        marcas.addItem("Empty");
        marcas.addActionListener(this);
        add(marcas);
        
        add(new JLabel("Modelos Disponibles: "));
        modelos = new JComboBox<>();
        modelos.addItem("Seleccione una Marca");
        modelos.setEnabled(false);
        add(modelos);
        modelos.addActionListener(this); 
        
        add(new JLabel("Colores Disponibles:"));
        
        colores = new JComboBox<>();
        colores.addItem("Seleccione Marca y Modelo");
        colores.setEnabled(false);
        add(colores);
        colores.addActionListener(this);
        
        actualizar(main.vVehiculos);
    }
    
    public final void actualizar(VentaVehiculos v)
    {
        if(v!= null)
        {
            marcas.removeAllItems();
            marcas.addItem("");
            for(Vehiculo vh : v.getLista())
            {
                boolean exist = false;
                
                for(int x = 0; x < marcas.getItemCount(); x++)
                {
                    if(vh.getMarca().equalsIgnoreCase(marcas.getItemAt(x)))
                    {
                        exist = true;
                    }
                }
            
                if(exist == false)
                {
                    marcas.addItem(vh.getMarca());
                }
            }
        }
    }
    
    private void coloresVehiculo(String color)
    {
        boolean exist = false;
        for(int x = 0; x<colores.getItemCount(); x++)
        {
            String c = String.valueOf(colores.getItemAt(x));
            if(c.equalsIgnoreCase(color))
            {
                exist = true;
            }
        }
            
        if(!exist)
        {
            colores.addItem(color);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(main.pCentral!=null)
        {
            if(ae.getSource() == marcas)
            {
                if(marcas.getSelectedIndex()>0)
                {
                    modelos.setEnabled(true);
                    modelos.removeAllItems();
                    modelos.addItem("");
                    String name = String.valueOf(marcas.getSelectedItem());
                    for(Vehiculo vh: main.vVehiculos.getLista())
                    {
                        boolean exist = false;
                        if(vh.getMarca().equalsIgnoreCase(name))
                        {
                            for(int x = 0; x<modelos.getItemCount();x++)
                            {
                                String temp = vh.getModelo()+"("+vh.getAño()+")";
                                if(temp.equalsIgnoreCase(modelos.getItemAt(x)))
                                {
                                    exist = true;
                                }
                            }
                    
                            if(exist == false)
                            {
                                modelos.addItem(vh.getModelo()+"("+vh.getAño()+")");
                            }
                        }
                    }
                }
                else
                {
                    modelos.removeAllItems();
                    modelos.addItem("Seleccione una Marca");
                    modelos.setEnabled(false);
                    
                    colores.removeAllItems();
                    colores.addItem("Seleccione Marca y Modelo");
                    colores.setEnabled(false);
                }
            }
        
            if(ae.getSource() == modelos)
            {
                colores.removeAllItems();
                colores.removeActionListener(this);
                colores.addItem("");
                if(modelos.getSelectedIndex()>0)
                {
                    String s = String.valueOf(marcas.getSelectedItem());
                    String temp = String.valueOf(modelos.getSelectedItem());
                    int aux = temp.indexOf("(");
                    String m = temp.substring(0,aux);
                    String time = temp.substring(aux+1,temp.length()-1);
                    
                    for(Vehiculo vh: main.vVehiculos.getLista())
                    {
                        if(vh.getMarca().equalsIgnoreCase(s) && vh.getModelo().equalsIgnoreCase(m) && vh.getAño().equalsIgnoreCase(time))
                        {
                            colores.setEnabled(true);
                            coloresVehiculo(vh.getColor());
                        }
                    }
                    colores.addActionListener(this);
                }
                else
                {
                    main.pCentral.spDetalles.defaultValue();
                    colores.removeAllItems();
                    colores.addItem("Seleccione Marca y Modelo");
                    colores.setEnabled(false);
                    main.pCentral.spDetalles.newNextAndBefore(false);
                }
            }
            
            if(ae.getSource() == colores)
            {
                if(colores.getSelectedIndex()>0)
                {
                    String s = String.valueOf(marcas.getSelectedItem());
                    String temp = String.valueOf(modelos.getSelectedItem());
                    int aux = temp.indexOf("(");
                    String m = temp.substring(0,aux);
                    String time = temp.substring(aux+1,temp.length()-1);
                    
                    for(Vehiculo vh: main.vVehiculos.getLista())
                    {
                        String color = String.valueOf(colores.getSelectedItem());
                        if(vh.getMarca().equalsIgnoreCase(s) && vh.getModelo().equalsIgnoreCase(m) && vh.getAño().equals(time) && vh.getColor().equalsIgnoreCase(color))
                        {
                            main.pCentral.spDetalles.actualizar(vh);
                        }
                    }
                }
                else
                {
                    main.pCentral.spDetalles.defaultValue();
                    main.pCentral.spDetalles.newNextAndBefore(false);
                }
            }
        }
    }
}