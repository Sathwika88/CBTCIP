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
 * Servlet implementation class Create_Account
 */
@WebServlet("/Create_Account")
public class Create_Account extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Create_Account() {
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
		String act_no=request.getParameter("act_num");
		String name=request.getParameter("name");
		float bal=Float.parseFloat(request.getParameter("balance"));
		String query="insert into bank_app values (?,?,?)";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","sathwika12345678");
			PreparedStatement pst=con.prepareStatement(query);
			PrintWriter pw=response.getWriter();
			pst.setString(1, act_no);
			pst.setString(2, name);
			pst.setFloat(3, bal);
			int t=pst.executeUpdate();
			if(t>0) {
				pw.println("Account Created Successfully!");
			}
			else {
				pw.print("Account not created, try again");
			}
		pw.println("<br>");
			pw.println("<a href='index.html'> GO BACK TO HOME PAGE</a>");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
