package cursos.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cursos.base.DB;
import cursos.bean.CursosBean;

@WebServlet("/AdminCursos")
public class AdminCursos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Verificar se o usuário é um administrador (pode ser implementado de acordo com sua lógica)
        

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DB.getConnection();
            ps = con.prepareStatement("SELECT * FROM cursos");
            rs = ps.executeQuery();

            List<CursosBean> cursos = new ArrayList<>();
            while (rs.next()) {
                CursosBean curso = new CursosBean();
                curso.setId(rs.getInt("id_curso"));
                curso.setTitulo(rs.getString("titulo"));
                curso.setDescricao(rs.getString("descricao"));
                curso.setDuracao(rs.getInt("duracao"));
                curso.setPreco(rs.getDouble("preco"));

                // Get the banner image as InputStream
                InputStream bannerInputStream = rs.getBinaryStream("banner");
                // Convert InputStream to Base64-encoded String
                String base64Image = convertToBase64(bannerInputStream);
                // Set the Base64-encoded image in the curso bean
                curso.setBannerBase64(base64Image);

                cursos.add(curso);
            }

            // Definir a lista de cursos como um atributo no objeto request
            request.setAttribute("cursos", cursos);

            // Encaminhar para a página JSP para exibir os cursos
            request.getRequestDispatcher("AdminCursos.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error retrieving cursos");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } finally {
            // Fechar recursos e conexão com o banco de dados
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    

    private String convertToBase64(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }

        byte[] bytes = inputStream.readAllBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }
}
