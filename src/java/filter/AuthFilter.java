package filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String path = req.getServletPath();
        
        // Cho phép truy cập các tài nguyên tĩnh (CSS, JS, hình ảnh)
        if (path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".png") || path.endsWith(".jpg")) {
            chain.doFilter(request, response);
            return;
        }
        
        // Nếu chưa đăng nhập, chỉ cho phép vào các trang công khai
        if (session == null || session.getAttribute("account") == null) {
            if (path.equals("/RefineServlet") || path.equals("/login.jsp") || path.equals("/LoginServlet") 
                    || path.equals("/RegisterServlet") || path.equals("/register.jsp")) {
                chain.doFilter(request, response);
                return;
            }
            // Nếu truy cập trang khác, chuyển hướng về RefineServlet
            res.sendRedirect("RefineServlet");
            return;
        }
        
        // Nếu đã đăng nhập, tiếp tục request
        chain.doFilter(request, response);
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }
    
    @Override
    public void destroy() {
        // Cleanup code if needed
    }
}