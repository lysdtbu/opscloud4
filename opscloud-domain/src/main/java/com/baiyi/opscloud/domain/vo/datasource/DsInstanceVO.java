package com.baiyi.opscloud.domain.vo.datasource;

import com.baiyi.opscloud.domain.types.BusinessTypeEnum;
import com.baiyi.opscloud.domain.vo.base.BaseVO;
import com.baiyi.opscloud.domain.vo.tag.TagVO;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * @Author baiyi
 * @Date 2021/5/18 4:54 下午
 * @Version 1.0
 */
public class DsInstanceVO {

    @EqualsAndHashCode(callSuper = true)
    @Builder
    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    @ApiModel
    public static class Instance extends BaseVO implements TagVO.ITags {

        private final Integer businessType = BusinessTypeEnum.DATASOURCE_INSTANCE.getType();
        private List<TagVO.Tag> tags;

        @Override
        public Integer getBusinessId() {
            return this.id;
        }

        private DsConfigVO.DsConfig dsConfig;

        private List<AssetDetail> assetDetails;

        private List<Instance> children;
        private Integer id;
        private Integer parentId;
        private String instanceName;
        private String uuid;
        private String instanceType;
        private String kind;
        private String version;
        private Boolean isActive;
        private Integer configId;
        private Date createTime;
        private Date updateTime;
        private String comment;

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel
    public static class AssetDetail {

        private String assetType;
        private Integer assetSize;

    }

}
