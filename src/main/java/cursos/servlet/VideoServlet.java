package cursos.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cursos.base.DB;

@WebServlet("/videoServlet")
public class VideoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        OutputStream out = null;

        try {
            int videoId = Integer.parseInt(request.getParameter("videoId"));

            con = DB.getConnection();
            ps = con.prepareStatement("SELECT video FROM videos WHERE id_video = ?");
            ps.setInt(1, videoId);
            rs = ps.executeQuery();

            if (rs.next()) {
                response.setContentType("video/mp4");

                out = response.getOutputStream();
                InputStream videoStream = rs.getBinaryStream("video");

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = videoStream.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

