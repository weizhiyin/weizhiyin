package net.weizhiyin.base.controller;


import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import net.weizhiyin.base.entity.Sysfile;
import net.weizhiyin.base.service.SysfileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * <p>
 * 文件表 前端控制器
 * </p>
 *
 * @author Huang zhineng
 * @since 2018-08-10
 */
@RestController
@RequestMapping("/sysfile")
public class SysfileController {
    @Autowired
    private Environment evn;
    @Autowired
    private SysfileService sysfileService;

    @RequestMapping("/upload")
    public Sysfile upload(MultipartFile file) throws IOException {
        Sysfile sysFile = sysfileService.writeFile(file);
        if(sysFile!=null){
            return sysFile;
        }
        return null;
    }

    @RequestMapping("/downloadFile")
    public void downloadFile(String fileId, String filePath, HttpServletRequest request, HttpServletResponse response) throws IOException{

        if(StringUtils.isBlank(filePath) && StringUtils.isBlank(fileId)){
            throw new IOException();
        }

        Sysfile file = null;
        if(StringUtils.isNotBlank(fileId)){
            file = sysfileService.selectById(fileId);
        }else{
            file = new Sysfile();
            file.setFilePath(filePath);
            String[] split = filePath.split("\\.");
            file.setFileType(split[split.length-1]);

            if(filePath.indexOf("/")>-1){
                String[] name = split[split.length-2].split("/");
                file.setFileName(name[name.length-1]);
            }else if(filePath.indexOf("\\")>-1){
                String[] name = split[split.length-2].split("\\\\");
                file.setFileName(name[name.length-1]);
            }else{
                file.setFileName(filePath.substring(0, filePath.indexOf(".")));
            }
        }

        String path = evn.getProperty("dirPath")+file.getFilePath();
        String fileName = file.getFileName()+"."+file.getFileType();
        String agent = request.getHeader("USER-AGENT");
        if (null != agent && -1 != agent.indexOf("MSIE")) // IE
        {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else if (null != agent && -1 != agent.indexOf("Mozilla")) // Firefox
        {
            fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        } else {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        }

        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
        InputStream inputStream= null;
        OutputStream os = null;
        try {
            inputStream = new FileInputStream(new File(path));

            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.close();
            inputStream.close();
        }finally{
            if(os!=null){os.close();}
            if(inputStream!=null){inputStream.close();}
        }
    }
    @RequestMapping("/getFile")
    public void filePath(Sysfile SysFile,HttpServletRequest request,HttpServletResponse response,Boolean or) throws IOException{
        String filePath = SysFile.getFilePath();
        if(SysFile.getId()!=null) {
            Sysfile SysFile1 = sysfileService.selectById(SysFile.getId());
            if (SysFile1 != null) {
                filePath=SysFile1.getFilePath();
            }
        }
        if(or!=null&&or){
            int splitInt = filePath.lastIndexOf(".");
            String name = filePath.substring(0, splitInt);
            String suffix = filePath.substring(splitInt + 1, filePath.length());
            filePath=name+""+SysfileService.THUMBNAIL+"."+suffix;
        }
        if(StringUtils.isEmpty(filePath)){
            String fileName = StrUtil.isEmpty(SysFile.getFileName())?"FILE":SysFile.getFileName();
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE")) // IE
            {
                fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) // Firefox
            {
                fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            } else {
                fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            }
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "fileName="+fileName);
        }
        File file = new File(evn.getProperty("dirPath")+filePath);
        if(!file.exists()){
            throw new IOException("Could not find file");
        }
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            throw new IOException("file too big...");
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        int offset = 0;
        int numRead = 0;
        while (offset < buffer.length
                && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file "
                    + file.getName());
        }
        fi.close();
        IoUtil.write(response.getOutputStream(), true, buffer);
    }
}

