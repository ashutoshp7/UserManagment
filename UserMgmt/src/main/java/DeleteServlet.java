import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteurl")

public class DeleteServlet extends HttpServlet {

private final static String query = "delete from user where id = ?";
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	
    	PrintWriter pw=res.getWriter();
    	
    	res.setContentType("text/html");
    	pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
    	
    	int id=Integer.parseInt(req.getParameter("id"));
    	try {
    		
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	try(Connection con =DriverManager.getConnection("jdbc:mysql:///usermgmt","root","geeks");
    			PreparedStatement ps = con.prepareStatement(query);){
    		
    		ps.setInt(1, id);
    		int count =ps.executeUpdate();
    		
    		pw.println("<div class='card' style='margin:auto; width:300px;margin-top:100px'>");
    		if(count==1) {
    			pw.println("<h2 class='bg-danger text-light  text-center'>Record Deleted Successfully</h2>");
    		}
    		else {
    			pw.println("<h2 class='bg-danger text-light  text-center'>Record Not Deleted  </h2>");

    		}
    		
    	}catch (SQLException se) {
    		pw.println("<h2 class='bg-danger text-light  text-center'>"+ se.getMessage()+"</h2>");
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
    	pw.println("<a href='Home.html'><button class='btn btn-outline-success'>Home</button></a>");
    	pw.println("&nbsp &nbsp");
    	pw.println("<a href='showdata'><button class='btn btn-outline-success'>Show User</button></a>");
    	
    	pw.println("</div>");
    	pw.close();
    	
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	doGet(req,res);
    }
}
