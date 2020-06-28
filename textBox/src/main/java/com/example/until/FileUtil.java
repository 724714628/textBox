package com.example.until;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author ：linhui
 * @date ：2019/06/27  16:40
 * @description：文件操作类
 */
public class FileUtil {
    /**
     * 写入文件
     * @param fileName
     * @param content
     * @throws IOException
     */
    public static void writeFile(String fileName, String content) throws IOException {
        File file = new File("textFiles//"+fileName+".txt");//保存文件地址
        if (!file.exists()) {
            file.createNewFile();
        }
        try (FileOutputStream fop = new FileOutputStream(file)) {
            byte[] contentInBytes = content.getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读文件
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String readFile(String fileName) throws IOException {
        File file = new File("textFiles//"+fileName+".txt");//目标文件地址
        String content = "";
        try {
            FileInputStream in = new FileInputStream(file);
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            content = new String(buffer, "utf-8");
            return content;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static HttpServletResponse downloadFile(String fileName, HttpServletResponse response){
        try {
            // path是指欲下载的文件的路径。
            String path = "textFiles//"+fileName+".txt";
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            } catch (IOException ex) {
            ex.printStackTrace();
            }
        return response;
    }
}
