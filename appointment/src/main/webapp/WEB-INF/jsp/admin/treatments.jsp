<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tedavi Yönetimi</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
 <%@ include file="/WEB-INF/jsp/head.jsp" %>
    <div class="container">
        <h1>Tedavi Yönetimi</h1>
        <hr>
        <h2>Tedaviler</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Açıklama</th>
                    <th>Eylemler</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="treatment" items="${treatments}">
                    <tr>
                        <td>${treatment.description}</td>
                        <td>
                            <a href="/admin/treatments/view/${treatment.id}" target="_blank" class="btn btn-primary">Görüntüle</a>
            				<a href="#" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal-${treatment.id}">Sil</a>
                        </td>
                    </tr>
                    <div class="modal fade" id="deleteModal-${treatment.id}" tabindex="-1" aria-labelledby="deleteModalLabel-${treatment.id}" aria-hidden="true">
				        <div class="modal-dialog">
				            <div class="modal-content">
				                <div class="modal-header">
				                    <h5 class="modal-title" id="deleteModalLabel-${treatment.id}">Tedaviyi Sil</h5>
				                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				                </div>
				                <div class="modal-body">
				                    <p>Tedaviyi silmek istediğinize emin misiniz?</p>
				                </div>
				                <div class="modal-footer">
				                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">İptal</button>
				                    <a href="/admin/treatments/delete/${treatment.id}" class="btn btn-danger">Sil</a>
				                </div>
				            </div>
				        </div>
				    </div>
                </c:forEach>
            </tbody>
        </table>
        <hr>
        <h2>Tedavi Ekle</h2>
        <form action="/admin/treatments/add" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="name" class="form-label">Açıklama:</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="mb-3">
                <label for="file" class="form-label">Dosya:</label>
                <input type="file" class="form-control" id="file" name="file" accept=".pdf,.doc,.docx" required>
            </div>
            <button type="submit" class="btn btn-primary">Kaydet</button>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
