<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Browser</title>
</head>
<body>
<h3><%= request.getAttribute("date") %></h3>
<h2><%= request.getAttribute("path") %></h2>
<a href="/servlet/files?path=<%= new File(request.getAttribute("path").toString()).getParent() %>">⬆ Вверх</a>
<hr/>
<ul>
<%
    File[] files = (File[]) request.getAttribute("files");
    if (files != null) {
        for (File f : files) {
            String fullPath = f.getAbsolutePath();
            if (f.isDirectory()) {
%>
                <li>[DIR] <a href="/servlet/files?path=<%= fullPath %>"><%= f.getName() %></a></li>
<%
            } else {
%>
                <li>[FILE] <a href="/servlet/download?file=<%= fullPath %>"><%= f.getName() %></a></li>
<%
            }
        }
    }
%>
</ul>
</body>
</html>
