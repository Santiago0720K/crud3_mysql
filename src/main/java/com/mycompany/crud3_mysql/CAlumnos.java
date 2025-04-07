/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud3_mysql;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author SENA
 */
public class CAlumnos {
    
     int codigo;
    String nombreAlumno;
    String apellidoAlumno;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApellidoAlumno() {
        return apellidoAlumno;
    }

    public void setApellidoAlumno(String apellidoAlumno) {
        this.apellidoAlumno = apellidoAlumno;
    }
 public void insertarAlumno(JTextField paramNombres, JTextField paramApellidos){
     
     setNombreAlumno(paramNombres.getText());
     setApellidoAlumno(paramApellidos.getText());
     
     CConexion objetoCConexion = new CConexion();
     
     String consulta = "insert into Alumnos (nombres, apellidos) values (?,?)";
     try {
         CallableStatement cs = objetoCConexion.establecerConexion().prepareCall(consulta);
         
            cs.setString(1, getNombreAlumno());
            cs.setString(2, getApellidoAlumno());

            cs.execute();
            
            JOptionPane.showMessageDialog(null, "se inserto correctamente el alumno. ");
         
     } catch (HeadlessException | SQLException e) {
         
         JOptionPane.showMessageDialog(null,"No se inserto correctamente el alumno.Error: "+e.toString());
     }
     }
 
 public void MostrarAlumno(JTable paramTablaTotalAlumnos){
        
        CConexion objetoConexion = new CConexion(); 

        DefaultTableModel  modelo = new DefaultTableModel();
        
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<>(modelo);
        paramTablaTotalAlumnos.setRowSorter(OrdenarTabla);
        
        String sql="";
        
        modelo.addColumn("ID");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        paramTablaTotalAlumnos.setModel(modelo);
        
        
        sql= "select*from Alumnos";

        String[] datos = new String[3];
        Statement st;

        try {
        
             st = objetoConexion.establecerConexion().createStatement(); 
        
             ResultSet rs = st.executeQuery(sql); 
             
             while (rs.next()){
             datos[0]=rs.getString(1);
             datos[1]=rs.getString(2);
             datos[2]=rs .getString(3);
             
             modelo.addRow(datos);
             
             }
             
             paramTablaTotalAlumnos.setModel(modelo);
            
     } catch (SQLException e) {
         
         JOptionPane.showMessageDialog(null, "no se pudo mostar los registros error:"+ e.toString());
     }
 }
      
    public void seleccionarAlumno(JTable paramTableAlumnos, JTextField paramId, JTextField paramNombres, JTextField paramApellidos){
   
        try {
       
       int fila =paramTableAlumnos.getSelectedRow();
       
       if (fila>=0) {
           paramId.setText((String) (paramTableAlumnos.getValueAt(fila,0)));
           paramNombres.setText((String) (paramTableAlumnos.getValueAt(fila,1)));
           paramApellidos.setText((String) (paramTableAlumnos.getValueAt(fila,2)));
                   }
       else{
       JOptionPane.showMessageDialog(null, "fila no seleccionada");
       }
       
               } catch (Exception e) {
                     JOptionPane.showConfirmDialog(null, "Error : " + e.toString());
    }
 }
}
