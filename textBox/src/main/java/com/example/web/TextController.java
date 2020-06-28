package com.example.web;

import com.example.dto.TextDto;
import com.example.service.TextService;
import com.example.until.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author ：linhui
 * @date ：2019/06/27 16:40
 * @description：文本盒控制类
 */

@Controller
public class TextController {
    @Autowired
    TextService textService;

    @RequestMapping("/")
    public String index(){
        return "redirect:/toAdd";
    }

    /**
     * 查询表记录并查出文件内容
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String hello(Model model) throws IOException {
        List<TextDto> all = textService.findAll();
        model.addAttribute("all",all);
        return "index";
    }

    @RequestMapping("toAdd")
    public String toAdd(){
        return "add";
    }

    /**
     * 增加表记录并写入文件
     * @param textDto
     * @return
     * @throws IOException
     */
    @RequestMapping("/add")
    public String add(TextDto textDto) throws IOException {
        textService.add(textDto);
        textService.unlock(textDto.getTextId());
        return "redirect:/list";
    }

    /**
     * 查出文件记录并跳转编辑页
     * @param textId
     * @param model
     * @return
     */
    @RequestMapping("/edit/{textId}")
    public String edit(@PathVariable String textId, Model model) throws IOException {
        TextDto sel = textService.sel(textId);
        model.addAttribute("text",sel);
        return "edit";
    }

    /**
     * 查看是否上锁 1--上锁成功 0--已上锁
     * @return
     */
    @RequestMapping("/isLock/{textId}")
    @ResponseBody
    public boolean isLock(@PathVariable String textId){
        if(textService.lock(textId)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 下载文件
     * @param textId
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/download/{textId}")
    public HttpServletResponse download(@PathVariable String textId, HttpServletResponse response) throws IOException {
        TextDto sel = textService.sel(textId);
        HttpServletResponse response0 = FileUtil.downloadFile(sel.getTextName(),response);
        return response0;
    }
}
