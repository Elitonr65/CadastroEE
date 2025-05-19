<%@page import="cadastroee.model.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Produto p = (Produto) request.getAttribute("produto");
    String acao = (p == null) ? "incluir" : "alterar";
%>
<html>
<head><title>Cadastro de Produto</title></head>
<body>
    <h1><%= (p != null) ? "Alterar Produto" : "Novo Produto" %></h1>
    <form action="ServletProdutoFC" method="post">
        <input type="hidden" name="acao" value="<%= acao %>"/>
        <% if (p != null) { %>
            <input type="hidden" name="id" value="<%= p.getIdProduto() %>"/>
        <% } %>
        <p>Nome: <input type="text" name="nome" value="<%= (p != null) ? p.getNome() : "" %>"/></p>
        <p>Quantidade: <input type="text" name="quantidade" value="<%= (p != null) ? p.getQuantidade() : "" %>"/></p>
        <p>Preço de Venda: <input type="text" name="precoVenda" value="<%= (p != null) ? p.getPrecoVenda() : "" %>"/></p>
        <input type="submit" value="<%= (p != null) ? "Salvar Alterações" : "Cadastrar Produto" %>"/>
    </form>
</body>
</html>
