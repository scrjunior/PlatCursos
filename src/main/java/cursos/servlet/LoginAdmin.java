package cursos.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cursos.base.DB;

@WebServlet("/LoginAdmin")
public class LoginAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String senha = request.getParameter("password");

        
        if (authenticateUser(email, senha)) {
            
            HttpSession session = request.getSession();
            session.setAttribute("email", email); 

            
            response.sendRedirect("AdminCursos");
        } else {
            
            response.getWriter().println("Credenciais inválidas. Tente novamente.");
        }
    }

    
    private boolean authenticateUser(String email, String senha) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = DB.getConnection(); 
            String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ? AND admin = 1";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            rs = stmt.executeQuery();

            
            return rs.next(); 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
