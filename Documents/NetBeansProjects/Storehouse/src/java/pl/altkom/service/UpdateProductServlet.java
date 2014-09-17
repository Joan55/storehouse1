/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.altkom.service;

import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
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
public class UpdateProductServlet extends HttpServlet {

    @Inject
    private Manager dao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Product product = (Product) req.getAttribute("product");
            dao.updateProduct(product);
        } catch (java.lang.NumberFormatException e) {
            this.getServletContext().log("ta wartość jest niepoprawna", e);}
            resp.sendRedirect(req.getContextPath() + "/manageProducts");
        }

    }

