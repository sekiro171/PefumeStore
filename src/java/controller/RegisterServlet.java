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
import model.User;
import model.Wallet;
import perfumeshopDAO.UserDAO;
import perfumeshopDAO.WalletDAO;

/**
 *
 * @author Sekiro
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

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
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String fName = request.getParameter("name");
        String uName = request.getParameter("username");
        String uPass = request.getParameter("password");
        String uPho = request.getParameter("phone");
        String uEmail = request.getParameter("email");
        String birthDate = request.getParameter("dob");
        UserDAO ud = new UserDAO();
        WalletDAO wd = new WalletDAO();
        User user;
        Wallet wallet;
        String message = "Something wrong";

        // Validate name (only letters allowed)
        if (!fName.matches("[a-zA-Z\\s]+")) {
            message = "Name must contain only letters!";
            request.setAttribute("error", message);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Validate phone (only numbers allowed)
        if (!uPho.matches("\\d+")) {
            message = "Phone must contain only numbers!";
            request.setAttribute("error", message);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Validate email format
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!uEmail.matches(emailRegex)) {
            message = "Please enter a valid email address!";
            request.setAttribute("error", message);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        int slUPrev = ud.getNumberUsers();
        int slWPrev = wd.getNumberWallets();

        // Check for duplicate username
        boolean isDup = ud.checkUserNameDuplicate(uName);
        if (isDup) {
            message = "Username already exist!";
            request.setAttribute("error", message);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Check for duplicate phone number
        boolean isPhoneDup = ud.isPhoneExist(uPho);
        if (isPhoneDup) {
            message = "Phone number already exist!";
            request.setAttribute("error", message);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Check for duplicate email
        boolean isEmailDup = ud.isEmailExist(uEmail);
        if (isEmailDup) {
            message = "Email already exist!";
            request.setAttribute("error", message);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // If all validations pass, proceed with registration
        System.out.println(birthDate.toString());
        user = new User(uName, fName, uPass, "", uPho, uEmail, "", birthDate, 2);
        ud.insert(user);
        wallet = new Wallet(uName, 0);
        wd.addWallet(wallet);
        int slUAfter = ud.getNumberUsers();
        int slWAfter = wd.getNumberWallets();

        if (slUAfter > slUPrev && slWAfter > slWPrev) {
            message = "Register successfully. Please Login!";
        }
        request.setAttribute("successfully", message);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
