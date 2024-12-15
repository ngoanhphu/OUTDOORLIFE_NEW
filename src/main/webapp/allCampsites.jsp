<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<div class="headeradmin" <jsp:include page="headeradmin.jsp"></jsp:include>
<style>
  /* Global Styles */
  body {
    font-family: Arial, sans-serif;
    background-color: #f4f6f9;
    margin: 0;
    padding: 0;
  }

  .container {
    padding: 3rem 2rem;
    margin-top: 100px;
  }
  .container px-0{
    margin-top: -45px;
  }

  /* Button Styles */
  .btn-create {
    font-size: 18px;
    padding: 12px 24px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }

  .btn-create:hover {
    background-color: #45a049;
  }

  .btn {
    padding: 0.5rem 1rem;
    font-size: 1rem;
    border-radius: 8px;
    cursor: pointer;
    text-decoration: none;
    display: inline-block;
    text-align: center;
  }

  .btn-primary {
    background-color: #007bff;
    color: white;
  }

  .btn-primary:hover {
    background-color: #0056b3;
  }

  .btn-danger {
    background-color: #dc3545;
    color: white;
  }

  .btn-danger:hover {
    background-color: #c82333;
  }

  /* Layout Styles */
  .row {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    gap: 1.5rem;
  }

  .col-md-3 {
    flex: 1 1 calc(25% - 1.5rem);
    max-width: calc(25% - 1.5rem);
    box-sizing: border-box;
  }

  /* Card Styles */
  .card {
    background-color: white;
    border-radius: 8px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    overflow: hidden;
    transition: transform 0.3s, box-shadow 0.3s;
  }

  .card:hover {
    transform: translateY(-5px);
    box-shadow: 0 6px 15px rgba(0, 0, 0, 0.2);
  }

  .card-img-top {
    width: 100%;
    height: 200px;
    object-fit: cover;
  }

  .card-body {
    padding: 1.5rem;
    text-align: center;
  }

  .card-title {
    font-size: 1.25rem;
    font-weight: bold;
    margin-bottom: 1rem;
  }

  .price {
    font-size: 1.2rem;
    font-weight: bold;
    color: #28a745;
  }

  .address {
    font-size: 1rem;
    color: #555;
    margin-bottom: 1rem;
  }

  .mt-3 {
    margin-top: 1rem;
  }

  /* Pagination */
  .pagination {
    display: flex;
    justify-content: center;
    margin-top: 2rem;
  }

  /* Nếu adminheader cố định ở đầu trang, thêm một margin-top cho phần tạo khu cắm trại */
  .headeradmin {
    margin-top: -150px;
    position: fixed;
    width: 100%;
    top: 0;
    left: 0;
    z-index: 999; /* Đảm bảo adminheader không che mất các phần tử khác */
  }

  .create-button-container text-center {
    margin-top: 100px; /* Tùy chỉnh giá trị margin-top phù hợp để tránh bị che */
  }
  .btn-create{
    margin-top: 40px;
  }
  body {
    font-family: Arial, sans-serif;
    background-color: #f4f6f9;
    margin: 0;
    padding: 50px 0 0 0; /* Thêm padding-top 50px */
  }
</style>

<body>


</div>
<div class="container">
  <!-- Search Form -->
  <div class="search-container">
    <form action="search-campsite-admin" method="post" class="form-inline">
      <div class="input-group">
        <button type="submit" class="btn btn-secondary">
          <i class="fa fa-search"></i>
        </button>
        <input name="search" type="text" placeholder="Search..." class="form-control" />
      </div>
    </form>

    <!-- Hiển thị kết quả tìm kiếm -->
    <div class="row">
      <c:forEach var="item" items="${campsites}">
        <div class="col-md-3">
          <div class="card">
            <img class="card-img-top" src="img/${item[0].campImage}" alt="Campsite image">
            <div class="card-body">
              <h3 class="card-title">
                <i class="fas fa-campground"></i> ${item[0].campName}
              </h3>
              <h5 class="price">
                <i class="fas fa-money-bill-wave"></i> ${item[0].campPrice} VND
              </h5>
              <h5 class="address">
                <i class="fas fa-map-marker-alt"></i> ${item[0].campAddress}
              </h5>
              <p>
                <i class="fas fa-info-circle"></i> ${item[0].campDescription}
              </p>
              <p>
                <i class="fas fa-calendar-day"></i> ${item[0].limite}
              </p>
              <p>
                <i class="fas fa-user"></i>
                <strong style="color: #007bff;">Owner: ${item[1]}</strong> <!-- Nổi bật tên owner bằng màu xanh -->
              </p>
              <p>
                <c:if test="${item[0].campStatus}">
                  <i class="fas fa-check-circle text-success"></i> Active
                </c:if>
                <c:if test="${!item[0].campStatus}">
                  <i class="fas fa-times-circle text-danger"></i> Inactive
                </c:if>
              </p>
              <div class="mt-3">
                <a href="#" onclick="showMess(${item[0].campId}); return false;" class="btn btn-danger">
                  <i class="fas fa-trash"></i> Delete
                </a>
              </div>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>




  </div>

  <!-- Pagination -->
  <div class="pagination">
    <div id="pagination"></div>
  </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.min.js"></script>
<script>
  // Confirmation for Delete
  function showMess(campId) {
    // Hiển thị hộp thoại xác nhận
    const option = confirm('Are you sure you want to delete this campsite?');
    if (option) {
      // Nếu xác nhận xóa, chuyển hướng đến URL xóa
      window.location.href = 'deleteCampsiteAdmin?id=' + campId;
    }
  }
</script>
</body>
