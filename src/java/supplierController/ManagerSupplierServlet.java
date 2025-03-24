/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package supplierController;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Category;
import model.Supplier;
import perfumeshopDAO.CategoryDAO;
import perfumeshopDAO.SupplierDAO;

/**
 *
 * @author Sekiro
 */
@WebServlet(name = "ManagerSupplierServlet", urlPatterns = {"/ManagerSupplierServlet"})
public class ManagerSupplierServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManagerSupplierServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerSupplierServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SupplierDAO daoS = new SupplierDAO();
        CategoryDAO daoC = new CategoryDAO();

        List<Supplier> listAllSupplier = daoS.getAll();

        request.setAttribute("listAllSupplier", listAllSupplier);

        request.getRequestDispatcher("dashboard/supplier.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String txtSearch = request.getParameter("valueSearch");
        SupplierDAO daoS = new SupplierDAO();
        CategoryDAO daoC = new CategoryDAO();

        List<Supplier> listAllSupplier = daoS.getSuppliersBySearch(txtSearch);
        List<Category> listAllCategory = daoC.getAll();

        request.setAttribute("listAllSupplier", listAllSupplier);
        request.setAttribute("listAllCategory", listAllCategory);
        request.setAttribute("searchValue", txtSearch);
        request.getRequestDispatcher("dashboard/supplier.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
