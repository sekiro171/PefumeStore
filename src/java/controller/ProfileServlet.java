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
import model.User;
import perfumeshopDAO.UserDAO;

/**
 *
 * @author Sekiro
 */
@WebServlet(name = "ProfileServlet", urlPatterns = {"/ProfileServlet"})
public class ProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO ud = new UserDAO();
        String imageUpdate = request.getParameter("imagelink");
        String userId = request.getParameter("uid");
        String link = "";
        String[] arr = null;
        HttpSession session = request.getSession();

        if (imageUpdate != null) {
            arr = imageUpdate.split("_");
            for (int i = 0; i < arr.length; i++) {
                link += arr[i] + "/";
            }
        }
        if (link.endsWith("/")) {
            link = link.substring(0, link.length() - 1);
        }

        ud.updateImage(link, userId);
        User account = null;

        if (userId != null) {
            account = ud.getUserByUserName(userId);
        }

        if (account != null) {
            session.setAttribute("imageUser", account.getImage());
        }

        request.setAttribute("acceptUpdate", 0);
        request.setAttribute("link", link);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO u = new UserDAO();
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String birthdate = request.getParameter("birthday");
        HttpSession session = request.getSession();

        u.update(name, address, phone, email, birthdate, username);

        User account = u.getUserByUserName(username);

        request.setAttribute("acceptUpdate", 1);
        session.setAttribute("name", account.getFullName());
        session.setAttribute("address", account.getAddress());
        session.setAttribute("phone", account.getPhone());
        session.setAttribute("email", account.getEmail());
        session.setAttribute("birthdate", account.getBirthdate());
        response.sendRedirect("ProfileServlet");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
