/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ir.restcurt.route.builder;

import ir.restcurt.http.HttpMethod;
import ir.restcurt.route.handler.Handler;
import ir.restcurt.route.mapping.FilterMapping;
import ir.restcurt.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @author  Hamid Samani
 * @since 0.0.1
 */
public class DefaultFilterBuilderImpl implements FilterBuilder {
    private static final Logger log = LoggerFactory.getLogger(DefaultFilterBuilderImpl.class);

    private Set<FilterMapping> filterMappings = new HashSet<>();

    private String rootPath;

    public DefaultFilterBuilderImpl() {
    }

    public DefaultFilterBuilderImpl(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public FilterBuilder beforeAnyGet(Handler handler) {
        Assert.notNull(handler, "handler should not be null");
        this.filterMappings.add(new FilterMapping(rootPath, HttpMethod.GET, handler, FilterMapping.FilterType.BEFORE));
        if(log.isDebugEnabled()){
            log.debug("Filter : [Type : Before, Method: GET, Path: {}]", rootPath);
        }
        return this;
    }

    @Override
    public FilterBuilder beforeAnyPost(Handler handler) {
        Assert.notNull(handler, "handler should not be null");
        this.filterMappings.add(new FilterMapping(rootPath, HttpMethod.POST, handler, FilterMapping.FilterType.BEFORE));

        if(log.isDebugEnabled()){
            log.debug("Filter : [Type : Before, Method: POST, Path: {}]",rootPath );
        }
        return this;
    }

    @Override
    public FilterBuilder afterAnyGet(Handler handler) {
        Assert.notNull(handler, "handler should not be null");
        this.filterMappings.add(new FilterMapping(rootPath, HttpMethod.GET, handler, FilterMapping.FilterType.AFTER));

        if(log.isDebugEnabled()){
            log.debug("Filter : [Type : After, Method: GET, Path: {}]",rootPath );
        }
        return this;
    }

    @Override
    public FilterBuilder afterAnyPost(Handler handler) {
        Assert.notNull(handler, "handler should not be null");
        this.filterMappings.add(new FilterMapping(rootPath, HttpMethod.POST, handler, FilterMapping.FilterType.AFTER));

        if(log.isDebugEnabled()){
            log.debug("Filter : [Type : After, Method: POST, Path: {}]",rootPath );
        }
        return this;
    }

    @Override
    public FilterBuilder beforeGet(String path, Handler handler) {
        Assert.notNull(handler, "handler should not be null");
        String combinedPath = combinePaths(path);
        this.filterMappings.add(new FilterMapping(combinedPath, HttpMethod.GET, handler, FilterMapping.FilterType.BEFORE));

        if(log.isDebugEnabled()){
            log.debug("Filter : [Type : Before, Method: GET, Path: {}]",combinedPath );
        }
        return this;
    }

    @Override
    public FilterBuilder beforePost(String path, Handler handler) {
        Assert.notNull(handler, "handler should not be null");
        String combinedPath = combinePaths(path);
        this.filterMappings.add(new FilterMapping(combinedPath, HttpMethod.POST, handler, FilterMapping.FilterType.BEFORE));

        if(log.isDebugEnabled()){
            log.debug("Filter : [Type : Before, Method: POST, Path: {}]",combinedPath );
        }
        return this;
    }

    @Override
    public FilterBuilder afterGet(String path, Handler handler) {
        Assert.notNull(handler, "handler should not be null");
        String combinedPath = combinePaths(path);
        this.filterMappings.add(new FilterMapping(combinedPath, HttpMethod.GET, handler, FilterMapping.FilterType.AFTER));

        if(log.isDebugEnabled()){
            log.debug("Filter : [Type : After, Method: GET, Path: {}]",combinedPath );
        }
        return this;
    }

    @Override
    public FilterBuilder afterPost(String path, Handler handler) {
        Assert.notNull(handler, "handler should not be null");
        String combinedPath = combinePaths(path);
        this.filterMappings.add(new FilterMapping(combinedPath, HttpMethod.POST, handler, FilterMapping.FilterType.AFTER));

        if(log.isDebugEnabled()){
            log.debug("Filter : [Type : After, Method: POST, Path: {}]",combinedPath );
        }

        return this;
    }

    @Override
    public boolean isContainAnyFilter() {
        return !filterMappings.isEmpty();
    }

    @Override
    public Set<FilterMapping> getFilterMappings() {
        return this.filterMappings;
    }

    private String combinePaths(String path) {
        if (rootPath != null) {
            final String slash = "/";
            if (rootPath.endsWith(slash) && path.startsWith(slash)) {
                return rootPath + path.substring(1);
            } else if (!rootPath.endsWith(slash) && !path.startsWith(slash)) {
                return rootPath + slash + path;
            }
            return rootPath + path;
        }else{
            return path;
        }
    }
}
