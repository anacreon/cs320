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
 * Servlet implementation class Revisions
 */
@WebServlet("/Revisions")
public class Revisions extends HttpServlet {
	
	final String PATH = "/cs320stu13/";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Revisions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String  path = request.getParameter( "path" );
        List<PageListEntry> entries = WikiDB.updateEntries();
        PageListEntry entry = null;
        for(int i = 0; i < entries.size(); i++)
        {
        	entry = entries.get(i);
        	if(entry.getPath().equals(path))
        		break;
        }
        List<Revision> changes = entry.getRevisions();
        request.setAttribute("changes", changes);
        request.setAttribute("entry", entry);
        request.getRequestDispatcher("/WEB-INF/Revisions.jsp").forward(request, response);
        /*
        PrintWriter out = response.getWriter();
        response.setContentType( "text/html" );
        
        out.println( "<html>" );
        out.println( "<head><title>Wiki - Revisions</title></head>" );
        out.println( "<body>" );
        out.println("<p>Revisions for "+entry.getPath()+"</p>");
        for(i = 0; i < changes.size(); i++)
        {
        	out.println("<a href='"+ PATH +"Rev?path="+entry.getPath()+"&index="+ i +"'>Revision " + i + "</a><br>");
        }
        out.println("</body></html>");
        */


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
