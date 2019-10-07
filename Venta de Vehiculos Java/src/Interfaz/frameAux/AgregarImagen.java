package Interfaz.frameAux;

import Interfaz.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import main.Vehiculo;

public class AgregarImagen extends JFrame implements ActionListener
{
    JFileChooser fc;
    Vehiculo v;
    Main main;
    public AgregarImagen(Vehiculo v, Main main)
    {
        super();
        setSize(450, 400);
        setTitle("Búsqueda");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(main);
        
        this.v = v;
        this.main = main;
        
        fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG,JPG, JPEG", "png", "jpg","jpeg");
        fc.setFileFilter(filter);
        fc.addActionListener(this);
        add(fc);
        pack();
    }
    
    public String getRuta()
    {
        return fc.getSelectedFile().getParent();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals(JFileChooser.CANCEL_SELECTION))
        {
            this.dispose();
        }
        
        if(ae.getActionCommand().equals(JFileChooser.APPROVE_SELECTION))
        {
            ImageIcon temp = new ImageIcon(getRuta()+"\\"+fc.getSelectedFile().getName());
            v.agregarImagen(temp, main);
            main.pCentral.spDetalles.actualizar(v);
            this.dispose();
        }
    }
}