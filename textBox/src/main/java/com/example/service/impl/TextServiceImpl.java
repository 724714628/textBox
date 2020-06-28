package com.example.service.impl;

import com.example.dto.TextDto;
import com.example.entity.Text;
import com.example.repository.TextRepository;
import com.example.service.TextService;
import com.example.until.BeanUtils;
import com.example.until.FileUtil;
import com.example.until.RedisUtil;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author ：linhui
 * @date ：2019/06/27  16:40
 * @description：文本盒服务类
 */

@Service
public class TextServiceImpl implements TextService {

    @Autowired
    private TextRepository textRepository;
    @Autowired
    private RedisUtil redisUtil;
//    @Autowired
//    private IdGenerator idGenerator;

    @Override
    public boolean add(TextDto textDto) throws IOException {
        Text text = new Text();
        BeanUtils.copyProperties(textDto,text);
        textRepository.save(text);
        FileUtil.writeFile(textDto.getTextName(),textDto.getContent());
        return true;
    }

    @Override
    public List<TextDto> findAll() throws IOException {
        List<Text> all = textRepository.findAll();
        List<TextDto> allDto = new ArrayList<>();
        for(Text text:all){
            String content = FileUtil.readFile(text.getTextName());
            TextDto textDto = new TextDto();
            textDto.setContent(content);
            BeanUtils.copyProperties(text,textDto);
            allDto.add(textDto);
        }
        return allDto;
    }

    @Override
    public TextDto sel(String textId) throws IOException {
        TextDto textDto = new TextDto();
        Optional<Text> opText = textRepository.findById(textId);
        Text text = opText.get();
        BeanUtils.copyProperties(text,textDto);
        String content = FileUtil.readFile(textDto.getTextName());
        textDto.setContent(content);
        return textDto;
    }

    /**
     * 并发控制上锁成功--1  已上锁--0 无需上锁--0
     * @param textId
     * @return
     */
    @Override
    public boolean lock(String textId) {
        if(!StringUtils.isNullOrEmpty(textId)){
            //无人操作
            if(!"1".equals(redisUtil.get("lock"+textId))){
                redisUtil.setEx("lock"+textId,"1",60, TimeUnit.SECONDS);
                return true;
            }else{
                return false;
            }
        }
        return true;
    }

    /**
     * 并发控制解锁
     * @param textId
     * @return
     */
    @Override
    public void unlock(String textId) {
        if(!StringUtils.isNullOrEmpty(textId)){
            //无人操作
            if("1".equals(redisUtil.get("lock"+textId))){
                redisUtil.delete("lock"+textId);
            }
        }
    }

}
