<%--
  Created by IntelliJ IDEA.
  User: yirenyixin
  Date: 2022/6/16
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品页</title>
    <link rel="stylesheet" href="css/Product_page.css" type="text/css">
    <script>
        function getsize(a){
            document.getElementById("size").value=a.value;
        }
        function check(){
            var a=document.getElementById("size").value;
            if(a==""||a==null){
                alert("请选择尺码！！！")
                return false;
            }else{
                return true;
            }
        }
        function post1(){
            document.getElementById("form").action="Product?status=selectone";
        }
        function post2(){
            document.getElementById("form").action="Shopping_Cart?status=insert";
        }
    </script>
</head>
<body>
<%String img=request.getParameter("img");
String name=request.getParameter("name");
String price=request.getParameter("price");
String type=request.getParameter("type");
String color=request.getParameter("color");
String size=request.getParameter("size");
String id=request.getParameter("id");
String shopid=request.getParameter("shopid");
String flag= String.valueOf(session.getAttribute("uflag"));
if(type.equals("0")){
    type="无尺码";
}else if(type.equals("1")){
    type="女装";
}
else if(type.equals("2")){
    type="内衣";
}
else if(type.equals("3")){
    type="奢品";
}
else if(type.equals("4")){
    type="女鞋";
}
else if(type.equals("5")){
    type="男鞋";
}
else if(type.equals("6")){
    type="箱包";
}
else if(type.equals("7")){
    type="美妆";
}
else if(type.equals("8")){
    type="饰品";
}
else if(type.equals("9")){
    type="洗护";
}
else if(type.equals("10")){
    type="男装";
}
else if(type.equals("11")){
    type="运动";
}
else if(type.equals("12")){
    type="百货";
}
else if(type.equals("13")){
    type="手机";
}
else if(type.equals("14")){
    type="数码";
}
else if(type.equals("15")){
    type="企业礼品";
}
else if(type.equals("16")){
    type="家装";
}
else if(type.equals("17")){
    type="电器";
}
else if(type.equals("18")){
    type="车品";
}
else if(type.equals("19")){
    type="食品";
}
else if(type.equals("20")){
    type="生鲜";
}
else if(type.equals("21")){
    type="母婴";
}
else if(type.equals("22")){
    type="医药";
}
else if(type.equals("23")){
    type="保健";
}
else if(type.equals("24")){
    type="进口";
}
%>
<%--<%=img+" "+name+" "+price+" "+type+" "+color+" "+size+" "+id+" "+shopid%>--%>
<form  method="post" onsubmit="return check()" name="form" id="form">
<div class="tb-property">
<div class="tb-booth">
    <img src="imgweb/<%=img%>" class="J-imgbooth">
</div>
    <div class="tb-wrap">
        <div class="title"><h2 class="title1"><%=name%></h2></div>
        <div class="price">
            <h2 class="price1">价格</h2>
            <h2 style="float: left;color: #f10215;font-size: 25px;margin-left: 60px">¥</h2>
            <h2 style="font-size: 40px;color: #f10215;margin-left: 10px;margin-top: 9px"><%=price%></h2>
        </div>
        <div class="label">
            <h2 style="font-size: 20px;margin-left: 10px;">类型：</h2>
            <h2 style="margin-left: 100px;margin-top: 14.9px"><%=type%></h2>
        </div>
        <div class="size">
            <h2 style="font-size: 20px;margin-left: 10px;float: left">尺码：</h2>
            <%if(size.equals("1")){%>
            <div style="float: left;margin-top: 20px">
                <input type="radio" name="size1" style="float: left" onclick="getsize(this)" value="M">
                <h2  style="float: left;font-size: 10px;margin-top: 0px;margin-bottom: 0px;" >M</h2>
            </div>
            <div style="float: left;margin-top: 20px" style="float: left">
                <input type="radio" name="size1" style="float: left" onclick="getsize(this)" value="L">
                <h2  style="float: left;font-size: 10px;margin-top: 0px;margin-bottom: 0px;" >L</h2>
            </div>
            <div style="float: left;margin-top: 20px" style="float: left">
                <input type="radio" name="size1" style="float: left" onclick="getsize(this)" value="XL">
                <h2  style="float: left;font-size: 10px;margin-top: 0px;margin-bottom: 0px;" >XL</h2>
            </div>
            <%}else if(size.equals("2")){%>
            <div style="float: left;margin-top: 20px">
                <input type="radio" name="size1" style="float: left" onclick="getsize(this)" value="小">
                <h2  style="float: left;font-size: 10px;margin-top: 0px;margin-bottom: 0px;" >小</h2>
            </div>
            <div style="float: left;margin-top: 20px" style="float: left">
                <input type="radio" name="size1" style="float: left" onclick="getsize(this)" value="中">
                <h2  style="float: left;font-size: 10px;margin-top: 0px;margin-bottom: 0px;" >中</h2>
            </div>
            <div style="float: left;margin-top: 20px" style="float: left">
                <input type="radio" name="size1" style="float: left" onclick="getsize(this)" value="大">
                <h2  style="float: left;font-size: 10px;margin-top: 0px;margin-bottom: 0px;" >大</h2>
            </div>
            <%}else{%>
            <div style="float: left;margin-top: 20px">
                <h2  style="float: left;font-size: 10px;margin-top: 0px;margin-bottom: 0px;" >无尺码</h2>
            </div>
            <%}%>
        </div>
        <div class="color">
            <h2 style="font-size: 20px;margin-left: 10px;float: left">颜色：</h2>
            <div style="float: left;margin-top: 20px">
<%--                <input type="checkbox" name="color1" style="float: left" >--%>
                <h2  style="float: left;font-size: 10px;margin-top: 0px;margin-bottom: 0px;" ><%=color%></h2>
            </div>
        </div>
        <input type="hidden" value="<%=id%>" name="id">
        <input type="hidden" value="<%=name%>" name="name">
        <input type="hidden" value="<%=price%>" name="price">
        <input type="hidden" value="<%=type%>" name="type">
        <input type="hidden" value="<%=color%>" name="color">
        <%if(!size.equals("1")&&!size.equals("2")){%>
        <input type="hidden" value="<%="无尺码"%>" name="size" id="size">
        <%}else{%>
        <input type="hidden"  name="size" id="size">
        <%}%>
        <input type="hidden" value="<%=shopid%>" name="shopid" id="shopid">
<%--        购买者ID--%>
        <input type="hidden" value="<%=session.getAttribute("uId")%>" name="uid">
        <%if(!flag.equals("1")){%>
        <div style="width: 100%;height: 100px;display: flex">
        <input type="submit" value="立即购买" class="btn1" onclick="post1()" style="float: left">
        <input type="submit" value="加入购物车" class="btn2" onclick="post2()" style="float: right">
        </div>
        <%}%>
<%--        <input type="submit" value="">--%>
    </div>
</div>
</form>
</body>
</html>
