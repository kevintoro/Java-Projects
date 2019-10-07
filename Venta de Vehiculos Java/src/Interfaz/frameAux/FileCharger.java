package Interfaz.frameAux;

import java.io.*;
import javax.swing.*;
import Interfaz.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileCharger extends JFileChooser
{
    Main main;
    public FileCharger(Main main)
    {
        this.main = main;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("dat", "dat");
        setFileFilter(filter);
    }
    
    public ObjectInputStream loadFile()
    {
        int sel = showOpenDialog(main);
        if(sel == APPROVE_OPTION)
        {
            ObjectInputStream input = null;
            try
            {
                input = new ObjectInputStream(new FileInputStream(getSelectedFile().toString()));
            }   
            catch(IOException e)
            {
                JOptionPane.showMessageDialog(main, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
            }
            
            return input;
        }
        else
            return null;
    }
    
    public ObjectOutputStream saveFile(Object ob)
    {
        int sel = showSaveDialog(main);
        if(sel == APPROVE_OPTION)
        {
            ObjectOutputStream output = null;
            try
            {
                output = new ObjectOutputStream(new FileOutputStream(getSelectedFile().toString()+".dat"));
                output.writeObject(ob);
                output.close();
            }   
            catch(IOException e)
            {
                JOptionPane.showMessageDialog(main, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
            }
            
            return output;
        }
        else
            return null;
    }
}