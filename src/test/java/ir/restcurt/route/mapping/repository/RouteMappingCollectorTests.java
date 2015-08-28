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

package ir.restcurt.route.mapping.repository;

import org.junit.Test;

import ir.restcurt.route.handler.registrar.DefaultRouteHandlerRegistrar;
import ir.restcurt.route.handler.registrar.DummyRouteHandlers.DummyRouteHandler1;
import ir.restcurt.route.mapping.repository.RouteMappingCollector;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class RouteMappingCollectorTests {

    @Test
    public void test() {

        DefaultRouteHandlerRegistrar registrar = new DefaultRouteHandlerRegistrar(DummyRouteHandler1.class);
        RouteMappingCollector collector = new RouteMappingCollector(registrar.getHandlers());
        System.out.println(collector.getRepository().getAllMappings());

    }

}
