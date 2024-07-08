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
 * Servlet implementation class Transfer
 */
@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transfer() {
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
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String from_no=request.getParameter("from_no");
		String to_no =request.getParameter("to_no");
		float amt=Float.parseFloat(request.getParameter("amt"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","sathwika12345678");
			
			String q="select balance from bank_app where act_num=?";
			PreparedStatement p=con.prepareStatement(q);
			p.setString(1, from_no);
			ResultSet rs=p.executeQuery();
			
			if(rs.next()) {
				float cur_bal=rs.getFloat("balance");
				
				if(cur_bal>=amt) {
					String query1="update bank_app set balance=balance-? where act_num=?";
					PreparedStatement pst1=con.prepareStatement(query1);
					pst1.setFloat(1, amt);
					pst1.setString(2, from_no);
					int t=pst1.executeUpdate();
					
					String query="update bank_app set balance=balance+? where act_num=?";
					PreparedStatement pst=con.prepareStatement(query);
					pst.setFloat(1, amt);
					pst.setString(2, to_no);
					
					pw.println("Amount transffered successfully");
				}
				else {
					pw.print("Insufficient balance in sender's account");
				}
			}
			else {
				pw.print("sender account not found");
			}
			
			response.getWriter().print("<br>");
			pw.println("<a href='index.html'> GO BACK TO HOME PAGE</a>");
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}