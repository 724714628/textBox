package com.example.dto;

import javax.persistence.Column;

/**
 * @author ：linhui
 * @date ：2019/06/27  16:40
 * @description：文本盒dto
 */
public class TextDto {

    private String textId;
    private String textName;
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
