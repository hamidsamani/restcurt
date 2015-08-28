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

package ir.restcurt.route;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ir.restcurt.route.mapping.RouteMapping;

/**
 * Checks for matching income path and {@link RouteMapping} path. simply
 * determines variables as well as static url parts.
 * 
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class DefaultRouteMatcher implements RouteMatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRouteMatcher.class);

    private RouteValidator routeValidator = new DefaultRouteValidator();
    // private PathVariableConfigurer pathVariable = new Colon
    private static final String ROUTE_GROUP = "(:?\\w+)";
    private final Pattern routeGroupPattern = Pattern.compile(ROUTE_GROUP);

    // simply determine matched \w chars after forward slash. (positive look
    // behind)
    private static final String PATH_COUNTER = "(?<=/):?\\w+";
    private final Pattern pathCounterPattern = Pattern.compile(PATH_COUNTER);

    /**
     * @return the routeValidator
     */
    public RouteValidator getRouteValidator() {

        return routeValidator;
    }

    /**
     * @param routeValidator the routeValidator to set
     */
    public void setRouteValidator(RouteValidator routeValidator) {

        this.routeValidator = routeValidator;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ir.restcurt.route.PathMatcher#isSatisfyMapping(java.lang.String,
     * java.lang.String)
     */
    @Override
    public boolean isSatisfyMapping(String income, RouteMapping mapping) {

        String pattern = mapping.getPath();

        if (!routeValidator.isValidRoute(income)) {
            return false;
        }
        Matcher incomeMatcher = routeGroupPattern.matcher(income);
        Matcher patternMatcher = routeGroupPattern.matcher(pattern);

        if (!euqalVariables(income, pattern)) {
            return false;
        }
        while (incomeMatcher.find() && patternMatcher.find()) {
            String incomeGroup = incomeMatcher.group();
            String patternGroup = patternMatcher.group();

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("incomeGroup : {}  patternGroup: {}", incomeGroup, patternGroup);
            }

            if (!incomeGroup.contentEquals(patternGroup)) {
                if (!isPathVariable(patternGroup)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param income
     * @param pattern
     * @return
     */
    private boolean euqalVariables(String income, String pattern) {

        Matcher incomeMatcher = pathCounterPattern.matcher(income);
        Matcher patternMatcher = pathCounterPattern.matcher(pattern);

        int incomeCount = 0, patternCount = 0;
        while (incomeMatcher.find()) {
            incomeCount++;
        }
        while (patternMatcher.find()) {
            patternCount++;
        }
        return incomeCount == patternCount;
    }

    private boolean isPathVariable(String variable) {

        return Pattern.matches("\\/?:\\w+\\/?", variable);
    }
}
