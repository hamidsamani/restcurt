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

import java.util.Set;

import ir.restcurt.route.builder.RoutesBuilder;
import ir.restcurt.route.builder.SimpleRoutesBuilderImpl;
import ir.restcurt.route.handler.RouteHandler;
import ir.restcurt.util.Assert;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class RouteMappingCollector {

    private RouteMappingRepository repository = new DefaultRouteMappingRepository();
    private Set<RouteHandler> handlers;

    public RouteMappingCollector(Set<RouteHandler> handlers) {
        this.handlers = handlers;
        createRepository();
    }

    private void createRepository() {

        Assert.hasValue(handlers, "There aren't any handler.");

        for (RouteHandler routeHandler : handlers) {

            RoutesBuilder route = new SimpleRoutesBuilderImpl();
            routeHandler.route(route);

            repository.add(route.getRoutes());
        }
    }

    /**
     * @return the repository
     */
    public RouteMappingRepository getRepository() {

        return repository;
    }

    /**
     * @param repository the repository to set
     */
    public void setRepository(RouteMappingRepository repository) {

        this.repository = repository;
    }

}
