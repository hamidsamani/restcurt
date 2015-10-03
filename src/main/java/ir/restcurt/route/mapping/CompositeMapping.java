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

import ir.restcurt.route.DefaultRouteMatcher;
import ir.restcurt.route.RouteMatcher;
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

    private String path;
    private RouteMapping routeMapping;
    private Set<FilterMapping> beforeFilterMappings = new HashSet<>();
    private Set<FilterMapping> afterFilterMappings = new HashSet<>();
    private Set<ExceptionHandlerMapping> exceptionHandlerMappings = new HashSet<>();

    private RouteMatcher matcher;

    public CompositeMapping(RouteMapping routeMapping, RouteMatcher matcher) {
        Assert.notNull(routeMapping, "routeMapping should not be null");
        this.routeMapping = routeMapping;
        this.path = routeMapping.getPath();
        this.matcher = matcher == null ? new DefaultRouteMatcher() : matcher;
    }

    public CompositeMapping(RouteMapping routeMapping) {
        this(routeMapping, null);

    }

    public Set<FilterMapping> getAfterFilterMappings() {
        return afterFilterMappings;
    }

    public Set<FilterMapping> getBeforeFilterMappings() {
        return beforeFilterMappings;
    }

    public String getPath() {
        return path;
    }

    public RouteMapping getRouteMapping() {
        return routeMapping;
    }

    public void addFilterIfSuitable(FilterMapping filterMapping) {
        if (isPathSuitable(filterMapping)) {
            addFilter(filterMapping);
        }
    }

    private boolean isPathSuitable(FilterMapping filterMapping) {
        if (filterMapping.getPath() == null) {
            return true;
        }
        return matcher.isSatisfyMapping(filterMapping.getPath(), routeMapping.getPath());
    }

    private void addFilter(FilterMapping filterMapping) {
        log.debug("Filter of type {} for method {} Added for {}", filterMapping.getType(), filterMapping.getMethod(), path);
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CompositeMapping{");
        sb.append("path='").append(path).append('\'');
        sb.append(", routeMapping=").append(routeMapping.getPath());
        sb.append(", method=").append(routeMapping.getMethod());
        sb.append(", beforeFilterMappings=").append(beforeFilterMappings);
        sb.append(", afterFilterMappings=").append(afterFilterMappings);
        sb.append('}');
        return sb.toString();
    }
}
