<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: abc
  Date: 17/11/24
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/nhan-vien/add" method="post">
    <input type="hidden" name="id" value="${nv.id}">
    Mã <input type="text" name="ma" value="${nv.ma}"> <br>
    Tên <input type="text" name="ten" value="${nv.ten}"> <br>
    Giới Tính
    <input type="radio" name="gioiTinh" ${nv.gioiTinh == "Nam" ? "Checked" : ""} value="Nam" checked> Nam
    <input type="radio" name="gioiTinh" ${nv.gioiTinh == "Nữ" ? "Checked" : ""} value="Nữ"> Nữ <br>
    Địa Chỉ <input type="text" name="diaChi" value="${nv.diaChi}"> <br>
    <button type="submit" onclick="ConfirmAndAlertAdd()">Add</button>
    <button type="submit" formaction="/nhan-vien/update" onclick="return confirm('Bạn có muốn update không')">Update
    </button>
</form>

<table border="1">
    <thead>
    <tr>
        <th>#</th>
        <th>Mã</th>
        <th>Tên</th>
        <th>Giới Tính</th>
        <th>Địa Chỉ</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listEmployee}" var="nv" varStatus="i">
        <tr>
            <td>${i.index + 1}</td>
            <td>${nv.ma}</td>
            <td>${nv.ten}</td>
            <td>${nv.gioiTinh}</td>
            <td>${nv.diaChi}</td>
            <td>
                <button><a href="/nhan-vien/detail?id=${nv.id}">Detail</a></button>
                <button>
                    <a href="/nhan-vien/delete?id=${nv.id}"
                           onclick="ConfirmAndAlertDelete(event)">Delete</a>
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script>
    function ConfirmAndAlertDelete(event) {
        if (confirm('Bạn có muốn xoá không')){
            alert('Xoá thành công');
        } else {
            event.preventDefault();
            return false;
        }
    }
    function ConfirmAndAlertAdd() {
        if (!confirm('Bạn có muốn add không')){
            event.preventDefault();
            return false;
        } else {
            alert('Add thành công');
        }
    }
</script>
</body>
</html>
