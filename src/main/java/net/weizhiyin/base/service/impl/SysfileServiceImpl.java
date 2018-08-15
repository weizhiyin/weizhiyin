package net.weizhiyin.base.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.weizhiyin.base.entity.Sysfile;
import net.weizhiyin.base.mapper.SysfileMapper;
import net.weizhiyin.base.service.SysfileService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author Huang zhineng
 * @since 2018-08-10
 */
@Service
public class SysfileServiceImpl extends ServiceImpl<SysfileMapper, Sysfile> implements SysfileService {
    @Autowired
    private Environment evn;

    @Override
    @Transactional
    public Sysfile writeFile(MultipartFile multipartFile) throws IOException {
        Sysfile file = new Sysfile();
        String originalFilename = multipartFile.getOriginalFilename();
        int splitInt = originalFilename.lastIndexOf(".");
        String name = originalFilename.substring(0, splitInt);
        String suffix = originalFilename.substring(splitInt + 1, originalFilename.length());

        Date now = Calendar.getInstance().getTime();
        String filename = name + DateUtil.format(now,"yyyyMMddHHmmss");
        String filePath =  "/" + filename + "." + suffix;
        String filePathThumbnail =  "/" + filename +""+SysfileService.THUMBNAIL+"." + suffix;
        String fileSrc =evn.getProperty("dirPath") + filePath;

        file.setFileName(name);
        file.setFileType(suffix);
        file.setFilePath(filePath);
        file.setUploadDate(new Date());
        // 写文件
        FileUtil.writeBytes(multipartFile.getBytes(), fileSrc);
        file.setFileSize(Double.valueOf(new File(fileSrc).length()+""));
        this.insert(file);
        if (SysfileService.IMAGE_JPG.equals(suffix.toUpperCase()) || SysfileService.IMAGE_PNG.equals(suffix.toUpperCase())) {
            Thumbnails.of(fileSrc)
                    .scale(1f)
                    .outputQuality(0.3f)
                    .toFile(evn.getProperty("dirPath") + filePathThumbnail);
        }
        return file;
    }
}
