package com.chavhun.partlist.dao;

import com.chavhun.partlist.model.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartDaoImpl implements PartDao {
    private static final Logger logger = LoggerFactory.getLogger(PartDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addPart(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(part);
        logger.info("Деталь успешно сохранена. Детали: " + part);
    }

    public void updatePart(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.update(part);
        logger.info("Деталь успешно обновлена. Детали:" + part);
    }

    public void removePart(int id) {
        Session session = sessionFactory.getCurrentSession();
        Part part = (Part) session.load(Part.class, id);
        if(part!=null){
            session.delete(part);
        }
        logger.info("Деталь успешно удалена. Детали: " + part);
    }

    public Part getPartById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Part part = (Part) session.load(Part.class, id);
        logger.info("Книга успешно загружена. Детели:" + part);
        return part;
    }

    @SuppressWarnings("unchecked")
    public List<Part> listParts() {
        Session session = sessionFactory.getCurrentSession();
        List<Part> listParts = session.createQuery("from Part").list();
        for(Part part : listParts){
            logger.info("Лист деталей:" + part);
        }

        return listParts;
    }
}
