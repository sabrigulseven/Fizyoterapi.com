<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Randevularm</title>
  </head>

  <body>
    <%@ include file="/WEB-INF/jsp/head.jsp" %>

    <div class="container mt-5">
      <div class="row">
        <div class="col-md-12 mx-auto">
          <h2 class="text-center mb-4">Randevularm</h2>
          <div class="row">
            <div class="col-md-6">
              <h4>Bugünün Randevuları</h4>
              <table class="table table-striped table-hover">
                <thead>
                  <tr>
                    <th>Saat</th>
                    <th>Hasta</th>
                    <th>Durum</th>
                    <th>Eylem</th>
                  </tr>
                </thead>
                <tbody>
                  <c:if test="${not empty appointmentsToday}">
                    <c:forEach items="${appointmentsToday}" var="appointment">
                      <tr>
                        <td>${appointment.time}</td>
                        <td>
                          ${empty appointment.patient ? '-' :
                          appointment.patient.name}
                        </td>
                        <td>
                        	<c:if test="${not empty appointment.patient}">
                          		${appointment.attendance ? 'Geldi' : 'Gelmedi'}
                          	</c:if>
                          	<c:if test="${empty appointment.patient}">
                          		Boş
                          	</c:if>
                        </td>
                        <td>
                          <c:if test="${not empty appointment.patient}">
                            <a
                              href="/physiotherapist/patient/${appointment.patient.id}"
                              class="btn btn-primary btn-sm"
                              >Hastayı Görüntüle</a
                            >
                          </c:if>
                        </td>
                      </tr>
                    </c:forEach>
                  </c:if>
                  <c:if test="${empty appointmentsToday}">
                    <tr>
                      <td colspan="3">Bugün için randevunuz bulunmamaktadır.</td>
                    </tr>
                  </c:if>
                </tbody>
              </table>
            </div>
            <div class="col-md-6">
              <h4>Gelecek Randevular</h4>
              <table class="table table-striped table-hover">
                <thead>
                  <tr>
                    <th>Tarih</th>
                    <th>Zaman</th>
                    <th>Hasta</th>
                    <th>Eylem</th>
                  </tr>
                </thead>
                <tbody>
                  <c:if test="${not empty appointmentsUpcoming}">
                    <c:forEach items="${appointmentsUpcoming}" var="appointment">
                      <tr>
                        <td>${appointment.date}</td>
                        <td>${appointment.time}</td>
                        <td>
                          ${empty appointment.patient ? '-' :
                          appointment.patient.name}
                        </td>
                        <td>
                          <c:if test="${not empty appointment.patient}">
                            <a
                              href="/physiotherapist/patient/${appointment.patient.id}"
                              class="btn btn-primary btn-sm"
                              >Hastayı Görüntüle</a
                            >
                          </c:if>
                        </td>
                      </tr>
                    </c:forEach>
                  </c:if>
                  <c:if test="${empty appointmentsUpcoming}">
                    <tr>
                      <td colspan="4">Planlanmış randevunuz bulunmamaktadır.</td>
                    </tr>
                  </c:if>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
