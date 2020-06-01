

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
 * Servlet implementation class adminaddteacher
 */
@WebServlet("/adminaddteacher")
public class adminaddteacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminaddteacher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String tid = request.getParameter("tid");
		String password = request.getParameter("password");
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");
            System.out.println(name+tid+password);
            PreparedStatement ps = con.prepareStatement("insert into admin_teacher values(?,?,?)");
 
            ps.setString(1, name);
            ps.setString(2, tid);
            ps.setString(3, password);
            System.out.println(name+tid+password);
            int i = ps.executeUpdate();
            if (i > 0){
              
            	response.sendRedirect("afteraddingteachersheet.html");
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
