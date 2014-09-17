/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.altkom.service;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.altkom.beans.Manager;
import pl.altkom.entity.Product;

/**
 *
 * @author Edward
 */
public class EditProductServlet extends HttpServlet {

    @Inject
    private Manager dao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = dao.getProductById(id);
        req.setAttribute("product", product);
        getServletContext().getRequestDispatcher("/editProduct.jsp").forward(req, resp);

    }
}
