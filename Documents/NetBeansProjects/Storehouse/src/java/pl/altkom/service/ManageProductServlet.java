package pl.altkom.service;

import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.altkom.beans.Manager;
import pl.altkom.entity.Product;

@WebServlet(urlPatterns = "/manageProducts")
public class ManageProductServlet extends HttpServlet {

    @Inject
    private Manager dao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = dao.getProducts();
        req.setAttribute("products", products);
        getServletContext().getRequestDispatcher("/storehouseManager.jsp").forward(req, resp);
    }

}
