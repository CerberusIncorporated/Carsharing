<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${log_in}</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/util.css">

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:bundle basename="content">
        <fmt:message key="login.password" var="password"/>
        <fmt:message key="login.log_in" var="log_in"/>
        <fmt:message key="login.error_message" var="error_message"/>
        <fmt:message key="login.no_account" var="no_account"/>
        <fmt:message key="register.validation_message" var="validation_message"/>
        <fmt:message key="header.sign_up" var="sign_up"/>
    </fmt:bundle>
</head>
<body style="background: #3DDAFA">
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <form action="Controller" method="post" class="login100-form validate-form p-l-55 p-r-55 p-t-178">
					<span class="login100-form-title">
                        ${log_in}
                    </span>
                <input type="hidden" name="command" value="login">
                <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter email">
                    <input class="input100" type="text" name="email" placeholder="email" required>
                    <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Please enter password">
                    <input class="input100" type="password" name="password" placeholder="Password" required>
                    <span class="focus-input100"></span>
                </div>

                <div class="container-login100-form-btn">
                    <button class="login100-form-btn" type="submit">${log_in}</button>
                </div>

                <c:set var="is_register_failed" scope="request" value="${requestScope.error}"/>
                <c:set var="is_invalid_data" scope="request" value="${requestScope.validation}"/>

                <c:choose>
                    <c:when test="${is_register_failed}">
                        ${error_message}
                    </c:when>
                    <c:when test="${is_invalid_data}">
                        ${validation_message}
                    </c:when>
                </c:choose>

                <div class=" flex-col-c p-t-170 p-b-40">
						<span class="txt1 p-b-9">
                            ${no_account}
                        </span>

                    <a href="register" class="txt3">${sign_up}</a>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
