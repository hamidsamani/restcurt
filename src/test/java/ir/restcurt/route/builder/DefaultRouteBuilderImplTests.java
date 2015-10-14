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

package ir.restcurt.route.builder;

import ir.restcurt.http.HttpMethod;
import ir.restcurt.route.handler.Handler;
import ir.restcurt.route.mapping.RouteMapping;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class DefaultRouteBuilderImplTests {
    private Handler handler = Mockito.mock(Handler.class);

    @Test
    public void pathsCreatedAsExpected() {

        DefaultRouteBuilderImpl routes = new DefaultRouteBuilderImpl();

        routes.route("/customers").get((req, res) -> System.out.println("/"))
                .get("/:id", (req, res) -> System.out.println("/:id"))
                .get("/:id/invoices", (req, res) -> System.out.println("/:id/invoices"));

        assertThat(routes.getRouteMappings().size(), is(3));
    }

    @Test
    public void putMethodHandler() {

        DefaultRouteBuilderImpl routes = new DefaultRouteBuilderImpl();
        routes.put("/foo", handler);

        RouteMapping routeMapping = routes.getRouteMappings().iterator().next();

        assertThat(routeMapping.getMethod(), is(HttpMethod.PUT));
        assertThat(routeMapping.getPath(), is("/foo"));
        assertThat(routeMapping.getHandler(), is(handler));
    }

}
