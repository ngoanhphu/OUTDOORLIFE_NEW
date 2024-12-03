<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<body>


<!--================Breadcrumb Area =================-->
<section class="breadcrumb_area">
  <div class="overlay bg-parallax" data-stellar-ratio="0.8" data-stellar-vertical-offset="0" data-background=""></div>
  <div class="container">
    <div class="page-cover text-center">
      <h2 class="page-cover-tittle">Contact Us</h2>
      <ol class="breadcrumb">
        <li><a href="index.html">Home</a></li>
        <li class="active">Contact Us</li>
      </ol>
    </div>
  </div>
</section>
<!--================Breadcrumb Area =================-->

<h2 style="color: green; text-align: center;" >${sendContact} </h2>
<!--================Contact Area =================-->
<section class="contact_area section_gap">
  <div class="container">
    <div class="col-md-6 wow fadeIn" data-wow-delay="0.1s">
      <iframe class="position-relative rounded w-100 h-100"
              src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3835.6562639715808!2d108.24985467575523!3d15.979317184687314!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3142108f00670d49%3A0x337f1710d8c78ba0!2zMjAgTmd1eeG7hW4gTWluaCBDaMOidSwgSG_DoCBI4bqjaSwgTmfFqSBIw6BuaCBTxqFuLCDEkMOgIE7hurVuZyA1NTAwMDAsIFZp4buHdCBOYW0!5e0!3m2!1svi!2s!4v1685383290004!5m2!1svi!2s"
              frameborder="0" style="min-height: 720px; border:0; min-width: 1080px; margin-bottom: 50px" allowfullscreen="" aria-hidden="false"
              tabindex="0"></iframe>
    </div>
    <div class="row">
      <div class="col-md-3">
        <div class="contact_info">
          <div class="info_item">
            <i class="lnr lnr-home"></i>
            <h6>Viet Nam, Da Nang, Ngu Hang Son</h6>
            <p>20 Nguyen Minh Chau</p>
          </div>
          <div class="info_item">
            <i class="lnr lnr-phone-handset"></i>
            <h6><a href="#">00 (440) 9865 562</a></h6>
            <p>Mon to Fri 6am to 12 pm</p>
          </div>
          <div class="info_item">
            <i class="lnr lnr-envelope"></i>
            <h6><a href="#">goldenHotel@gmail.com</a></h6>
            <p>Send us your query anytime!</p>
          </div>
        </div>
      </div>

    </div>
  </div>
</section>
<!--================Contact Area =================-->

<%@include file="footer.jsp" %>


<!--       ================Contact Success and Error message Area =================
        <div id="success" class="modal modal-message fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <i class="fa fa-close"></i>
                        </button>
                        <h2>Thank you</h2>
                        <p>Your message is successfully sent...</p>
                    </div>
                </div>
            </div>
        </div>

         Modals error

        <div id="error" class="modal modal-message fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <i class="fa fa-close"></i>
                        </button>
                        <h2>Sorry !</h2>
                        <p> Something went wrong </p>
                    </div>
                </div>
            </div>
        </div>-->
<!--================End Contact Success and Error message Area =================-->


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/popper.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="vendors/owl-carousel/owl.carousel.min.js"></script>
<script src="js/jquery.ajaxchimp.min.js"></script>
<script src="vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.js"></script>
<script src="vendors/nice-select/js/jquery.nice-select.js"></script>
<script src="js/mail-script.js"></script>
<script src="js/stellar.js"></script>
<script src="vendors/imagesloaded/imagesloaded.pkgd.min.js"></script>
<script src="vendors/isotope/isotope-min.js"></script>
<script src="js/stellar.js"></script>
<script src="vendors/lightbox/simpleLightbox.min.js"></script>
<!--gmaps Js-->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
<script src="js/gmaps.min.js"></script>
<!-- contact js -->
<script src="js/jquery.form.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/contact.js"></script>
<script src="js/custom.js"></script>
</body>