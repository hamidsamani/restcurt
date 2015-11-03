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

import ir.restcurt.http.response.MethodNotAllowedBodyCreator;
import ir.restcurt.http.response.ResponseNotFoundBodyCreator;
import ir.restcurt.route.mapping.CompositeMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class CompositeMappingRequestMatcher {
    private CompositeMapping compositeMapping;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private Set<CompositeMapping> compositeMappingsCandidates = new HashSet<>();

    private RouteMatcher routeMatcher = new RegexSupportRouteMatcher();

    public CompositeMappingRequestMatcher(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
    }

    public void setCompositeMapping(CompositeMapping compositeMapping) {
        this.compositeMapping = compositeMapping;
    }

    public boolean isMethodEqual() {
        return compositeMapping.getHttpMethod().name().equals(httpServletRequest.getMethod());
    }

    public boolean isSatisfyMapping() {
        return routeMatcher.isSatisfyMapping(httpServletRequest.getPathInfo(), compositeMapping.getPath());
    }

    public void addCandidate(CompositeMapping compositeMapping) {
        compositeMappingsCandidates.add(compositeMapping);
    }

    public void determineResponse() {
        if (!compositeMappingsCandidates.isEmpty()) {
            new MethodNotAllowedBodyCreator(httpServletResponse).buildResponse();
        } else {
            new ResponseNotFoundBodyCreator(httpServletResponse).buildResponse();
        }
    }


}
