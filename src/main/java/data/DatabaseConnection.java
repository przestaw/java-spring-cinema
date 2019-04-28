package data;

import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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
	    	  System.out.println("==:  Problem on HSQLDB server side [propably not running] :==\n"
	    	  		+ e.getMessage());
	    	  e.printStackTrace();
	      }
	}
    
    public void addReservation(){
    	//TODO
    }
    
    public void addMovie(){
    	//TODO
    }
    
    public void addScreening(){
    	//TODO
    }
    
    public Screening getScreening(int screeningId) throws SQLException{
    	PreparedStatement stmt = conn.prepareStatement("SELECT s.id, m.title, s.timestamp, s.room_id FROM screening s INNER JOIN movie m ON s.movie_id = m.id WHERE s.id = ?;");
    	stmt.setInt(1, screeningId);
    	ResultSet rset = stmt.executeQuery();
    	if(rset.next()) {
    		return new Screening(rset.getInt(1), rset.getString(2), rset.getInt(4), rset.getTimestamp(3).toLocalDateTime());
    	}else {
    		return new Screening(-1, "not a screening", -1, null);
    	}
    }
    
    public Movie getMovie(int movieId) throws SQLException{
    	PreparedStatement stmt = conn.prepareStatement("SELECT id, title FROM movie WHERE id = ?;");
    	stmt.setInt(1, movieId);
    	ResultSet rset = stmt.executeQuery();
    	if(rset.next()) {
    		return new Movie(rset.getInt(1), rset.getString(2));
    	}else {
    		return new Movie(-1, "not a movie");
    	}
    }
    
    public List<Screening> getScreenings() throws SQLException{
    	ArrayList<Screening> ret = new ArrayList<Screening>();
    	PreparedStatement stmt = conn.prepareStatement("SELECT s.id, m.title, s.timestamp, s.room_id FROM screening s INNER JOIN movie m ON s.movie_id = m.id;");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
        	ret.add(new Screening(rset.getInt(1), rset.getString(2), rset.getInt(4), rset.getTimestamp(3).toLocalDateTime()));
        }
    	return ret;
    }
    
    public List<Screening> getScreenings(TimePeriod time) throws SQLException{
    	ArrayList<Screening> ret = new ArrayList<Screening>();
    	PreparedStatement stmt = conn.prepareStatement("SELECT s.id, m.title, s.timestamp, s.room_id FROM screening s INNER JOIN movie m ON s.movie_id = m.id WHERE s.timestamp >= ? AND s.timestamp <= ?;");
    	stmt.setTimestamp(1, Timestamp.valueOf(time.getBegin()));
    	stmt.setTimestamp(2, Timestamp.valueOf(time.getEnd()));
    	ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
        	ret.add(new Screening(rset.getInt(1), rset.getString(2), rset.getInt(4), rset.getTimestamp(3).toLocalDateTime()));
        }
    	return ret;
    }
    
    public List<SeatPlace> getOccupiedPlaces(int screeningId) throws SQLException{
    	ArrayList<SeatPlace> ret = new ArrayList<SeatPlace>();
    	PreparedStatement stmt = conn.prepareStatement("SELECT p.row, p.column FROM PLACE p INNER JOIN reservation r ON p.res_id = r.id WHERE r.screen_id = ?;");
    	stmt.setInt(1, screeningId);
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
        	ret.add(new SeatPlace(rset.getInt(1), rset.getInt(2)));
        }
    	return ret; 
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
    
    public List<TicketPrice> getPrices() throws SQLException{
    	ArrayList<TicketPrice> ret = new ArrayList<TicketPrice>();
    	PreparedStatement stmt = conn.prepareStatement("SELECT id, price, name FROM prices;");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
        	ret.add(new TicketPrice(rset.getInt(1), rset.getDouble(2), rset.getString(3)));
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
