<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@include file="../jspf/head.jspf" %>

<body>
<div class="container">
    <%@include file="../jspf/navbar.jspf" %>
    <h2>Search results for "${search}": </h2>
    <form class="row g-3" action="/search">
        <div class="col-md-6">
            <input name="search" class="form-control me-2" type="search" placeholder="Search"
                   aria-label="Search"/>
        </div>
        <div class="col-md-2">
            <label>
                <select name="order" class="form-select">
                    <option selected disabled>Order by</option>
                    <option label="Date ↑" value="date_startDESC"></option>
                    <option label="Date ↓" value="date_startASC"></option>
                    <option label="Topic ↑" value="topicDESC"></option>
                    <option label="Topic ↓" value="topicASC"></option>
                    <option label="Price ↑" value="priceDESC"></option>
                    <option label="Price ↓" value="priceASC"></option>
                </select>
            </label>
        </div>
        <div class="col-md-4">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </div>
    </form>
    <br>
    <c:if test="${expositionList.size() == 0}">
        <h5>No results found.</h5>
    </c:if>
    <c:if test="${expositionList.size() != 0}">
        <c:forEach var="exposition" items="${expositionList}">
            <div class="card">
                <img src="${exposition.imagePath}" class="image"
                     alt="No image"/>
                <div class="cardList">
                    <h5>${exposition.topic}</h5>
                    <ul>
                        <li>Starting: ${exposition.dateStart}</li>
                        <li>Finishing: ${exposition.dateEnd}</li>
                        <li>Price: ${exposition.price}</li>
                        <li>Places left: ${exposition.capacity}</li>
                    </ul>
                    <h6>For more information:</h6>
                    <a href="https://${exposition.detailsLink}">Link</a>
                </div>
                <c:if test="${exposition.capacity > 0}">
                    <button class="btn btn-success knopka">Purchase ticket</button>
                </c:if>
                <c:if test="${exposition.capacity == null}">
                    <button class="btn knopka" disabled>Sold out</button>
                </c:if>
            </div>
        </c:forEach>
    </c:if>
</div>

</body>
</html>