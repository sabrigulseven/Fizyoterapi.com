<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>

<head>
  <link
    rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
  />
  <style>
    .nav-item {
      font-family: "Nunito";
    }

    .nav-link:hover {
      color: #22dd9f !important;
    }

    .nav-link.active {
      color: #22dd9f !important;
    }

    .navbar-brand {
      color: #22dd9f !important;
    }

    .header {
      background-color: #22dd9f;
      color: white;
      font-family: Nunito;
      text-align: center;
    }

    .header a {
      color: white;
    }
  </style>
</head>

<body>
  <div class="header">Fizyoterapi Uygulamaları</div>
  <nav class="navbar navbar-expand-md navbar-light bg-light">
    <div class="container">
      <a class="navbar-brand" href="/">Fizyoterapi.com</a>
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="/">Ana Sayfa</a>
          </li>
          <% if (session.getAttribute("userType")==null) { %>
          <!-- User is not logged in -->
          <li class="nav-item">
            <a class="nav-link" href="/login">Giriş Yap</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/patient/register">Kayıt Ol</a>
          </li>
          <% } else if (session.getAttribute("userType").equals("patient")) { %>
          <!-- User is a patient -->
          <li class="nav-item">
            <a class="nav-link" href="/patient/appointment">Randevu Al</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/patient/appointments">Randevularım</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/logout">Çıkış Yap</a>
          </li>
          <% } else if (session.getAttribute("userType").equals("admin")) { %>
          <!-- User is an admin -->
          <li class="nav-item">
            <a class="nav-link" href="/admin/physiotherapists"
              >Fizyoterapist Yönetimi</a
            >
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/admin/patients">Hasta Yönetimi</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/admin/appointments">Randevu Yönetimi</a>
          </li>
           <li class="nav-item">
            <a class="nav-link" href="/admin/treatments">Tedavi Yönetimi</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/logout">Çıkış Yap</a>
          </li>
          <% } else if
          (session.getAttribute("userType").equals("physiotherapist")) { %>
          <!-- User is a physiotherapist -->
          <li class="nav-item">
            <a class="nav-link" href="/physiotherapist/appointments"
              >Randevularım</a
            >
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/physiotherapist/patients">Hastalarım</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/physiotherapist/schedule"
              >Randevu oluştur</a
            >
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/logout">Çıkış Yap</a>
          </li>
          <% } %>
        </ul>
      </div>
    </div>
  </nav>
</body>
