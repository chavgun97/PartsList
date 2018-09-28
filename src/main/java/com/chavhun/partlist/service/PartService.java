package com.chavhun.partlist.service;

import com.chavhun.partlist.model.Part;

import java.util.List;

public interface PartService {

    void addPart(Part part);
    void updatePart(Part part);
    void removePart(int id);
    Part getPartById(int id);
    List<Part> listParts();
}
