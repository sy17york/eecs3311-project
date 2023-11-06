package nutrifit;

import java.sql.Statement;

import java.sql.Connection;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;  
import java.sql.Date;

public class UserProfile extends Datebase{
	//static final String usertable = "SELECT id, name, age , DOB , height, weight,sex FROM user_profile";
	/*private String name;
    private int age;
    private Date DOB;
    
    private double height;
    private double weight;
    private String sex;
    */

        /**
         * Create a new table in the test database
         *
         */
    	@Override
    	public void createNewTable() {
    		try(Connection conn = super.connect();
    				Statement stmt = conn.createStatement()
    		      ) {		      
    		          String sql = "CREATE TABLE IF NOT EXISTS user_profile " +
    		        		   "(id INTEGER not NULL, " +
    		                   " name VARCHAR(255), " +
    		                   " age INTEGER, " + 
    		                   " DOB VARCHAR(255), " + 
    		                   " height double, " + 
    		                   " weight double, " + 
    		                   " sex VARCHAR(255), " + 
    		                   " PRIMARY KEY ( id ))"; 

    		         stmt.executeUpdate(sql);
    		         System.out.println("Created table in given database...");
    		         stmt.close();
    		         conn.close();
    		      } catch (SQLException e) {
    		         e.printStackTrace();
    		      } 
    	}

    	
		public void insertTable(User user) {
			String sql = "INSERT INTO user_profile(id,name, age, DOB, height, weight, sex) VALUES(?,?,?,?,?,?,?)";
    		try(Connection conn = super.connect();
    				PreparedStatement pstmt = conn.prepareStatement(sql)
    			){
    		     		      
    		         // Execute a query
    		         System.out.println("Inserting records into the table...");
    		        // INSERT INTO t(dob) VALUES(TO_DATE('17/12/2015', 'DD/MM/YYYY'));
    		         pstmt.setInt(1,1);
    		         pstmt.setString(2, user.getName());
    		         pstmt.setInt(3, user.getAge());
    		         pstmt.setString(4, user.getDOB());
    		         pstmt.setDouble(5, user.getHeight());
    		         pstmt.setDouble(6, user.getWeight());
    		         pstmt.setString(7, user.getSex());
    		        
    		         pstmt.executeUpdate();
    		       
    		         System.out.println("Inserted records into the table...");
    		         pstmt.close();
    		         conn.close();
    		      } catch (SQLException e) {
    		         e.printStackTrace();
    		      } 
		}
			
		
        


    	@Override
    	public User readTable() {
			 User user = new User();
			 String sql = "SELECT name, age , DOB , height, weight,sex FROM user_profile WHERE id = 1";
			 try(Connection conn = super.connect();
			         Statement stmt = conn.createStatement();
			         ResultSet rs = stmt.executeQuery(sql);
			      ) {		      
			         while(rs.next()){
			            //set user values
			        	// rs.getInt("id");
			        	 user.setName(rs.getString("name"));
			        	 user.setAge(rs.getInt("age"));
			        	 user.setDOB(rs.getString("DOB"));
			        	 user.setHeight(rs.getDouble("height"));
			        	 user.setWeight(rs.getDouble("weight"));
			        	 user.setSex(rs.getString("sex"));
			          
			         }
			         stmt.close();
			         conn.close();
			      } catch (SQLException e) {
			         e.printStackTrace();
			      }
			return user; 
		}
			
		


		
		public void editTable(User user) {
			User user1 = readTable();
			String sql = "UPDATE user_profile SET name=?, age=?, DOB=?, height=?, weight=?, sex=? where id =?";
    		try(Connection conn = super.connect();
    				PreparedStatement pstmt = conn.prepareStatement(sql)
    			){
    		     		      
    		         // Execute a query
    		         System.out.println("updating records into the table...");
    		        // INSERT INTO t(dob) VALUES(TO_DATE('17/12/2015', 'DD/MM/YYYY'));
    		        /* if(user.getName()!=null) {pstmt.setString(1, user.getName());}else {pstmt.setString(1, user1.getName());}
    		         if(user.getAge()!=0) {pstmt.setInt(2, user.getAge());}else {pstmt.setInt(2, user1.getAge());}
    		         if(user.getDOB()!=null) {pstmt.setString(3, user.getDOB());}else {pstmt.setString(3, user1.getDOB());}
    		         if(user.getHeight()!=0.0) {pstmt.setDouble(4, user.getHeight());}else {pstmt.setDouble(4, user1.getHeight());}
    		         if(user.getWeight()!=0.0) {pstmt.setDouble(5, user.getWeight());}else {pstmt.setDouble(5, user1.getWeight());}
    		         if(user.getSex()!=null) {pstmt.setString(6, user.getSex());}else {pstmt.setString(6, user1.getSex());}
    		        */
    		         pstmt.setString(1, user.getName());
    		         pstmt.setInt(2, user.getAge());
    		         pstmt.setString(3, user.getDOB());
    		         pstmt.setDouble(4, user.getHeight());
    		         pstmt.setDouble(5, user.getWeight());
    		         pstmt.setString(6, user.getSex());
    		         pstmt.setInt(7, 1);
    		         
    		         pstmt.executeUpdate();

    		         System.out.println("updated records into the table..."); 
    		         pstmt.close();
    		         conn.close();
    		      } catch (SQLException e) {
    		         e.printStackTrace();
    		      } 

		}
		
		
		

		@Override
		public void removeTable() {
			try(Connection conn = super.connect();
			         Statement stmt = conn.createStatement();
			      ) {		      
			         String sql = "DROP TABLE user_profile";
			         stmt.executeUpdate(sql);
			         System.out.println("Table deleted in given database..."); 
			         stmt.close();
			         conn.close();
			      } catch (SQLException e) {
			         e.printStackTrace();
			      } 
			   }

	
		

}	
