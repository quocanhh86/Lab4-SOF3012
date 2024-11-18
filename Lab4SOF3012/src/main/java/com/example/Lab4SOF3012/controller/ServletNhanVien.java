package com.example.Lab4SOF3012.controller;

import com.example.Lab4SOF3012.model.NhanVien;
import com.example.Lab4SOF3012.repository.NhanVienRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "ServletNhanVien",
        value = {
        "/nhan-vien/hien-thi",
        "/nhan-vien/add",
        "/nhan-vien/detail",
        "/nhan-vien/update",
        "/nhan-vien/delete",
})
public class ServletNhanVien extends HttpServlet {
    private NhanVienRepository repository = new NhanVienRepository();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("/nhan-vien/hien-thi")){
            this.viewEmployee(request, response);
        } else if(uri.contains("/nhan-vien/detail")){
            this.detailEmployee(request, response);
        } else {
            this.deleteEmployee(request, response);
        }
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        NhanVien nv = repository.getOne(id);
//        boolean success = repository.delete(nv);
////        if (success) {
////            req.getSession().setAttribute("message", "Xoá  nhân viên thành công!");
////        } else {
////            req.getSession().setAttribute("message", "Xoá nhân viên thất bại!");
////        }
        repository.delete(nv);
        response.sendRedirect("/nhan-vien/hien-thi");
    }

    private void detailEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        NhanVien nv = repository.getOne(id);
        request.setAttribute("nv", nv);
        request.setAttribute("listEmployee", repository.getAll());
        request.getRequestDispatcher("/view/hien-thi.jsp").forward(request, response);
    }

    private void viewEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listEmployee", repository.getAll());
        request.getRequestDispatcher("/view/hien-thi.jsp").forward(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("/nhan-vien/add")){
            this.addEmployee(request, response);
        } else {
            this.updateEmployee(request, response);
        }
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        NhanVien nv = repository.getOne(id);
        BeanUtils.populate(nv, request.getParameterMap());
        //        boolean success = repository.update(nv);
//        if (success) {
//            req.getSession().setAttribute("message", "Cập nhật nhân viên thành công!");
//        } else {
//            req.getSession().setAttribute("message", "Cập nhật nhân viên thất bại!");
//        }
        repository.update(nv);
        response.sendRedirect("/nhan-vien/hien-thi");
    }

    private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        NhanVien nv = new NhanVien();
        BeanUtils.populate(nv, request.getParameterMap());
        //        boolean success = repository.add(nv);
//        if (success) {
//            req.getSession().setAttribute("message", "Cập nhật nhân viên thành công!");
//        } else {
//            req.getSession().setAttribute("message", "Cập nhật nhân viên thất bại!");
//        }
        repository.add(nv);
        response.sendRedirect("/nhan-vien/hien-thi");
    }
}
