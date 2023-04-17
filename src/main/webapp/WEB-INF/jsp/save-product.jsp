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
        #div-btn-create {
            display: flex;
            justify-content: center;
            margin-top: 15px;
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
                        <c:if test="${create == true}">
                            <h1>Cadastrar Produto</h1>
                        </c:if>
                        <c:if test="${create == false}">
                            <h1>Editar Produto</h1>
                        </c:if>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <form>
                            <div class="mb-3">
                                <label for="nameInput" class="form-label">Nome</label>
                                <input type="text" class="form-control" id="nameInput" value="${product.name}">
                            </div>
                            <div class="mb-3">
                                <label for="valueInput" class="form-label">Valor</label>
                                <input type="number" class="form-control" id="valueInput" value="${product.value != null ? product.value : 0.0}" onInput="editDiscountedValue()">
                            </div>
                            <div class="mb-3">
                                <label for="descriptionInput" class="form-label">Descrição</label>
                                <input type="text" class="form-control" id="descriptionInput" value="${product.description}">
                            </div>
                            <div class="mb-3">
                                <label for="discountInput" class="form-label">Desconto</label>
                                <input type="number" class="form-control" id="discountInput" value="${product.discount != null ? product.discount : 0}" onInput="editDiscountedValue()" aria-describedby="discountHelp">
                                <div id="discountHelp" class="form-text">Formato: % (Exemplo: 80%)</div>
                            </div>
                            <div>
                                <label for="discountedValueInput" class="form-label">Novo Valor</label>
                                <input type="text" class="form-control" id="discountedValueInput" value="${product.discountedValue != null ? product.discountedValue : 0.0}" disabled>
                            </div>
                            <div id="div-btn-create">
                                <c:choose>
                                    <c:when test="${create == false}">
                                        <button type="submit" class="btn btn-primary" onClick="editProduct(${product.id})">Editar</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" class="btn btn-primary" onClick="addProduct()">Cadastrar</button>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <script src="/webjars/bootstrap/5.1.0/js/bootstrap.min.js"></script>
    <script>
        function editDiscountedValue() {
              var value = parseFloat(document.getElementById("valueInput").value);
              if(isNaN(value)) {
                value = 0.0;
              }
              var discount = parseFloat(document.getElementById("discountInput").value);
              if(isNaN(discount)) {
                discount = 0.0;
              }
              var discountedValue = value - (value * discount / 100);
               document.getElementById("discountedValueInput").value = discountedValue.toFixed(2);
        }

        function addProduct(){
            const data = {
                name: document.getElementById('nameInput').value,
                value: document.getElementById('valueInput').value,
                description: document.getElementById('descriptionInput').value,
                discount: document.getElementById('discountInput').value
            }
            fetch('/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
            .then(response => response.json())
            .catch(error => console.error(error));
        }

        function editProduct(id){
            const data = {
                name: document.getElementById('nameInput').value,
                value: document.getElementById('valueInput').value,
                description: document.getElementById('descriptionInput').value,
                discount: document.getElementById('discountInput').value
            }
            fetch('/edit/'+id, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
            .then(response => {
                response.json();
                location.reload();
            })
            .catch(error => console.error(error));
        }
    </script>
</body>
</html>
