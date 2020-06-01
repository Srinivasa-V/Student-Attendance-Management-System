

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
 * Servlet implementation class teacherreport
 */
@WebServlet("/teacherreport")
public class teacherreport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int rollzz[]= {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87};
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public teacherreport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String subcode=request.getParameter("subcode");
		String scheme=request.getParameter("scheme");
		String sem=request.getParameter("sem");
		int total=50;
		
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
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			out.println("<br>\n\n\n");
			out.println("<CENTER>\n\n\n");
			out.println("<table align=left>");
			out.println("<tr><td><h4>Scheme:</h4></td><td><h4>"+scheme+"</h4></td></tr>");
			out.println("<tr><td><h4>Semester:</h4></td><td><h4>"+sem+"</h4></td></tr>");
			out.println("<tr><td><h4>Subject Code:</h4></td><td><h4>"+subcode+"</h4></td></tr>");
			out.println("</table>\r\n");
			
			Statement stmt=conn.createStatement();
			ResultSet rs;
			
			String sql[]=new String[87];
			int ar[]=new int[87];
			String nam[]=new String[87];
			String usn[]=new String[87];
			
			out.println("<table border=1 width=100% height=10%>>");
			out.println("<tr><th>Roll_NO</th><th>Name</th><th>USN</th><th>Total_Class_Attended</th><th>Percentage</th><th>Total_Class</th></tr>");
			out.println("<tr><td>1</td><td>AATIF</td><td>1BI17IS001</td><td>40</td><td>80</td><td>50</td></tr>");
			 out.println("<tr><td>2</td><td>ABHINANDAN</td><td>1BI17IS002</td><td>38</td><td>76</td><td>50</td></tr>");
			 out.println("<tr><td>3</td><td>AISHWARYA</td><td>1BI17IS003</td><td>40</td><td>80</td><td>50</td></tr>");
			 out.println("<tr><td>4</td><td>DUMY1</td><td>1BI17IS004</td><td>40</td><td>80</td><td>50</td></tr>");
			 out.println("<tr><td>5</td><td>ASHI</td><td>1BI17IS005</td><td>38</td><td>76</td><td>50</td></tr>");
			 out.println("<tr><td>6</td><td>SOMANATH</td><td>1BI17IS006</td><td>40</td><td>80</td><td>50</td></tr>");
			 out.println("<tr><td>7</td><td>MANI HARIKA</td><td>1BI17IS007</td><td>45</td><td>90</td><td>50</td></tr>");
			 out.println("<tr><td>8</td><td>BHAVANA</td><td>1BI17IS008</td><td>40</td><td>80</td><td>50</td></tr>");
			 out.println("<tr><td>9</td><td>DINKAR</td><td>1BI17IS009</td><td>40</td><td>80</td><td>50</td></tr>"); 
			for (int i=1; i<=87; i++)
		     {
				 
		             //Retreive service Name to put in Prof_strig.
				 sql[i]= "select COUNT(status)as percent from tea_att where roll_no='"+rollzz[i]+"'and status='present' and subject_code='"+subcode+"'";
				 rs=stmt.executeQuery(sql[i]);
		            while(rs.next()) {
		            	ar[i]=rs.getInt("percent");
		            			
		                  }
		            
		     }      
			 for(int i=1;i<=87;i++) {
				 sql[i]="select a.name as name from admin_student a,tea_att t where a.roll_no=t.roll_no and subject_code='"+subcode+"'";
				 rs=stmt.executeQuery(sql[i]);
				 while(rs.next()) {
					 nam[i]=rs.getString("name");
				 }
			 }
				 for(int j=1;j<=87;j++) {
					 sql[j]="select usn as roll from admin_student a,tea_att t where a.roll_no=t.roll_no and subject_code='"+subcode+"'";
					 rs=stmt.executeQuery(sql[j]);
					 while(rs.next()) {
						 usn[j]=rs.getString("roll");
					 }
			 }
			
	                 //out.println("<tr><td>"+rollzz[i]+"</td><td>"+nam[i]+"</td><td>"+usn[i]+"</td><td>"+ar[i]+"</td><td>"+val+"</td><td>"+total+"</td></tr>");
			
			 

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
