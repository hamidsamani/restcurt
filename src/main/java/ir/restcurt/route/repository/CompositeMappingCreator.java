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

package ir.restcurt.route.repository;

import ir.restcurt.route.builder.*;
import ir.restcurt.route.configure.CompositeCommonConfigurationApplier;
import ir.restcurt.route.handler.RouteHandler;
import ir.restcurt.route.mapping.CompositeMapping;
import ir.restcurt.route.mapping.FilterMapping;
import ir.restcurt.route.mapping.RouteMapping;
import ir.restcurt.route.matcher.DefaultRouteMatcher;
import ir.restcurt.route.matcher.RouteMatcher;
import ir.restcurt.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class CompositeMappingCreator {

    private static final Logger log = LoggerFactory.getLogger(CompositeMappingCreator.class);

    private MappingRepository<CompositeMapping> repository = new CompositeMappingRepository();

    private Set<RouteHandler> handlers;

    public CompositeMappingCreator(Set<RouteHandler> handlers) {
        Assert.hasValue(handlers, "handlers should contain at least one handler");
        this.handlers = handlers;
        populateRepository();
    }

    private void populateRepository() {
        log.debug("staring of creating repository \n ------------");

        for (RouteHandler handler : handlers) {
            log.debug("stating of creating {} resource", handler);

            log.debug("creating filters");
            DefaultFilterBuilderImpl filters = new DefaultFilterBuilderImpl();
            handler.filter(filters);

            log.debug("creating routes");
            DefaultRouteBuilderImpl routes = new DefaultRouteBuilderImpl();
            handler.route(routes);

            log.debug("creating common configs");
            DefaultConfigurationBuilderImpl configs = new DefaultConfigurationBuilderImpl();
            handler.config(configs);

            log.debug("creating exceptionHandlers");
            DefaultExceptionHandlerBuilderImpl exception = new DefaultExceptionHandlerBuilderImpl();
            handler.exception(exception);

            createCompositeMapping(filters, routes, configs, exception);
        }
    }

    private void createCompositeMapping(DefaultFilterBuilderImpl filters, DefaultRouteBuilderImpl routes,
                                        ConfigurationBuilder configs,
                                        DefaultExceptionHandlerBuilderImpl exception) {

        for (RouteMapping rm : routes.getRouteMappings()) {

            CompositeMapping compositeMapping = new CompositeMapping();

            compositeMapping.setRouteMapping(rm);
            compositeMapping.setFilterMappings(suitableFilters(rm.getPath(), filters.getFilterMappings()));
            compositeMapping.setExceptionHandlerMappings(exception.getExceptionHandlerMappings());

            CompositeCommonConfigurationApplier commonConfigurationApplier = new CompositeCommonConfigurationApplier(configs);

            commonConfigurationApplier.apply(compositeMapping);


            repository.add(compositeMapping);
        }
    }

    private Set<FilterMapping> suitableFilters(String routePath, Set<FilterMapping> filterMappings) {
        RouteMatcher matcher = new DefaultRouteMatcher();
        Set<FilterMapping> selectedFilterMappings = new HashSet<>();

        filterMappings.forEach((fm) -> {
            if (fm.getPath() == null) selectedFilterMappings.add(fm);
            else if (matcher.isSatisfyMapping(fm.getPath(), routePath))
                selectedFilterMappings.add(fm);
        });

        return selectedFilterMappings;
    }


    public MappingRepository<CompositeMapping> getRepository() {
        return repository;
    }

}
