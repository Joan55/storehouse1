/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.altkom.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.altkom.beans.Manager;
import pl.altkom.entity.Product;

/**
 *
 * @author Edward
 */
@WebServlet(name = "sort", urlPatterns = {"/sort"})
public class SortServlet extends HttpServlet {

    @Inject
    private Manager dao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("byName") + "%";
        long min = 0;
        try {
            min = Long.parseLong(req.getParameter("byPriceMin"));
        } catch (NumberFormatException e) {
        }
        long max = Long.MAX_VALUE;
        try {
            max = Long.parseLong(req.getParameter("byPriceMax"));
        } catch (NumberFormatException e) {
        }
        String category = req.getParameter("byCategory") + "%";
       
        String sort = req.getParameter("sortBy");
        String order = req.getParameter("order");
        List<Product> products = dao.getProducts(name, min, max, category, sort, order);
        req.setAttribute("products", products);
        getServletContext().getRequestDispatcher("/storehouseManager.jsp").forward(req, resp);
    }

}
