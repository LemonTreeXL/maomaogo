package cn.itsource.maomaogo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 商品扩展
 * </p>
 *
 * @author LemonTreeXL
 * @since 2019-05-20
 */
@TableName("t_product_ext")
public class ProductExt extends Model<ProductExt> {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 简介
     */
    private String description;

    /**
     * 图文内容
     */
    @TableField("richContent")
    private String richContent;

    /**
     * 商品ID
     */
    @TableField("productId")
    private Long productId;

    @TableField("viewProperties")
    private String viewProperties;

    @TableField("skuProperties")
    private String skuProperties;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRichContent() {
        return richContent;
    }

    public void setRichContent(String richContent) {
        this.richContent = richContent;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getViewProperties() {
        return viewProperties;
    }

    public void setViewProperties(String viewProperties) {
        this.viewProperties = viewProperties;
    }

    public String getSkuProperties() {
        return skuProperties;
    }

    public void setSkuProperties(String skuProperties) {
        this.skuProperties = skuProperties;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProductExt{" +
        "id=" + id +
        ", description=" + description +
        ", richContent=" + richContent +
        ", productId=" + productId +
        ", viewProperties=" + viewProperties +
        ", skuProperties=" + skuProperties +
        "}";
    }
}
