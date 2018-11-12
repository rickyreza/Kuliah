/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import com.session.deteksiJiwa;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ricky
 */
public class deteksiServlet extends HttpServlet {

    @EJB
    private deteksiJiwa deteksiJiwa;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Quiz DeteksiJiwa</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");
            out.println("<h1>Quiz Kejiwaan</h1>");
            out.println("</center>");
            out.println("</br>");
            out.println("<form method=\"get\" >\n");
            out.println("<h3>1. Saya seringkali terbangun oleh suara berisik</h3>");
            out.println("<div>");
            out.println("<input type=\"radio\" name=\"question-1-answers\" id=\"question-1-answers-ya\" value=\"Ya\" />");
            out.println("<label for=\"question-1-answers-A\"> Iya </label>");
            out.println("</div>");
            out.println("<div>");
            out.println("<input type=\"radio\" name=\"question-1-answers\" id=\"question-1-answers-tidak\" value=\"Tidak\" />");
            out.println("<label for=\"question-1-answers-A\"> Tidak </label>");
            out.println("</div>");
            
            out.println("<h3>2. Saya seringkali merasa tegang ketika bekerja</h3>");
            out.println("<div>");
            out.println("<input type=\"radio\" name=\"question-2-answers\" id=\"question-2-answers-ya\" value=\"Ya\" />");
            out.println("<label for=\"question-1-answers-A\"> Iya </label>");
            out.println("</div>");
            out.println("<div>");
            out.println("<input type=\"radio\" name=\"question-2-answers\" id=\"question-2-answers-tidak\" value=\"Tidak\" />");
            out.println("<label for=\"question-1-answers-A\"> Tidak </label>");
            out.println("</div>");
            
            out.println("<h3>3. Terkadang ada keinginan untuk membanting barang barang yang ada di hadapan saya </h3>");
            out.println("<div>");
            out.println("<input type=\"radio\" name=\"question-3-answers\" id=\"question-3-answers-ya\" value=\"Ya\" />");
            out.println("<label for=\"question-1-answers-A\"> Iya </label>");
            out.println("</div>");
            out.println("<div>");
            out.println("<input type=\"radio\" name=\"question-3-answers\" id=\"question-3-answers-tidak\" value=\"Tidak\" />");
            out.println("<label for=\"question-1-answers-A\"> Tidak </label>");
            out.println("</div>");
            
            out.println("<h3>4. Sekarang saya telah mampu mengambil keputusan ketimbang dulu </h3>");
            out.println("<div>");
            out.println("<input type=\"radio\" name=\"question-4-answers\" id=\"question-4-answers-ya\" value=\"Ya\" />");
            out.println("<label for=\"question-1-answers-A\"> Iya </label>");
            out.println("</div>");
            out.println("<div>");
            out.println("<input type=\"radio\" name=\"question-4-answers\" id=\"question-4-answers-tidak\" value=\"Tidak\" />");
            out.println("<label for=\"question-1-answers-A\"> Tidak </label>");
            out.println("</div>");
            
            out.println("<h3>5. Segala sesuatunya telah terjadi seperti apa adanya dan sulit untuk merubahnya </h3>");
            out.println("<div>");
            out.println("<input type=\"radio\" name=\"question-5-answers\" id=\"question-5-answers-ya\" value=\"Ya\" />");
            out.println("<label for=\"question-1-answers-A\"> Iya </label>");
            out.println("</div>");
            out.println("<div>");
            out.println("<input type=\"radio\" name=\"question-5-answers\" id=\"question-5-answers-tidak\" value=\"Tidak\" />");
            out.println("<label for=\"question-1-answers-A\"> Tidak </label>");
            out.println("</div>");
            out.println("</br>");
            out.println("</br>");
            out.println("</br>");
            
            out.println("<input type=\"submit\" name=\"submit\" value=\"Submit Quiz\" />");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
            
            if(request.getParameter("submit")!=null){
                String jwb1 = request.getParameter("question-1-answers");
                String jwb2 = request.getParameter("question-2-answers");
                String jwb3 = request.getParameter("question-3-answers");
                String jwb4 = request.getParameter("question-4-answers");
                String jwb5 = request.getParameter("question-5-answers");
                
                Integer hasil1 = deteksiJiwa.cekAnswer1(jwb1);
                Integer hasil2 = deteksiJiwa.cekAnswer2(jwb2);
                Integer hasil3 = deteksiJiwa.cekAnswer3(jwb3);
                Integer hasil4 = deteksiJiwa.cekAnswer4(jwb4);
                Integer hasil5 = deteksiJiwa.cekAnswer5(jwb5);
                
                Integer clever =  deteksiJiwa.setScore(hasil1, hasil2, hasil3, hasil4, hasil5);
                
                
                out.println("<h3>Presentase Kejiwaan anda sebesar "
                        + clever +
                      " Persen Mengidap gangguan jiwa"+
                "</h3>");
            }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
