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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import ir.restcurt.route.mapping.RouteMapping;
import ir.restcurt.route.mapping.RouteMapping.RouteMappingBuilder;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class DefaultRouteMatcherTests {

    @Test
    public void pathSatisfiesAsExpected() {

        DefaultRouteMatcher matcher = new DefaultRouteMatcher();

        assertThat(matcher.isSatisfyMapping("/persons", create("/persons")), is(true));
        assertThat(matcher.isSatisfyMapping("/persons/123", create("/persons/")), is(false));
        assertThat(matcher.isSatisfyMapping("/persons", create("/persons/:id")), is(false));
        assertThat(matcher.isSatisfyMapping("/persons/123/invoices", create("/persons/:id/invoice")), is(false));
        assertThat(matcher.isSatisfyMapping("/persons/123", create("/persons/invoices")), is(false));

    }

    private RouteMapping create(String path) {

        return RouteMappingBuilder.route().path(path).build();
    }

}
