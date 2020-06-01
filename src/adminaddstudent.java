

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class adminaddstudent
 */
@WebServlet("/adminaddstudent")
public class adminaddstudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminaddstudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String usn = request.getParameter("usn");
		String semester = request.getParameter("semester");
		String scheme = request.getParameter("scheme");
		String password = request.getParameter("pass");
		String proctorid = request.getParameter("proctor_id");
		System.out.println("hi");
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");
            System.out.println(name+semester+usn+scheme+password+proctorid);
            PreparedStatement ps = con.prepareStatement("insert into admin_student values(?,?,?,?,?,?)");
 
            ps.setString(1, name);
            ps.setString(2, semester);
            ps.setString(3, usn);
            ps.setString(4, scheme);
            ps.setString(5, password);
            ps.setString(6, proctorid);
           System.out.println(name+semester+usn+scheme+password+proctorid);
            int i = ps.executeUpdate();
            if (i > 0){
              
            	response.sendRedirect("afteraddingstudentsheet.html");
            }else
            {
            	System.out.println("invalid");
            }
        } catch (Exception e2) {
        	
            System.out.println(e2);
        }    
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
