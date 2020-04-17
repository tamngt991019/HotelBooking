<%-- 
    Document   : viewCart
    Created on : Mar 21, 2020, 2:28:11 PM
    Author     : Kami.Sureiya
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
        <style>
            body a{
                color: black;
                text-decoration: none;
            }
            a:active{
                color: red;
            }
            table{
                text-align: center;
            }
        </style>

    </head>
    <body>
        <h1>${sessionScope.ACCOUNT.userName}'s CART</h1>

        <c:if test="${sessionScope.CART != null}">
            <form action="MainController" method="POST">
                Check in : <input type="date" name="txtCheckIn" value="${sessionScope.CHECKIN}"/>${requestScope.DATE_ERROR.checkInDateError}<br>
                Check out: <input type="date" name="txtCheckOut" value="${sessionScope.CHECKOUT}"/>${requestScope.DATE_ERROR.checkOutDateError}<br>
                Number of Days : ${sessionScope.DAYNUM} <br>
                <input type="submit" value="GetDays" name="btnAction" />
                &NegativeMediumSpace;<br><br>


                <c:if test="${not empty sessionScope.CART}">
                    <c:set var="cartMAP" value="${sessionScope.CART}"/>
                    <c:if test="${not empty cartMAP}">
                        <c:set var="items" value="${cartMAP.cart}"/>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Hotel Name</th>
                                    <th>Hotel Address</th>
                                    <th>Type Room</th>
                                    <th>Amount</th>
                                    <th>Unit Price</th>
                                    <th>No. Days</th>
                                    <th>Update</th>
                                    <th>Remove</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" varStatus="counter" items="${items.entrySet()}">
                                    <c:set var="listRoom" value="${item.value}" />


                                    <tr>
                                        <td>
                                            ${counter.count}
                                            <c:set var="roomNo" value="${counter.count}"/>
                                            <input type="hidden" name="txtRoomID" value="${listRoom.roomID}"/>
                                            <input type="hidden" name="txtHotelID" value="${listRoom.hotelID}"/>
                                            <c:set var="totalAll" value="${totalAll + (listRoom.quantity * listRoom.price * sessionScope.DAYNUM)}"/>
                                        </td>
                                        <c:forEach var="listHotel" varStatus="counter" items="${sessionScope.HOTEL}">
                                            <c:if test="${listRoom.hotelID == listHotel.hotelID}">
                                                <td>
                                                    ${listHotel.hotelName}
                                                </td>
                                                <td>
                                                    ${listHotel.address}
                                                </td>
                                            </c:if>
                                        </c:forEach>
                                        <td>
                                            <c:forEach var="listType" varStatus="counter" items="${sessionScope.TYPE}">
                                                <c:if test="${listRoom.typeID == listType.typeID}">
                                                    ${listType.typeName}
                                                </c:if>
                                            </c:forEach>
                                            <input type="hidden" name="txtTypeID" value="${listRoom.typeID}"/>
                                        </td>
                                        <td>
                                            <input type="number" name="txtAmount" value="${listRoom.quantity}"/>
                                        </td>
                                        <td>
                                            ${listRoom.price}
                                            <input type="hidden" name="txtPrice" value="${listRoom.price}"/>
                                        </td>
                                        <td>${sessionScope.DAYNUM}</td>
                                        <td>
                                            <input type="submit" name="btnAction" value="UpdateCart"/>
                                        </td>
                                        <td>
                                            <c:url var="deleteLink" value="MainController">
                                                <c:param name="txtRoomID" value="${listRoom.roomID}"></c:param>
                                                <c:param name="txtIsRemove" value="${requestScope.ISREMOVE}"></c:param>
                                                <c:param name="btnAction" value="RemoveCart" ></c:param>
                                            </c:url>
                                            <a href="${deleteLink}" onclick="return confirm('Do you wanna Remove room No.${roomNo} ?');">Remove</a>
                                        </td>
                                    </tr>

                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>

                    <h1>Total All : ${totalAll}</h1>
                    <h4>${requestScope.UPDATE_ERROR.quantityError}</h4>
                    
                    <input type="hidden" name="txtTotalAll" value="${totalAll}"/>
                    <h3><input type="submit" name="btnAction" value="ConfirmBooking"/></h3>
                    ${requestScope.ROOMERROR}
                    <c:url var="clearCart" value="MainController">
                        <c:param name="btnAction" value="ClearCart"></c:param>
                    </c:url>
                    <h3><a href="${clearCart}">Clear Cart</a></h3>

                    <c:url var="backToUser" value="MainController">
                        <c:param name="btnAction" value="BackToUser"></c:param>
                    </c:url>
                    <h3><a href="${backToUser}"><<< Continue booking room</a></h3>

                </c:if>
            </form>
            <c:if test="${empty sessionScope.CART}">
                <h1>No room found!!!</h1>
            </c:if>

        </c:if>

        <c:if test="${sessionScope.CART == null}">
            <c:url var="back" value="MainController">
                <c:param name="btnAction" value="ManageRoom"></c:param>
            </c:url>
            <h3>No room found, <a href="${back}" style="text-decoration: underline; font-style: italic" >CLICK HERE</a> to booking room !</h3>
        </c:if>
    </body>
</html>
