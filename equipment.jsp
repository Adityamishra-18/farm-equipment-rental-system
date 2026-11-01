
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Equipment</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/equipment.css">
</head>

<body>

<h1>Add Farm Equipment</h1>

<form action="${pageContext.request.contextPath}/addEquipment"
      method="post"
      enctype="multipart/form-data">

    <label>Equipment Name</label>
    <input type="text" name="name" required>

    <label>Category</label>
    <input type="text" name="category" required>

    <label>Brand</label>
    <input type="text" name="brand">

    <label>Model</label>
    <input type="text" name="model">

    <label>Description</label>
    <textarea name="description"></textarea>

    <label>Price Per Day (₹)</label>
    <input type="number" name="pricePerDay"
           min="0.01" step="0.01" required>

    <label>Location</label>
    <input type="text" name="location" required>

    <label>Equipment Image URL</label>
    <input type="url" name="image"
           placeholder="tractor.jpg"
           required>
    <label>Availability</label>
    <select name="availability">
        <option value="Available">Available</option>
        <option value="Unavailable">Unavailable</option>
    </select>

    <button type="submit">Add Equipment</button>

</form>

</body>
</html>
