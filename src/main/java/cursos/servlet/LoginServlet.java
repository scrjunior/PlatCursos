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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obter parâmetros do formulário de login
        String email = request.getParameter("email");
        String senha = request.getParameter("password");

        // Verificar se o email e a senha são válidos
        if (authenticateUser(email, senha)) {
            // Se a autenticação for bem-sucedida, definir a variável de sessão 'usuarioLogado' como true
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", true);

            // Redirecionar para a página principal do usuário
            response.sendRedirect("getcursos"); // Substitua "getcursos" pela sua página principal
        } else {
            // Se a autenticação falhar, exibir mensagem de erro
            response.getWriter().println("Credenciais inválidas. Tente novamente.");
        }
    }

    // Método para autenticar o usuário com base no email e senha fornecidos
    private boolean authenticateUser(String email, String senha) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = DB.getConnection();
            String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            rs = stmt.executeQuery();

            // Verificar se há correspondência de usuário
            return rs.next(); // Retorna true se houver um usuário correspondente
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
