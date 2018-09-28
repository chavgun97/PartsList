package com.chavhun.partlist.service;

import com.chavhun.partlist.dao.PartDao;
import com.chavhun.partlist.model.Part;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PartServiceImpl implements PartService {
    private PartDao partDao;

    @Transactional
    public void setPartDao(PartDao partDao) {
        this.partDao = partDao;
    }
    @Transactional
    public void addPart(Part part) {
        partDao.addPart(part);
    }
    @Transactional
    public void updatePart(Part part) {
        partDao.updatePart(part);
    }
    @Transactional
    public void removePart(int id) {
        partDao.removePart(id);
    }
    @Transactional
    public Part getPartById(int id) {
        return partDao.getPartById(id);
    }
    @Transactional
    public List<Part> listParts() {
        return partDao.listParts();
    }
}
