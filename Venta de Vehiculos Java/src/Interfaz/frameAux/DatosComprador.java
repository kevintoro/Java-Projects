package Interfaz.frameAux;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import Interfaz.*;
import java.awt.event.*;
import main.*;

public class DatosComprador extends JFrame implements ActionListener, KeyListener
{
    JTextField txtModelo, txtMarca, txtTipo, txtCilindraje, txtAnio, txtNombre, txtCedula;
    JPanel comprador, vh;
    JButton butAceptar, butCancelar;
    Main main;
    public DatosComprador(Main main)
    {
        this.main = main;
        
        setSize(410, 360);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(main);
        setLayout(null);
        setResizable(false);
        
        vh = new JPanel();
        vh.setLayout(new GridLayout(5, 2));
        TitledBorder border = BorderFactory.createTitledBorder("Datos de Vehículo");
        border.setTitleColor(Color.BLUE);
        vh.setBorder(border);
        
        vh.add(new JLabel("Modelo"));
        txtModelo = new JTextField("");
        txtModelo.setEditable(false);
        txtModelo.setHorizontalAlignment(JTextField.CENTER);
        vh.add(txtModelo);
        
        vh.add(new JLabel("Marca"));
        txtMarca = new JTextField(""); 
        txtMarca.setEditable(false);
        txtMarca.setHorizontalAlignment(JTextField.CENTER);
        vh.add(txtMarca);
        
        vh.add(new JLabel("Tipo"));
        txtTipo = new JTextField("");
        txtTipo.setEditable(false);
        txtTipo.setHorizontalAlignment(JTextField.CENTER);
        vh.add(txtTipo);
        
        vh.add(new JLabel("Cilindraje"));
        txtCilindraje = new JTextField("");
        txtCilindraje.setEditable(false);
        txtCilindraje.setHorizontalAlignment(JTextField.CENTER);
        vh.add(txtCilindraje);
        
        vh.add(new JLabel("Año"));
        txtAnio = new JTextField(""); 
        txtAnio.setEditable(false);
        txtAnio.setHorizontalAlignment(JTextField.CENTER);
        vh.add(txtAnio);
        
        comprador = new JPanel();
        comprador.setLayout(new GridLayout(3, 2));
        TitledBorder nBorder = BorderFactory.createTitledBorder("Datos de Comprador");
        nBorder.setTitleColor(Color.BLUE);
        comprador.setBorder(nBorder);
        
        comprador.add(new JLabel("Nombre"));
        txtNombre = new JTextField("");
        txtNombre.addKeyListener(this);
        comprador.add(txtNombre);
        
        comprador.add(new JLabel("Cédula"));
        txtCedula = new JTextField("");
        txtCedula.addKeyListener(this);
        comprador.add(txtCedula);
        
        butAceptar = new JButton("Aceptar");
        butAceptar.addActionListener(this);
        comprador.add(butAceptar);
        
        butCancelar = new JButton("Cancelar");
        butCancelar.addActionListener(this);
        comprador.add(butCancelar);
        
        vh.setBounds(2, 0, 390, 200);
        comprador.setBounds(2, 210, 390, 110);
        
        add(vh);
        add(comprador);
    }
    
    public void actualizarInfo(Vehiculo v)
    {
        txtModelo.setText(v.getModelo());
        txtMarca.setText(v.getMarca());
        txtCilindraje.setText(v.getCilindraje());
        txtAnio.setText(v.getAño());
        
        String tipe;
        
        switch(v.getTipo())
        {
            case Vehiculo.MOTOCICLETA: tipe = "Motocicleta";
            break;
            
            case Vehiculo.CAMION: tipe = "Camión";
            break;
            
            default: tipe = "Automóvil";
            break;
        }
        
        txtTipo.setText(tipe);
    }

    
    

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == butCancelar)
        {
            this.dispose();
        }
        
        if(ae.getSource() == butAceptar)
        {
            if(txtCedula.getText().isEmpty() || txtNombre.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Llene todos los campos de texto", "error en venta", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                int confirm = JOptionPane.showConfirmDialog(main, "Datos de Comprador: \n"
                        + "Nombre: "+txtNombre.getText()+"\n"
                        + "Cédula: "+txtCedula.getText()+"\n"
                        + "¿es ésto correcto?","confirmación",JOptionPane.YES_NO_OPTION);
                
                if(confirm == JOptionPane.YES_OPTION)
                {
                    this.dispose();
                    Vehiculo temp = main.pCentral.spDetalles.vehiculoMostrado();
                    main.pCentral.spListado.actualizar(main.getVentaVehiculo());
                    GuardarPdf gp = new GuardarPdf(temp, main, txtNombre.getText(), txtCedula.getText());
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        if(e.getSource() == txtNombre)
        {
            char c = e.getKeyChar();
            if(Character.isDigit(c))
            {
                getToolkit().beep();
                e.consume();
            }
        }
        
        if(e.getSource() == txtCedula)
        {
            char c = e.getKeyChar();
            if(Character.isLetter(c))
            {
                getToolkit().beep();
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        
    }
}