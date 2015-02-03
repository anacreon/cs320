package cs320.wiki;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Rev
 */
@WebServlet("/Rev")
public class Rev extends HttpServlet {
	
	final String PATH = "/cs320stu13/";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rev() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String  path = request.getParameter("path");
        int index = Integer.parseInt(request.getParameter("index"));
        List<PageListEntry> entries = WikiDB.updateEntries();
        PageListEntry entry = null;
        for(int i = 0; i < entries.size(); i++)
        {
        	entry = entries.get(i);
        	if(entry.getPath().equals(path))
        		break;
        }
        List<Revision> changes = entry.getRevisions();
        Revision change = changes.get(index);
        String loginout;
		if(request.getSession().getAttribute( "user" ) == null)
		{
			loginout ="Login";
		}
		else
		{
			loginout ="Logout";
		}
		request.setAttribute("entry", entry);
		request.setAttribute("loginout", loginout);
		request.setAttribute("change", change);
		request.getRequestDispatcher("/WEB-INF/Rev.jsp").forward(request, response);
/*        PrintWriter out = response.getWriter();
        response.setContentType( "text/html" );
        out.println( "<html>" );
        out.println( "<head><title>Wiki - Revisions</title></head>" );
        out.println( "<body>" );
		out.println("<a href ='"+ PATH +"EditPage?path="+ entry.getPath() + "'>Edit</a> | " +
				    "<a href ='"+ PATH +"Revisions?path="+ entry.getPath() +"'>Revisions</a> | " +
				    "<a href ='"+ PATH + loginout+"'>"+ loginout +"</a><br>");
		out.println(change.getContent());
		out.println("<p><a href='"+PATH+"wiki/index'>Return to index</a></p>");
		out.println("<p><i>This page was edited by " + change.getName() +
				" on " + change.getDate() + "</i></p>");
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
