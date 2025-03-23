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
import jakarta.servlet.http.HttpSession;
import perfumeshopDAO.WalletDAO;

/**
 *
 * @author Sekiro
 */
@WebServlet(name = "AddBalanceServlet", urlPatterns = {"/AddBalanceServlet"})
public class AddBalanceServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String balance_raw = request.getParameter("balance");
        String userName = request.getParameter("userName");
        String action = request.getParameter("action");
        if (action.equals("addBalance")) {
            WalletDAO dao = new WalletDAO();
            double balance;
            try {
                balance = Double.parseDouble(balance_raw);
                dao.inputMoney(userName, balance);
            } catch (Exception e) {
            }
            session.removeAttribute("balance");
            response.sendRedirect("ManagerWalletServlet");
        }
        if (action.equals("reject")) {
            session.removeAttribute("balance");
            response.sendRedirect("ManagerWalletServlet");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
