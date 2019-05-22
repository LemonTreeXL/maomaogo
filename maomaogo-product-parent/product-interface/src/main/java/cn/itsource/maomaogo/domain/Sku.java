package cn.itsource.maomaogo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * SKU
 * </p>
 *
 * @author LemonTreeXL
 * @since 2019-05-21
 */
@TableName("t_sku")
public class Sku extends Model<Sku> {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("createTime")
    private Long createTime;

    @TableField("updateTime")
    private Long updateTime;

    /**
     * 商品ID
     */
    @TableField("productId")
    private Long productId;

    /**
     * SKU编码
     */
    @TableField("skuCode")
    private String skuCode;

    @TableField("skuName")
    private String skuName;

    /**
     * 市场价
     */
    @TableField("marketPrice")
    private Integer marketPrice;

    /**
     * 优惠价
     */
    private Integer price;

    /**
     * 成本价
     */
    @TableField("costPrice")
    private Integer costPrice;

    /**
     * 销量
     */
    @TableField("saleCount")
    private Integer saleCount;

    /**
     * 排序
     */
    @TableField("sortIndex")
    private Integer sortIndex;

    /**
     * 可用库存
     */
    @TableField("availableStock")
    private Integer availableStock;

    /**
     * 锁定库存
     */
    @TableField("frozenStock")
    private Integer frozenStock;

    private String skuIndex;

    private String skuProperties;


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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Integer costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public Integer getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(Integer availableStock) {
        this.availableStock = availableStock;
    }

    public Integer getFrozenStock() {
        return frozenStock;
    }

    public void setFrozenStock(Integer frozenStock) {
        this.frozenStock = frozenStock;
    }

    public String getSkuIndex() {
        return skuIndex;
    }

    public void setSkuIndex(String skuIndex) {
        this.skuIndex = skuIndex;
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
        return "Sku{" +
        "id=" + id +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", productId=" + productId +
        ", skuCode=" + skuCode +
        ", skuName=" + skuName +
        ", marketPrice=" + marketPrice +
        ", price=" + price +
        ", costPrice=" + costPrice +
        ", saleCount=" + saleCount +
        ", sortIndex=" + sortIndex +
        ", availableStock=" + availableStock +
        ", frozenStock=" + frozenStock +
        ", skuIndex=" + skuIndex +
        ", skuProperties=" + skuProperties +
        "}";
    }
}
