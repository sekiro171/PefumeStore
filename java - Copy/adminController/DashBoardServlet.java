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
import perfumeshopDAO.OrderDAO;
import perfumeshopDAO.ProductDAO;
import perfumeshopDAO.SupplierDAO;
import perfumeshopDAO.UserDAO;

/**
 *
 * @author Sekiro
 */
@WebServlet(name = "DashBoardServlet", urlPatterns = {"/DashBoardServlet"})
public class DashBoardServlet extends HttpServlet {

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
            out.println("<title>Servlet DashBoardServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashBoardServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        UserDAO udao = new UserDAO();
        OrderDAO odao = new OrderDAO();
        SupplierDAO sdao = new SupplierDAO();
        int count = dao.countAllProduct();
        int countS = dao.countAllTypeProduct();
        int countu = udao.countAllUser();
        int countSupplier = sdao.countAllSupplier();
        int sumquantitySold = dao.getSumQuantitySold();
        double totalmoneyAll = odao.sumAllMoneyOrder();
        request.setAttribute("countProduct", count);
        request.setAttribute("countSupplier", countSupplier);
        request.setAttribute("countTypeProduct", countS);
        request.setAttribute("sumquantitySold", sumquantitySold);
        request.setAttribute("countUser", countu);
        request.setAttribute("totalmoneyAll", totalmoneyAll);
        request.getRequestDispatcher("dashboard/dashboard.jsp").forward(request, response);
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
