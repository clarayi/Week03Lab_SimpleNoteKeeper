/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        System.out.println("in processRequest");
        
        response.setContentType("text/html;charset=UTF-8");
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        
        Note noteObj = null;
    
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String title = br.readLine();
            System.out.println(title);
            String content = br.readLine();
            System.out.println(content);

            noteObj = new Note(title, content);
            request.setAttribute("note", noteObj);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error! File not found.");
        }
        
        String linkClicked = request.getParameter("link");
        
        if(linkClicked == null)
        {
            getServletContext().getRequestDispatcher("/viewnote.jsp").forward(request, response);
        }
        else
        {
            getServletContext().getRequestDispatcher("/editnote.jsp").forward(request, response);
        }
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
        System.out.println("in doGet");
        processRequest(request, response);
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
        System.out.println("in doPost");
        
        response.setContentType("text/html;charset=UTF-8");
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        
        String newTitle = request.getParameter("newTitle");
        System.out.println(newTitle);
        String newContent = request.getParameter("newContent");
        System.out.println(newContent);
        
        try
        {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));
            pw.println(newTitle);
            pw.println(newContent);
            
            pw.flush();
            pw.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error! File not found. In doPost.");
        }
        
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
