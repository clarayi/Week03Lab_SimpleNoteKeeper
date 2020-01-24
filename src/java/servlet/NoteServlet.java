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
    Note noteObj = new Note();
    
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
        
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String title = br.readLine();
            System.out.println("file read: " + title);
            String content = br.readLine();
            System.out.println("file read: " + content);
            
            br.close();
            
            noteObj.setTitle(title);
            noteObj.setContent(content);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error! File not found.");
        }
        
        String editButtonClicked = request.getParameter("editButton");
        
        if(editButtonClicked == null)
        {
            System.out.println("in linkClicked null");
            request.setAttribute("viewNote", noteObj);
            getServletContext().getRequestDispatcher("/viewnote.jsp").forward(request, response);
        }
        else
        {
            System.out.println("in linkClicked not null");
            request.setAttribute("editNote", noteObj);
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
        System.out.println("file write: " + newTitle);
        String newContent = request.getParameter("newContent");
        System.out.println("file write: " + newContent);
        
        noteObj.setTitle(newTitle);
        noteObj.setContent(newContent);
        
        try
        {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));
            pw.println(newTitle);
            pw.println(newContent);
            
            pw.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error! File not found. In doPost.");
        }
        
        request.setAttribute("viewNote", noteObj);
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
