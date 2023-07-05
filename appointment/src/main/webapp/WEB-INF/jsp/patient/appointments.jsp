<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <title>Randevularım</title>
  </head>
  <body>
    <%@ include file="/WEB-INF/jsp/head.jsp" %>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js"></script>
    <div class="container my-5">
      <h2>Yaklaşan Randevularım</h2>
      <table class="table table-striped">
        <thead>
          <tr>
            <th>Fizyoterapist</th>
            <th>Tarih</th>
            <th>Saat</th>
            <th>Eylemler</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="appointment" items="${appointmentsFuture}">
            <tr>
              <td>${appointment.physiotherapist.name}</td>
              <td>${appointment.date}</td>
              <td>${appointment.time}</td>
              <td>
                <button
                  type="button"
                  class="btn btn-danger"
                  data-bs-toggle="modal"
                  data-bs-target="#cancelAppointmentModal-${appointment.appointmentId}"
                >
                  İptal
                </button>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>

      <h2>Geçmiş Randevularım</h2>
      <table class="table table-striped">
        <thead>
          <tr>
            <th>Fizyoterapist</th>
            <th>Tarih</th>
            <th>Saat</th>
            <th>Katılım</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="appointment" items="${appointmentsPast}">
            <tr>
              <td>${appointment.physiotherapist.name}</td>
              <td>${appointment.date}</td>
              <td>${appointment.time}</td>
              <td>
                <c:choose>
                  <c:when test="${appointment.attendance}">
                    <span class="text-success">Katıldı</span>
                  </c:when>
                  <c:otherwise>
                    <span class="text-danger">Katılmadı</span>
                  </c:otherwise>
                </c:choose>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>

    <c:forEach var="appointment" items="${appointmentsFuture}">
      <div
        class="modal fade"
        id="cancelAppointmentModal-${appointment.appointmentId}"
        tabindex="-1"
        aria-labelledby="cancelAppointmentModalLabel-${appointment.appointmentId}"
        aria-hidden="true"
      >
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5
                class="modal-title"
                id="cancelAppointmentModalLabel-${appointment.appointmentId}"
              >
                Randevu İptali
              </h5>
              <button
                type="button"
                class="close"
                data-bs-dismiss="modal"
                aria-label="Close"
              >
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <p>Randevuyu iptal etmek istediğinze emin misiniz?</p>
            </div>
            <div class="modal-footer">
              <form
                action="/patient/appointment/cancel/${appointment.appointmentId}"
                method="POST"
              >
                <button type="submit" class="btn btn-danger">
                  Evet, İptal Et
                </button>
              </form>
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Hayır, Vazgeç
              </button>
            </div>
          </div>
        </div>
      </div>
    </c:forEach>
  </body>
</html>
