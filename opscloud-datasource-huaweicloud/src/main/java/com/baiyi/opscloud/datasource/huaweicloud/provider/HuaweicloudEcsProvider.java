package com.baiyi.opscloud.datasource.huaweicloud.provider;

import com.baiyi.opscloud.common.annotation.SingleTask;
import com.baiyi.opscloud.common.constants.enums.DsTypeEnum;
import com.baiyi.opscloud.common.datasource.HuaweicloudConfig;
import com.baiyi.opscloud.core.factory.AssetProviderFactory;
import com.baiyi.opscloud.core.model.DsInstanceContext;
import com.baiyi.opscloud.core.provider.asset.AbstractAssetBusinessRelationProvider;
import com.baiyi.opscloud.core.util.AssetUtil;
import com.baiyi.opscloud.datasource.huaweicloud.ecs.driver.HuaweicloudEcsDriver;
import com.baiyi.opscloud.datasource.huaweicloud.ecs.entity.HuaweicloudEcs;
import com.baiyi.opscloud.domain.constants.DsAssetTypeConstants;
import com.baiyi.opscloud.domain.generator.opscloud.DatasourceConfig;
import com.baiyi.opscloud.domain.generator.opscloud.DatasourceInstanceAsset;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import jakarta.annotation.Resource;
import java.util.Collections;
import java.util.List;

import static com.baiyi.opscloud.common.constants.SingleTaskConstants.PULL_HUAWEICLOUD_ECS;

/**
 * @Author baiyi
 * @Date 2022/7/12 18:25
 * @Version 1.0
 */
@Component
public class HuaweicloudEcsProvider extends AbstractAssetBusinessRelationProvider<HuaweicloudEcs.Ecs> {

    @Resource
    private HuaweicloudEcsProvider huaweicloudEcsProvider;

    @Override
    @SingleTask(name = PULL_HUAWEICLOUD_ECS , lockTime = "5m")
    public void pullAsset(int dsInstanceId) {
        doPull(dsInstanceId);
    }

    private HuaweicloudConfig.Huaweicloud buildConfig(DatasourceConfig dsConfig) {
        return dsConfigManager.build(dsConfig, HuaweicloudConfig.class).getHuaweicloud();
    }

    @Override
    protected boolean equals(DatasourceInstanceAsset asset, DatasourceInstanceAsset preAsset) {
        if (!AssetUtil.equals(preAsset.getName(), asset.getName())) {
            return false;
        }
        if (!AssetUtil.equals(preAsset.getAssetKey2(), asset.getAssetKey2())) {
            return false;
        }
        if (!AssetUtil.equals(preAsset.getKind(), asset.getKind())) {
            return false;
        }
        if (!AssetUtil.equals(preAsset.getDescription(), asset.getDescription())) {
            return false;
        }
        if (!AssetUtil.equals(preAsset.getExpiredTime(), asset.getExpiredTime())) {
            return false;
        }
        return true;
    }

    @Override
    protected List<HuaweicloudEcs.Ecs> listEntities(DsInstanceContext dsInstanceContext) {
        HuaweicloudConfig.Huaweicloud huaweicloud = buildConfig(dsInstanceContext.getDsConfig());
        if (CollectionUtils.isEmpty(huaweicloud.getRegionIds())) {
            return Collections.emptyList();
        }
        List<HuaweicloudEcs.Ecs> ecsList = Lists.newArrayList();
        huaweicloud.getRegionIds().forEach(regionId ->
                ecsList .addAll(HuaweicloudEcsDriver.listServers(regionId, huaweicloud))
        );
        return ecsList ;
    }

    @Override
    public String getInstanceType() {
        return DsTypeEnum.HUAWEICLOUD.name();
    }

    @Override
    public String getAssetType() {
        return DsAssetTypeConstants.HUAWEICLOUD_ECS.name();
    }

    @Override
    public void afterPropertiesSet() {
        AssetProviderFactory.register(huaweicloudEcsProvider);
    }

}
