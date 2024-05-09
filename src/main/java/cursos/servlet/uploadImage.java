package cursos.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import cursos.base.DB;

@WebServlet(name = "uploadImage", urlPatterns = {"/uploadImage"})
@MultipartConfig(maxFileSize = 156177216) 
public class uploadImage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            
            String titulo = request.getParameter("titulo");
            String descricao = request.getParameter("descricao");
            int duracao = Integer.parseInt(request.getParameter("duracao"));
            double preco = Double.parseDouble(request.getParameter("preco"));
            
            
            Part bannerPart = request.getPart("bannerImage");
            InputStream bannerInputStream = bannerPart.getInputStream();

            
            Connection con = null;
            PreparedStatement psCurso = null;
            ResultSet generatedKeys = null;

            try {
                con = DB.getConnection();
                psCurso = con.prepareStatement("INSERT INTO cursos (titulo, descricao, duracao, preco, banner) VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

                
                psCurso.setString(1, titulo);
                psCurso.setString(2, descricao);
                psCurso.setInt(3, duracao);
                psCurso.setDouble(4, preco);

                
                psCurso.setBlob(5, bannerInputStream);

                
                psCurso.executeUpdate();

                
                int idCurso;
                try (ResultSet generatedKeys1 = psCurso.getGeneratedKeys()) {
                    if (generatedKeys1.next()) {
                        idCurso = generatedKeys1.getInt(1);

                        
                        for (Part part : request.getParts()) {
                            if ("videos".equals(part.getName())) {
                                String fileName = extractFileName(part);
                                String videoTitulo = fileName.replaceFirst("\\.[^.]+$", "");
                                InputStream is = part.getInputStream();

                                PreparedStatement psVideo = con.prepareStatement("INSERT INTO videos (id_curso, titulo, video) VALUES (?, ?, ?)");
                                psVideo.setInt(1, idCurso);
                                psVideo.setString(2, videoTitulo);
                                psVideo.setBlob(3, is);
                                psVideo.executeUpdate();

                                is.close();
                                System.out.println("Video Title: " + videoTitulo);
                            }
                        }

                        response.sendRedirect("AdminCursos");
                    } else {
                        throw new SQLException("Failed to retrieve auto-generated ID for curso.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace(); 
                response.sendRedirect("error.jsp");
            } finally {
                
                if (generatedKeys != null) {
                    try {
                        generatedKeys.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (psCurso != null) {
                    try {
                        psCurso.close();
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
                if (bannerInputStream != null) {
                    bannerInputStream.close();
                }
            }
        } catch (Exception e) {
            
            e.printStackTrace();
            
            response.sendRedirect("error.jsp");
        }
    }

    
    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return "";
    }
}
