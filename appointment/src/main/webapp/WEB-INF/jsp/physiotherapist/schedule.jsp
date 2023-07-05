<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Çalışma Takvimi Belirle</title>
  </head>

  <body>
    <%@ include file="/WEB-INF/jsp/head.jsp" %>
    <!-- physiotherapist/schedule.jsp -->
    <div class="container mt-5">
      <div class="row">
        <div class="col-md-6 mx-auto">
          <h2 class="text-center mb-4">Çalışma Takvimi Belirle</h2>
          <form method="post" action="/physiotherapist/schedule">
            <div class="mb-3">
              <label for="date" class="form-label">Tarih</label>
              <input
                type="date"
                class="form-control"
                id="date"
                name="date"
                placeholder="dd.MM.yyyy"
                min="${todayDate}"
                required
              />
            </div>
            <div class="mb-3">
              <label for="start-time" class="form-label">Başlangıç Saati</label>
              <input
                type="time"
                class="form-control"
                id="start-time"
                name="startTime"
                placeholder="HH:mm"
                step="${appointmentDuration}"
                min="${startHour}"
                max="${endHour}"
                required
              />
            </div>
            <div class="mb-3">
              <label for="end-time" class="form-label">Bitiş Saati</label>
              <input
                type="time"
                class="form-control"
                id="end-time"
                name="endTime"
                placeholder="HH:mm"
                step="${appointmentDuration}"
                min="${startHour}"
                max="${endHour}"
                required
              />
            </div>
            <button type="submit" class="btn btn-primary">Takvimi Ekle</button>
          </form>
        </div>
      </div>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.min.js"></script>
  </body>
</html>
