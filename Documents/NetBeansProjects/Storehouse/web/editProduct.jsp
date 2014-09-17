<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aktualizacja danych o produktach</title>
    </head>
    <body>
        <jsp:useBean id="product" type="pl.altkom.entity.Product" scope="request"/>
        <h1>Aktualizacja danych o produktach</h1>
        <form method="post" action="updateProduct.jsp">
            <input type="hidden" name="id" value="${product.id}">
            <table>
                <tr>
                    <td>Nazwa:</td>
              
                    <td><input type="text" name="name" value="${product.name}"></td>
                </tr>
                <tr>
                    <td>Mięsność:</td>
                    <td><input type="text" name="description" value="${product.description}"></td>
                </tr>
                <tr>
                    <td>Cena:</td>
                    <td><input type="text" name="price" value="${product.price}"></td>
                </tr>
                <tr>
                    <td>Kategoria</td>
                    <td> <select id="category" name="category" value="${product.category}">
                            <option value="BEEF" ${product.category}>wołowina</option>
                            <option value="PORK" ${product.category}>wieprzowina</option>
                            <option value="CHICKEN"${product.category}>drób</option>
                            <option value="MUTTON"${product.category}>baranina</option>
                            <option value="GAME"${product.category}>dziczyzna</option>
                            <option value="MIX"${product.category}>mieszane</option>
                        </select></td>
                </tr>
                 <tr>
                    <td>Ilość:</td>
                    <td><input type="text" name="quantity" value="${product.quantity}"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Zapisz"></td>
                </tr>
            </table>
                <a href="manageProducts">Powrót do zarządzania</a>
        </form>
    </body>
</html>
