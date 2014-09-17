
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zarządzanie produktami</title>
    </head>
    <body>
        <h1>Zarządzaj produktami</h1>
    <hr>
        <form method="post" action="sort">
            Sortuj wg: <select name="sortBy">
                <option value="name" ${param.sortBy eq 'name'?"selected":""}>nazwa produktu</option>
                <option value="price" ${param.sortBy eq 'price'?"selected":""}>cena</option>
                <option value="category" ${param.sortBy eq 'category'?"selected":""}>kategoria</option>
            </select>
            <select name="order">
                <option value="asc" ${param.order eq 'asc'?"selected":""}>rosnąco</option>
                <option value="desc" ${param.order eq 'desc'?"selected":""}>malejąco</option>
            </select><p>
                Filtruj po:
            <table>
                <tr><th>nazwa</th><th>cena</th><th>kategoria</th></tr>
                <tr>
                    <td><input type="text" name="byName" value="${param.byName}"></td>
                    <td>od<input type="text" name="byPriceMin" value="${param.byPriceMin}">zł
                        do<input type="text" name="byPriceMax" value="${param.byPriceMax}">zł</td>
                    <td><select name="byCategory">
                            <option></option>
                            <option value="BEEF" ${param.byCategory eq 'BEEF'?"selected":""}>wołowina</option>
                            <option value="PORK" ${param.byCategory eq 'PORK'?"selected":""}>wieprzowina</option>
                            <option value="CHICKEN" ${param.byCategory eq 'CHICKEN'?"selected":""}>drób</option>
                            <option value="MUTTON" ${param.byCategory eq 'MUTTON'?"selected":""}>baranina</option>
                            <option value="GAME" ${param.byCategory eq 'GAME'?"selected":""}>dziczyzna</option>
                            <option value="MIX" ${param.byCategory eq 'MIX'?"selected":""}>mieszane</option>
                        </select></td>
                </tr>
            </table>
            <input type="submit" value="Wykonaj">
        </form>
        <hr>
        <table>
            <tr>
                <th>Nazwa</th>
                <th>Mięsność</th>
                <th>Cena</th>
                <th>Kategoria</th>
                <th>Ilość</th>
            </tr>
            <c:forEach var="p" items="${products}">
                <tr>
                    <td><c:out value="${p.name}"/></td>
                    <td><c:out value="${p.description}"/></td>
                    <td align="right"><c:out value="${p.price}"/></td>
                    <td><c:out value="${p.category}"/></td>
                    <td><c:out value="${p.quantity}"/></td>       
                    <td><a href="deleteProduct?id=${p.id}">Usuń</td>
                    <td><a href="editProduct?id=${p.id}">Edytuj</td>
                </tr>
            </c:forEach>
        </table>
        <a href="manageProducts">Czyść wyniki</a><br>
        <a href= "controller.jsp">powrót do strony głównej</a>
        
    </body>
</html>
