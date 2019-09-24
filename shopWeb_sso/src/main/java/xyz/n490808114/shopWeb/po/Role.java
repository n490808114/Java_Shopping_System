package xyz.n490808114.shopWeb.po;

import java.io.Serializable;

public class Role implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String name;
    private String remark;

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }
}