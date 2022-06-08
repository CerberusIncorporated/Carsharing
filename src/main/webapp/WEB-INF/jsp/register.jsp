<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${sign_up}</title>
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">

    <fmt:setLocale value="${sessionScope.locale}" scope="session"/>
    <fmt:bundle basename="content">
        <fmt:message key="register.password" var="password"/>
        <fmt:message key="register.sign_up" var="register"/>
        <fmt:message key="register.error_message" var="error_message"/>
        <fmt:message key="register.have_account" var="have_account"/>
        <fmt:message key="register.first_name" var="first_name"/>
        <fmt:message key="register.second_name" var="second_name"/>
        <fmt:message key="register.middle_name" var="middle_name"/>
        <fmt:message key="register.phone_number" var="phone_number"/>
        <fmt:message key="register.passport_number" var="passport_number"/>
        <fmt:message key="register.identification_number" var="identification_number"/>
        <fmt:message key="register.issue_date" var="issue_date"/>
        <fmt:message key="register.validation_message" var="validation_message"/>
        <fmt:message key="header.log_in" var="log_in"/>
        <fmt:message key="header.sign_up" var="sign_up"/>
    </fmt:bundle>
</head>
<body style="background: #3DDAFA">
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <form action="Controller" method="post" class="login100-form validate-form p-l-55 p-r-55 p-t-178">
					<span class="login100-form-title">
                        ${sign_up}
                    </span>
                <input type="hidden" name="command" value="register">
                <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter email">
                    <input class="input100" type="text" name="email" placeholder="email" required>
                    <span class="focus-input100"></span>
                </div>
                <div class="wrap-input100 validate-input" data-validate="Please enter password">
                    <input class="input100" type="password" name="password" placeholder="${password}" required>
                    <span class="focus-input100"></span>
                </div>
                <br>
                <div class="wrap-input100 validate-input m-b-16" data-validate="${first_name}">
                    <input class="input100" type="text" name="first_name" placeholder="${first_name}"
                           pattern="[A-ZА-Я][a-zа-я]*" required>
                    <span class="focus-input100"></span>
                </div>
                <div class="wrap-input100 validate-input m-b-16" data-validate="${second_name}">
                    <input class="input100" type="text" name="second_name" placeholder="${second_name}"
                           pattern="[A-ZА-Я][a-zа-я]*" required>
                    <span class="focus-input100"></span>
                </div>
                <div class="wrap-input100 validate-input m-b-16" data-validate="${middle_name}">
                    <input class="input100" type="text" name="middle_name" placeholder="${middle_name}"
                           pattern="[A-ZА-Я][a-zа-я]*">
                    <span class="focus-input100"></span>
                </div>
                <div class="wrap-input100 validate-input m-b-16" data-validate="${phone_number}">
                    <input class="input100" type="tel" name="phone_number" placeholder="${phone_number}" size="13"
                           pattern="[+]([0-9]{12})" required>
                    <span class="focus-input100"></span>
                </div>
                <div class="wrap-input100 validate-input m-b-16" data-validate="${passport_number}">
                    <input class="input100" name="passport_number" placeholder="${passport_number}(AA1234567)"
                           pattern="([A-ZА-Я]{2})([0-9]{7})" required>
                    <span class="focus-input100"></span>
                </div>
                <div class="wrap-input100 validate-input m-b-16" data-validate="${identification_number}">
                    <input class="input100" name="identification_number"
                           placeholder="${identification_number}(1234567A123PB1)"
                           pattern="([0-9]{7})[A-ZА-Я]([0-9]{3})[A-ZА-Я][A-ZА-Я][0-9]" required>
                    <span class="focus-input100"></span>
                </div>
                <div class="wrap-input100 validate-input m-b-16" data-validate="${issue_date}">
                    <input class="input100" name="issue_date" type="date" placeholder="${issue_date}(DD-MM-YYYY)"
                           min="01/01/1970" max="01/01/2100" required>
                    <span class="focus-input100"></span>
                </div>
                <div class="container-login100-form-btn">
                    <button class="login100-form-btn" type="submit">${register}</button>
                </div>
                <c:set var="is_register_failed" scope="request" value="${requestScope.error}"/>
                <c:set var="is_invalid_data" scope="request" value="${requestScope.validation}"/>
                <c:choose>
                    <c:when test="${is_register_failed}">
                        <p>${error_message}</p>
                    </c:when>
                    <c:when test="${is_invalid_data}">
                        <p>${validation_message}</p>
                    </c:when>
                </c:choose>
                <div class=" flex-col-c p-t-170 p-b-40">
						<span class="txt1 p-b-9">
                            ${have_account}
                        </span>

                    <a href="login" class="txt3">${log_in}</a>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
