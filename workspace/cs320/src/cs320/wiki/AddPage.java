package cs320.wiki;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddPage
 */
@WebServlet("/AddPage")
public class AddPage extends HttpServlet {
	
	final String PATH = "/cs320stu13/";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	if( request.getSession().getAttribute( "user" ) == null )
        {
            response.sendRedirect( PATH + "Login" );
            return;
        }
		String path = request.getParameter("path");
		String name = request.getSession().getAttribute("user").toString();
		request.setAttribute("name", name);
		request.setAttribute("path", path);
		request.getRequestDispatcher("/WEB-INF/AddPage.jsp").forward(request, response);
		/*
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html>");
		out.println( "<head><title>Wiki- Add Page</title></head>" );
        out.println( "<body>" );

        out.println( "<form action='AddPage' method='POST'>" );
        out.println("<table border ='1'>");
        out.println("<tr><td>Path:</td><td><input type='text' name='path' readonly='readonly' value='"+path+ "'><br/></td>");
        out.println("<tr><td>Your Name:</td><td><input type='text' name='name' readonly='readonly' value ='"+ name +"'/><br/></td>");
        out.println("<tr><td>Content:</td><td><textarea name='content' rows='5' cols='60'></textarea><br />");
        out.println( "<input type='submit' name='add' value='Add' />" );
        out.println( "</form>" );

        out.println( "</body></html>" );
        */
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String path = request.getParameter("path");
		
        try
        {
            String author = WikiDB.userToName(name);
            String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu13";
            String username = "cs320stu13";
            String password = "P8*InnQ1";

            Connection c = DriverManager.getConnection( url, username, password );
            String sql = "insert into pages(name, path, content, date) values (?, ?, ?, now())";
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setString(1, author );
            pstmt.setString(2, path );
            pstmt.setString(3, content);
            pstmt.executeUpdate();
            
            int id= -1;
            
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from pages" );
            while(rs.next())
            {
            	id = rs.getInt("id");
            }
            sql ="insert into revisions(page, content, author, date) values (?, ?, ?, now())";
            pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, content);
            pstmt.setString(3, name);
            pstmt.executeUpdate();

            c.close();
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }
        response.sendRedirect(PATH + "wiki/" + path);
	}
}
