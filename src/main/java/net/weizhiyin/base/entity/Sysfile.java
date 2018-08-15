package net.weizhiyin.base.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author Huang zhineng
 * @since 2018-08-10
 */
@TableName("sysfile")
public class Sysfile extends Model<Sysfile> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 创建人
     */
    private String sysCreateBy;
    /**
     * 创建时间
     */
    private Date sysCreate;
    /**
     * 修改人
     */
    private String sysUpdateBy;
    /**
     * 修改时间
     */
    private Date sysUpdate;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 业务id
     */
    private String bussId;
    /**
     * 业务类型
     */
    private String bussType;
    /**
     * 上传时间
     */
    private Date uploadDate;
    /**
     * 文件大小
     */
    private Double fileSize;
    /**
     * 文件md5
     */
    private String fileMD5;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSysCreateBy() {
        return sysCreateBy;
    }

    public void setSysCreateBy(String sysCreateBy) {
        this.sysCreateBy = sysCreateBy;
    }

    public Date getSysCreate() {
        return sysCreate;
    }

    public void setSysCreate(Date sysCreate) {
        this.sysCreate = sysCreate;
    }

    public String getSysUpdateBy() {
        return sysUpdateBy;
    }

    public void setSysUpdateBy(String sysUpdateBy) {
        this.sysUpdateBy = sysUpdateBy;
    }

    public Date getSysUpdate() {
        return sysUpdate;
    }

    public void setSysUpdate(Date sysUpdate) {
        this.sysUpdate = sysUpdate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getBussId() {
        return bussId;
    }

    public void setBussId(String bussId) {
        this.bussId = bussId;
    }

    public String getBussType() {
        return bussType;
    }

    public void setBussType(String bussType) {
        this.bussType = bussType;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileMD5() {
        return fileMD5;
    }

    public void setFileMD5(String fileMD5) {
        this.fileMD5 = fileMD5;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Sysfile{" +
        ", id=" + id +
        ", sysCreateBy=" + sysCreateBy +
        ", sysCreate=" + sysCreate +
        ", sysUpdateBy=" + sysUpdateBy +
        ", sysUpdate=" + sysUpdate +
        ", fileName=" + fileName +
        ", fileType=" + fileType +
        ", filePath=" + filePath +
        ", bussId=" + bussId +
        ", bussType=" + bussType +
        ", uploadDate=" + uploadDate +
        ", fileSize=" + fileSize +
        ", fileMD5=" + fileMD5 +
        "}";
    }
}
