package net.weizhiyin.base.service;

import net.weizhiyin.base.entity.Sysfile;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author Huang zhineng
 * @since 2018-08-10
 */
public interface SysfileService extends IService<Sysfile> {
    public static final String THUMBNAIL="thumbnail";
    public static final String IMAGE_JPG="JPG";
    public static final String IMAGE_PNG="PNG";
    Sysfile writeFile(MultipartFile file) throws IOException;
}
