package cs320.wiki;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditPage
 */
@WebServlet("/EditPage")
public class EditPage extends HttpServlet {
	
	final String PATH = "/cs320stu13/";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	if( request.getSession().getAttribute( "user" ) == null )
        {
            response.sendRedirect( PATH + "Login" );
            return;
        }
		String  path = request.getParameter( "path" );
        List<PageListEntry> entries = WikiDB.updateEntries();
		String name = request.getSession().getAttribute("user").toString();
        PageListEntry entry = null;
        int i;
        for(i = 0; i < entries.size(); i++)
        {
        	entry = entries.get(i);
        	if(entry.getPath().equals(path))
        		break;
        }
        request.setAttribute("entry", entry);
        request.setAttribute("name", name);
        request.setAttribute("index", i);
        request.getRequestDispatcher("/WEB-INF/EditPage.jsp").forward(request, response);
        /*
        response.setContentType( "text/html" );
        PrintWriter out = response.getWriter();
        out.println( "<html>" );
        out.println( "<head><title>Wiki - Edit Page</title></head>" );
        out.println( "<body>" );

        out.println( "<form action='EditPage' method='POST'>" );
        out.println( "Your name: <input type='text' name='name' readonly='readonly' value ='"+name+"'/><br />" );
        out.println( "Comment: <br />" );
        out.println( "<textarea name='content' rows='5' cols='60'>"
            + entry.getContent() + "</textarea><br />" );
        out.println( "<input type='hidden' name='index' value='" + i
            + "' />" );
        out.println( "<input type='submit' name='save' value='Save' />" );
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
		int index = Integer.parseInt( request.getParameter( "index" ) );
		String user = request.getParameter("name");
		String content = request.getParameter("content");
		List<PageListEntry> entries = WikiDB.updateEntries();
        PageListEntry entry = entries.get( index );
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd h:mm a");
        String date = format.format(currentDate.getTime());
        
        
        try
        {
            String name = WikiDB.userToName(user);
            String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu13";
            String username = "cs320stu13";
            String password = "P8*InnQ1";

            Connection c = DriverManager.getConnection( url, username, password );
            String sql = "update pages set content=?,name=?, date=now() where id=?";
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setString(1, content );
            pstmt.setString(2, name );
            pstmt.setInt(3, index + 1);
            pstmt.executeUpdate();
            
            Statement stmt = c.createStatement();
            sql ="insert into revisions(page, content, author, date) values (?, ?, ?, now())";
            pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, index + 1);
            pstmt.setString(2, content);
            pstmt.setString(3, user);
            pstmt.executeUpdate();
            
            c.close();
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }
        response.sendRedirect(entry.getUripath());
	}

}
