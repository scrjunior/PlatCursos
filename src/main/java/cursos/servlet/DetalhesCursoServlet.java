package cursos.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cursos.base.DB;
import cursos.bean.CursosBean;

@WebServlet("/detalhescurso")
public class DetalhesCursoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // Obter o ID do curso da requisição
            int cursoId = Integer.parseInt(request.getParameter("cursoId"));

            con = DB.getConnection();
            ps = con.prepareStatement("SELECT * FROM cursos WHERE id_curso = ?");
            ps.setInt(1, cursoId);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Criar um objeto CursosBean com os detalhes do curso
                CursosBean curso = new CursosBean();
                curso.setId(rs.getInt("id_curso"));
                curso.setTitulo(rs.getString("titulo"));
                curso.setDescricao(rs.getString("descricao"));
                curso.setDuracao(rs.getInt("duracao"));
                curso.setPreco(rs.getDouble("preco"));

                // Obter o banner como InputStream
                InputStream bannerInputStream = rs.getBinaryStream("banner");

                // Converter InputStream para Base64
                if (bannerInputStream != null) {
                    byte[] bytes = bannerInputStream.readAllBytes();
                    String base64Image = Base64.getEncoder().encodeToString(bytes);
                    curso.setBannerBase64(base64Image);
                }

                // Adicionar o curso como atributo na requisição
                request.setAttribute("curso", curso);

                // Encaminhar para a página de detalhes do curso (course.jsp)
                request.getRequestDispatcher("course.jsp").forward(request, response);
            } else {
                // Se o curso não for encontrado, redirecionar ou mostrar uma mensagem de erro
                response.sendRedirect("error.jsp"); // Exemplo: redirecionar para página de erro
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Tratamento de erro: redirecionar para página de erro
        } 
    }
}