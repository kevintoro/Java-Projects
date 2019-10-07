package Interfaz;

/**
 * 
 * @author 1151510 && 1151506
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import main.*;

public class Main extends JFrame
{
    PanelSuperior pSuperior;
    public PanelCentral pCentral;
    public VentaVehiculos vVehiculos;
    
    public Main()
    {
        super();
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(Color.BLACK);
        setTitle("Venta Automóviles");
        setLayout(new BorderLayout());
        
        ImageIcon temp = new ImageIcon(getClass().getResource("/Imagenes/651.jpg"));
        Image icon = temp.getImage();
        icon.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        
        setIconImage(icon);
        vVehiculos = new VentaVehiculos();
        
        pSuperior = new PanelSuperior();
        add(pSuperior,BorderLayout.NORTH);
        
        pCentral = new PanelCentral(this);
        add(pCentral, BorderLayout.CENTER);
    }
    
    public VentaVehiculos getVentaVehiculo()
    {
        return vVehiculos;
    }
    
    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            Main m = new Main();
            m.setVisible(true);
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e)
        {
            System.out.println(e);
        }
    }
}