package com.example.service;

import com.example.dto.TextDto;

import java.io.IOException;
import java.util.List;

/**
 * @author ：linhui
 * @date ：2019/06/27  16:40
 * @description：文本盒服务类
 */

public interface TextService {

    boolean add(TextDto textDto) throws IOException;

    List<TextDto> findAll() throws IOException;

    TextDto sel(String textId) throws IOException;

    boolean lock(String textId);

    void unlock(String textId);
}
