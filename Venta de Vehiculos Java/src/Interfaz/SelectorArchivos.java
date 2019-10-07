package Interfaz;

import Interfaz.frameAux.FrameAgregarVehiculo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SelectorArchivos extends JFrame implements ActionListener
{
    JFileChooser fc;
    FrameAgregarVehiculo a;
    public SelectorArchivos(FrameAgregarVehiculo a)
    {
        super();
        setSize(450, 400);
        setTitle("Búsqueda");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        
        fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG,JPG, JPEG", "png", "jpg","jpeg");
        fc.setFileFilter(filter);
        fc.addActionListener(this);
        add(fc);
        pack();
        
        this.a = a;
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
            a.txtImagen.setText(getRuta()+"\\"+fc.getSelectedFile().getName());
            this.dispose();
        }
    }
}