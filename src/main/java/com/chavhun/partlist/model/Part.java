package com.chavhun.partlist.model;

import com.chavhun.partlist.dao.PartDao;

import javax.persistence.*;
import java.nio.charset.Charset;
import java.util.List;

@Entity   // Класс сущность.
@Table(name = "parts")   //Какая таблица

public class Part {

    @Id
    @Column(name = "ID")
    @GeneratedValue  //генерируемое значение
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NECESSARILY")
    private boolean necessarily;

    @Column (name = "COUNT")
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNecessarily() {
        return  necessarily?"Да":"Нет";
    }

    public void setNecessarily(String necessarily) {
        System.out.println(necessarily);
        if(necessarily.equalsIgnoreCase("Да"))
        this.necessarily = true;
        else
            this.necessarily = false;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", necessarily=" + necessarily +
                ", count=" + count +
                '}';
    }


}
