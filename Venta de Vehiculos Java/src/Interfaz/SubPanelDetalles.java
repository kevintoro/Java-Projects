package Interfaz;

import Interfaz.frameAux.AgregarImagen;
import Interfaz.frameAux.DatosComprador;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.border.*;
import main.*;
import ListaCircular.*;

public class SubPanelDetalles extends JPanel implements ActionListener
{
    JTextField txtModelo, txtMarca, txtAño, txtTipo, txtCilindraje, txtEjes, txtValor, txtCantidad;
    JLabel labMd, labMc, labA, labT, labC, labE, labV, labCt, labImagen;
    JButton butComprarV, next, bef, addImagen;
    JPanel imagen;
    DatosComprador dc;
    Main main;
    ListaCircularImagenes lcImagenes;
    
    /**
     * Constructor del Panel de Detalles del vehículo
     * @param main Ventana principal conectada a éste panel
     */
    public SubPanelDetalles(Main main)
    {
        ActionListener al = this;
        setLayout(null);
        TitledBorder border = BorderFactory.createTitledBorder("Detalles del Vehículo");
        border.setTitleColor(Color.BLUE);
        setBorder(border);
        this.main = main;
        
        labMd = new JLabel("Modelo: ");
        labMd.setBounds(20, 27, 80, 25);
        add(labMd);
        txtModelo = new JTextField("");
        txtModelo.setEditable(false);
        txtModelo.setHorizontalAlignment(JTextField.CENTER);
        txtModelo.setForeground(Color.BLUE.darker());
        txtModelo.setBounds(100, 25, 150, 30);
        add(txtModelo);
        
        labMc = new JLabel("Marca: ");
        labMc.setBounds(20, 62, 80, 25);
        add(labMc);
        txtMarca = new JTextField("");
        txtMarca.setEditable(false);
        txtMarca.setHorizontalAlignment(JTextField.CENTER);
        txtMarca.setForeground(Color.BLUE.darker());
        txtMarca.setBounds(100, 60, 150, 30);
        add(txtMarca);
        
        labA = new JLabel("Año: ");
        labA.setBounds(20, 97, 80, 25);
        add(labA);
        txtAño = new JTextField("");
        txtAño.setEditable(false);
        txtAño.setHorizontalAlignment(JTextField.CENTER);
        txtAño.setForeground(Color.BLUE.darker());
        txtAño.setBounds(100, 95, 150, 30);
        add(txtAño);
        
        labT = new JLabel("Tipo: ");
        labT.setBounds(20, 132, 80, 25);
        add(labT);
        txtTipo = new JTextField("");
        txtTipo.setEditable(false);
        txtTipo.setHorizontalAlignment(JTextField.CENTER);
        txtTipo.setForeground(Color.BLUE.darker());
        txtTipo.setBounds(100, 130, 150, 30);
        add(txtTipo);
        
        labC = new JLabel("Cilindraje: ");
        labC.setBounds(20, 167, 80, 25);
        add(labC);
        txtCilindraje = new JTextField("");
        txtCilindraje.setEditable(false);
        txtCilindraje.setHorizontalAlignment(JTextField.CENTER);
        txtCilindraje.setForeground(Color.BLUE.darker());
        txtCilindraje.setBounds(100, 165, 150, 30);
        add(txtCilindraje);
        
        labE = new JLabel("Ejes: ");
        labE.setBounds(20, 202, 80, 25);
        add(labE);
        txtEjes = new JTextField("");
        txtEjes.setEditable(false);
        txtEjes.setHorizontalAlignment(JTextField.CENTER);
        txtEjes.setForeground(Color.BLUE.darker());
        txtEjes.setBounds(100, 200, 150, 30);
        add(txtEjes);
        
        labV = new JLabel("Valor: ");
        labV.setBounds(20, 237, 80, 25);
        add(labV);
        txtValor = new JTextField("");
        txtValor.setEditable(false);
        txtValor.setHorizontalAlignment(JTextField.CENTER);
        txtValor.setForeground(Color.BLUE.darker());
        txtValor.setBounds(100, 235, 150, 30);
        add(txtValor);
        
        labCt = new JLabel("Cantidad: ");
        labCt.setBounds(20, 277, 80, 25);
        add(labCt);
        txtCantidad = new JTextField();
        txtCantidad.setEditable(false);
        txtCantidad.setHorizontalAlignment(JTextField.CENTER);
        txtCantidad.setForeground(Color.BLUE.darker());
        txtCantidad.setBounds(100, 275, 150, 30);
        add(txtCantidad);
        
        ImageIcon temp = new ImageIcon(getClass().getResource("/Imagenes/DefaultImage.png"));
        ImageIcon icon = new ImageIcon(temp.getImage().getScaledInstance(285, 195, Image.SCALE_DEFAULT));
        
        labImagen = new JLabel(icon);
        imagen = new JPanel();
        imagen.setBounds(260, 27, 285, 190);
        imagen.setLayout(new GridLayout(1, 1));
        imagen.add(labImagen);
        imagen.setBorder(BorderFactory.createTitledBorder(""));
        add(imagen);
        
        butComprarV = new JButton("Comprar Vehículo Seleccionado");
        butComprarV.addActionListener(al);
        butComprarV.setBackground(new Color(30, 132, 73));
        butComprarV.setForeground(Color.WHITE);
        butComprarV.setBounds(260, 275, 285, 30);
        add(butComprarV);
        
        addImagen = new JButton("Agregar Imagen");
        addImagen.addActionListener(al);
        addImagen.setBounds(260, 220, 285, 25);
        add(addImagen);
        
        bef = new JButton(" << ");
        bef.setBounds(260, 245, 142, 25);
        bef.addActionListener(al);
        bef.setEnabled(false);
        add(bef);
        
        next = new JButton(" >> ");
        next.setBounds(403, 245, 142, 25);
        next.addActionListener(al);
        next.setEnabled(false);
        add(next);
        
        lcImagenes = new ListaCircularImagenes();
    }
    
    /**
     * actualiza cuadros de detalles dado el vehículo que se elija
     * @param v Vehículo del cual se desea tener los detalles
     */
    public void actualizar(Vehiculo v)
    {
        txtModelo.setText(v.getModelo());
        txtMarca.setText(v.getMarca());
        txtAño.setText(v.getAño());
        txtCilindraje.setText(v.getCilindraje());
        txtEjes.setText(String.valueOf(v.getEjes()));
        txtValor.setText("$ "+new DecimalFormat("##,###.###").format(v.getPrecio()));
        txtCantidad.setText(String.valueOf(v.getCantidad()));
        ImageIcon icon = new ImageIcon(v.getImageDefault().getImage().getScaledInstance(285, 195, Image.SCALE_DEFAULT));
        labImagen.setIcon(icon);
        
        switch (v.getTipo())
        {
            case Vehiculo.AUTOMOVIL: txtTipo.setText("Automóvil");
            break;
            
            case Vehiculo.MOTOCICLETA:txtTipo.setText("Motocicleta");
            break;
            
            default:txtTipo.setText("Camión");
            break;
        }
        
        lcImagenes = new ListaCircularImagenes();
        
        if(v.imagenes.size()>1)
        {
            newNextAndBefore(true);
            v.imagenes.stream().forEach((i) -> {
                lcImagenes.agregar(i);
            });
        }
        else
        {
            newNextAndBefore(false);
        }
    }
    
    /**
     * Vuelve a poner los valores por defecto en los cuadros de detalles
     */
    public void defaultValue()
    {
        txtModelo.setText("");
        txtMarca.setText("");
        txtAño.setText("");
        txtTipo.setText("");
        txtCilindraje.setText("");
        txtEjes.setText("");
        txtValor.setText("");
        txtCantidad.setText("");
        ImageIcon temp = new ImageIcon(getClass().getResource("/Imagenes/DefaultImage.png"));
        ImageIcon icon = new ImageIcon(temp.getImage().getScaledInstance(285, 195, Image.SCALE_DEFAULT));
        labImagen.setIcon(icon);
    }
    
    public void newNextAndBefore(boolean enable)
    {
        next.setEnabled(enable);
        bef.setEnabled(enable);
    }
    
    public Vehiculo vehiculoMostrado()
    {
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        String año = txtAño.getText();
        Vehiculo vTemp = null;
        for(Vehiculo vh: main.vVehiculos.getLista())
        {
            if(vh.getMarca().equalsIgnoreCase(marca) && vh.getModelo().equalsIgnoreCase(modelo) && vh.getAño().equals(año))
            {
                vTemp = vh;
            }
        }
        
        return vTemp;
    }

    /**
     * Ejecuta una acción en el JFrame, dada una acción
     * @param ae Acciones ejecutadas en el JFrame
     */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == butComprarV)
        {
            if(main.vVehiculos.getLista().isEmpty() == false)
            {
                if(main.pCentral.spDetalles.txtMarca.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(main, "Seleccione un vehículo por favor","select empty",JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    DatosComprador data = new DatosComprador(main);
                    data.actualizarInfo(vehiculoMostrado());
                    data.setVisible(true);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(main, "No hay Vehículos Agregados", "Error en ejecución", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(ae.getSource() == bef)
        {
            ImageIcon temp = (ImageIcon) lcImagenes.anterior().dato;
            ImageIcon nIcon = new ImageIcon(temp.getImage().getScaledInstance(285, 195, Image.SCALE_DEFAULT));
            labImagen.setIcon(nIcon);
        }
        
        if(ae.getSource() == next)
        {
            ImageIcon temp = (ImageIcon) lcImagenes.siguiente().dato;
            ImageIcon nIcon = new ImageIcon(temp.getImage().getScaledInstance(285, 195, Image.SCALE_DEFAULT));
            labImagen.setIcon(nIcon);
        }
        
        if(ae.getSource() == addImagen)
        {
            if(!txtMarca.getText().isEmpty())
            {
                AgregarImagen ai = new AgregarImagen(vehiculoMostrado(), main);
                ai.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(main, "No Hay Vehículo Seleccionado Para Agregar Imagen", "agregar Imagen", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}