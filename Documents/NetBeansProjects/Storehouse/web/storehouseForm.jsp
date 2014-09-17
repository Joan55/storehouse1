<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formularz magazynu</title>
    </head>
    <body>
    <center>
        <h1>wprowadź towar na magazyn</h1>
        <form method="POST" action="checkStorehouseForm.jsp">
            <table>
                <tr>
                    <td><label for="name">Wprowadź nazwę produktu</label></td>
                    <td><input id="name" name="name" type="text"></td>
                </tr>
                <tr>
                    <td><label for="description">Wprowadź mięsność produktu</label></td>
                    <td><input id="description" name="description" type="text"></td>
                </tr>
                <tr>
                    <td><label for="price">Wprowdź cenę produktu</label></td>
                    <td><input id="price" name="price" type="text"></td>
                </tr>
                <tr>
                    <td><label for="category">Wybierz kategorię produktu</label></td>
                    <td>
                        <select id="category" name="category">
                            <option value="BEEF">wołowina</option>
                            <option value="PORK">wieprzowina</option>
                            <option value="CHICKEN">drób</option>
                            <option value="MUTTON">baranina</option>
                            <option value="GAME">dziczyzna</option>
                            <option value="MIX">mieszane</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="quantity">Podaj ilość produktu:</label></td>
                    <td><input id="quantity" name="quantity" type="text"></td>
                </tr>
            </table>
            <input type="submit" value="Zapisz"/>
        </form>
        <a href="controller.jsp">Powrót</a>
    </center>
</body>
</html>
