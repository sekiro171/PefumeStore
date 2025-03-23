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
import perfumeshopDAO.UserDAO;

/**
 *
 * @author Sekiro
 */
@WebServlet(name = "AddAccountServlet", urlPatterns = {"/AddAccountServlet"})
public class AddAccountServlet extends HttpServlet {

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String FullName = request.getParameter("name");
        String UserName = request.getParameter("user");
        String Password = request.getParameter("pass");
        String roleId_raw = request.getParameter("roleId");
        String Email = request.getParameter("email");
        String Phone = request.getParameter("phone");
        String BirthDay = request.getParameter("birthdate");
        int roleId = (roleId_raw == null ? 2 : Integer.parseInt(roleId_raw));
        String msg = "";
        UserDAO dao = new UserDAO();
        boolean check = dao.checkUserNameDuplicate(UserName);
        if (check) {
            msg = "Username already exist!";
            request.setAttribute("error", msg);
        } else {
            dao.insertUser(UserName, FullName, Password, roleId, Email, BirthDay, Phone);
            msg = "Username " + UserName + " add successfully!";
            request.setAttribute("mess", msg);
        }
        request.getRequestDispatcher("ManagerAccountServlet").forward(request, response);
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
