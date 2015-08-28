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

package ir.restcurt.route.handler.registrar;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ir.restcurt.route.handler.RouteHandler;
import ir.restcurt.route.handler.registrar.DefaultRouteHandlerRegistrar;
import ir.restcurt.route.handler.registrar.RouteHandlerRegistrar;
import ir.restcurt.route.handler.registrar.DummyRouteHandlers.DummyRouteHandler1;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class DefaultRouteHandlerRegistrarTests {

    @Test
    public void routeHandlerRegistrarInitializedAsExpected() {

        RouteHandlerRegistrar<RouteHandler> registrar = new DefaultRouteHandlerRegistrar(DummyRouteHandler1.class);
        assertThat(registrar.getHandlers().size(), is(1));
    }

    @Test
    public void routeHandlerRegistrarThrowAwayNonRouteHandlers() {

        RouteHandlerRegistrar<RouteHandler> registrar = new DefaultRouteHandlerRegistrar(DummyRouteHandler1.class,
                String.class);

        assertThat(registrar.getHandlers().size(), is(1));

    }

}
