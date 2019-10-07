package Interfaz.frameAux;

import javax.swing.*;
import main.*;
import Interfaz.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;

public class FrameAgregarVehiculo extends JFrame implements ActionListener, KeyListener
{

    public JTextField txtModelo, txtMarca, txtAnio, txtCilindrada, txtColor, txtEjes, txtValor, txtImagen;
    private final JComboBox<String> cbTipo, cbColor;
    private final JButton butAgregar, butExaminar;
    Main main;

    public FrameAgregarVehiculo(Main main)
    {
        this.main = main;
        
        setLayout(null);
        setSize(440, 330);
        setLocationRelativeTo(main);
        setLayout(new GridLayout(10, 2));
        setTitle("Agregar Vehículo");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        setVisible(true);

        add(new JLabel("Modelo"));
        txtModelo = new JTextField();
        add(txtModelo);

        add(new JLabel("Marca: "));
        txtMarca = new JTextField();
        add(txtMarca);

        txtImagen = new JTextField("Ruta de Imágen");
        txtImagen.setEditable(false);
        txtImagen.setHorizontalAlignment(JTextField.CENTER);
        txtImagen.setForeground(Color.BLUE);
        add(txtImagen);
        butExaminar = new JButton("Búsqueda");
        butExaminar.addActionListener(this);
        add(butExaminar);

        add(new JLabel("Tipo de Vehículo: "));
        cbTipo = new JComboBox<>();
        cbTipo.addItem("");
        cbTipo.addItem("Automóvil");
        cbTipo.addItem("Motocicleta");
        cbTipo.addItem("Camión");
        add(cbTipo);

        add(new JLabel("Año de Módelo: "));
        txtAnio = new JTextField();
        txtAnio.addKeyListener(this);
        add(txtAnio);

        add(new JLabel("Cilindrada: "));
        txtCilindrada = new JTextField();
        txtCilindrada.addKeyListener(this);
        add(txtCilindrada);

        //Modificado N
        add(new JLabel("Color: "));
        cbColor = new JComboBox<>();
        cbColor.removeAllItems();
        cbColor.addItem("");
        cbColor.addItem("Blanco");
        cbColor.addItem("Negro");
        cbColor.addItem("Gris");
        cbColor.addItem("Rojo");
        cbColor.addItem("Azul");
        cbColor.addItem("Verde");
        add(cbColor);
        
        add(new JLabel("Ejes: "));
        txtEjes = new JTextField();
        txtEjes.addKeyListener(this);
        add(txtEjes);
        
        add(new JLabel("Precio: "));
        txtValor = new JTextField();
        txtValor.addKeyListener(this);
        add(txtValor);

        butAgregar = new JButton("Aceptar");
        butAgregar.addActionListener(this);
        add(butAgregar);

        JButton temp = new JButton("Cancelar");
        temp.addActionListener((ActionEvent ae) -> {
            if (ae.getSource() == temp) {
                dispose();
            }
        });
        add(temp);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == butExaminar) {
            SelectorArchivos sa = new SelectorArchivos(this);
        }

        if (e.getSource() == butAgregar) {
            if (txtModelo.getText().isEmpty() || txtMarca.getText().isEmpty() || txtCilindrada.getText().isEmpty() || txtEjes.getText().isEmpty() || txtAnio.getText().isEmpty() || txtValor.getText().isEmpty() || cbColor.getSelectedIndex() == 0 || txtImagen.getText().equals("Ruta de Imágen")) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    Double precio = Double.parseDouble(txtValor.getText());
                    int ejes = Integer.parseInt(txtEjes.getText());
                    int año = Integer.parseInt(txtAnio.getText());
                    int cil = Integer.parseInt(txtCilindrada.getText());
                    Vehiculo v = new Vehiculo(txtMarca.getText().toUpperCase(), txtModelo.getText().toUpperCase(), txtAnio.getText(), precio, ejes, cbTipo.getSelectedIndex(), txtCilindrada.getText(), cbColor.getSelectedIndex(), txtImagen.getText());

                    main.getVentaVehiculo().agregarVehiculo(v,main);
                    main.pCentral.spListado.actualizar(main.getVentaVehiculo());
                    this.dispose();
                } catch (NumberFormatException er) {
                    JOptionPane.showMessageDialog(main, er.getMessage(), "Error al Agregar", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {
        if(ke.getSource() == txtAnio)
        {
            char c = ke.getKeyChar();
            
            if(Character.isLetter(c) || txtAnio.getText().length()>3)
            {
                getToolkit().beep();
                ke.consume();
            }
        }
        
        if(ke.getSource() == txtEjes)
        {
            char c = ke.getKeyChar();
            
            if(Character.isLetter(c) || txtEjes.getText().length()>= 1)
            {
                getToolkit().beep();
                ke.consume();
            }
            
            if(Character.isDigit(c) && txtEjes.getText().length() == 0)
            {
                int d = c;
                if(d>52 || d<50)
                {
                    getToolkit().beep();
                    ke.consume();
                }
            }
        }
        
        if(ke.getSource() == txtCilindrada)
        {
            char c = ke.getKeyChar();
            
            if(Character.isLetter(c) || txtCilindrada.getText().length()>4)
            {
                getToolkit().beep();
                ke.consume();
            }
        }
        
        if(ke.getSource() == txtValor)
        {
            char c = ke.getKeyChar();
            
            if(Character.isLetter(c))
            {
                getToolkit().beep();
                ke.consume();
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent ke)
    {
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
    }
}