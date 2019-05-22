package cn.itsource.maomaogo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 品牌信息
 * </p>
 *
 * @author LemonTreeXL
 * @since 2019-05-16
 */
@TableName("t_brand")
public class Brand extends Model<Brand> {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("createTime")
    private Long createTime;

    @TableField("updateTime")
    private Long updateTime;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 英文名
     */
    @TableField("englishName")
    private String englishName;

    /**
     * 首字母
     */
    @TableField("firstLetter")
    private String firstLetter;

    private String description;

    /**
     * 商品分类ID
     */
    private Long productTypeId;

    @TableField("sortIndex")
    private Integer sortIndex;

    /**
     * 品牌LOGO
     */
    private String logo;

    @TableField(exist = false) //标识数据库中没有这个字段
    private ProductType productType;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
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

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Brand{" +
        "id=" + id +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", name=" + name +
        ", englishName=" + englishName +
        ", firstLetter=" + firstLetter +
        ", description=" + description +
        ", productTypeId=" + productTypeId +
        ", sortIndex=" + sortIndex +
        ", logo=" + logo +
        "}";
    }
}
