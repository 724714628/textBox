package com.example.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_text")
public class Text {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)//id生成策略自动增长
    //配置uuid，本来jpa是不支持uuid的，但借用hibernate的方法可以实现。
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "text_id")
    private String textId;
    @Column(name = "text_name")
    private String textName;

    public String getTextId() {
        return textId;
    }

    public void setTextId(String textId) {
        this.textId = textId;
    }

    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
    }

}
