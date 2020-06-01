

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class proctrep
 */
@WebServlet("/proctrep")
public class proctrep extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public proctrep() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String pid=request.getParameter("pid");
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("<html>");
		out.println(" <link href=\"bit logo.jpg\" rel=\"shortcut icon\" type=\"image/vnd.microsoft.icon\" />\r\n" ); 
				
    	out.println("<body>\r");
    	out.println("<table><tr><td>\r\n"+
    		 "<img src=\"bit logo.jpg\" alt=\"bit-logo\"></td>\r\n" + 
    			"                    <td><h1><font size: 36px; color:blue;>Bangalore Institute <br>of Technology</font></h1></td></tr>\r\n" + 
    			"        </table>\r\n" );
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");
			Statement stmt=conn.createStatement();
			out.println("<br>\n\n\n");
			out.println("<CENTER>\n\n\n");
			out.println("<table align=left>");
			out.println("<tr><td><h4>Proctor Name:</h4></td><td><h4>"+name+"</h4></td></tr>");
			out.println("<tr><td><h4>Proctor id:</h4></td><td><h4>"+pid+"</h4></td></tr>");
			out.println("</table>\r\n");
			ResultSet rs=stmt.executeQuery("select * from admin_student where pid='"+pid+"'");
			
			out.println("<table border=1 width=20% height=20%");
			out.println("<tr><th>NAME</th><th>USN</th><th>SEMESTER</th></tr>");
			while(rs.next()) {
				String na=rs.getString("name");
				String us=rs.getString("usn");
				String se=rs.getString("sem");
				out.println("<tr><td>"+na+"</td><td>"+us+"</td><td>"+se+"</td></tr>");
				}
			out.println("</table>");
			out.println("</html></body>");
			conn.close();
		
	}catch(Exception e)
	{
		e.printStackTrace();
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
