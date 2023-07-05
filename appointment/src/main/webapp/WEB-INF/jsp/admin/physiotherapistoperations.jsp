<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<title>Fizyoterapist Yönetimi</title>
	<!-- Bootstrap 5 CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
		rel="stylesheet">
	<style>
		.btn-inline {
			display: inline-block;
		}
	</style>
</head>

<body>
	<%@ include file="/WEB-INF/jsp/head.jsp" %>
		<div class="container mt-5">
			<div class="row justify-content-center">
				<div class="col-md-8">
					<!-- Form for adding a new physiotherapist -->
					<h2>Fizyoterapist Ekle</h2>
					<form:form method="POST" action="/admin/physiotherapists/save"
						modelAttribute="physiotherapist">
						<div class="mb-3">
							<form:label path="identityNumber" class="form-label">Kimlik Numarası:
							</form:label>
							<form:input path="identityNumber" class="form-control"
								required="required" pattern="[0-9]{11}"
								title="Please enter 11-digit identity number." />
						</div>
						<div class="mb-3">
							<form:label path="name" class="form-label">İsim:</form:label>
							<form:input path="name" class="form-control" required="required" />
						</div>
						<div class="mb-3">
							<form:label path="password" class="form-label">Şifre:</form:label>
							<form:input path="password" class="form-control" required="required"
								pattern="^.{6,}$" title="Password must be at least 6 characters." />
						</div>
						<div class="mb-3">
							<form:label path="profession" class="form-label">Uzmanlık:</form:label>
							<form:input path="profession" class="form-control"
								required="required" />
						</div>
						<div class="mb-3 text-center">
							<button type="submit" class="btn btn-primary">Kaydet</button>
						</div>
					</form:form>

					<!-- Table for displaying all physiotherapists -->
					<h2>Kayıtlı Fizyoterapistler</h2>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>ID</th>
								<th>Kimlik Numarası</th>
								<th>İsim</th>
								<th>Uzmanlık</th>
								<th>Eylemler</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${physiotherapists}" var="physiotherapist">
								<tr>
									<td>${physiotherapist.id}</td>
									<td>${physiotherapist.identityNumber}</td>
									<td>${physiotherapist.name}</td>
									<td>${physiotherapist.profession}</td>
									<td><!-- Button trigger modal -->
										<button type="button" class="btn btn-warning btn-sm"
											data-bs-toggle="modal"
											data-bs-target="#exampleModal-${physiotherapist.id}">
											Düzenle
										</button>
										<!-- Modal -->
										<div class="modal fade"
											id="exampleModal-${physiotherapist.id}" tabindex="-1"
											aria-labelledby="exampleModalLabel" aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title"
															id="exampleModalLabel">Fizyoterapist
															Bilgilerini Düzenle</h5>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal"
															aria-label="Close"></button>
													</div>
													<div class="modal-body">
														<form method="post"
															action="/admin/physiotherapists/edit/${physiotherapist.id}">
															<div class="mb-3">
																<label for="identityNumber"
																	class="form-label">Kimlik
																	Numarası</label>
																<input type="text"
																	class="form-control"
																	id="identityNumber"
																	name="identityNumber"
																	value="${physiotherapist.identityNumber}"
																	required pattern="[0-9]{11}"
																	title="Please enter 11-digit identity number.">
															</div>
															<div class="mb-3">
																<label for="name"
																	class="form-label">İsim</label>
																<input type="text"
																	class="form-control" id="name"
																	name="name"
																	value="${physiotherapist.name}"
																	required>
															</div>
															<div class="mb-3">
																<label for="password"
																	class="form-label">Şifre</label>
																<input type="password"
																	class="form-control"
																	id="password" name="password"
																	value="${physiotherapist.password}"
																	required pattern="^.{6,}$"
																	title="Password must be at least 6 characters.">
															</div>
															<div class="mb-3">
																<label for="profession"
																	class="form-label">Uzmanlık</label>
																<input type="text"
																	class="form-control"
																	id="profession"
																	name="profession"
																	value="${physiotherapist.profession}"
																	required>
															</div>
															<div class="modal-footer">
																<button type="button"
																	class="btn btn-secondary"
																	data-bs-dismiss="modal">Vazgeç</button>
																<button type="submit"
																	class="btn btn-primary">Değişiklikleri
																	Kaydet</button>
															</div>
														</form>

													</div>
												</div>
											</div>
										</div>
										<!-- Trigger modal -->
										<button type="button"
											class="btn btn-danger btn-sm btn-inline"
											data-bs-toggle="modal"
											data-bs-target="#deletePhysiotherapistModal-${physiotherapist.id}">
											Sil
										</button>

										<!-- Modal -->
										<div class="modal fade"
											id="deletePhysiotherapistModal-${physiotherapist.id}"
											tabindex="-1"
											aria-labelledby="deletePhysiotherapistModalLabel"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title"
															id="deletePhysiotherapistModalLabel">
															Fizyoterapist Silme</h5>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal"
															aria-label="Close"></button>
													</div>
													<div class="modal-body">
														Fizyoterapisti silmek istediğinzden emin
														misiniz?
													</div>
													<div class="modal-footer">
														<button type="button"
															class="btn btn-secondary"
															data-bs-dismiss="modal">İptal</button>
														<form
															action="/admin/physiotherapists/delete/${physiotherapist.id}"
															method="POST">
															<input type="hidden" name="_method"
																value="DELETE">
															<button type="submit"
																class="btn btn-danger">Sil</button>
														</form>
													</div>
												</div>
											</div>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js"></script>
</body>

</html>