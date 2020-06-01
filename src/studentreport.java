

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class studentreport
 */
@WebServlet("/studentreport")
public class studentreport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentreport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String []sub=new String[6];
		String name=request.getParameter("name");
		String usn=request.getParameter("usn");
		String sem=request.getParameter("sem");
		String scheme=request.getParameter("scheme");
		String roll=request.getParameter("roll");
		int total=50;
		int seme=Integer.parseInt(sem);
		System.out.println(sem+seme);
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("<html>");
		out.println(" <link href=\"bit logo.jpg\" rel=\"shortcut icon\" type=\"image/vnd.microsoft.icon\" />\r\n" ); 
		out.println("<head></head><style>\r\n" + 
				"        .row {\r\n" + 
				"            margin-top: 20px;\r\n" + 
				"            margin-left: 20px;\r\n" + 
				"            \r\n" + 
				"        }\r\n" +
				" body {"+
		            " margin: \"20px 10px 20px 20px\""+
		        "}"+
				"    ");
    	out.println("</style><body>\r");
    	out.println("<table><tr><td>"+
        "    <img src=\"bit logo.jpg\" alt=\"bit-logo\"></td><td>"+"<h1>Bangalore Institute <br>of Technology</h1></td></tr>"+
        "</table>");    
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
		
				Statement stmt=conn.createStatement();
				ResultSet rs;
				switch(seme) {
				case 3: sub[0]= "CS32";
						  sub[1]="CS33";
						  sub[2]="CS34";
						  sub[3]="CS35";
						  break;
				case 4: sub[0]="CS42";
						  sub[1]="CS43";
						  sub[2]="CS44";
						  sub[3]="CS45";
						  sub[4]="CS46";
						  break;
				case 5: sub[0]="CS51";
						  sub[1]="CS52";
						  sub[2]="CS53";
						  sub[3]="CS54";
						  sub[4]="CS553";
						  sub[5]="CS562";
						  break;
				case 6: sub[0]="CS61";
						  sub[0]="CS52";
						  sub[0]="CS63";
						  sub[0]="CS64";
						  sub[0]="CS652";
						  sub[0]="CS664";
						  break;
				case 7: sub[0]="CS71";
						  sub[1]="CS72";
						  sub[2]="CS73";
						  sub[3]="CS744";
						  sub[4]="CS753";
						  break;
				case 8: sub[0]="CS81";
						  sub[1]="CS82";
						  sub[2]="CS833";
						  break;
				}
				String query="DELIMITER//"
						+"CREATE PROCEDURE"
						+"get_all_student_status("
						+"IN usn varchar2(15)"
						+"BEGIN"
						+"SELECT * FROM 'student'"
						+"WHERE student.usn=usn"
						+"END//"
						+"DELIMITER";
				String sql[]=new String[6];
				int ar[]=new int[6];
				out.println("<br>\n\n\n");
				out.println("<CENTER>\n\n\n");
				out.println("<table align=left>");
				out.println("<tr><td><h4>Name:</h4></td><td><h4>"+name+"</h4></td></tr>");
				out.println("<tr><td><h4>USN:</h4></td><td><h4>"+usn+"</h4></td></tr>");
				out.println("<tr><td><h4>Scheme:</h4></td><td><h4>"+scheme+"</h4></td></tr>");
				out.println("<tr><td><h4>Sem:</h4></td><td><h4>"+seme+"</h4></td></tr>");
				out.println("</table>\r\n");
				out.println("<table border=1 width=50% height=50%>");
				out.println("<tr><th>Subject Id</th><th>Percentage</th></tr>");
				//int va[]= {92,90,86,94,84,82};
				 for (int i = 0; i <6; i++)
			     {
			             //Retreive service Name to put in Prof_strig.
					 sql[i]= "select COUNT(status)as percent from tea_att where roll_no='"+roll+"'and status='present' and subject_code='"+sub[i]+"' and scheme='"+scheme+"'";
			            
			             rs=stmt.executeQuery(sql[i]);
			            while(rs.next()) {
			            	
			            	ar[i]=rs.getInt("percent");
			            			int val=ar[i]*2;
			            			System.out.println(ar[i]+val);
			                 out.println(" <tr><td>"+sub[i]+"</td><td>"+ar[i]+"</td></tr>");
			      
			                  }
			     }
			           
			       
					out.println("</table>\r\n"+
							"</CENTER></body>\r\n" + 
							
							"</html>");
					String call="CALL"
							+"get_all_student_status(usn)";
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
