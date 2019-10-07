package Interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import Interfaz.frameAux.FrameAgregarVehiculo;
import javax.swing.JOptionPane;
import main.Vehiculo;

public class PanelBotonesFuncion extends JPanel implements ActionListener 
{
    JButton butAgregarV, butOferta, butMasEconomico, butPrecioNormal, butMasAntiguo, butMasPotente;
    Main main;
    
    public PanelBotonesFuncion(Main main)
    {
        ActionListener al = this;
        this.main = main;
        setVisible(true);
        TitledBorder border = BorderFactory.createTitledBorder("Consultas y Operaciones");
        border.setTitleColor(Color.blue);
        setBorder(border);
        setLayout(new GridLayout(2,3));
        
        butAgregarV = new JButton("Agregar Vehiculo");
        butAgregarV.addActionListener(al);
        
        butOferta = new JButton("Disminuir Precio");
        butOferta.addActionListener(al);
        
        butMasEconomico = new JButton("Más Económico");
        butMasEconomico.addActionListener(al);
        
        butPrecioNormal = new JButton("Precios a Normalidad");
        butPrecioNormal.addActionListener(al);
        
        butMasAntiguo = new JButton("Más Antiguo");
        butMasAntiguo.addActionListener(al);
        
        butMasPotente = new JButton("Más Potente");
        butMasPotente.addActionListener(al);
        
        add(butAgregarV);
        add(butOferta);
        add(butMasEconomico);
        add(butPrecioNormal);
        add(butMasAntiguo);
        add(butMasPotente);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == butPrecioNormal)
        {
            if(main.getVentaVehiculo().getLista().size()>0)
            {
                for(Vehiculo v: main.vVehiculos.getLista())
                {
                    v.precioNormal();
                }
                main.pCentral.spListado.actualizar(main.vVehiculos);
            }
            else
            {
                JOptionPane.showMessageDialog(main, "No hay Vehículos agregados", "Error en búsqueda", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(ae.getSource() == butAgregarV)
        {
            Interfaz.frameAux.FrameAgregarVehiculo av = new FrameAgregarVehiculo(main);
            av.setVisible(true);
        }
        
        if(ae.getSource() == butOferta)
        {
            if(main.vVehiculos.getLista().size()>0)
            {
                try
                {
                    Double descuento = Double.parseDouble(JOptionPane.showInputDialog("Digite el Porcentaje de Descuento (sin %)"));
                    main.vVehiculos.getLista().forEach((vh) -> {
                        vh.descuento(descuento);
                        main.pCentral.spListado.actualizar(main.vVehiculos);
                    });
                }
                catch(NumberFormatException | NullPointerException e)
                {
                    JOptionPane.showMessageDialog(main, e.getMessage(),"error en ejecución",JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(main, "No hay Vehículos agregados", "Error en Ejecución", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(ae.getSource() == butMasAntiguo)
        {
            if(main.vVehiculos.getLista().size()>0)
            {
                if(main.vVehiculos.getMasAntiguo().size()>1)
                {
                    String data = "Vehículos Más Antiguos: \n";
                    for(Vehiculo vh: main.vVehiculos.getMasAntiguo())
                    {
                        data = data.concat(vh.getMarca()+" - "+vh.getModelo()+"("+vh.getAño()+")\n");
                    }
                    JOptionPane.showMessageDialog(null, data);
                }
                else
                {
                    main.pCentral.spDetalles.actualizar(main.vVehiculos.getMasAntiguo().get(0));
                }
            }
            else
            {
                JOptionPane.showMessageDialog(main, "No hay Vehículos agregados", "Error en Ejecución", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(ae.getSource() == butMasEconomico)
        {
            if(main.vVehiculos.getLista().size()>0)
            {
                if(main.vVehiculos.getEconomico().size()>1)
                {
                    String data = "Vehículos Más Económicos: \n";
                    for(Vehiculo vh: main.vVehiculos.getEconomico())
                    {
                        data = data.concat(vh.getMarca()+" - "+vh.getModelo()+"("+vh.getAño()+")\n");
                    }
                    JOptionPane.showMessageDialog(null, data);
                }
                else
                {
                    main.pCentral.spDetalles.actualizar(main.vVehiculos.getEconomico().get(0));
                }
            }
            else
            {
                JOptionPane.showMessageDialog(main, "No hay Vehículos agregados", "Error en Ejecución", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(ae.getSource() == butMasPotente)
        {
            if(main.vVehiculos.getLista().size()>0)
            {
                if(main.vVehiculos.getMayorPotencia().size()>1)
                {
                    String data = "Vehículos Más Antiguos: \n";
                    for(Vehiculo vh: main.vVehiculos.getMayorPotencia())
                    {
                        data = data.concat(vh.getMarca()+" - "+vh.getModelo()+"("+vh.getAño()+")\n");
                    }
                    JOptionPane.showMessageDialog(null, data);
                }
                else
                {
                    main.pCentral.spDetalles.actualizar(main.vVehiculos.getMayorPotencia().get(0));
                }
            }
            else
            {
                JOptionPane.showMessageDialog(main, "No hay Vehículos agregados", "Error en Ejecución", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}