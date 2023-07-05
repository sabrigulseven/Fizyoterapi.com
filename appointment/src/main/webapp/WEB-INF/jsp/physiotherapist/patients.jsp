<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Hastalarım</title>
  </head>

  <body>
    <%@ include file="/WEB-INF/jsp/head.jsp" %>
    <div class="container">
      <h1>Hastalarım</h1>
      <table class="table">
        <thead>
          <tr>
            <th>Kimlik Numarası</th>
            <th>İsim</th>
            <th>E-posta Adresi</th>
            <th>Eylem</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="patient" items="${patients}">
            <tr>
              <td>${patient.identityNumber}</td>
              <td>${patient.name}</td>
              <td>${patient.emailAddress}</td>
              <td>
                <a
                  href="/physiotherapist/patient/${patient.id}"
                  class="btn btn-primary"
                  >Görüntüle</a
                >
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </body>
</html>
