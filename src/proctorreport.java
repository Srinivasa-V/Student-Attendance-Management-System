

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
 * Servlet implementation class proctorreport
 */
@WebServlet("/proctorreport")
public class proctorreport extends HttpServlet {
	public static String na[];
	public static String se[];
	public static String us[];
	public static int s=1,j;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public proctorreport() {
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
				ResultSet rs1;
				out.println("\n\n\n");
				
				out.println("<table border=1 width=75% height=75%>");
				out.println("<tr><th>Student_Name</th><th>Semester</th><th>USN</th></tr>");
				String query1="select name as n from admin_student where pid='"+pid+"'";	
				String query2="select sem as s from admin_student where pid='"+pid+"'";	
				String query3="select usn as u from admin_student where pid='"+pid+"'";
				rs1=stmt.executeQuery(query1);
				while(rs1.next()) {
					na[s]=rs1.getString("n");
					s++;
					}
				rs1=stmt.executeQuery(query2);
				while(rs1.next()) {
					se[s]=rs1.getString("s");
					s++;
				}
				rs1=stmt.executeQuery(query3);
				
				while(rs1.next()) {
					us[s]=rs1.getString("u");
					s++;
					j=s;
				}
				for(int i=1;i<j;i++) {
				out.println(" <tr><td>"+na[i]+"</td><td>"+se[i]+"</td><td>"+us[i]+"</td></tr>");
				}
				out.println("</table>\r\n"+
						"</body>\r\n" + 
						
						"</html>");
				conn.close();
			}catch(Exception e) {
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
