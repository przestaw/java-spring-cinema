package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DatabaseConnection {
	private Connection conn;
	
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
    
    public int addReservation(Reservation newReservation, double total){
    	try {
    		conn.setAutoCommit(false);
        	try {
        		PreparedStatement stmt = conn.prepareStatement("SELECT MAX(id) FROM reservation;");
            	ResultSet rset2 = stmt.executeQuery();
            	rset2.next();
            	PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO reservation (id, screen_id, total) values (?, ?, ?)");
            	stmt2.setInt(1, rset2.getInt(1) + 1);
            	stmt2.setInt(2, newReservation.getScreeningId());
            	stmt2.setDouble(3, total);
            	stmt2.executeUpdate();
            	
            	PreparedStatement stmt3 = conn.prepareStatement("UPDATE place (RES_ID, TYPE) values (?, ?) WHERE row = ?, column = ?");
        	    stmt3.setInt(1, rset2.getInt(1) + 1);
            	
            	for(ResPlace iter : newReservation.getPlaces()) {
            		stmt3.setInt(2, iter.getType());
            		stmt3.setInt(3, iter.getColumn());
            		stmt3.setInt(4, iter.getRow());
            		stmt3.executeUpdate();		
            	}
            	conn.commit();
            	return (rset2.getInt(1) + 1);
        	}catch(SQLException e){
        		conn.rollback();
        		return -1; //no such movie//null; //other error
            }finally{
            	conn.setAutoCommit(true);
            }
    	}catch(SQLException e){
    		e.printStackTrace();
    		return -1;//failed to create Screening
    	}     
    }
    
    public void addMovie(String Title){
    	//TODO
    }
    
    public ListScreen addScreening(Screening newScreening){
    	try {
    		conn.setAutoCommit(false);
        	try {
        		PreparedStatement stmt = conn.prepareStatement("SELECT id FROM movie WHERE title = ?;");
            	stmt.setString(1, newScreening.getMovieTitle());
            	ResultSet rset1 = stmt.executeQuery();
            	if(!rset1.next())
            		return new ListScreen(0,"NOPE",-1, null); //no such movie
            	
            	PreparedStatement stmt4 = conn.prepareStatement("SELECT MAX(id) FROM screening;");
            	ResultSet rset2 = stmt4.executeQuery();
            	rset2.next();//?
            	PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO screening (id, movie_id, room_id, timestamp ) values (?, ?, ?, ?)");
            	stmt2.setInt(1, rset2.getInt(1) + 1);
            	stmt2.setInt(2, rset1.getInt(1));
            	stmt2.setInt(3, newScreening.getRoomId());
            	stmt2.setTimestamp(4, Timestamp.valueOf(newScreening.getDate()));
            	stmt2.executeUpdate();
            	
            	PreparedStatement stmt3 = conn.prepareStatement("INSERT INTO place (RES_ID, SCREENING_ID, ROW, COLUMN, TYPE ) values (?, ?, ?, ?, ?)");
        	    stmt3.setInt(1, -1);
            	
            	stmt3.setInt(2, rset2.getInt(1) + 1);
            	for(int i = 1; i < 7; ++i) {
            		stmt3.setInt(3, i);
        			for(int j = 1; j < 7; ++j) {
            			stmt3.setInt(4, j);
            			stmt3.setInt(5, 3);
            			stmt3.executeUpdate();
        			}
        		}
            	conn.commit();
            	return new ListScreen(rset2.getInt(1) + 1, newScreening.getMovieTitle(), newScreening.getRoomId(), newScreening.getDate());
        	}catch(SQLException e){
        		conn.rollback();
        		return new ListScreen(0,"ROLLBACK",-1, null); //no such movie//null; //other error
            }finally{
            	conn.setAutoCommit(true);
            }
    	}catch(SQLException e){
    		e.printStackTrace();
    		return null;//failed to create Screening
    	}     
    }
    
    public ListScreen getScreening(int screeningId){
    	try {
    		PreparedStatement stmt = conn.prepareStatement("SELECT s.id, m.title, s.timestamp, s.room_id FROM screening s "
    				+ "INNER JOIN movie m ON s.movie_id = m.id WHERE s.id = ?;");
        	stmt.setInt(1, screeningId);
        	ResultSet rset = stmt.executeQuery();
        	if(rset.next()) {
        		return new ListScreen(rset.getInt(1), rset.getString(2), rset.getInt(4), rset.getTimestamp(3).toLocalDateTime());
        	}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return new ListScreen(-1, "not a screening", -1, null);
    }
    
    public Movie getMovie(int movieId) {
    	try {
    		PreparedStatement stmt = conn.prepareStatement("SELECT id, title FROM movie WHERE id = ? ORDER BY title;");
        	stmt.setInt(1, movieId);
        	ResultSet rset = stmt.executeQuery();
        	if(rset.next()) {
        		return new Movie(rset.getInt(1), rset.getString(2));
        	}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return new Movie(-1, "not a movie");
    }
    
    public List<ListScreen> getScreenings() {
    	ArrayList<ListScreen> ret = new ArrayList<ListScreen>();
    	try {
        	PreparedStatement stmt = conn.prepareStatement("SELECT s.id, m.title, s.timestamp, s.room_id FROM screening s "
        			+ "INNER JOIN movie m ON s.movie_id = m.id ORDER BY s.timestamp, m.title ;");
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
            	ret.add(new ListScreen(rset.getInt(1), rset.getString(2), rset.getInt(4), rset.getTimestamp(3).toLocalDateTime()));
            }
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return ret;
    }
    
    public List<ListScreen> getScreenings(TimePeriod time) {
    	ArrayList<ListScreen> ret = new ArrayList<ListScreen>();
    	try {
        	PreparedStatement stmt = conn.prepareStatement("SELECT s.id, m.title, s.timestamp, s.room_id FROM screening s "
        			+ "INNER JOIN movie m ON s.movie_id = m.id WHERE s.timestamp >= ? AND s.timestamp <= ? "
        			+ "ORDER BY s.timestamp, m.title ;");
        	stmt.setTimestamp(1, Timestamp.valueOf(time.getBegin()));
        	stmt.setTimestamp(2, Timestamp.valueOf(time.getEnd()));
        	ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
            	ret.add(new ListScreen(rset.getInt(1), rset.getString(2), rset.getInt(4), rset.getTimestamp(3).toLocalDateTime()));
            }
        }catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return ret;
    }
    
    public List<SeatPlace> getPlaces(int screeningId) {
		ArrayList<SeatPlace> ret = new ArrayList<SeatPlace>();
    	try {
    		PreparedStatement stmt = conn.prepareStatement("select p.res_id, p.row, p.column from place p where p.screening_id = ? order by p.row, p.column;");
    		stmt.setInt(1, screeningId);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
            	ret.add(new SeatPlace(rset.getInt(2), rset.getInt(3), (rset.getInt(1) < 1)));
            }
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return ret;
	}
    
    public List<Movie> getMovies() {
    	ArrayList<Movie> ret = new ArrayList<Movie>();
    	try {
    		PreparedStatement stmt = conn.prepareStatement("SELECT id, title FROM MOVIE ORDER BY id;");
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
            	ret.add(new Movie(rset.getInt(1), rset.getString(2)));
            }
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return ret;
    }
    
    public List<TicketPrice> getPrices() {
    	ArrayList<TicketPrice> ret = new ArrayList<TicketPrice>();
    	try {
        	PreparedStatement stmt = conn.prepareStatement("SELECT id, price, name FROM prices ORDER BY id;");
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
            	ret.add(new TicketPrice(rset.getInt(1), rset.getDouble(2), rset.getString(3)));
            }
        }catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return ret;
    }
    
    public double getTotal(int reservationId) {
    	return -1.0;
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
