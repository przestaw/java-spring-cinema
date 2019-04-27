package data;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	Connection conn;
	
	private static final String JDBC_URL = "jdbc:hsqldb:hsql://localhost/testdb";
    private static final String JDBC_USER = "SA";
    private static final String JDBC_PASS = "";
	
    public DatabaseConnection(){
		try {
	         //Registering the HSQLDB JDBC driver
	         Class.forName("org.hsqldb.jdbc.JDBCDriver");
	         //Creating the connection with HSQLDB
	         conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
	         if (conn!= null){
	            System.out.println("==: Connection to db created successfully :==\n");
	            
	         }else{
	            System.out.println("==:  Problem with creating db connection :==\n");
	         }
	      
	      }  catch (Exception e) {
	         //e.printStackTrace(System.out); 
	    	  System.out.println("==:  Problem on HSQLDB server side [propably not running] :==\n"
	    	  		+ e.getMessage());
	      }
	}
    
    public void addReservation() {
    	//TODO
    }
    
    public void addMovie() {
    	//TODO
    }
    
    public void addScreening() {
    	//TODO
    }
    
    public List<Screening> getScreenings(){
    	//TODO + time
    	return new ArrayList<Screening>();
    }
    
    public List<Place> getOccupiedPlaces(long screeningId){
    	//TODO
    	return new ArrayList<Place>();
    }
}
