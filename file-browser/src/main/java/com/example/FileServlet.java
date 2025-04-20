package com.example;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class FileServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log("Method init =)");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Method service enter\n");
        super.service(req, resp);
        resp.getWriter().write("Method service exit\n");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path");
        if (path == null || path.isEmpty()) {
            path = System.getProperty("user.home");
        }
        File currentDir = new File(path);
        File[] files = currentDir.listFiles();

        request.setAttribute("files", files);
        request.setAttribute("path", currentDir.getAbsolutePath());
        request.setAttribute("date", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        request.getRequestDispatcher("/views/files.jsp").forward(request, response);
    }
}
