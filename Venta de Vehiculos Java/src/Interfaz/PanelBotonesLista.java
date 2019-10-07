package Interfaz;

import Interfaz.frameAux.FileCharger;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import main.*;

public class PanelBotonesLista extends JPanel implements ActionListener
{
    
    JButton butCilindrada, butBuscarV, butCargar, butGuardar;
    Main main;
    public PanelBotonesLista(Main main)
    {
        ActionListener al = this;
        TitledBorder border = BorderFactory.createTitledBorder("                 ");
        setBorder(border);
        setVisible(true);
        setLayout(new GridLayout(2,2));
        
        butCilindrada = new JButton("Buscar por Cilindraje");
        butCilindrada.addActionListener(al);
        butCilindrada.setFont(new Font("Arial", Font.BOLD, 11));
        
        butBuscarV = new JButton("Buscar por Años");
        butBuscarV.addActionListener(al);
        
        butCargar = new JButton("Cargar");
        butCargar.addActionListener(al);
        
        butGuardar = new JButton("Guardar");
        butGuardar.addActionListener(al);
        
        add(butCilindrada);
        add(butBuscarV);
        add(butCargar);
        add(butGuardar);
        
        this.main = main;
    }   

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == butBuscarV)
        {
            if(main.vVehiculos.getLista().size()>0)
            {
                try
                {
                    int añoMin = Integer.parseInt(JOptionPane.showInputDialog("Año Mínimo de Búsqueda"));
                    int añoMax = Integer.parseInt(JOptionPane.showInputDialog("Año Máximo de Búsqueda"));
                    
                    if(añoMin<1900 || añoMax<1900)
                    {
                        do
                        {
                            JOptionPane.showConfirmDialog(main, "Datos de búsqueda erróneos", "error en ejecución", JOptionPane.ERROR_MESSAGE);
                            añoMin = Integer.parseInt(JOptionPane.showInputDialog("Año Mínimo de Búsqueda"));
                            añoMax = Integer.parseInt(JOptionPane.showInputDialog("Año Máximo de Búsqueda"));
                        }
                        while(añoMin<1900 || añoMax <1900);
                    }
                
                    String data = "Resultado de Búsqueda: \n";
                    for(Vehiculo vh: main.vVehiculos.getVehiculosEntre(añoMin, añoMax))
                    {
                        data = data.concat(vh.getMarca()+" - "+vh.getModelo()+" ("+vh.getAño()+")\n");
                    }
                
                    if(data.equals("Resultado de Búsqueda: \n"))
                    {
                        JOptionPane.showMessageDialog(null, "No hay Vehículos Entre Dichos Años");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, data);
                    }
                }
                catch(NumberFormatException | NullPointerException er)
                {
                    JOptionPane.showMessageDialog(main, er.getMessage(), "error en ejecución", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(main, "No hay Vehículos agregados", "Error en búsqueda", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(ae.getSource() == butCilindrada)
        {
            if(main.vVehiculos.getLista().size()>0)
            {
                try
                {
                    int min = Integer.parseInt(JOptionPane.showInputDialog("Cilindraje Mínimo de Búsqueda"));
                    int max = Integer.parseInt(JOptionPane.showInputDialog("Cilindraje Máximo de Búsqueda"));
                    
                    String data = "Resultado de Búsqueda: \n";
                    for(Vehiculo vh: main.vVehiculos.getVehiculosCilindraje(min, max))
                    {
                        data = data.concat(vh.getMarca()+" - "+vh.getModelo()+" ("+vh.getAño()+")\n");
                    }
                    if(data.equals("Resultado de Búsqueda: \n"))
                    {
                        JOptionPane.showMessageDialog(null, "No hay Vehículos Entre Dichos Años");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, data);
                    }
                }
                catch(NumberFormatException | NullPointerException e)
                {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"error en ejecución",JOptionPane.ERROR_MESSAGE);
                }
                
            }
            else
            {
                JOptionPane.showMessageDialog(main, "No hay Vehículos agregados", "Error en búsqueda", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(ae.getSource() == butCargar)
        {
            try
            {
                FileCharger fc = new FileCharger(main);
                Vehiculo [] veh;
                
                if(fc.loadFile().readObject() != null)
                {
                    Object ob = fc.loadFile().readObject();
                    veh = (Vehiculo[])(ob);
                    for(Vehiculo v: veh)
                    {
                        main.vVehiculos.cargaListado(v);
                    }
                    main.pCentral.spListado.actualizar(main.vVehiculos);
                    JOptionPane.showMessageDialog(main, "Carga Exitoso");
                }
            }
            catch(IOException | ClassNotFoundException e)
            {
                JOptionPane.showMessageDialog(main, e.getMessage(), "error al cargar", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(ae.getSource() == butGuardar)
        {
            Vehiculo[] veh = new Vehiculo[main.vVehiculos.getLista().size()];
            int i = 0;
            for(Vehiculo v: main.vVehiculos.getLista())
            {
                veh[i] = v;
                i++;
            }
            
            FileCharger fc = new FileCharger(main);
            fc.saveFile(veh);
        }
    }
}