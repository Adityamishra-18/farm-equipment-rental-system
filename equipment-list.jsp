<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Farm Equipment Marketplace</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/equipment.css">

    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 28px;
            font-family: Arial, sans-serif;
            background: #f4f7f2;
            color: #243326;
        }

        .page-container {
            max-width: 1450px;
            margin: auto;
        }

        .page-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-wrap: wrap;
            gap: 15px;
            margin-bottom: 24px;
        }

        .page-header h1 {
            margin: 0 0 8px;
            color: #245e37;
        }

        .page-description {
            margin: 0;
            color: #667366;
        }

        .role-badge {
            display: inline-block;
            padding: 7px 13px;
            border-radius: 20px;
            background: #e2efe4;
            color: #245e37;
            font-weight: bold;
            font-size: 13px;
        }

        .toolbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-wrap: wrap;
            gap: 15px;
            padding: 18px;
            background: white;
            border-radius: 12px;
            margin-bottom: 20px;
            box-shadow: 0 3px 12px rgba(0, 0, 0, 0.05);
        }

        .search-form {
            display: flex;
            flex-wrap: wrap;
            gap: 9px;
            flex: 1;
        }

        .search-form input {
            min-width: 220px;
            flex: 1;
            padding: 11px 13px;
            border: 1px solid #d5ddd4;
            border-radius: 7px;
        }

        .btn {
            display: inline-flex;
            justify-content: center;
            align-items: center;
            padding: 10px 14px;
            border: none;
            border-radius: 7px;
            text-decoration: none;
            font-size: 14px;
            font-weight: bold;
            cursor: pointer;
            white-space: nowrap;
        }

        .btn-primary {
            background: #287342;
            color: white;
        }

        .btn-primary:hover {
            background: #1e5932;
        }

        .btn-light {
            background: #edf1ed;
            color: #344b38;
        }

        .btn-view {
            background: #e4efff;
            color: #2054a0;
        }

        .btn-edit {
            background: #fff1ce;
            color: #855b00;
        }

        .btn-delete {
            background: #fde8e7;
            color: #a52c2c;
        }

        .equipment-count {
            margin: 0 0 15px;
            color: #4c5b4d;
        }

        .table-wrapper {
            overflow-x: auto;
            background: white;
            border-radius: 12px;
            box-shadow: 0 3px 15px rgba(0, 0, 0, 0.05);
        }

        table {
            width: 100%;
            min-width: 1000px;
            border-collapse: collapse;
        }

        th, td {
            padding: 14px 12px;
            border-bottom: 1px solid #e9eee8;
            text-align: left;
            vertical-align: middle;
        }

        th {
            background: #e8f1e7;
            color: #285b35;
            font-size: 13px;
            white-space: nowrap;
        }

        td {
            font-size: 14px;
        }

        tbody tr:hover {
            background: #f8fbf7;
        }

        .equipment-image {
            width: 100px;
            height: 72px;
            object-fit: cover;
            border-radius: 7px;
            background: #eef1ed;
        }

        .availability-badge {
            display: inline-block;
            padding: 6px 10px;
            border-radius: 20px;
            background: #e2f5e7;
            color: #23723b;
            font-size: 12px;
            font-weight: bold;
        }

        .unavailable-badge {
            background: #fde8e7;
            color: #a52c2c;
        }

        .action-buttons {
            display: flex;
            align-items: center;
            flex-wrap: wrap;
            gap: 7px;
        }

        .action-buttons form {
            margin: 0;
        }

        .empty-state {
            padding: 55px 20px;
            text-align: center;
            color: #667366;
        }

        .empty-state h2 {
            color: #285b35;
        }

        .footer-note {
            margin-top: 24px;
            color: #758075;
            font-size: 13px;
            text-align: center;
        }

        @media (max-width: 700px) {
            body {
                padding: 15px;
            }

            .toolbar {
                align-items: stretch;
            }

            .search-form {
                width: 100%;
            }

            .search-form input {
                min-width: 100%;
            }
        }


        /* Payment History */
        .payment-history {
            margin-top: 30px;
            padding: 24px;
            background: #ffffff;
            border-radius: 12px;
            box-shadow: 0 3px 15px rgba(0, 0, 0, 0.05);
        }

        .payment-history-header {
            margin-bottom: 18px;
        }

        .payment-history-header h2 {
            margin: 0 0 7px;
            color: #245e37;
            font-size: 25px;
        }

        .payment-history-header p {
            margin: 0;
            color: #667366;
        }

        .payment-table-wrapper {
            width: 100%;
            overflow-x: auto;
        }

        .payment-table {
            width: 100%;
            min-width: 650px;
            border-collapse: collapse;
        }

        .payment-table th {
            background: #e8f1e7;
            color: #285b35;
            padding: 14px 12px;
            text-align: left;
            white-space: nowrap;
        }

        .payment-table td {
            padding: 14px 12px;
            border-bottom: 1px solid #e9eee8;
            text-align: left;
            color: #344b38;
        }

        .payment-status {
            display: inline-block;
            padding: 6px 10px;
            border-radius: 20px;
            background: #e2f5e7;
            color: #23723b;
            font-size: 12px;
            font-weight: bold;
        }

        .empty-payments {
            padding: 35px 20px !important;
            text-align: center !important;
            color: #667366 !important;
        }

        .empty-payments h3 {
            margin: 0 0 8px;
            color: #285b35;
        }

        .empty-payments p {
            margin: 0;
        }

    </style>
</head>

<body>

<div class="page-container">

    <c:set var="role" value="${sessionScope.userRole}"/>

    <!-- PAGE HEADER -->
    <header class="page-header">

        <div>
            <h1>
                <c:choose>
                    <c:when test="${role == 'OWNER'}">
                        My Farm Equipment
                    </c:when>
                    <c:when test="${role == 'ADMIN'}">
                        Equipment Management
                    </c:when>
                    <c:otherwise>
                        Farm Equipment Marketplace
                    </c:otherwise>
                </c:choose>
            </h1>

            <p class="page-description">
                <c:choose>
                    <c:when test="${role == 'OWNER'}">
                        Add and manage the equipment you offer for rent.
                    </c:when>
                    <c:when test="${role == 'ADMIN'}">
                        View and manage marketplace equipment.
                    </c:when>
                    <c:otherwise>
                        Explore farm equipment available from different owners.
                    </c:otherwise>
                </c:choose>
            </p>
        </div>

        <c:if test="${not empty role}">
            <span class="role-badge">
                <c:out value="${role}"/>
            </span>
        </c:if>

    </header>


    <!-- SEARCH AND ACTIONS -->
    <section class="toolbar">

        <form class="search-form"
              action="${pageContext.request.contextPath}/equipmentSearch"
              method="get">

            <input type="text"
                   name="keyword"
                   placeholder="Search name, category, brand or location..."
                   value="<c:out value='${keyword}'/>"
                   aria-label="Search equipment">

            <button type="submit" class="btn btn-primary">
                Search
            </button>

            <a href="${pageContext.request.contextPath}/equipmentList"
               class="btn btn-light">
                Clear
            </a>

        </form>

        <c:if test="${role == 'OWNER' || role == 'ADMIN'}">
            <a href="${pageContext.request.contextPath}/equipment/add-equipment.jsp"
               class="btn btn-primary">
                + Add Equipment
            </a>
        </c:if>

    </section>


    <!-- EQUIPMENT COUNT -->
    <p class="equipment-count">
        Equipment found:
        <strong><c:out value="${empty equipmentList ? 0 : equipmentList.size()}"/></strong>
    </p>


    <!-- EQUIPMENT TABLE -->
    <div class="table-wrapper">

        <c:choose>

            <c:when test="${empty equipmentList}">
                <div class="empty-state">
                    <h2>No Equipment Found</h2>

                    <p>
                        <c:choose>
                            <c:when test="${role == 'OWNER'}">
                                You have not added any equipment yet.
                            </c:when>
                            <c:otherwise>
                                No equipment matches your search right now.
                            </c:otherwise>
                        </c:choose>
                    </p>

                    <c:if test="${role == 'OWNER' || role == 'ADMIN'}">
                        <a href="${pageContext.request.contextPath}/equipment/add-equipment.jsp"
                           class="btn btn-primary">
                            Add Equipment
                        </a>
                    </c:if>
                </div>
            </c:when>

            <c:otherwise>

                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Brand</th>
                        <th>Model</th>
                        <th>Price / Day</th>
                        <th>Location</th>
                        <th>Image</th>
                        <th>Availability</th>
                        <th>Actions</th>
                    </tr>
                    </thead>

                    <tbody>

                    <c:forEach var="equipment" items="${equipmentList}">

                        <tr>
                            <td><c:out value="${equipment.id}"/></td>

                            <td>
                                <strong>
                                    <c:out value="${equipment.name}"/>
                                </strong>
                            </td>

                            <td><c:out value="${equipment.category}"/></td>
                            <td><c:out value="${equipment.brand}"/></td>
                            <td><c:out value="${equipment.model}"/></td>

                            <td>
                                ₹<c:out value="${equipment.pricePerDay}"/>
                            </td>

                            <td><c:out value="${equipment.location}"/></td>

                            <td>
                                <c:choose>
                                    <c:when test="${not empty equipment.image}">
                                        <img
                                                class="equipment-image"
                                                src="${pageContext.request.contextPath}/images/${equipment.image}"
                                                alt="${equipment.name}"
                                                onerror="this.alt='Image not found';">
                                    </c:when>

                                    <c:otherwise>
                                        <span>No image uploaded</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <td>
                                <c:choose>
                                    <c:when test="${equipment.availability == 'Available'}">
                                        <span class="availability-badge">
                                            Available
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="availability-badge unavailable-badge">
                                            <c:out value="${equipment.availability}"/>
                                        </span>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <td>
                                <div class="action-buttons">

                                    <!-- Everyone can view details -->
                                    <a class="btn btn-view"
                                       href="${pageContext.request.contextPath}/equipmentDetails?id=${equipment.id}">
                                        View Details
                                    </a>

                                    <!-- Farmer can rent available equipment -->
                                    <c:if test="${role == 'FARMER' && equipment.availability == 'Available'}">
                                        <a class="btn btn-primary"
                                           href="${pageContext.request.contextPath}/bookingForm?equipmentId=${equipment.id}">
                                            Rent
                                        </a>
                                    </c:if>

                                    <!-- Owner manages their own equipment.
                                         The servlet must verify ownership. -->
                                    <c:if test="${role == 'OWNER'}">

                                        <a class="btn btn-edit"
                                           href="${pageContext.request.contextPath}/editEquipment?id=${equipment.id}">
                                            Edit
                                        </a>

                                        <form action="${pageContext.request.contextPath}/deleteEquipment"
                                              method="post"
                                              onsubmit="return confirm('Delete this equipment?');">

                                            <input type="hidden"
                                                   name="id"
                                                   value="${equipment.id}">

                                            <button type="submit"
                                                    class="btn btn-delete">
                                                Delete
                                            </button>
                                        </form>

                                    </c:if>

                                    <!-- Admin management controls -->
                                    <c:if test="${role == 'ADMIN'}">

                                        <a class="btn btn-edit"
                                           href="${pageContext.request.contextPath}/editEquipment?id=${equipment.id}">
                                            Edit
                                        </a>

                                        <form action="${pageContext.request.contextPath}/deleteEquipment"
                                              method="post"
                                              onsubmit="return confirm('Delete this equipment?');">

                                            <input type="hidden"
                                                   name="id"
                                                   value="${equipment.id}">

                                            <button type="submit"
                                                    class="btn btn-delete">
                                                Delete
                                            </button>
                                        </form>

                                    </c:if>

                                </div>
                            </td>
                        </tr>

                    </c:forEach>

                    </tbody>
                </table>

            </c:otherwise>

        </c:choose>
        <!-- Payment History Section -->
        <section class="payment-history">
            <div class="payment-history-header">
                <div>
                    <h2>Payment History</h2>
                    <p>View payments from your equipment rentals.</p>
                </div>
            </div>

            <div class="payment-table-wrapper">
                <table class="payment-table">
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Equipment</th>
                        <th>Renter</th>
                        <th>Amount</th>
                        <th>Payment Status</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:choose>
                        <c:when test="${not empty paymentHistory}">
                            <c:forEach var="payment"
                                       items="${paymentHistory}">
                                <tr>
                                    <td>
                                        <c:out value="${payment.paymentTime}" />
                                    </td>
                                    <td>
                                        <c:out value="${payment.equipmentName}" />
                                    </td>
                                    <td>
                                        <c:out value="${payment.renterName}" />
                                    </td>
                                    <td>
                                        ₹<c:out value="${payment.totalAmount}" />
                                    </td>
                                    <td>
                                    <span class="payment-status">
                                        <c:out value="${payment.status}" />
                                    </span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>

                        <c:otherwise>
                            <tr>
                                <td colspan="5" class="empty-payments">
                                    <h3>No payment history yet</h3>
                                    <p>
                                        Payments from your equipment
                                        rentals will appear here.
                                    </p>
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </section>


    </div>


    <footer class="footer-note">
        Farm Equipment Marketplace · Supporting smarter farming.
    </footer>

</div>

</body>
</html>
