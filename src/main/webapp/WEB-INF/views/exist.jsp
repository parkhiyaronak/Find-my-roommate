<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Exit page</title>
</head>
<body>
	<%
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con =(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/findmyroomie", "root", "root");
            PreparedStatement psMerc = con.prepareStatement("SELECT  * FROM seeker WHERE " +
                    "merchantUserName = ?");
            psMerc.setString(1,request.getParameter("username"));
            ResultSet resM = psMerc.executeQuery();
            
            PreparedStatement psCust = con.prepareStatement("SELECT  * FROM user WHERE " +
                    "custUserName = ?");
            psCust.setString(1,request.getParameter("username"));
            ResultSet resC = psCust.executeQuery();
            
            if(resM.first() || resC.first()){
                out.print("User already exists");
            }else{
                out.print("User name is valid");
            }
        }catch (Exception e){
            System.out.println(e);  
        }
%>
</body>
</html>