<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">  
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>
   	<body>	
   		<p>Visitors</p>
   		<ul>
   			<c:forEach items="${visitors}" var="each">
   				<li><a href = "/lab/blog/${each.id}">${each.nickname}</a></li>
   			</c:forEach>
   		</ul>

   		<p>Articles</p>
   		<ul>
   			<c:forEach items="${articles}" var="each">
   				<li>Title : ${each.title} ${each.createdate} [${each.hit}]</li>
   				<li>Contents : ${each.contents}</li>
   			</c:forEach>
   		</ul>

   		<p>Comments</p>
   		<ul>
   			<c:forEach items="${comments}" var="each">
   				<li>${each.comment} ${each.createdate}</li>
   			</c:forEach>
   		</ul>

   		<p>Profile</p>
   		<ul>
  			<li>name : ${profile.name}</li>
  			<li>age : ${profile.age}</li>
  			<li>hobby : ${profile.hobby}</li>
   		</ul>
	</body>
</html>
