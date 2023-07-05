<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <title>Randevu Al</title>
  </head>
  <body>
    <%@ include file="/WEB-INF/jsp/head.jsp" %>
 	<div class="container mt-5">
        <h2>Fizyoterapist Seçimi</h2>
        <div class="row mt-3">
            <c:forEach var="physiotherapist" items="${physiotherapists}">
                <div class="col-md-4 mb-3">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${physiotherapist.name}</h5>
                            <p class="card-text">${physiotherapist.profession}</p>
                            <a href="/patient/appointment/book?physiotherapistId=${physiotherapist.id}" class="btn btn-primary">Seç</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
