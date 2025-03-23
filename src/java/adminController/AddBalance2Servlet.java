/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminController;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import perfumeshopDAO.WalletDAO;

/**
 *
 * @author Sekiro
 */
@WebServlet(name = "AddBalance2Servlet", urlPatterns = {"/AddBalance2Servlet"})
public class AddBalance2Servlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           response.setContentType("text/html;charset=UTF-8");
        String balance_raw = request.getParameter("balance");
        String userName = request.getParameter("userName");
        WalletDAO dao = new WalletDAO();
        double balance;
        try {
            balance = Double.parseDouble(balance_raw);
            dao.inputMoney(userName, balance);
        } catch (Exception e) {
        }
        response.sendRedirect("ManagerWalletServlet");
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
