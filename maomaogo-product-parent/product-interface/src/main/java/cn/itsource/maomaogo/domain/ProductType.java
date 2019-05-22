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
 * 商品目录
 * </p>
 *
 * @author LemonTreeXL
 * @since 2019-05-16
 */
@TableName("t_product_type")
public class ProductType extends Model<ProductType> {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("createTime")
    private Long createTime;

    @TableField("updateTime")
    private Long updateTime;

    /**
     * 类型名
     */
    private String name;

    /**
     * 父ID
     */
    private Long pid;

    /**
     * 图标
     */
    private String logo;

    /**
     * 描述
     */
    private String description;

    @TableField("sortIndex")
    private Integer sortIndex;

    /**
     * 路径
     */
    private String path;

    /**
     * 商品数量
     */
    @TableField("totalCount")
    private Integer totalCount;

    /**
     * 分类标题（SEO）
     */
    @TableField("seoTitle")
    private String seoTitle;

    /**
     * 分类关键字（SEO）
     */
    @TableField("seoKeywords")
    private String seoKeywords;

    private Long typeTemplateId;

    /**
     *  自关联。为了查询无限极菜单
     */
    @TableField(exist = false) //单表操作时，忽略该属性
    private List<ProductType> children = new ArrayList<>();


    public List<ProductType> getChildren() {
        return children;
    }

    public void setChildren(List<ProductType> children) {
        this.children = children;
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

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }

    public Long getTypeTemplateId() {
        return typeTemplateId;
    }

    public void setTypeTemplateId(Long typeTemplateId) {
        this.typeTemplateId = typeTemplateId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProductType{" +
        "id=" + id +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", name=" + name +
        ", pid=" + pid +
        ", logo=" + logo +
        ", description=" + description +
        ", sortIndex=" + sortIndex +
        ", path=" + path +
        ", totalCount=" + totalCount +
        ", seoTitle=" + seoTitle +
        ", seoKeywords=" + seoKeywords +
        ", typeTemplateId=" + typeTemplateId +
        "}";
    }
}
