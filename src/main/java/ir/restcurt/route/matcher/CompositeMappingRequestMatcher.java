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

package ir.restcurt.route.matcher;

import ir.restcurt.route.DefaultRouteMatcher;
import ir.restcurt.route.RouteMatcher;
import ir.restcurt.route.mapping.CompositeMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class CompositeMappingRequestMatcher {
    private CompositeMapping compositeMapping;
    private HttpServletRequest httpServletRequest;

    private RouteMatcher routeMatcher = new DefaultRouteMatcher();

    public CompositeMappingRequestMatcher() {
    }

    public CompositeMappingRequestMatcher(CompositeMapping compositeMapping, HttpServletRequest httpServletRequest) {
        this.compositeMapping = compositeMapping;
        this.httpServletRequest = httpServletRequest;
    }

    public void setCompositeMapping(CompositeMapping compositeMapping) {
        this.compositeMapping = compositeMapping;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public boolean isCompositeMappingSatisfyRequest() {
        return isMethodEqual() && isSatisfyMapping();
    }

    private boolean isMethodEqual() {
        return compositeMapping.getHttpMethod().name().equals(httpServletRequest.getMethod());
    }

    private boolean isSatisfyMapping() {
        return routeMatcher.isSatisfyMapping(httpServletRequest.getPathInfo(), compositeMapping.getPath());
    }


}
