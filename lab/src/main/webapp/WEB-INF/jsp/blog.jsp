<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">  
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>
   	<body>	
   		<p>최근 방문객</p>
   		<ul>
   			<c:forEach items="${visitors}" var="each">
   				<li><a href = "/lab/blog/${each.id}">${each.nickname}</a></li>
   			</c:forEach>
   		</ul>
	</body>
</html>
