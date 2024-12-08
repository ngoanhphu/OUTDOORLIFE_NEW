<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
        }

        .container {
            padding: 3rem 2rem;
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
            box-shadow: 0 4px 6px rgba(0, 123, 255, 0.3);
        }

        .btn-create:hover {
            background-color: #0056b3;
            box-shadow: 0 6px 10px rgba(0, 123, 255, 0.5);
        }

        .search-container {
            margin-bottom: 2rem;
            text-align: center;
        }

        .input-group {
            max-width: 500px;
            margin: 0 auto;
        }

        .input-group input {
            width: 80%;
            padding: 0.5rem;
            font-size: 1rem;
            border: 1px solid #ccc;
            border-radius: 0.5rem;
        }

        .input-group button {
            padding: 0.5rem 1rem;
            background-color: #007bff;
            border: none;
            border-radius: 0.5rem;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .input-group button:hover {
            background-color: #0056b3;
        }

        .row {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
            gap: 1.5rem;
        }

        .col-md-3 {
            max-width: 22%;
            flex-grow: 1;
            display: flex;
            justify-content: center;
        }

        .card {
            background-color: white;
            border-radius: 0.5rem;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            width: 100%;
            transition: box-shadow 0.3s ease;
        }

        .card:hover {
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.2);
        }

        .card-img-top {
            width: 100%;
            height: 200px;
            object-fit: cover;
            border-bottom: 1px solid #ddd;
        }

        .card-body {
            padding: 1.5rem;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        .card-title {
            font-size: 1.25rem;
            font-weight: bold;
            margin-bottom: 1rem;
            color: #333;
        }

        .price {
            font-size: 1.2rem;
            font-weight: bold;
            color: #28a745;
            margin-bottom: 0.75rem;
        }

        .category {
            font-size: 1rem;
            color: #555;
            margin-bottom: 1rem;
        }

        .btn {
            padding: 0.5rem 1.5rem;
            font-size: 1rem;
            border-radius: 0.5rem;
            cursor: pointer;
        }

        .btn-primary {
            background-color: #007bff;
            color: white;
            border: none;
            transition: background-color 0.3s ease;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .btn-danger {
            background-color: #dc3545;
            color: white;
            border: none;
            transition: background-color 0.3s ease;
        }

        .btn-danger:hover {
            background-color: #c82333;
        }

        .mt-3 {
            margin-top: 1rem;
        }

        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 2rem;
        }

        #pagination {
            padding: 10px;
        }
        .row {
            display: flex;
            flex-wrap: wrap;
            justify-content: flex-start; /* Canh trái */
            gap: 1.5rem; /* Khoảng cách giữa các sản phẩm */
        }

        .col-md-3 {
            flex: 1 1 calc(25% - 1.5rem); /* Đảm bảo 4 sản phẩm trên 1 hàng */
            max-width: calc(25% - 1.5rem);
            box-sizing: border-box;
        }
        .search-container {
            margin-bottom: 2rem;
            text-align: left; /* Canh trái */
        }

        .input-group {
            display: flex;
            align-items: center;
            max-width: 500px;
            margin: 0 auto;
        }

        .input-group input {
            flex: 1;
            padding: 0.5rem;
            font-size: 1rem;
            border: 1px solid #ccc;
            border-radius: 0 5px 5px 0; /* Bo góc bên phải */
            outline: none;
            transition: box-shadow 0.3s ease, border-color 0.3s ease;
        }

        .input-group input:focus {
            border-color: #007bff;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }

        .input-group button {
            background-color: #007bff;
            color: white;
            border: 1px solid #007bff;
            border-radius: 5px 0 0 5px; /* Bo góc bên trái */
            padding: 0.5rem;
            cursor: pointer;
            display: flex;
            align-items: center;
            justify-content: center;
            transition: background-color 0.3s ease;
        }

        .input-group button:hover {
            background-color: #0056b3;
        }

        .input-group button i {
            font-size: 1rem; /* Kích thước icon */
        }
        .row {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between; /* Đảm bảo không gian được phân bổ đều */
        }

        .col-md-3 {
            width: 23%; /* Cho phép không gian cho 4 sản phẩm mỗi hàng */
            margin-bottom: 20px; /* Thêm khoảng cách giữa các hàng */
            box-sizing: border-box;
        }
    </style>

    <body>
        <jsp:include page="headeradmin.jsp"></jsp:include>
    <div class="container" style="margin-top: 200px">
        <!-- Create Gear Button -->
        <div class="create-button-container">
            <button class="btn-create" onclick="window.location.href='createTentForm.jsp'">Create Tent</button>
        </div>

        <div class="search-container">
            <form action="search" method="post" class="form-inline">
                <div class="input-group">
                    <button type="submit" class="btn btn-secondary">
                        <i class="fa fa-search"></i>
                    </button>
                    <input name="txt" type="text" placeholder="Search..." class="form-control" />
                </div>
            </form>
        </div>
        
        <div class="row">
            <c:forEach var="g" items="${gears}">
                <div class="col-md-3 d-flex">
                    <div class="card border-e shadow rounded-3 w-100">
                        <img class="card-img-top" src="img/${g.gearImage}" alt="Card image cap" />
                        <div class="card-body">
                            <h5 class="card-title">${g.gearName}</h5>
                            <h6 class="price">${g.gearPrice}</h6>
                            <h6 class="category">${g.gearDecription}</h6>
                            <div class="mt-3 d-flex justify-content-between">
                                <a href="updatetent?id=${g.gearId}" class="btn btn-primary">Update</a>
                                <a href="#" onclick="showMess(${g.gearId})" class="btn btn-danger">Delete</a>
                            </div>

                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="d-flex justify-content-center mt-2">
            <div id="pagination"></div>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.min.js"></script>

    <script type="text/javascript">
        // Setup variables for pagination
        let itemsPerPage = 8;
        let totalPages = ${totalPages};
        let currentPage = ${currentPage};

        // Initialize pagination plugin
        $('#pagination').twbsPagination({
            startPage: currentPage,
            totalPages: totalPages,
            visiblePages: 10,
            initiateStartPageClick: false,
            onPageClick: function (event, page) {
                window.location.href = 'admintent?page=' + page;
            }
        });
    </script>
    
    <script>
        function showMess(gearId) {
            var option = confirm('Are you sure to delete');
            if (option === true) {
                window.location.href = 'deletetent?id=' + gearId;
            }
        }
    </script>
</body>