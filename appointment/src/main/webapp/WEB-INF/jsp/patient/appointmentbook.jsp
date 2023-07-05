<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>Randevu Al</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>

    <%@ include file="/WEB-INF/jsp/head.jsp" %>

    <div class="container my-5">
        <h2>Randevu Al</h2>
        <div class="card my-3">
            <div class="card-header">
                Seçili Fizyoterapist
            </div>
            <div class="card-body">
                <h5 class="card-title">${physiotherapist.name}</h5>
                <p class="card-text">Uzmanlık: ${physiotherapist.profession}</p>
            </div>
        </div>

       
        <c:choose>
            <c:when test="${empty appointments}">
                <p>Randevu bulunmamaktadır.</p>
            </c:when>
            <c:otherwise>
            	 <h4>Alınabilecek Randevular</h4>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Tarih</th>
                            <th>Zaman</th>
                            <th>Eylem</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="appointment" items="${appointments}">
                            <tr>
                                <td>${appointment.date}</td>
                                <td>${appointment.time}</td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#bookingModal" data-appointment-id="${appointment.appointmentId}" data-appointment-date="${appointment.date}" data-appointment-time="${appointment.time}">Randevu Al</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Booking Modal -->
    <div class="modal fade" id="bookingModal" tabindex="-1" aria-labelledby="bookingModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="bookingModalLabel">Randevuyu Onayla</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p>Fizyoterapist: ${physiotherapist.name}</p>
                    <p>Uzmanlık: ${physiotherapist.profession}</p>
                    <p id="appointmentDate"></p>
                    <p id="appointmentTime"></p>
                    <p>Seçili Randevuyu Almak İstiyor Musunuz?</p>
                </div>
                <div class="modal-footer">
                    <form action="/patient/appointment/book/save" method="post">
                        <input type="hidden" name="appointmentId" id="appointmentIdInput">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">İptal</button>
                        <button type="submit" class="btn btn-primary">Onay</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
var bookingModal = document.getElementById('bookingModal');
bookingModal.addEventListener('show.bs.modal', function (event) {
var button = event.relatedTarget;
var appointmentId = button.getAttribute('data-appointment-id');
var appointmentDate = button.getAttribute('data-appointment-date');
var appointmentTime = button.getAttribute('data-appointment-time');
document.getElementById('appointmentIdInput').value = appointmentId;
document.getElementById('appointmentDate').textContent = 'Randevu Günü: ' + appointmentDate;
document.getElementById('appointmentTime').textContent = 'Randevu Saati: ' + appointmentTime;
});
</script>
</body>
</html>
