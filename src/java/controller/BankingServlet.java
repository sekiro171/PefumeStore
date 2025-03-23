/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import perfumeshopDAO.WalletDAO;

/**
 *
 * @author Sekiro
 */
@WebServlet(name = "BankingServlet", urlPatterns = {"/BankingServlet"})
public class BankingServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
              HttpSession session = request.getSession();
        String balance_raw = request.getParameter("balance");
        String userName = request.getParameter("userName");
        WalletDAO dao = new WalletDAO();
        double balance = Double.parseDouble(balance_raw);
        session.setAttribute("userName", userName );
        session.setAttribute("balance",  balance);
        response.sendRedirect("banking.jsp");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
