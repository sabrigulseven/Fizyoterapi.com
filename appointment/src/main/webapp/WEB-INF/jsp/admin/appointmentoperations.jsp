<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Randevu Yönetimi</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/head.jsp" %>
	
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6">
            <h2>Randevu Yönetimi</h2>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-md-4">
            <form:form method="post" action="/admin/appointments">
                <div class="form-group">
                    <label for="physiotherapist">Fizyoterapist Seçimi</label>
                    <select class="form-control" id="physiotherapist" name="physiotherapist">
                        <option value="">Hepsi</option>
                        <c:forEach items="${physiotherapists}" var="physiotherapist">
       						 <option value="${physiotherapist.id}" ${physiotherapist.id == selectedPhysiotherapistId ? 'selected' : ''}>${physiotherapist.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="startDate">Başlangıç Tarihi</label>
                    <input type="date" class="form-control" id="startDate" name="startDate">
                </div>
                <div class="form-group">
                    <label for="endDate">Bitiş Tarihi</label>
                    <input type="date" class="form-control" id="endDate" name="endDate">
                </div>
                <button type="submit" class="btn btn-primary">Filtrele</button>
            </form:form>
        </div>
        <div class="col-md-8">
            <table class="table">
                <thead>
                <tr>
                    <th>Fizyoterapist</th>
                    <th>Hasta</th>
                    <th>Tarih</th>
                    <th>Saat</th>
                    <th>Katılım</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${appointments}" var="appointment">
                    <tr>
                        <td>${appointment.physiotherapist.name}</td>
                        <td>${appointment.patient.name}</td>
                        <td>${appointment.date}</td>
                        <td>${appointment.time}</td>
                        <td>${appointment.attendance == true ? 'Geldi' : 'Gelmedi'}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
<!-- Bootstrap JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>