package cn.itsource.maomaogo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 商品
 * </p>
 *
 * @author LemonTreeXL
 * @since 2019-05-20
 */
@TableName("t_product")
public class Product extends Model<Product> {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("createTime")
    private Long createTime;

    @TableField("updateTime")
    private Long updateTime;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 副名称
     */
    @TableField("subName")
    private String subName;

    /**
     * 商品编码
     */
    private String code;

    /**
     * 商品类型ID
     */
    @TableField("productTypeId")
    private Long productTypeId;

    /**
     * 上架时间
     */
    @TableField("onSaleTime")
    private Long onSaleTime;

    /**
     * 下架时间
     */
    @TableField("offSaleTime")
    private Long offSaleTime;

    @TableField("brandId")
    private Long brandId;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 最高价
     */
    @TableField("maxPrice")
    private Integer maxPrice;

    /**
     * 最低价
     */
    @TableField("minPrice")
    private Integer minPrice;

    /**
     * 销量
     */
    @TableField("saleCount")
    private Integer saleCount;

    /**
     * 浏览量
     */
    @TableField("viewCount")
    private Integer viewCount;

    /**
     * 评论数
     */
    @TableField("commentCount")
    private Integer commentCount;

    /**
     * 评分
     */
    @TableField("commentScore")
    private Integer commentScore;

    /**
     * 显示属性摘要
     */
    @TableField("viewProperties")
    private String viewProperties;

    @TableField("goodCommentCount")
    private Integer goodCommentCount;

    @TableField("commonCommentCount")
    private Integer commonCommentCount;

    @TableField("badCommentCount")
    private Integer badCommentCount;

    private String medias;

    @TableField("skuProperties")
    private String skuProperties;

    @TableField(exist = false)
    private ProductType pt;
    @TableField(exist = false)
    private Brand brand;

    /**
     * 使用 domian来接收参数封装。方便维护【保存商品详细列表的信息】
     */
    @TableField(exist = false)
    private String content;
    @TableField(exist = false)
    private String description;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductType getPt() {
        return pt;
    }

    public void setPt(ProductType pt) {
        this.pt = pt;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Long getOnSaleTime() {
        return onSaleTime;
    }

    public void setOnSaleTime(Long onSaleTime) {
        this.onSaleTime = onSaleTime;
    }

    public Long getOffSaleTime() {
        return offSaleTime;
    }

    public void setOffSaleTime(Long offSaleTime) {
        this.offSaleTime = offSaleTime;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(Integer commentScore) {
        this.commentScore = commentScore;
    }

    public String getViewProperties() {
        return viewProperties;
    }

    public void setViewProperties(String viewProperties) {
        this.viewProperties = viewProperties;
    }

    public Integer getGoodCommentCount() {
        return goodCommentCount;
    }

    public void setGoodCommentCount(Integer goodCommentCount) {
        this.goodCommentCount = goodCommentCount;
    }

    public Integer getCommonCommentCount() {
        return commonCommentCount;
    }

    public void setCommonCommentCount(Integer commonCommentCount) {
        this.commonCommentCount = commonCommentCount;
    }

    public Integer getBadCommentCount() {
        return badCommentCount;
    }

    public void setBadCommentCount(Integer badCommentCount) {
        this.badCommentCount = badCommentCount;
    }

    public String getMedias() {
        return medias;
    }

    public void setMedias(String medias) {
        this.medias = medias;
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
        return "Product{" +
        "id=" + id +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", name=" + name +
        ", subName=" + subName +
        ", code=" + code +
        ", productTypeId=" + productTypeId +
        ", onSaleTime=" + onSaleTime +
        ", offSaleTime=" + offSaleTime +
        ", brandId=" + brandId +
        ", state=" + state +
        ", maxPrice=" + maxPrice +
        ", minPrice=" + minPrice +
        ", saleCount=" + saleCount +
        ", viewCount=" + viewCount +
        ", commentCount=" + commentCount +
        ", commentScore=" + commentScore +
        ", viewProperties=" + viewProperties +
        ", goodCommentCount=" + goodCommentCount +
        ", commonCommentCount=" + commonCommentCount +
        ", badCommentCount=" + badCommentCount +
        ", medias=" + medias +
        ", skuProperties=" + skuProperties +
        "}";
    }
}
