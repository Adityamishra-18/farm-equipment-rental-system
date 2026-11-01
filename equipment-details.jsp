
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Equipment Details</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/equipment.css">
</head>
<body>

<h1>Equipment Details</h1>

<div>
    <img
            src="${pageContext.request.contextPath}/images/${equipment.image}"
            alt="${equipment.name}"
            width="300"
            height="220"
            style="object-fit: contain;">

    <h2>${equipment.name}</h2>

    <p><strong>Category:</strong> ${equipment.category}</p>
    <p><strong>Brand:</strong> ${equipment.brand}</p>
    <p><strong>Model:</strong> ${equipment.model}</p>
    <p><strong>Description:</strong> ${equipment.description}</p>
    <p><strong>Price per day:</strong> ₹${equipment.pricePerDay}</p>
    <p><strong>Location:</strong> ${equipment.location}</p>
    <p><strong>Availability:</strong> ${equipment.availability}</p>
</div>

<br>

<a href="${pageContext.request.contextPath}/equipmentList">
    Back to Equipment List
</a>

</body>
</html>
