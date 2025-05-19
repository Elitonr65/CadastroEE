<%@page import="cadastroee.model.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%
    List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
%>
<html>
<head><title>Lista de Produtos</title></head>
<body>
    <h1>Produtos Cadastrados</h1>
    <p><a href="ServletProdutoFC?acao=formIncluir">Novo Produto</a></p>
    <table border="1" cellpadding="5">
        <tr><th>ID</th><th>Nome</th><th>Quantidade</th><th>Preço</th><th>Ações</th></tr>
        <%
            if (produtos != null) {
                for (Produto p : produtos) {
        %>
        <tr>
            <td><%= p.getIdProduto() %></td>
            <td><%= p.getNome() %></td>
            <td><%= p.getQuantidade() %></td>
            <td><%= p.getPrecoVenda() %></td>
            <td>
                <a href="ServletProdutoFC?acao=formAlterar&id=<%= p.getIdProduto() %>">Alterar</a>
                <a href="ServletProdutoFC?acao=excluir&id=<%= p.getIdProduto() %>">Excluir</a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
