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

@WebServlet(name = "GetCursosServlet", urlPatterns = {"/getcursos"}, loadOnStartup = 1)
public class GetCursosServlet extends HttpServlet {
	
	
	@Override
    public void init() throws ServletException {
        // Este método é chamado quando o servlet é inicializado
        super.init();
        
        // Você pode colocar aqui qualquer código de inicialização necessário
        // Este código será executado quando o servlet for carregado
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            
            request.setAttribute("cursos", cursos);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (SQLException e) {
            // Handle the exception, for example:
            e.printStackTrace();
            request.setAttribute("error", "Error retrieving cursos");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } finally {
            // Close the database resources and banner InputStreams
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Method to convert InputStream to Base64-encoded String
 // Method to convert InputStream to Base64-encoded String
    private String convertToBase64(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null; // Return null if InputStream is null
        }
        
        byte[] bytes = inputStream.readAllBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }

}
