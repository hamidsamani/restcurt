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

package ir.restcurt.route.mapping;

import ir.restcurt.http.HttpMethod;
import ir.restcurt.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class CompositeMapping {

    private static final Logger log = LoggerFactory.getLogger(CompositeMapping.class);

    private RouteMapping routeMapping;
    private Set<FilterMapping> beforeFilterMappings = new HashSet<>();
    private Set<FilterMapping> afterFilterMappings = new HashSet<>();
    private Set<ExceptionHandlerMapping> exceptionHandlerMappings = new HashSet<>();

    public CompositeMapping() {
    }

    public CompositeMapping(RouteMapping routeMapping) {
        setRouteMapping(routeMapping);
    }

    public Set<FilterMapping> getAfterFilterMappings() {
        return afterFilterMappings;
    }

    public Set<FilterMapping> getBeforeFilterMappings() {
        return beforeFilterMappings;
    }

    public RouteMapping getRouteMapping() {
        return routeMapping;
    }

    public void setRouteMapping(RouteMapping routeMapping) {
        Assert.notNull(routeMapping, "routeMapping should not be null");
        this.routeMapping = routeMapping;
    }

    public void setFilterMappings(Set<FilterMapping> filterMappings) {
        filterMappings.forEach(this::addFilter);
    }

    private void addFilter(FilterMapping filterMapping) {
        log.debug("Filter of type {} for method {} Added for {}", filterMapping.getType(), filterMapping.getMethod(), routeMapping.getPath());
        if (filterMapping.getType() == FilterMapping.FilterType.BEFORE) {
            this.beforeFilterMappings.add(filterMapping);
            return;
        }
        this.afterFilterMappings.add(filterMapping);
    }

    public Set<ExceptionHandlerMapping> getExceptionHandlerMappings() {
        return exceptionHandlerMappings;
    }

    public void setExceptionHandlerMappings(Set<ExceptionHandlerMapping> exceptionHandlerMappings) {
        this.exceptionHandlerMappings = exceptionHandlerMappings;
    }

    public HttpMethod getHttpMethod() {
        return routeMapping.getMethod();
    }

    public String getPath() {
        return routeMapping.getPath();
    }

    public void setPath(String path) {
        routeMapping.setPath(path);
    }

    @Override
    public String toString() {
        return "CompositeMapping{" +
                "routeMapping=" + routeMapping +
                ", beforeFilterMappings=" + beforeFilterMappings +
                ", afterFilterMappings=" + afterFilterMappings +
                ", exceptionHandlerMappings=" + exceptionHandlerMappings +
                '}';
    }
}
