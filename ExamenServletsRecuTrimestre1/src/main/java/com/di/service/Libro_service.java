package com.di.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.di.entities.Libro;

public class Libro_service {

	/*
	 * Pre: ---
	 * Post: Establece la conexión con la base de datos 
	 */
	public static Connection getConnection (){
        Connection con = null;
        try{
            Class.forName ("com.mysql.jdbc.Driver");
            con =DriverManager.getConnection ("jdbc:mysql://localhost:3306/almacenes","root", "root");;
        } 
        catch (Exception e){
            System.out.println (e);
        }
        return con;
    }
	
	/*
	 * Pre: ---
	 * Post: Guarda un libro que se le pasa como parámetro en la BBDD
	 */
	public static int save(Libro libro){
        int status = 0;
        try{
            Connection con = getConnection();
            PreparedStatement ps =con.prepareStatement ("insert into "
            		+ "LIBRO(isbn,fechaEdicion,almacen_old,LIBRO) values ((?,?,?,?)");
            ps.setString (1, libro.getIsbn());
            ps.setDate (2, (Date) libro.getFechaEdicion());
            ps.setInt (3, libro.getAlmacenOld());
            ps.setInt (4, libro.getAlmacenNew());
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
	 * Post: modifica en la BBDD el libro donde la id coincide con la del 
	 * objeto que se le pasa
	 */
    public static int update (Libro libro){
        int status = 0;
        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement ("update LIBRO set isbn=?,fechaEdicion=?,almacen_old=?,LIBRO=? where id=?");
            ps.setString (1, libro.getIsbn());
            ps.setDate (2, (Date) libro.getFechaEdicion());
            ps.setInt (3, libro.getAlmacenOld());
            ps.setInt (4, libro.getAlmacenNew());
            ps.setInt (5, libro.getId());
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
     * Post: Elimina de la BBDD el libro con la id que se le pasa
     */
    public static int delete (int id){
        int status = 0;
        try{
            Connection con = getConnection ();
            PreparedStatement ps =con.prepareStatement ("delete from LIBRO where id=?");
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
     * Post: obtiene y devuelve de la BBDD el libro 
     * que se corresponde con la id que se le pasa
     */
	public static Libro getLibroById(int id) {
		Libro libro = new Libro();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from LIBRO where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				libro.setId(rs.getInt(1));
				libro.setIsbn(rs.getString(2));
				libro.setFechaEdicion(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(3)));
				libro.setAlmacenOld(rs.getInt("almacen_old"));
				libro.setAlmacenOld(rs.getInt("almacen_new"));
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return libro;
	}
    
	/*
	 * Pre: ---
	 * Post: Obtiene y devuelve una lista con todos los almacenes old en la BBDD
	 */
	public static List<Libro> getAllLibros() {
		List<Libro> list = new ArrayList<Libro>();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from LIBRO");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Libro libro = new Libro();
				libro.setId(rs.getInt(1));
				libro.setIsbn(rs.getString(2));
				libro.setFechaEdicion(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(3)));
				libro.setAlmacenOld(rs.getInt("almacen_old"));
				libro.setAlmacenOld(rs.getInt("almacen_new"));

				list.add(libro);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
