

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
 * Servlet implementation class adminstudentadd
 */
@WebServlet("/adminstudentadd")
public class adminstudentadd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminstudentadd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String usn=request.getParameter("usn");
		String semester=request.getParameter("semester");
		String scheme=request.getParameter("scheme");
		String password=request.getParameter("password");
		String proctorid=request.getParameter("proctorid");
		String roll_no=request.getParameter("roll");
		long roll=Integer.parseInt(roll_no);
		System.out.println("hi");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");
			System.out.println(name+usn+semester+scheme+password+proctorid);
			PreparedStatement ps=conn.prepareStatement("insert into admin_student values(?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, semester);
			ps.setString(3, usn);
			ps.setString(4, scheme);
			ps.setString(5, password);
			ps.setString(6, proctorid);
			ps.setLong(7, roll);
			System.out.println(name+usn+semester+scheme+password+proctorid+roll);
			int i=ps.executeUpdate();
			if(i > 0) {
				response.sendRedirect("afteraddingstudentsheet.html");
			}
			else {
				System.out.println("error");
			}
		}catch(Exception e) {
			System.out.println(e);
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
