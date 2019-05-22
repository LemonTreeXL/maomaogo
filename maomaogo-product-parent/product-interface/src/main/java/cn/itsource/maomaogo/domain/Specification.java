package cn.itsource.maomaogo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品属性
 * </p>
 *
 * @author LemonTreeXL
 * @since 2019-05-20
 */
@TableName("t_specification")
public class Specification extends Model<Specification> {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 规格名称
     */
    @TableField("specName")
    private String specName;

    @TableField("productTypeId")
    private Long productTypeId;

    @TableField("isSku")
    private Integer isSku;

    //显示属性的值
    @TableField(exist=false)
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    //sku属性值列表
    @TableField(exist = false)
    private List<String> options = new ArrayList<>();

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getIsSku() {
        return isSku;
    }

    public void setIsSku(Integer isSku) {
        this.isSku = isSku;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Specification{" +
        "id=" + id +
        ", specName=" + specName +
        ", productTypeId=" + productTypeId +
        ", isSku=" + isSku +
        "}";
    }
}
