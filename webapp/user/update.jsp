<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="kr">
<%-- include 지시어 --%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<body>
<%@ include file="/WEB-INF/views/include/nav.jsp" %>
<div class="navbar navbar-default" id="subnav">
    <div class="col-md-12">
        <div class="navbar-header">
            <a href="#" style="margin-left:15px;" class="navbar-btn btn btn-default btn-plus dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-home" style="color:#dd1111;"></i> Home <small><i class="glyphicon glyphicon-chevron-down"></i></small></a>
            <ul class="nav dropdown-menu">
                <li><a href="../../../user/profile.html"><i class="glyphicon glyphicon-user" style="color:#1111dd;"></i> Profile</a></li>
                <li class="nav-divider"></li>
                <li><a href="/user/list"><i class="glyphicon glyphicon-cog" style="color:#dd1111;"></i> Settings</a></li>
            </ul>
        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse2">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="../../../index.html">Posts</a></li>
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <li><a href="/user/logout" role="button">로그아웃</a></li>
                        <li><a href="/user/update?userId=${user.userId}" role="button">개인정보수정</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/user/login" role="button">로그인</a></li>
                        <li><a href="../../../user/form.html" role="button">회원가입</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</div>
<div class="container" id="main">
    <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-default content-main">
            <form name="question" method="post" action="/user/update">
                <div class="form-group">
                    <label for="userId">사용자 아이디</label>
                    <p value="${user.userId}"></p>
                    <input class="form-control" id="userId" name="userId" value="${user.userId}" readonly>
                </div>
                <div class="form-group">
                    <label for="password">비밀번호</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                </div>
                <div class="form-group">
                    <label for="name">이름</label>
                    <input class="form-control" id="name" name="name" value="${user.name}">
                </div>
                <div class="form-group">
                    <label for="email">이메일</label>
                    <input type="email" class="form-control" id="email" name="email" value="${user.email}">
                </div>
                <button type="submit" class="btn btn-success clearfix pull-right">수정하기</button>
                <div class="clearfix" />
            </form>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/include/script.jsp" %>
</body>
</html>
