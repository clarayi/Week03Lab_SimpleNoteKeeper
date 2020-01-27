package servlet;

import domain.Note;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 810783
 */
public class NoteServlet extends HttpServlet
{
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("<<In doGet method>>");
        
        response.setContentType("text/html;charset=UTF-8");
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        
        Note noteObj = null;
        try
        {
            //Opening note.txt file and reading data inside it
            //Assumption : title and content are only one line
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String title = br.readLine();
            System.out.println("File read - title: " + title);
            String content = br.readLine();
            System.out.println("File read - content: " + content);
            
            br.close();
            
            //Note JavaBean object created with read data
            noteObj = new Note(title, content);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error: File not found.");
        }
        
        // Checking if 'Edit' link is clicked (and URL has '?edit' in it) or not
        String editInURL = request.getParameter("edit");
        System.out.println(editInURL);
        
        if(editInURL != null)
        {
            System.out.println("Opening editnote.jsp");
            request.setAttribute("editNote", noteObj);
            getServletContext().getRequestDispatcher("/editnote.jsp").forward(request, response);
        }
        else
        {
            System.out.println("Opening viewnote.jsp");
            request.setAttribute("viewNote", noteObj);
            getServletContext().getRequestDispatcher("/viewnote.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("<<In doPost method>>");
        
        response.setContentType("text/html;charset=UTF-8");
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        
        String newTitle = request.getParameter("newTitle");
        System.out.println("File written - title: " + newTitle);
        String newContent = request.getParameter("newContent");
        System.out.println("File written - content" + newContent);
        
        Note newNoteObj = null;
        try
        {
            //Opening note.txt file and writing user input into it
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));
            pw.println(newTitle);
            pw.println(newContent);
            
            pw.close();
            
            newNoteObj = new Note(newTitle, newContent);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error! File not found. In doPost.");
        }
        
        request.setAttribute("viewNote", newNoteObj);
        getServletContext().getRequestDispatcher("/viewnote.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
