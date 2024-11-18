package com.example.Lab4SOF3012.repository;

import com.example.Lab4SOF3012.model.NhanVien;
import com.example.Lab4SOF3012.util.HibernateConfig;
import org.hibernate.Session;

import java.util.List;

public class NhanVienRepository {
    Session s;

    public NhanVienRepository() {
        s = HibernateConfig.getFACTORY().openSession();
    }

    public List<NhanVien> getAll() {
        return s.createQuery("FROM NhanVien ").list();
    }

    public NhanVien getOne(Integer id) {
        return s.find(NhanVien.class, id);
    }

    public void add(NhanVien nv) {
        try {
            s.getTransaction().begin();
            s.save(nv);
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void update(NhanVien nv) {
        try {
            s.getTransaction().begin();
            s.merge(nv);
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void delete(NhanVien nv) {
        try {
            s.getTransaction().begin();
            s.delete(nv);
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(new NhanVienRepository().getOne(1));
    }
}
