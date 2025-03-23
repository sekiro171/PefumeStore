/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import model.Wallet;
import perfumeshopDAO.UserDAO;
import perfumeshopDAO.WalletDAO;


@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie arr[] = request.getCookies();
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].getName().equals("cUName")) {
                    request.setAttribute("uName", arr[i].getValue());
                }
                if (arr[i].getName().equals("pUName")) {
                    request.setAttribute("uPass", arr[i].getValue());
                }
                if (arr[i].getName().equals("reMem")) {
                    request.setAttribute("reMem", arr[i].getValue());
                }
            }
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uName = request.getParameter("username");
        String uPass = request.getParameter("password");
        String remember = request.getParameter("remember");
        UserDAO ud = new UserDAO();
        WalletDAO wd = new WalletDAO();

        User user = ud.check(uName, uPass);
        HttpSession session = request.getSession();
        if (user == null) {
            request.setAttribute("error", "Username or password invalid!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            Wallet wallet = wd.getWalletByUserName(uName);
            session.setAttribute("wallet", wallet);
            session.setAttribute("account", user);
            Cookie u = new Cookie("cUName", uName);
            Cookie p = new Cookie("pUName", uPass);
            Cookie r = new Cookie("reMem", remember);
            u.setMaxAge(60 * 60 * 24 * 30 * 3);
            if (remember != null) {
                p.setMaxAge(60 * 60 * 24 * 30 * 3);
                r.setMaxAge(60 * 60 * 24 * 30 * 3);
            } else {
                p.setMaxAge(0);
                r.setMaxAge(0);
            }

            response.addCookie(u);
            response.addCookie(r);
            response.addCookie(p);
            String image = user.getImage();

            session.setAttribute("imageUser", image);
            session.setAttribute("address", user.getAddress());
            session.setAttribute("name", user.getFullName());
            session.setAttribute("phone", user.getPhone());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("birthdate", user.getBirthdate());
            response.sendRedirect("RefineServlet");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
