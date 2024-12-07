<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <jsp:include page="headerAdmin.jsp"></jsp:include>
        <div class="container" style="margin-top: 200px">

            <div class="row">
                <div class="col-6">
                    <canvas id="myChart" style="width:100%;max-width:700px"></canvas></div>
                <div class="col-6">
                    <canvas id="myChart2" style="width:100%;max-width:700px"></canvas></div>
            </div>

        </div>


        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js">
        </script>
        <script>
            var monthList = [];
            var numberOrderByMonth = [];
            var totalAmountByMonth = [];
        <c:forEach var="o" items="${lstCountOrderByMonth}">
            monthList.push(${o.month});
            numberOrderByMonth.push(${o.numberOfOrders});
            totalAmountByMonth.push(${o.totalAmount});
        </c:forEach>
            const xValues = monthList;
            const yValues = numberOrderByMonth;
            const yValues2 = totalAmountByMonth;
            const barColors = ["red", "green", "blue", "orange", "brown", "yellow"];

            new Chart("myChart", {
                type: "bar",
                data: {
                    labels: xValues,
                    datasets: [{
                            backgroundColor: barColors,
                            data: yValues
                        }]
                },
                options: {
                    legend: {display: false},
                    title: {
                        display: true,
                        text: "Number of orders in the most recent 6 months"
                    }
                }
            });

            new Chart("myChart2", {
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
                        text: "Revenue in the most recent 6 months"
                    }
                }
            });
    </script>
</body>