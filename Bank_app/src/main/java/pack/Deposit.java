package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Deposit
 */
@WebServlet("/Deposit")
public class Deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deposit() {
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
		String act_no=request.getParameter("a");
		float amt=Float.parseFloat(request.getParameter("d"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","sathwika12345678");
			PreparedStatement pst=con.prepareStatement("update bank_app set balance=balance+? where act_num=?");
			pst.setFloat(1,amt);
			pst.setString(2, act_no);
			int t=pst.executeUpdate();
			PrintWriter pw=response.getWriter();
			if(t>0) {
				pw.println("Amount has been deposited");
			}
			else {
				pw.println("amount is not deposited, check and try again!");
			}
			pw.print("<br>");
			pw.println("<a href='index.html'> GO BACK TO HOME PAGE</a>");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
