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
import java.util.List;
import model.User;
import perfumeshopDAO.UserDAO;

/**
 *
 * @author Sekiro
 */
@WebServlet(name = "ManagerAccountServlet", urlPatterns = {"/ManagerAccountServlet"})
public class ManagerAccountServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String txtSearch = request.getParameter("valueSearch");
        UserDAO dao = new UserDAO();

        List<User> list = dao.getUsersBySearchName(txtSearch);

        request.setAttribute("listUser", list);

        request.getRequestDispatcher("dashboard/mngaccount.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User a = (User) session.getAttribute("account");
        String userName = a.getUserName();
        UserDAO dao = new UserDAO();

        List<User> list = dao.getAllUsers();

        request.setAttribute("listUser", list);
        request.getRequestDispatcher("dashboard/mngaccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String txtSearch = request.getParameter("valueSearch");
        UserDAO dao = new UserDAO();

        List<User> list = dao.getUsersBySearchName(txtSearch);

        request.setAttribute("listUser", list);
        request.setAttribute("searchValue", txtSearch);
        request.getRequestDispatcher("dashboard/mngaccount.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
