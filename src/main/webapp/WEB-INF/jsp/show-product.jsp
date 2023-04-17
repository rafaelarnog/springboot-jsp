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
            margin-left: 30%;
            margin-right: 30%;
        }
        .card {
            padding: 1%;
        }
        h1 {
            text-align: center;
        }
        #div-btn-cadastrar {
            display: flex;
            justify-content: center;
        }
        #btn-return {
            margin-bottom: 20px;
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
        <a href="/" class="btn btn-secondary" id="btn-return">Voltar</a>
        <div class="card">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <h1>Produto - ${product.name}</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <p><b>Nome:</b> ${product.name}<p>
                        <p><b>Valor:</b> ${product.value}<p>
                        <p><b>Descrição:</b> ${product.description}<p>
                        <p><b>Desconto:</b> ${product.discount}<p>
                        <p><b>Valor com desconto:</b> ${product.discountedValue}<p>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <script src="/webjars/bootstrap/5.1.0/js/bootstrap.min.js"></script>
</body>
</html>
