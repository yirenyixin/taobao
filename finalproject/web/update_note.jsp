<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="jmu.vo.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>修改</title>
    <link rel="stylesheet" href="css/list_note1.css" type="text/css">
    <script type="text/javascript">
        function getName(cell){

            var myFile = document.getElementById("file").value;
            var length = myFile.length;
            var x = myFile.lastIndexOf("\\");
            x++;
            var fileName = myFile.substring(x,length);
            document.getElementById("imgfile").value = fileName;
            document.getElementById('getImg1').src= window.URL.createObjectURL(cell.files[0]);

        }
        function showDialog(){
            alert("上传成功");
        }
    </script>
</head>
<body>
<%String author=request.getParameter("author");
String uname=request.getParameter("uname");%>
<form action="Note" method="post">
 <table>
  <tr></tr>
  <tr>
      <input type="hidden" id="imgfile" name="img">
	  <td><input type="hidden" name="title" value="${note.title}"></td>
  </tr>
  <tr>

	  <input type="hidden" name="author" value=${note.author}></td>
  </tr>
  <tr>
	  <td><textarea name="content" cols="30" rows="6">${note.content}</textarea></td>
  </tr>
     <%if(author.equals(uname)){%>
  <tr><td colspan="2">
		  <input type="hidden" name="id" value=${note.id}>
		  <input type="hidden" name="status" value="update">
		  <input type="submit" value="更新 ">
		  <input type="reset" value="重置">
	  </td>
  </tr>
     <%}%>
</table>
</form>
<form action="sucess.jsp" method="post" enctype="multipart/form-data" target="hidden_frame">

    <div id="img1">
        <img  src="./img/${note.img}" id="getImg1" style="width: 100px;height: 100px;">
    </div>
    <%if(author.equals(uname)){%>
    <input type="file" name="pic" id="file" onchange="getName(this)">
    <input type="submit" value="上传" onclick="showDialog()">
    <%}%>
</form>
<iframe name='hidden_frame' id="hidden_frame" style="display:none"></iframe>

<hr />	<br />
<%int a=0;%>
<c:forEach items="${all1}" var="revert" >
    <form>
        <div class="main0" style="height: 200px;width: 100%;margin: 0 auto;z-index: 3" id="main0<%=a%>" >
            <li style="list-style-type:none">
                <div class="main1" style="z-index: 1">
<%--                    <a href="Note?id=${note.id}&status=selectid&uname=<%=session.getAttribute("uname")%>&author=${note.author}" class="title"  style="    font-size: 20px;float: left;margin-top: 0px;margin-bottom: 0px;">${note.title}</a>--%>
                        <img src="./img/${revert.authorimg}" style="float: left;height: 30px;width: 30px">
                        <h2 class="author" style="float: left;margin-top: 0px;margin-bottom: 0px;" >${revert.author}</h2>
                        <td><a style="float: right;" href="Revert?revertID=${revert.revertID}&status=delete&writer=${revert.writer}&author=${revert.author}&uname=<%=session.getAttribute("uname")%>">删除</a></td>
                </div>
                <div class="main2" style="height: 200px;z-index: 2" id="main2<%=a%>"  >
                    <h2 class="content" style="margin-top: auto;padding-top: 50px;padding-left: 100px;">${revert.content}</h2>
                    <img src="./img/${revert.img}" id="getImg" alt="<%=a%>" style="padding-left: 100px;" height="100px" width="100px"  onclick="img(this)">
                </div>
                <h2 class="time" style="margin-top: -26px;float: right">${revert.writeDate}</h2>
            </li>
        </div>
    </form>
    <%a++;%>
</c:forEach>
<h3><a href="insert_revert.jsp?title=${note.title}&author=${note.author}&noteid=${note.id}&status=insert" >新增回帖</a></h3>


<%if(author.equals(uname)){%>
<h3><a href="Note?status=delete&author=${note.author}&id=${note.id}&uname=<%=session.getAttribute("uname")%>">删除帖子</a></h3>
<%}%>
<%--<h3><a href="Revert?title=${note.title}&author=${note.author}&noteid=${note.id}&status=select" >查看回帖列表</a></h3>--%>
<h3><a href="Note?status=selectall">回到留言列表页</a></h3>
</body>
</html>