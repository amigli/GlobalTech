<%@ page import="model.Offerta" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home</title>
        <%@include file="/WEB-INF/includes/links.html"%>
        <script src="script/gestione-carousel.js" defer></script>
        <meta charset="UTF-8">
    </head>
    <body>

    <%@include file="/WEB-INF/includes/navbar.jsp"%>

    <div id="offerteAttive">

        <div class="slideshow-container">

            <div class="mySlides">
                <img src="#" style="width:100%">
            </div>

            <div class="mySlides">
                <img src="#" style="width:100%">
            </div>

            <div class="mySlides">
                <img src="#" style="width:100%">
            </div>
        </div>
        <br>

        <div style="text-align:center">
            <span class="dot" onclick="currentSlide(1)"></span>
            <span class="dot" onclick="currentSlide(2)"></span>
            <span class="dot" onclick="currentSlide(3)"></span>
        </div>

    </div>
        <%@include file="/WEB-INF/includes/footer.jsp"%>
    </body>
</html>