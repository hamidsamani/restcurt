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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ir.restcurt.route.DefaultRouteMatcher;
import ir.restcurt.route.DefaultRouteValidator;
import ir.restcurt.route.RouteMatcher;
import ir.restcurt.route.RouteValidator;
import ir.restcurt.util.Assert;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class RouteMappingInfoImpl implements RouteMappingInfo {

    private RouteMatcher matcher = new DefaultRouteMatcher();

    private RouteValidator validator = new DefaultRouteValidator();

    private RouteMapping routeMapping;

    private List<String> variables = new ArrayList<>();

    private List<String> parameters = new ArrayList<>();

    /**
     * 
     */
    public RouteMappingInfoImpl(RouteMapping routeMapping) {
        isValidRouteMapping(routeMapping);
        this.routeMapping = routeMapping;
        findVariables(routeMapping);
    }

    /**
     * @param routeMapping to be validate against pre-conditions
     */
    private void isValidRouteMapping(RouteMapping routeMapping) {

        Assert.notNull(routeMapping.getHandler(), "Handler should not be null");
        Assert.notNull(routeMapping.getMethod(), "Method should not be null");
        String path = routeMapping.getPath();
        if (!validator.isValidRoute(path)) {
            throw new IllegalArgumentException(String.format("Path not defined correctly: %s ", path));

        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see ir.restcurt.route.RouteMappingInfo#getRequestMapping()
     */
    @Override
    public RouteMapping getRouteMapping() {

        return this.routeMapping;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ir.restcurt.route.RouteMappingInfo#isContainVariable()
     */
    @Override
    public boolean isContainsVariable() {

        return !this.variables.isEmpty();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ir.restcurt.route.RouteMappingInfo#isContainParameter()
     */
    @Override
    public boolean isContainsParameter() {

        return !this.parameters.isEmpty();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ir.restcurt.route.RouteMappingInfo#getVariables()
     */
    @Override
    public List<String> getVariables() {

        return this.variables;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ir.restcurt.route.RouteMappingInfo#getParameters()
     */
    @Override
    public List<String> getParameters() {

        return this.parameters;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ir.restcurt.route.RouteMappingInfo#isRouteSatisfyMapping(java.lang.
     * String)
     */
    @Override
    public boolean isRouteSatisfyMapping(String route) {

        return matcher.isSatisfyMapping(route, routeMapping.getPath());
    }

    /**
     * simply captures variables declared in the route path.
     * @param route may contain variables such as /foo/:bar
     * 
     */

    private void findVariables(RouteMapping route) {

        String rootPath = route.getPath();

        String containsVariableRegex = ":\\w*\\/?";

        Pattern pattern = Pattern.compile(containsVariableRegex);

        Matcher patternMatcher = pattern.matcher(rootPath);
        while (patternMatcher.find()) {
            String currentVariable = patternMatcher.group().replaceAll("\\/|:", "");

            if (!currentVariable.isEmpty()) {
                variables.add(currentVariable);
            }
        }
    }

}
