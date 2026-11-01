
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Equipment</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/equipment.css">
</head>
<body>

<h1>Edit Equipment</h1>

<form action="${pageContext.request.contextPath}/editEquipment"
      method="post">

    <input type="hidden" name="id" value="${equipment.id}">

    <label>Equipment Name</label>
    <input type="text" name="name" value="${equipment.name}" required>

    <label>Category</label>
    <input type="text" name="category" value="${equipment.category}" required>

    <label>Brand</label>
    <input type="text" name="brand" value="${equipment.brand}">

    <label>Model</label>
    <input type="text" name="model" value="${equipment.model}">

    <label>Description</label>
    <textarea name="description">${equipment.description}</textarea>

    <label>Price Per Day (₹)</label>
    <input type="number" name="pricePerDay"
           value="${equipment.pricePerDay}"
           min="0.01" step="0.01" required>

    <label>Location</label>
    <input type="text" name="location" value="${equipment.location}" required>

    <label>Image Filename</label>
    <input type="text" name="image" value="${equipment.image}">

    <label>Availability</label>
    <select name="availability">
        <option value="Available"
        ${equipment.availability == 'Available' ? 'selected' : ''}>
            Available
        </option>
        <option value="Unavailable"
        ${equipment.availability == 'Unavailable' ? 'selected' : ''}>
            Unavailable
        </option>
    </select>

    <br><br>
    <button type="submit">Update Equipment</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/equipmentList">
    Back to Equipment List
</a>

</body>
</html>
