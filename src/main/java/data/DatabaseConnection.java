package data;

import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
	Connection conn;
	
	private static final String JDBC_URL = "jdbc:hsqldb:hsql://localhost/cinemaDB";
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
	    	  e.printStackTrace();
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
    
    public List<Screening> getScreenings(TimePeriod time){
    	//TODO + time
    	return new ArrayList<Screening>();
    }
    
    public List<Place> getOccupiedPlaces(long screeningId){
    	//TODO
    	return new ArrayList<Place>();
    }
    
    public List<Movie> getMovies() throws SQLException {
    	ArrayList<Movie> ret = new ArrayList<Movie>();
    	PreparedStatement stmt = conn.prepareStatement("SELECT id, title FROM MOVIE;");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
        	ret.add(new Movie(rset.getInt(1), rset.getString(2)));
        }
    	return ret;
    }
    
    public void exitSQL() {
    	try {
    		Statement statement = conn.createStatement();
        	statement.execute("SHUTDOWN;");
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    }
}
