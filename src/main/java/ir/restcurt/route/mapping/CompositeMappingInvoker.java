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

import ir.restcurt.http.HttpServletRequestHolder;
import ir.restcurt.http.HttpServletResponseHolder;

import java.util.Set;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class CompositeMappingInvoker {
    private CompositeMapping compositeMapping;
    private HttpServletRequestHolder request;
    private HttpServletResponseHolder response;

    public CompositeMappingInvoker(CompositeMapping compositeMapping, HttpServletRequestHolder request, HttpServletResponseHolder response) {
        this.compositeMapping = compositeMapping;
        this.request = request;
        this.response = response;
    }

    /**
     * Iterate through before filters and invoke handlers.
     */
    public void invokeBeforeFilters() {
        final Set<FilterMapping> filterMappings = compositeMapping.getBeforeFilterMappings();
        for (FilterMapping filter : filterMappings) {
            filter.getHandler().handle(request, response);
        }
    }

    /**
     * Invoke main Handler
     */
    public void invokeHandler() {
        compositeMapping.getRouteMapping().getHandler().handle(request, response);
    }

    /**
     * Iterate through after filters and invoke handlers.
     */
    public void invokeAfterFilters() {
        final Set<FilterMapping> filterMappings = compositeMapping.getAfterFilterMappings();
        for (FilterMapping filter : filterMappings) {
            filter.getHandler().handle(request, response);
        }
    }
}
