<%-- 
    Document   : wynik
    Created on : 2017-02-11, 18:20:22
    Author     : katarzyna_bialach
--%>

<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <body>
        <h1 align="center">JSP z rekomendacjami dla piwa</h1>
        <p>
            <%
                List styles = (List)request.getAttribute("styles");
                Iterator it = styles.iterator();
                while (it.hasNext()){
                    out.println("<br>Spróbuj: " + it.next());
                }
                %>
    </body>
</html>
