<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Calendar" %>

<style>
    .container {
        padding: 2rem 0;
    }

    .card {
        background-color: #fff;
        border: none;
        border-radius: 0.5rem;
        margin-bottom: 1.5rem;
        display: flex;
        flex-direction: column;
        height: 100%;
    }

    .card-header {
        padding: 1rem 1.5rem;
        margin-bottom: 1rem;
        background-color: #f8f9fa;
        border-radius: 0.5rem 0.5rem 0 0;
        text-align: center;
        font-size: 1.25rem;
        font-weight: bold;
    }

    .card-body {
        padding: 1.5rem;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }

    .card-img-top {
        height: 200px;
        object-fit: cover;
        border-radius: 0.5rem 0.5rem 0 0;
    }

    .card-title {
        font-size: 1.25rem;
        margin: 0.5rem 0;
    }

    .price, .category {
        font-size: 1rem;
        margin: 0.25rem 0;
    }

    .btn {
        width: 80%;
    }

    .row {
        margin-bottom: 1.5rem; /* Adjust this value to increase/decrease spacing between rows */
    }

    .create-button-container {
        display: flex;
        justify-content: center;
        margin-bottom: 2rem;
    }

    .btn-create {
        background-color: #007bff;
        color: white;
        padding: 0.75rem 1.5rem;
        font-size: 1rem;
        border: none;
        border-radius: 0.5rem;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .btn-create:hover {
        background-color: #0056b3;
    }
</style>

<body>
<jsp:include page="headeradmin.jsp"/>
<div class="container" style="margin-top: 200px">
    <div class="row">
        <a href="dashboard" class="btn btn-outline-primary " style="width: 100px;">
            Back
        </a>
    </div>
    <h1>Dashboard</h1>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <!-- Owner Information Section -->
            <div class="card">
                <div class="card-header">Owner Information</div>
                <div class="card-body">
                    <p><strong>Name:</strong> ${ownerAccount.firstName} ${ownerAccount.lastName}</p>
                    <p><strong>Email:</strong> ${ownerAccount.email}</p>
                    <p><strong>Phone:</strong> ${ownerAccount.phoneNumber}</p>
                    <!-- Add more fields as necessary -->
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <ul class="nav nav-tabs mb-3">
            <li class="nav-item">
                <a class="nav-link
                    <c:if test="${queryType eq 'monthly'}">
                        active
                    </c:if>
                    " href="dashboard-owner?queryType=monthly&ownerId=${owner.ownerId}"
                >
                    Monthly
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link
                    <c:if test="${queryType eq 'yearly'}">
                        active
                    </c:if>
                    " href="dashboard-owner?queryType=yearly&ownerId=${owner.ownerId}"
                >
                    Yearly
                </a>
            </li>
        </ul>
        <c:if test="${queryType eq 'monthly'}">
            <form action="dashboard-owner" id="yearForm">
                <input type="hidden" name="queryType" value="${queryType}">
                <input type="hidden" name="ownerId" value="${owner.ownerId}">
                <select name="year" id="yearDropdown" onchange="document.getElementById('yearForm').submit();">
                    <%
                        int currentYear = Calendar.getInstance().get(Calendar.YEAR); // Get the current year
                        int selectedYear = request.getParameter("year") != null ?
                                Integer.parseInt(request.getParameter("year"))
                                : currentYear;
                        for (int year = 2020; year <= currentYear; year++) {
                    %>
                    <option
                            value="<%= year %>"
                            <%= year == selectedYear ? "selected" : "" %>
                    >
                        <%= year %>
                    </option>
                    <%
                        }
                    %>
                </select>
            </form>
        </c:if>
        <div class="col-6">
            <canvas id="myChart3" style="width:100%;max-width:700px"></canvas>
        </div>
        <div class="col-6">
            <canvas id="myChart4" style="width:100%;max-width:700px"></canvas>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js">
</script>
<script>
    var periodList = [];
    var numberOrderByPeriod = [];
    var totalAmountByPeriod = [];
    <c:if test="${queryType eq 'monthly'}">
    <c:forEach var="o" items="${orderRevenueList}">
    periodList.push(${o.month});
    numberOrderByPeriod.push(${o.numberOfOrders});
    totalAmountByPeriod.push(${o.totalAmount});
    </c:forEach>
    </c:if>

    <c:if test="${queryType eq 'yearly'}">
    <c:forEach var="o" items="${orderRevenueList}">
    periodList.push(${o.year});
    numberOrderByPeriod.push(${o.numberOfOrders});
    totalAmountByPeriod.push(${o.totalAmount});
    </c:forEach>
    </c:if>

    const xValues = periodList;
    const yValues = numberOrderByPeriod;
    const yValues2 = totalAmountByPeriod;

    const barColors = ["red", "green", "blue", "orange", "brown", "yellow"];

    new Chart("myChart3", {
        type: "bar",
        data: {
            labels: xValues,
            datasets: [{
                backgroundColor: barColors,
                suggestedMin: 0,
                data: yValues
            }]
        },
        options: {
            legend: {display: false},
            title: {
                display: true,
                text: <c:if test="${queryType eq 'yearly'}">
                    "Number of orders in 5 recent years"
                </c:if>
                <c:if test="${queryType eq 'monthly'}">
                "Number of orders in ${year}"
                </c:if>
            },
            scales: {
                y: {
                    beginAtZero: true, // This ensures the y-axis starts at 0
                    min: 0 // This explicitly sets the minimum value of the y-axis to 0
                }
            }
        }
    });

    new Chart("myChart4", {
        type: "bar",
        data: {
            labels: xValues,
            datasets: [{
                backgroundColor: barColors,
                data: yValues2
            }]
        },
        options: {
            legend: {display: false},
            title: {
                display: true,
                text: <c:if test="${queryType eq 'yearly'}">
                    "Revenue in 5 recent years"
                </c:if>
                <c:if test="${queryType eq 'monthly'}">
                "Revenue in ${year}"
                </c:if>
            },
            scales: {
                y: {
                    beginAtZero: true, // This ensures the y-axis starts at 0
                    min: 0 // This explicitly sets the minimum value of the y-axis to 0
                }
            }
        }
    });
</script>
</body>
