/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.altkom.service;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.altkom.beans.Manager;
import pl.altkom.entity.Product;

public class SaveProductDataServlet extends HttpServlet {

    @EJB
    private Manager dao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");  
//        Product p = new Product();
//        p.setName(req.getParameter("name"));
//        p.setDescription(req.getParameter("description"));
//        p.setPrice(Long.parseLong(req.getParameter("price")));
//        p.setCategory(req.getParameter("category"));
//        p.setQuantity(Integer.parseInt(req.getParameter("quantity")));
        Product p = (Product) req.getAttribute("product");
        dao.saveProductData(p);
        resp.sendRedirect("controller.jsp");

    }
}
