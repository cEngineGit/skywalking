/*
 * Copyright 2017, OpenSkywalking Organization All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Project repository: https://github.com/OpenSkywalking/skywalking
 */

package org.skywalking.apm.collector.storage.es.dao;

import java.util.HashMap;
import java.util.Map;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.skywalking.apm.collector.storage.base.dao.IPersistenceDAO;
import org.skywalking.apm.collector.storage.dao.ICpuMetricDAO;
import org.skywalking.apm.collector.storage.es.base.dao.EsDAO;
import org.skywalking.apm.collector.storage.table.jvm.CpuMetric;
import org.skywalking.apm.collector.storage.table.jvm.CpuMetricTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author peng-yongsheng
 */
public class CpuMetricEsDAO extends EsDAO implements ICpuMetricDAO, IPersistenceDAO<IndexRequestBuilder, UpdateRequestBuilder, CpuMetric> {

    private final Logger logger = LoggerFactory.getLogger(CpuMetricEsDAO.class);

    @Override public CpuMetric get(String id) {
        return null;
    }

    @Override public IndexRequestBuilder prepareBatchInsert(CpuMetric cpuMetric) {
        Map<String, Object> source = new HashMap<>();
        source.put(CpuMetricTable.COLUMN_INSTANCE_ID, cpuMetric.getInstanceId());
        source.put(CpuMetricTable.COLUMN_USAGE_PERCENT, cpuMetric.getUsagePercent());
        source.put(CpuMetricTable.COLUMN_TIME_BUCKET, cpuMetric.getTimeBucket());

        logger.debug("prepare cpu metric batch insert, getId: {}", cpuMetric.getId());
        return getClient().prepareIndex(CpuMetricTable.TABLE, cpuMetric.getId()).setSource(source);
    }

    @Override public UpdateRequestBuilder prepareBatchUpdate(CpuMetric cpuMetric) {
        return null;
    }
}
