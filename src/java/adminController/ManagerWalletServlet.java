
package adminController;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Wallet;
import perfumeshopDAO.WalletDAO;

/**
 *
 * @author Sekiro
 */
@WebServlet(name = "ManagerWalletServlet", urlPatterns = {"/ManagerWalletServlet"})
public class ManagerWalletServlet extends HttpServlet {


 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        WalletDAO wdao = new WalletDAO();
        List<Wallet> list = wdao.getAll();
        request.setAttribute("listWallet", list);
        request.getRequestDispatcher("dashboard/mngwallet.jsp").forward(request, response);
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
