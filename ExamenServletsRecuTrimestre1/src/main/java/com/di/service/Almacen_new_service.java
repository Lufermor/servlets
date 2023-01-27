package com.di.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.di.entities.AlmacenNew;

/*
 * Esta clae maneja la conexión con la base de datos 
 * para gestionar la tabla ALMACEN_NEW 
 */
public class Almacen_new_service {

	/*
	 * Pre: ---
	 * Post: Establece la conexión con la base de datos 
	 */
	public static Connection getConnection (){
        Connection con = null;
        try{
            Class.forName ("com.mysql.cj.jdbc.Driver");
            con =DriverManager.getConnection ("jdbc:mysql://localhost:3306/almacenes","root", "root");;
        } 
        catch (Exception e){
            System.out.println (e);
        }
        return con;
    }
	
	/*
	 * Pre: ---
	 * Post: Guarda un almacen que se le pasa como parámetro en la BBDD
	 */
	public static int save(AlmacenNew almacenNew){
        int status = 0;
        try{
            Connection con = getConnection();
            PreparedStatement ps =con.prepareStatement ("insert into ALMACEN_NEW(nombre) values (?)");
            ps.setString (1, almacenNew.getNombre());
            status = ps.executeUpdate();
            con.close();
        } 
        catch (Exception ex){
            ex.printStackTrace ();
        }
        return status;
    }
    
	/*
	 * Pre: ---
	 * Post: modifica en la BBDD el almacen donde la id coincide con la del 
	 * objeto que se le pasa
	 */
    public static int update (AlmacenNew almacenNew){
        int status = 0;
        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement ("update ALMACEN_NEW set nombre=? where id=?");
            ps.setString (1, almacenNew.getNombre());
            ps.setInt (3, almacenNew.getId());
            status = ps.executeUpdate();
            con.close ();
        } 
        catch (Exception ex){
            ex.printStackTrace ();
        }
        return status;
    }
    
    /*
     * Pre: ---
     * Post: Elimina de la BBDD el almacen con la id que se le pasa
     */
    public static int delete (int id){
        int status = 0;
        try{
            Connection con = getConnection ();
            PreparedStatement ps =con.prepareStatement ("delete from ALMACEN_NEW where id=?");
            ps.setInt (1, id);
            status = ps.executeUpdate ();
            con.close ();
        } 
        catch (Exception e){
            e.printStackTrace ();
        }
        return status;
    }
    
    /*
     * Pre: ---
     * Post: obtiene y devuelve de la BBDD el almacen 
     * que se corresponde con la id que se le pasa
     */
	public static AlmacenNew getAlmacenNewById(int id) {
		AlmacenNew almacenNew = new AlmacenNew();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from ALMACEN_NEW where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				almacenNew.setId(rs.getInt(1));
				almacenNew.setNombre(rs.getString(2));
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return almacenNew;
	}
    
	/*
	 * Pre: ---
	 * Post: Obtiene y devuelve una lista con todos los almacenes old en la BBDD
	 */
	public static List<AlmacenNew> getAllAlmacenNew() {
		List<AlmacenNew> list = new ArrayList<AlmacenNew>();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from ALMACEN_NEW");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AlmacenNew almacenNew = new AlmacenNew();
				almacenNew.setId(rs.getInt(1));
				almacenNew.setNombre(rs.getString(2));

				list.add(almacenNew);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
