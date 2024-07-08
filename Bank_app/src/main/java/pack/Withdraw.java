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
 * Servlet implementation class Withdraw
 */
@WebServlet("/Withdraw")
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Withdraw() {
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
		//doGet(request, response);
		response.setContentType("text/html");
		String act_no = request.getParameter("a");
        float amt = Float.parseFloat(request.getParameter("w"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","sathwika12345678");
			PreparedStatement pst=con.prepareStatement("select balance from bank_app where act_num=?");
			pst.setString(1, act_no);
			ResultSet rs=pst.executeQuery();
			PrintWriter pw=response.getWriter();
			if(rs.next()) {
				float curBal=rs.getFloat("balance");
				if(amt<=curBal) {
					PreparedStatement pst1=con.prepareStatement("update bank_app set balance=balance-? where act_num=?");
					pst1.setFloat(1, amt);
					pst1.setString(2, act_no);
					int t=pst1.executeUpdate();
					
					if(t>0) {
						pw.println("Amount withdrawn succesfully");
					}
					else {
						pw.print("Amount not withdrawn, try again!");
					}
				}
				else {
					pw.println("Insufficient balance");
				}
			}
			else {
				pw.println("Account number not found");
			}
			pw.print("<br>");
			pw.println("<a href='index.html'> GO BACK TO HOME PAGE </a>");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
