package cursos.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cursos.base.DB;

@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String CURRENCY_CODE_PATTERN = "^[M]\\d{2}[DH]$";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nomeCompleto = request.getParameter("nomeCompleto");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String currencyCode = request.getParameter("currencyCode");

        if (!isValidCurrencyCode(currencyCode)) {
            response.getWriter().println("Código da moeda inválido. O código deve seguir o formato correto.");
            return;
        }

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = DB.getConnection();
            String sql = "INSERT INTO usuarios (nome_completo, email, senha) VALUES (?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nomeCompleto);
            stmt.setString(2, email);
            stmt.setString(3, senha);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Registro inserido com sucesso, definir a sessão do usuário
                HttpSession session = request.getSession();
                session.setAttribute("usuarioLogado", true);

                // Redirecionar para a página principal do usuário (getcursos)
                response.sendRedirect("getcursos");
            } else {
                response.getWriter().println("Falha ao inserir registro.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Erro durante a inserção do registro: " + e.getMessage());
        } finally {
            try {
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

    private boolean isValidCurrencyCode(String currencyCode) {
        Pattern pattern = Pattern.compile(CURRENCY_CODE_PATTERN);
        Matcher matcher = pattern.matcher(currencyCode);
        return matcher.matches();
    }
}
