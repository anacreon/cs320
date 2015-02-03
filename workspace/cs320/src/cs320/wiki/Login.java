package cs320.wiki;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	final String PATH = "/cs320stu13/";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
		/*
		response.setContentType( "text/html" );
        PrintWriter out = response.getWriter();
        out.println( "<html>" );
        out.println( "<head><title>Login</title></head>" );
        out.println( "<body>" );

        out.println( "<form action='Login' method='post'>" );
        out.println( "Username: <input type='text' name='username' /> <br />" );
        out.println( "Password: <input type='password' name='password' /> <br />" );
        out.println( "<input type='submit' name='login' value='Login' /> <br />" );
        out.println( "</form>" );

        out.println( "</body></html>" );
        */

	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		List<Userinfo> users = getUsersFromDB();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Userinfo user = null;
		
		for(int i =0; i < users.size(); i++)
		{
			user = users.get(i);
			if(username.equals(user.getUserName()))
				break;
		}
        if(username.equals(user.getUserName())
                &&  password.equals(user.getPassword()))
            {
                request.getSession().setAttribute( "user", user.getUserName());
                response.sendRedirect( PATH +"wiki/index" );
            
            }
        else
        {
        	response.sendRedirect("Login");
        }

	}
	 List<Userinfo> getUsersFromDB() throws ServletException {
		// TODO Auto-generated method stub
	     List<Userinfo> users = new ArrayList<Userinfo>();
	        try
	        {
	            String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu13";
	            String username = "cs320stu13";
	            String password = "P8*InnQ1";

	            Connection c = DriverManager.getConnection( url, username, password );
	            Statement stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery( "select * from users" );

	            while( rs.next() )
	            {
	                String name = rs.getString( "name" );
	                String user = rs.getString( "username" );
	                String pass = rs.getString("password");

	                users.add( new Userinfo( user, pass, name));
	            }

	            c.close();
	        }
	        catch( SQLException e )
	        {
	            throw new ServletException( e );
	        }

	        return users;
	 }
}
