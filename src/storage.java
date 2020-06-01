

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
 * Servlet implementation class storage
 */
@WebServlet("/storage")
public class storage extends HttpServlet {
	public static String stat[]=new String[100];
	public static int roll[]=new int[100];
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public storage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int i,j=1;
		String subcode=request.getParameter("subcode");
		String scheme=request.getParameter("scheme");
		String att_date=request.getParameter("attdate");
		System.out.println("att_date");
		String arr_date[]=att_date.split("");
		for(i=0;i<arr_date.length;i++)
		{
			System.out.println(arr_date[i]);
		}
		String mon=arr_date[5]+arr_date[6];
		String dat=arr_date[8]+arr_date[9];
		String year=arr_date[0]+arr_date[1]+arr_date[2]+arr_date[3];
		switch(mon)
		{
			case "1":mon="Jan";
				 	 break;
			case "2":mon="Feb";
					 break;
			case "3":mon="Mar";
					 break;
			case "4":mon="Apr";
					 break;
			case "5":mon="May";
					 break;
			case "6":mon="Jun";
					 break;
			case "7":mon="Jul";
					 break;
			case "8":mon="Aug";
					 break;
			case "9":mon="Sep";
					 break;
			case "10":mon="Oct";
					  break;
			case "11":mon="Nov";
					  break;
			case "12":mon="Dec";
					  break;
			
		}
		String rev=dat+"-"+mon+"-"+year;
		System.out.println(rev);
		for(i=1;i<=88;i++)
		{
			roll[i]=j;
			j++;
			stat[i]=request.getParameter("status"+i);
			System.out.println(stat[i]+roll[i]);
		}
		System.out.println(subcode+scheme+rev);
		System.out.println("hi123");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			
			PreparedStatement ps=con.prepareStatement("insert into tea_att values(?,?,?,?,?)");
			for(i=1;i<=87;i++) {
				ps.setString(1,subcode);
				ps.setString(2,scheme);
				ps.setString(3,rev);
				ps.setString(4,stat[i]);
				ps.setInt(5,roll[i]);
				int k=ps.executeUpdate();
				if(k>0) {
					System.out.println("saved");
				}
				else {
					System.out.println("error");
				}
			}
			response.sendRedirect("attendanceplacesheet.html");
			System.out.println("hi");
		}catch(Exception e2) {
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
