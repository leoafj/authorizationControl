<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Desafio Zitrus</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
  <script src="https://kit.fontawesome.com/f3841fe893.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container p-3">
  <h4>Authorization Registration</h4>

  <form action="control" method="post">
    <div class="d-flex gap-3 mb-3">

      <div class="visually-hidden">
        <input type="text" name="id" value="<c:out value="${authorization.getId()}" />"/>
      </div>

      <div class="flex-grow-1">
        <label for="name" class="form-label">Name:</label>
        <input id="name" type="text" name="name" value="<c:out value="${authorization.getName()}" />" required class="form-control"/>
      </div>

      <div class="w-25">
        <label for="proceduresql" class="form-label">Procedure:</label>
        <select id="proceduresql" name="proceduresql" required class="form-select">
          <option value="">Select</option>
          <c:forEach items="${procedures}" var="item">
            <c:choose>
              <c:when test="${item.id == authorization.getProceduresql().getId()}">
                <option selected value="${item.id}">${item.name}</option>
              </c:when>
              <c:otherwise>
                <option value="${item.id}">${item.name}</option>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </select>
      </div>

      <div class="w-10">
        <label for="age" class="form-label">Age:</label>
        <input id="age" type="number" min="0" max="120" name="age" value="<c:out value="${authorization.getAge()}" />" required class="form-control"/>
      </div>

      <div class="w-25">
        <label for="gender" class="form-label">Gender:</label>
        <select id="gender" name="gender" pattern="^\d+$" required class="form-select">
        <option value="">Select</option>
        <c:forEach items="${genders}" var="item">
          <c:choose>
            <c:when test="${item.value == authorization.getGender().getValue()}">
              <option selected value="${item.value}">${item.description}</option>
            </c:when>
            <c:otherwise>
              <option value="${item.value}">${item.description}</option>
            </c:otherwise>
          </c:choose>
        </c:forEach>
        </select>
      </div>

    </div>

    <button type="submit" class="btn btn-dark" id="registerAuthorization">Register</button>
  </form>

  <table class="mt-3 table table-hover caption-top">
    <caption class="h4">List of Authorizations</caption>
    <thead class="table-light">
    <th>Procedure</th>
    <th>Name</th>
    <th>Age</th>
    <th>Gender</th>
    <th>Status</th>
    <th colspan=3 class="text-center">Action</th>
    </thead>
    <tbody>
    <c:forEach var="authorization" items="${authorizations}">
      <tr>
        <td>${authorization.getProceduresql().getName()}</td>
        <td>${authorization.getName()}</td>
        <td>${authorization.getAge()}</td>
        <td>${authorization.getGender().getDescription()}</td>
        <td>${authorization.getAuthorization().getDescription()}</td>
        <td class="text-center">
          <a href="control?action=updateAuthorization&authorizationid=<c:out value="${authorization.getId()}"/>">
            Update <br/>
            <button class="btn btn-link" style="margin-top: 3px;"><i class="fa fa-pencil"></i></button></a>
        </td>
        <td class="text-center">
          <a href="control?action=deleteAuthorization&authorizationid=<c:out value="${authorization.getId()}"/>">
            Delete <br/>
            <button class="btn btn-link text-danger" style="margin-top: 3px;"><i class="fa fa-trash"></i></button></a>
        </td>

        <td class="text-center">
          <a href="control?action=cancelAuthorization&authorizationid=<c:out value="${authorization.getId()}"/>">
            Cancel <br/>
            <button class="btn-close disabled" style="margin-top: 10px;" aria-label="Close"></button></a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
