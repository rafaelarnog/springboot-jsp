<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Meu Site</title>
    <link rel="stylesheet" href="/webjars/bootstrap/5.1.0/css/bootstrap.min.css">
    <style>
        main {
            margin-top: 2%;
            margin-bottom: 2%;
            margin-left: 3%;
            margin-right: 3%;
        }
        .card {
            padding: 1%;
        }
        .td-button {
            display: flex;
            justify-content: space-evenly;
        }
    </style>
</head>
<body>
    <header>
        <nav class="navbar navbar-dark bg-dark">
          <div class="container-fluid">
            <a class="navbar-brand" href="/">Produtos</a>
          </div>
        </nav>
    </header>
    <main>
        <div class="card">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-6">
                        <h1>Produtos</h1>
                    </div>
                    <div class="col-md-6">
                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                            <a href="/add-product"class="btn btn-primary">Novo Produto</a>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <c:if test="${empty products}">
                            <p>Sem produtos cadastrados no momento.</p>
                        </c:if>
                        <c:if test="${not empty products}">
                            <table class="table table-striped table-hover">
                                <thead class="text-center">
                                    <tr>
                                        <th>Nome</th>
                                        <th>Valor</th>
                                        <th>Descrição</th>
                                        <th>Desconto</th>
                                        <th>Valor Final</th>
                                        <th>Ações</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="product" items="${products}">
                                        <tr>
                                            <td>${product.name}</td>
                                            <td>${product.value}</td>
                                            <td>${product.description}</td>
                                            <td>${product.discount}</td>
                                            <td>${product.discountedValue}</td>
                                            <td class="td-button">
                                                <a href="/show-product/${product.id}" class="btn btn-primary">Visualizar</a>
                                                <a href="/edit-product/${product.id}" class="btn btn btn-dark">Editar</a>
                                                <form action="/delete/${product.id}" method="get">
                                                    <button type="submit" class="btn btn-danger">Deletar</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <script src="/webjars/bootstrap/5.1.0/js/bootstrap.min.js"></script>
</body>
</html>