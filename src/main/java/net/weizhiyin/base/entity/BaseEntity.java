package net.weizhiyin.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;

import java.util.Date;

public class BaseEntity  {
    /**
     * 主键
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
}
