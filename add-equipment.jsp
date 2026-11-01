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
      enctype="multipart/form-data"
>
    <label>Equipment Name</label>
    <input type="text" name="name" required>

    <br><br>

    <label>Category</label>
    <input type="text" name="category" required>

    <br><br>

    <label>Brand</label>
    <input type="text" name="brand">

    <br><br>

    <label>Model</label>
    <input type="text" name="model">

    <br><br>

    <label>Description</label>
    <textarea name="description"></textarea>

    <br><br>

    <label>Price Per Day</label>
    <input type="number"
           name="pricePerDay"
           step="0.01"
           required>

    <br><br>

    <label>Location</label>
    <input type="text" name="location" required>

    <br><br>


    <div class="mb-6">
        <label for="equipmentImage"
               class="mb-2 block font-semibold text-green-900">
            Equipment Photo
        </label>

        <input
                type="file"
                id="equipmentImage"
                name="equipmentImage"
                accept="image/png,image/jpeg,image/webp"
                required
                class="block w-full rounded-xl border border-green-200
               bg-green-50 p-3 text-sm text-gray-700
               file:mr-4 file:rounded-lg file:border-0
               file:bg-green-700 file:px-4 file:py-2
               file:font-semibold file:text-white
               hover:file:bg-green-800"
        />

        <p class="mt-2 text-sm text-gray-500">
            Choose a tractor or equipment photo from your device.
            Supported formats: JPG, PNG and WEBP.
        </p>
    </div>

    <br><br>

    <label>Availability</label>

    <select name="availability">
        <option value="Available">Available</option>
        <option value="Unavailable">Unavailable</option>
    </select>

    <br><br>

    <button type="submit">
        Add Equipment
    </button>

</form>

</body>
</html>




