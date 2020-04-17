<%-- 
    Document   : userManagement
    Created on : Mar 26, 2020, 7:58:01 PM
    Author     : Kami.Sureiya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage User Page</title>
        <style>
            body a{
                color: black;
                text-decoration: none;
            }
            a:active{
                color: red;
            }

        </style>
    </head>
    <body>
        <h1>Welcome, ${sessionScope.ACCOUNT.userName} to User Management</h1>

        <c:url var="logoutLink" value="MainController">
            <c:param name="btnAction" value="Logout"></c:param>
        </c:url>
        <h3><a href="${logoutLink}">Logout</a></h3>
        <form action="MainController" method="POST">
            Search User Name: <input type="text" name="txtSearch" value="${sessionScope.SEARCH_USER_ERROR}">
            <input type="submit" value="SearchUser" name="btnAction" />
            &NegativeMediumSpace; ${requestScope.SEARCH_USER_ERROR}<br><br>

            <input type="submit" name="btnAction" value="PreviousUser"/>
            &NegativeMediumSpace; ${sessionScope.PAGENUM} &NegativeMediumSpace;
            <input type="submit" name="btnAction" value="NextUser"/> <br><br>
        </form>
        <div>
            <c:if test="${requestScope.SEARCHUSER != null}">
                <c:if test="${not empty requestScope.SEARCHUSER}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>User ID</th>
                                <th>User Name</th>
                                <th>Role</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="list" varStatus="counter" items="${requestScope.SEARCHUSER}">
                            <form action="MainController" method="POST">
                                <tr>
                                    <td>${counter.count}</td>

                                    <td>
                                        ${list.userID}
                                        <input type="hidden" name="txtUserID" value="${list.userID}"/>
                                    </td>
                                    <td>
                                        <input type="text" name="txtUserName" value="${list.userName}"/>
                                    </td>
                                    <td>
                                        <select name="txtRoleID">
                                            <c:forEach var="listRole" varStatus="counter" items="${sessionScope.ROLE}">
                                                <c:if test="${list.roleID == listRole.roleID}">
                                                    <option value="${listRole.roleID}" selected >${listRole.roleID} - ${listRole.roleName}</option>
                                                </c:if>
                                                <c:if test="${list.roleID != listRole.roleID}">
                                                    <option value="${listRole.roleID}">${listRole.roleID} - ${listRole.roleName}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <c:url var="deleteLink" value="MainController">
                                            <c:param name="txtUserID" value="${list.userID}"></c:param>
                                            <c:param name="txtSearch" value="${param.txtSearch}"></c:param>
                                            <c:param name="btnAction" value="DeleteUser"></c:param>
                                        </c:url>
                                        <a href="${deleteLink}" onclick="return confirm('Do you wanna Delete userID: ${list.userID}?');">Delete</a>
                                    </td>
                                    <td>
                                        <input type="submit" name="btnAction" value="UpdateUser"/>
                                        <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${empty requestScope.SEARCHUSER}">
                    <h1>No record Found </h1>
                </c:if>
            </c:if>
        </div>

        <h4>${requestScope.UPDATE_ERROR.userNameError}</h4>
        <h4>${requestScope.DELETE_ERROR}</h4>

        <c:url var="backLink" value="MainController">
            <c:param name="btnAction" value="BackToAdmin"></c:param>
        </c:url>
        <h3><a href="${backLink}"><<< Back</a></h3>
    </body>
</html>
