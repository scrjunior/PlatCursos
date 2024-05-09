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

                
                InputStream bannerInputStream = rs.getBinaryStream("banner");
                
                String base64Image = convertToBase64(bannerInputStream);
                
                curso.setBannerBase64(base64Image);

                cursos.add(curso);
            }

            
            request.setAttribute("cursos", cursos);

            
            request.getRequestDispatcher("AdminCursos.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error retrieving cursos");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } finally {
            
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
