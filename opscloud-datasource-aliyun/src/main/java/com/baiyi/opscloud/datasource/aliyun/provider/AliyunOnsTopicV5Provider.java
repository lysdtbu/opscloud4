package com.baiyi.opscloud.datasource.aliyun.provider;

import com.baiyi.opscloud.common.annotation.SingleTask;
import com.baiyi.opscloud.common.constants.enums.DsTypeEnum;
import com.baiyi.opscloud.common.datasource.AliyunConfig;
import com.baiyi.opscloud.core.factory.AssetProviderFactory;
import com.baiyi.opscloud.core.model.DsInstanceContext;
import com.baiyi.opscloud.core.provider.annotation.ChildProvider;
import com.baiyi.opscloud.core.provider.asset.AbstractAssetChildProvider;
import com.baiyi.opscloud.core.util.AssetUtil;
import com.baiyi.opscloud.datasource.aliyun.ons.driver.AliyunOnsInstanceV5Driver;
import com.baiyi.opscloud.datasource.aliyun.ons.driver.AliyunOnsTopicV5Driver;
import com.baiyi.opscloud.datasource.aliyun.ons.entity.OnsInstanceV5;
import com.baiyi.opscloud.datasource.aliyun.ons.entity.OnsTopicV5;
import com.baiyi.opscloud.datasource.aliyun.util.AliyunRegionIdUtil;
import com.baiyi.opscloud.domain.constants.DsAssetTypeConstants;
import com.baiyi.opscloud.domain.generator.opscloud.DatasourceConfig;
import com.baiyi.opscloud.domain.generator.opscloud.DatasourceInstanceAsset;
import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.baiyi.opscloud.common.constants.SingleTaskConstants.PULL_ALIYUN_ONS5_TOPIC;

/**
 * @Author 修远
 * @Date 2023/9/12 12:48 AM
 * @Since 1.0
 */

@Component
@ChildProvider(parentType = DsAssetTypeConstants.ONS5_INSTANCE)
public class AliyunOnsTopicV5Provider extends AbstractAssetChildProvider<OnsTopicV5.Topic> {

    @Resource
    private AliyunOnsInstanceV5Driver aliyunOnsInstanceV5Driver;

    @Resource
    private AliyunOnsTopicV5Driver aliyunOnsTopicV5Driver;

    @Resource
    private AliyunOnsTopicV5Provider aliyunOnsTopicV5Provider;

    @Override
    @SingleTask(name = PULL_ALIYUN_ONS5_TOPIC, lockTime = "5m")
    public void pullAsset(int dsInstanceId) {
        doPull(dsInstanceId);
    }

    private AliyunConfig.Aliyun buildConfig(DatasourceConfig dsConfig) {
        return dsConfigManager.build(dsConfig, AliyunConfig.class).getAliyun();
    }

    @Override
    protected boolean equals(DatasourceInstanceAsset asset, DatasourceInstanceAsset preAsset) {
        if (!AssetUtil.equals(preAsset.getName(), asset.getName())) {
            return false;
        }
        if (!AssetUtil.equals(preAsset.getAssetKey2(), asset.getAssetKey2())) {
            return false;
        }
        if (!AssetUtil.equals(preAsset.getDescription(), asset.getDescription())) {
            return false;
        }
        return true;
    }

    @Override
    protected List<OnsTopicV5.Topic> listEntities(DsInstanceContext dsInstanceContext) {
        AliyunConfig.Aliyun aliyun = buildConfig(dsInstanceContext.getDsConfig());
        Set<String> regionIds = AliyunRegionIdUtil.toOnsRegionIds(aliyun);
        List<OnsTopicV5.Topic> entities = Lists.newArrayList();
        regionIds.forEach(regionId -> {
            try {
                List<OnsInstanceV5.InstanceInfo> instances = aliyunOnsInstanceV5Driver.listInstance(regionId, aliyun);
                if (!CollectionUtils.isEmpty(instances)) {
                    instances.forEach(instance -> {
                        try {
                            entities.addAll(aliyunOnsTopicV5Driver.listTopic(regionId, aliyun, instance.getInstanceId()));
                        } catch (Exception ignored) {
                        }
                    });
                }
            } catch (Exception ignored) {
            }
        });
        return entities;
    }

    @Override
    protected List<OnsTopicV5.Topic> listEntities(DsInstanceContext dsInstanceContext, DatasourceInstanceAsset asset) {
        AliyunConfig.Aliyun aliyun = buildConfig(dsInstanceContext.getDsConfig());
        try {
            return aliyunOnsTopicV5Driver.listTopic(asset.getRegionId(), aliyun, asset.getAssetId());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public String getInstanceType() {
        return DsTypeEnum.ALIYUN.name();
    }

    @Override
    public String getAssetType() {
        return DsAssetTypeConstants.ONS5_TOPIC.name();
    }

    @Override
    public void afterPropertiesSet() {
        AssetProviderFactory.register(aliyunOnsTopicV5Provider);
    }

}