package controller;

import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ejb.EJB;
import java.io.IOException;

@WebServlet(name = "ServletProdutoFC", urlPatterns = {"/ServletProdutoFC"})
public class ServletProdutoFC extends HttpServlet {

    @EJB
    private ProdutoFacadeLocal facade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String destino;

        if ("formIncluir".equals(acao) || "formAlterar".equals(acao)) {
            destino = "ProdutoDados.jsp";

            if ("formAlterar".equals(acao)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Produto p = facade.find(id);
                request.setAttribute("produto", p);
            }

        } else {
            destino = "ProdutoLista.jsp";

            if (null != acao) switch (acao) {
                case "listar" -> request.setAttribute("produtos", facade.findAll());
                case "incluir" -> {
                    Produto p = new Produto();
                    p.setNome(request.getParameter("nome"));
                    p.setQuantidade(Integer.valueOf(request.getParameter("quantidade")));
                    p.setPrecoVenda(Float.valueOf(request.getParameter("precoVenda")));
                    facade.create(p);
                    request.setAttribute("produtos", facade.findAll());
                    }
                case "alterar" -> {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Produto p = facade.find(id);
                    p.setNome(request.getParameter("nome"));
                    p.setQuantidade(Integer.valueOf(request.getParameter("quantidade")));
                    p.setPrecoVenda(Float.valueOf(request.getParameter("precoVenda")));
                    facade.edit(p);
                    request.setAttribute("produtos", facade.findAll());
                    }
                case "excluir" -> {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Produto p = facade.find(id);
                    facade.remove(p);
                    request.setAttribute("produtos", facade.findAll());
                    }
                default -> {
                }
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher(destino);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
