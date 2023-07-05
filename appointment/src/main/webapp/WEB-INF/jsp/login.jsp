<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Giriş Yap</title>
  </head>

  <body>
    <%@ include file="/WEB-INF/jsp/head.jsp" %> <% String
    error=request.getParameter("error"); %>

    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card">
            <div class="card-header">
              <h1 class="card-title text-center">Giriş Yap</h1>
            </div>
            <div class="card-body">
              <% if (error !=null && error.equals("invalid")) { %>
              <div class="alert alert-danger" role="alert">
                Geçersiz TC Kimlik Numarası veya Şifre! Lütfen tekrar deneyin.
              </div>
              <% } %>

              <form method="POST" action="/login">
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
                    Giriş Yap
                  </button>
                </div>
              </form>
              <p class="text-center">
                Hesabınız yoksa kayıt olmak için
                <a href="/patient/register">Tıklayın</a>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
