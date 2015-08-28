/*
 * Copyright 2002-2011 the original author or authors.
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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import ir.restcurt.http.HttpMethod;
import ir.restcurt.route.handler.Handler;
import ir.restcurt.route.mapping.RouteMapping;
import ir.restcurt.route.mapping.RouteMapping.RouteMappingBuilder;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class RouteMappingTests {

    private static final HttpMethod METHOD = HttpMethod.GET;

    private static final String PATH = "/path";

    private static final Handler HANDLER = (request, response) -> System.out.println("call me");

    @Test
    public void routeMappingCreatesRoutesAsEcpected() {

        RouteMapping routeMapping = RouteMappingBuilder.route().method(METHOD).path(PATH).handler(HANDLER).build();

        assertThat(routeMapping.getMethod(), is(METHOD));
        assertThat(routeMapping.getPath(), is(PATH));
        assertThat(routeMapping.getHandler(), is(HANDLER));

    }

}
