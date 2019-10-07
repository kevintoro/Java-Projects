package Interfaz.frameAux;

import Interfaz.Main;
import javax.swing.*;
import main.Vehiculo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.HeadlessException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GuardarPdf extends JFileChooser
{
    Vehiculo v;
    DatosComprador dc;
    
    public GuardarPdf(Vehiculo v, Main main, String comprador, String cedula)
    {
        int sel = showSaveDialog(main);
        
        String marca = v.getMarca();
        String modelo = v.getModelo();
        String cilindraje = v.getCilindraje();
        String anio = v.getAño();
        String tipo;
        String color = v.getColor();
        
        switch(v.getTipo())
        {
            case Vehiculo.MOTOCICLETA: tipo = "Motocicleta";
            break;
            
            case Vehiculo.CAMION: tipo = "Camión";
            break;
            
            default: tipo = "Automóvil";
            break;
        }
        
        if(sel == APPROVE_OPTION)
        {
            try
            {
                File f = getSelectedFile();
                FileOutputStream op = new FileOutputStream(f.toString()+".pdf");
                Document doc = new Document(PageSize.LETTER);
                PdfWriter.getInstance(doc, op);
                
                doc.open();
                doc.add(getHeader("VentaVehiculos"));
                
                String data = "\n\n\n\nMarca: \n-"+marca+"\n\n"
                        + "Modelo: \n-"+modelo+"\n\n"
                        + "Tipo: \n-"+tipo+"\n\n"
                        + "Cilindraje: \n-"+cilindraje+"\n\n"
                        + "Año de Vehículo: \n-"+anio+"\n\n"
                        + "Color de Vehículo: \n-"+color+"\n\n"
                        + "Fecha de Compra: "+new SimpleDateFormat("dd - MM - yyyy").format(new Date());
                
                doc.add(addData(data));
                doc.add(addSignature(comprador, cedula));
                
                doc.close();
                
                main.getVentaVehiculo().venderVehiculo(v);
                JOptionPane.showMessageDialog(main, "Venta Realizada con Éxito");
            }
            catch(FileNotFoundException | DocumentException | HeadlessException e)
            {
                JOptionPane.showMessageDialog(main, e.getMessage(), "error en venta", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(sel == CANCEL_OPTION)
        {
            JOptionPane.showMessageDialog(main, "Guardado Necesario", "error en guardado", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private Paragraph getHeader(String tittle)
    {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(tittle);
        c.setFont(new Font(Font.FontFamily.COURIER, 25, Font.BOLD));
        p.add(c);
        
        return p;
    }
    
    private Paragraph addData(String data)
    {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        
        p.setAlignment(Element.ALIGN_JUSTIFIED);
        c.append(data);
        c.setFont(new Font(Font.FontFamily.COURIER, 14, Font.NORMAL));
        p.add(c);
        
        return p;
    }
    
    private Paragraph addSignature(String n, String cd)
    {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        
        p.setAlignment(Element.ALIGN_RIGHT);
        c.append("\n\n\n\n\n\n-------------------------\n"+n+"\n"+cd);
        c.setFont(new Font(Font.FontFamily.COURIER, 14, Font.NORMAL));
        p.add(c);
        
        return p;
    }
}