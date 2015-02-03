package cs320.wiki;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PageList
 */
@WebServlet("/PageList")

public class PageList extends HttpServlet {
	
	final String PATH = "/cs320stu13/";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageList() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init( ServletConfig config ) throws ServletException
    {
        super.init( config );
        /*
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd h:mm a");
        String date = format.format(currentDate.getTime());
        
        List<PageListEntry> entries = new ArrayList<PageListEntry>();
        List<Userinfo> users = new ArrayList<Userinfo>();
        entries.add(new PageListEntry( "index", "John Doe", date, "Welcome to CS320Wiki. To see all the pages currently hosted on the wiki, please click <a href ='"+ PATH +"PageList'>Page List</a>"));
        entries.add(new PageListEntry( "mypage", "Scott Rice", date, "My Page"));
        
        users.add(new Userinfo("cysun", "abcd", "Chengyu Sun"));
        users.add(new Userinfo("jdoe", "abcd", "John Doe"));
        
        getServletContext().setAttribute("users", users);
        getServletContext().setAttribute( "pageListEntries", entries );
        */
        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
        //get The info from the database then store into a data class then we store it in the application scope
        //adding and editing pages will cause entries to update its data. This is done to reduce the amount of times the database is queried.
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        List<PageListEntry> entries = WikiDB.updateEntries();
        request.setAttribute("pageListEntries", entries);
    	request.getRequestDispatcher("/WEB-INF/pagelist.jsp").forward(request, response);
    	/*	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println( "<head><title>Page List</title></head>" );
        out.println( "<body>" );
        //table
        out.println( "<table border='1'>" );
        out.println( "<tr><th>Path</th><th>Last Edited By</th><th>Time of Last Edit</th></tr>" );
        List<PageListEntry> entries = (List<PageListEntry>) getServletContext().getAttribute(
            "pageListEntries" );
        for( int i = 0; i < entries.size(); ++i )
        {
            PageListEntry entry = entries.get( i );
            out.println( "<tr><td>" + "<a href='"+ PATH + "wiki/" + entry.getPath() + "'>" + entry.getPath() + "</a></td><td>"
                + entry.getName() + "</td><td>" + entry.getDate() + "</tr>" );
        }
        out.println("</table>");
        */
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
