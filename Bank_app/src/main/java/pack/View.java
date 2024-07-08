package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class View
 */
@WebServlet("/View")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public View() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		String act_no=request.getParameter("act_no");
		String query="select * from bank_app where act_num=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","sathwika12345678");
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, act_no);
			ResultSet rs=pst.executeQuery();
			PrintWriter pw=response.getWriter();
			pw.println("<table border='1'>");
            pw.println("<tr>");
            pw.println("<th>Account Number</th>");
            pw.println("<th>Name</th>");
            pw.println("<th>Balance</th>");
            pw.println("</tr>");


            while (rs.next()) {
                pw.println("<tr>");
                pw.println("<td>" + rs.getString(1) + "</td>");
                pw.println("<td>" + rs.getString(2) + "</td>");
                pw.println("<td>" + rs.getInt(3) + "</td>");
                pw.println("</tr>");
            }

            pw.println("</table>");
			pw.println("<br>");
			pw.println("<a href='index.html'> GO BACK TO HOME PAGE</a>");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
