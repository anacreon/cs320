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
 * Servlet implementation class Wiki
 */
@WebServlet("/wiki/*")
public class Wiki extends HttpServlet {
	
	final String PATH = "/cs320stu13/";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Wiki() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		List<PageListEntry> entries = WikiDB.updateEntries();
		PageListEntry entry = null;
//		PrintWriter out = response.getWriter();
		int i;
		for(i = 0; i < entries.size(); i++)
		{
			entry = entries.get(i);
			if(entry.getUripath().equals(uri))
			{
				break;
			}				
		}
		getServletContext().setAttribute("entry", entry);
		if(entry.getUripath().equals(uri))
		{
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
			request.getRequestDispatcher("/WEB-INF/exists.jsp").forward(request, response);
			/*
			The code below here was redone using JSP
				out.println("<html>");
				out.println("<head><title></title></head>");
				out.println("<body>");
				out.println("<a href ='"+ PATH +"EditPage?path="+ entry.getPath() + "'>Edit</a> | " +
							"<a href ='"+ PATH +"Revisions?path="+ entry.getPath() +"'>Revisions</a> | " +
							"<a href ='"+ PATH +loginout+"'>"+loginout+"</a><br>");
				out.println(entry.getContent());
				out.println("<p><a href='"+ PATH +"wiki/index'>Return to index</a></p>");
				out.println("<p><i>This page was edited by " + entry.getName() +
							" on " + entry.getDate() + "</i></p>");
			*/
		}
		else
		{
			String[] path = uri.split("/wiki/");
			request.setAttribute("path", path[path.length-1]);
			request.getRequestDispatcher("/WEB-INF/notExist.jsp").forward(request, response);
			/*
			out.println("<html>");
			out.println("<head><title></title></head>");
			out.println("<body>");
			out.println("The page at does not Exist would you like to Create it? <a href='"+ PATH +"AddPage?path="+path[path.length-1]+"'>Yes</a> <a href='"+ PATH +"PageList' onClick='history.back();return false;'>No</a>");
			*/
		}
		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
