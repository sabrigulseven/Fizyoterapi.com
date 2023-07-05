<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Hasta Görüntüle</title>
  </head>

  <body>
    <%@ include file="/WEB-INF/jsp/head.jsp" %>
    <div class="container my-5">
      <div class="row">
        <div class="col-md-6 offset-md-3">
          <div class="card">
            <div class="card-header text-center bg-primary text-white">
              <h4>Hasta Bilgileri</h4>
            </div>
            <div class="card-body">
              <div class="form-group row">
                <label for="name" class="col-sm-3 col-form-label">İsim:</label>
                <div class="col-sm-9">
                  <input
                    type="text"
                    class="form-control"
                    id="name"
                    value="${patient.name}"
                    readonly
                  />
                </div>
              </div>
              <div class="form-group row">
                <label for="identityNumber" class="col-sm-3 col-form-label"
                  >Kimlik Numarası:</label
                >
                <div class="col-sm-9">
                  <input
                    type="text"
                    class="form-control"
                    id="identityNumber"
                    value="${patient.identityNumber}"
                    readonly
                  />
                </div>
              </div>
              <div class="form-group row">
                <label for="emailAddress" class="col-sm-3 col-form-label"
                  >E-posta Adresi:</label
                >
                <div class="col-sm-9">
                  <input
                    type="text"
                    class="form-control"
                    id="emailAddress"
                    value="${patient.emailAddress}"
                    readonly
                  />
                </div>
              </div>
              <hr />
              <h5 class="card-title text-center">Randevuları</h5>
              <table class="table">
                <thead>
                  <tr>
                    <th scope="col">Tarih</th>
                    <th scope="col">Saat</th>
                    <th scope="col">Katılım</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="appointment" items="${appointments}">
                    <tr>
                      <td>${appointment.date}</td>
                      <td>${appointment.time}</td>
                      <td>
                        ${appointment.attendance ? 'Geldi' : 'Gelmedi'}
                      </td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
              <hr />
              <div class="text-center">
                <button
                  type="button"
                  class="btn btn-primary"
                  data-bs-toggle="modal"
                  data-bs-target="#sendEmailModal"
                >
                  E-posta Gönder
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="modal fade" id="sendEmailModal" tabindex="-1" role="dialog" aria-labelledby="sendEmailModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="sendEmailModalLabel">E-posta Gönder</h5>
                <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="/physiotherapist/patient/sendmail" method="post">
                    <input type="hidden" name="patientId" value="${patient.id}" />
                    <div class="form-group">
                        <label for="treatment" class="col-form-label">Tedavi:</label>
                        <select class="form-control" id="treatment" name="treatmentId">
                            <c:forEach var="treatment" items="${treatments}">
                                <option value="${treatment.id}">${treatment.description}</option>
                            </c:forEach>
                        </select>
                    </div> 
                    <div class="modal-footer">
		            	<button type="submit" class="btn btn-primary">Gönder</button>
		                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">İptal</button>
		            </div>    
                </form>
            </div>
    
        </div>
    </div>
</div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
