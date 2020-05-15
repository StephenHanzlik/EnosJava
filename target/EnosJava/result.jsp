<%@ page import ="java.util.*" %>
<!DOCTYPE html>
<html>
<body>
<center>
    <h1>
        Station Metadata
    </h1>
        <%
List result= (List) request.getAttribute("stationMetadata");
Iterator it = result.iterator();
out.println("<br>Station Info: <br><br>");
while(it.hasNext()){
out.println(it.next()+"<br>");
}
%>
</body>
</html>