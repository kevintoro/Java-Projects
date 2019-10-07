package Interfaz;

import javax.swing.*;

public class PanelCentral extends JPanel
{
    Main main;
    public SubPanelListado spListado;
    public SubPanelDetalles spDetalles;
    PanelBotonesFuncion pbFuncion;
    PanelBotonesLista pbLista;
    
    public PanelCentral(Main main)
    {
        setLayout(null);
        this.main = main;
        spListado = new SubPanelListado(main);
        spListado.setBounds(10, 0, 310, 330);
        add(spListado);
        
        spDetalles = new SubPanelDetalles(main);
        spDetalles.setBounds(335, 0, 560, 330);
        add(spDetalles);
        
        pbFuncion = new PanelBotonesFuncion(main);
        pbFuncion.setBounds(335, 315, 560, 80);
        add(pbFuncion);
        
        pbLista = new PanelBotonesLista(main);
        pbLista.setBounds(10, 315, 310, 70);
        add(pbLista);
    }
}