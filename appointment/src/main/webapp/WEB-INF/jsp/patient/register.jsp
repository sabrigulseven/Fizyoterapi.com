<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>

<!DOCTYPE html>
<html>
  <head>
    <title>Kayıt Ol</title>
  </head>
  <body>
    <%@ include file="/WEB-INF/jsp/head.jsp" %>
    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card">
            <div class="card-header">
              <h1 class="card-title text-center">Kayıt Ol</h1>
            </div>
            <div class="card-body">
              <form method="POST" action="/patient/register">
                <div class="mb-3">
                  <label for="name" class="form-label"
                    >Adınız ve Soyadınız:</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    id="name"
                    name="name"
                    required="required"
                    pattern="^[a-zA-ZığüşiöçİĞÜŞÖÇ\s]+$"
                  />
                </div>
                <div class="mb-3">
                  <label for="identityNumber" class="form-label"
                    >TC Kimlik Numaranız:</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    id="identityNumber"
                    name="identityNumber"
                    required="required"
                    pattern="[0-9]{11}"
                    title="Lütfen 11 haneli TC Kimlik Numaranızı giriniz."
                  />
                </div>
                <div class="mb-3">
                  <label for="emailAddress" class="form-label"
                    >E-posta Adresiniz:</label
                  >
                  <input
                    type="email"
                    class="form-control"
                    id="emailAddress"
                    name="emailAddress"
                    required="required"
                  />
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">Şifreniz:</label>
                  <input
                    type="password"
                    class="form-control"
                    id="password"
                    name="password"
                    required="required"
                    pattern="^.{6,}$"
                    title="Şifreniz en az 6 karakter uzunluğunda olmalıdır"
                  />
                </div>
                <div class="mb-3 text-center">
                  <button type="submit" class="btn btn-primary">
                    Kayıt Ol
                  </button>
                </div>
              </form>
              <p class="text-center">
                Zaten bir hesabınız var mı?
                <a href="/login">Giriş Yapın</a>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
