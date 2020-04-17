<%-- 
    Document   : admin
    Created on : Mar 18, 2020, 2:30:46 PM
    Author     : Kami.Sureiya
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Room Page</title>
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
        <h1>Welcome, ${sessionScope.ACCOUNT.userName} to Room Management</h1>

        <c:url var="logoutLink" value="MainController">
            <c:param name="btnAction" value="Logout"></c:param>
        </c:url>
        <h3><a href="${logoutLink}">Logout</a></h3>
        <form action="MainController" method="POST">
            <form action="MainController" method="POST">
            Search Hotel Name: <input type="text" name="txtSearch" value="${sessionScope.SEARCH_ROOM_VALUE}">
            <input type="submit" value="SearchRoom" name="btnAction" />
            &NegativeMediumSpace; ${requestScope.SEARCH_ROOM_ERROR}<br><br>

            Area : <input type="text" name="txtAddress" value="${sessionScope.ADDRESS}"/>${requestScope.ADDRESS_ERROR}<br>
            Check in : <input type="date" name="txtCheckIn" value="${sessionScope.CHECKIN}"/>${requestScope.DATE_ERROR.checkInDateError}<br>
            Check out: <input type="date" name="txtCheckOut" value="${sessionScope.CHECKOUT}"/>${requestScope.DATE_ERROR.checkOutDateError}<br>
            Amount of Room: <input type="number" name="txtAmount" value="${sessionScope.AMOUNT}"/>${requestScope.AMOUNT_ERROR}<br>
            <input type="submit" value="SearchByDate" name="btnAction" />${requestScope.SEARCH_DATE_ERROR}<br><br>

            <input type="submit" name="btnAction" value="PreviousRoom"/>
            &NegativeMediumSpace; ${sessionScope.PAGENUM} &NegativeMediumSpace;
            <input type="submit" name="btnAction" value="NextRoom"/> <br><br>
        </form>

        </form>
        <div>
            <c:if test="${requestScope.SEARCHROOM != null}">
                <c:if test="${not empty requestScope.SEARCHROOM}">
                    <c:if test="${sessionScope.HOTEL != null}">
                        <c:if test="${not empty sessionScope.HOTEL}">
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>Room ID</th>
                                        <th>Hotel Name</th>
                                        <th>Type Room</th>
                                        <th>Quantity</th>
                                        <th>Unit Price</th>
                                        <th>Delete</th>
                                        <th>Update</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="list" varStatus="counter" items="${requestScope.SEARCHROOM}">
                                    <form action="MainController" method="POST">
                                        <tr>
                                            <td>${counter.count}</td>

                                            <td>
                                                ${list.roomID}
                                                <input type="hidden" name="txtRoomID" value="${list.roomID}"/>
                                            </td>
                                            <td>
                                                <c:forEach var="listHotel" varStatus="counter" items="${sessionScope.HOTEL}">
                                                    <c:if test="${list.hotelID == listHotel.hotelID}">
                                                         ${list.hotelID} - ${listHotel.hotelName}
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>
                                                <select name="txtTypeID">
                                                    <c:forEach var="listType" varStatus="counter" items="${sessionScope.TYPE}">
                                                        <c:if test="${list.typeID == listType.typeID}">
                                                            <option value="${listType.typeID}" selected >${listType.typeID} - ${listType.typeName}</option>
                                                        </c:if>
                                                        <c:if test="${list.typeID != listType.typeID}">
                                                            <option value="${listType.typeID}">${listType.typeID} - ${listType.typeName}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td>
                                                <input type="number" name="txtQuantity" value="${list.quantity}"/>
                                            </td>
                                            <td>
                                                <input type="number" name="txtPrice" value="${list.price}"/>
                                            </td>
                                            <td>
                                                <c:url var="deleteLink" value="MainController">
                                                    <c:param name="txtRoomID" value="${list.roomID}"></c:param>
                                                    <c:param name="txtSearch" value="${param.txtSearch}"></c:param>
                                                    <c:param name="btnAction" value="DeleteRoom"></c:param>
                                                </c:url>
                                                <a href="${deleteLink}" onclick="return confirm('Do you wanna Delete roomID: ${list.roomID}?');">Delete</a>
                                            </td>
                                            <td>
                                                <input type="submit" name="btnAction" value="UpdateRoom"/>
                                                <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                                            </td>
                                        </tr>
                                    </form>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </c:if>
                </c:if>
                <c:if test="${empty requestScope.SEARCHROOM}">
                    <h1>No record Found </h1>
                </c:if>
            </c:if>
        </div>

        <h3><a href="createRoom.jsp">Create New Room >>></a></h3>
        <!--<h3><input type="submit" name="btnAction" value="CreateRoom"/></h3><br>-->
        <h4>${requestScope.UPDATE_ERROR.roomIDError}</h4>
        <h4>${requestScope.UPDATE_ERROR.typeRoomError}</h4>
        <h4>${requestScope.UPDATE_ERROR.quantityError}</h4>
        <h4>${requestScope.UPDATE_ERROR.priceError}</h4>
        <h4>${requestScope.DELETE_ERROR}</h4>

        <c:url var="backLink" value="MainController">
            <c:param name="btnAction" value="BackToAdmin"></c:param>
        </c:url>
        <h3><a href="${backLink}"><<< Back</a></h3>

    </body>
</html>
