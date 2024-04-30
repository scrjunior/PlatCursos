package cursos.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cursos.base.DB;

@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Define the regex pattern for currency code validation
    private static final String CURRENCY_CODE_PATTERN = "^[M]\\d{2}[DH]$";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtain parameters from the form
        String nomeCompleto = request.getParameter("nomeCompleto");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String currencyCode = request.getParameter("currencyCode");

        // Validate currencyCode against the defined pattern
        if (!isValidCurrencyCode(currencyCode)) {
            response.getWriter().println("Código da moeda inválido. O código deve seguir o formato correto.");
            return;
        }

        // Database connection and insertion logic
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
                response.getWriter().println("Registro inserido com sucesso!");
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

    // Method to validate currency code against the specified pattern
    private boolean isValidCurrencyCode(String currencyCode) {
        Pattern pattern = Pattern.compile(CURRENCY_CODE_PATTERN);
        Matcher matcher = pattern.matcher(currencyCode);
        return matcher.matches();
    }
}

