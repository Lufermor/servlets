package com.di.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.di.entities.AlmacenOld;


/*
 * Esta clae maneja la conexión con la base de datos 
 * para gestionar la tabla ALMACEN_OLD 
 */
public class Almacen_old_service {

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
	public static int save(AlmacenOld almacenOld){
        int status = 0;
        try{
            Connection con = getConnection();
            PreparedStatement ps =con.prepareStatement ("insert into ALMACEN_OLD (nombre) values (?)");
            ps.setString (1, almacenOld.getNombre());
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
    public static int update (AlmacenOld almacenOld){
        int status = 0;
        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement ("update ALMACEN_OLD set nombre=? where id=?");
            ps.setString (1, almacenOld.getNombre());
            ps.setInt (3, almacenOld.getId());
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
            PreparedStatement ps =con.prepareStatement ("delete from ALMACEN_OLD where id=?");
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
	public static AlmacenOld getAlmacenOldById(int id) {
		AlmacenOld almacenOld = new AlmacenOld();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from ALMACEN_OLD where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				almacenOld.setId(rs.getInt(1));
				almacenOld.setNombre(rs.getString(2));
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return almacenOld;
	}
    
	/*
	 * Pre: ---
	 * Post: Obtiene y devuelve una lista con todos los almacenes old en la BBDD
	 */
	public static List<AlmacenOld> getAllAlmacenOld() {
		List<AlmacenOld> list = new ArrayList<AlmacenOld>();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from ALMACEN_OLD");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AlmacenOld almacenOld = new AlmacenOld();
				almacenOld.setId(rs.getInt(1));
				almacenOld.setNombre(rs.getString(2));

				list.add(almacenOld);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
