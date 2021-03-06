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

import java.util.regex.Pattern;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public abstract class AbstractRouteMatcher implements RouteMatcher {

    protected static final String ROUTE_GROUP = "(:?\\w+)";

    protected static final String PATH_COUNTER = "(?<=/):?\\w+";

    protected RouteValidator routeValidator = new DefaultRouteValidator();

    protected boolean isPathVariable(String variable) {

        return Pattern.matches("\\/?:\\w+\\/?", variable);
    }
}
