/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import perfumeshopDAO.UserDAO;

/**
 *
 * @author Sekiro
 */
@WebServlet(name = "uploadImg", urlPatterns = {"/uploadImg"})

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 5, // 5MB
        maxRequestSize = 1024 * 1024 * 10 // 10MB
)
public class uploadImg extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet uploadImg</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet uploadImg at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private static final String UPLOAD_DIR = "images/users"; // Thư mục lưu ảnh

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDAO userDAO = new UserDAO();

        try {
            // Lấy đường dẫn thư mục upload (trong thư mục webapp)
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // Tạo thư mục nếu chưa có
            }
            // Xử lý file upload
            for (Part part : request.getParts()) {
                if (part.getName().equals("imageFile") && part.getSize() > 0) {
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    String filePath = uploadPath + File.separator + fileName;

                    // Lưu file vào thư mục
                    part.write(filePath);

                    // Cập nhật đường dẫn ảnh trong database
                    String imagePath = UPLOAD_DIR + "/" + fileName;
                    String userId = request.getParameter("uid");
                    userDAO.updateImage(imagePath, userId);

                    // Cập nhật session
                    session.setAttribute("imageUser", imagePath);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Chuyển hướng về trang profile
        response.sendRedirect("profile.jsp");
    }


@Override
public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
