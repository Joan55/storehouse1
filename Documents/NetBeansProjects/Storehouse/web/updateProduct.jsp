<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="product" class="pl.altkom.entity.Product" scope="request"/>
<jsp:setProperty name="product" property="*"/>
<jsp:forward page="/updateProduct" />