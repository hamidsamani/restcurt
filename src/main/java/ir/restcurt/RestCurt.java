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

package ir.restcurt;

import ir.restcurt.route.handler.RouteHandler;
import ir.restcurt.route.registrar.DefaultRouteHandlerRegistrar;
import ir.restcurt.route.registrar.RouteHandlerRegistrar;
import ir.restcurt.route.repository.CompositeMappingCreator;
import ir.restcurt.route.repository.MappingRepository;
import ir.restcurt.server.HttpWebServerDeterminer;
import ir.restcurt.server.ServerConfigurer;
import ir.restcurt.util.Assert;

/**
 * Main interface of interacting with RESTCurt framework. simply call
 * {@link RestCurt#run(Class<?>... handlers)} to bootstrap RESTCurt.
 * 
 * @see {@link RouteHandler}
 * @author Hamid Samani
 * @since 0.0.1
 *
 */
public class RestCurt {

    private RouteHandlerRegistrar<RouteHandler> handlerRegistrar;
    private ServerConfigurer serverConfigurer;
    private MappingRepository repository;

    private RestCurt() {

    }

    public static void run(Class<?>... handlers) {

        Assert.notNull(handlers, "handlers should not be null");
        new RestCurt().init(handlers);

    }

    private void init(Class<?>... handlers) {

        determineWebServer(); // determine available HTTP web server eagerly
        registerHandlers(handlers);
        collectRouteMaapings();
        startupServer();
    }

    private void determineWebServer() {

        serverConfigurer = HttpWebServerDeterminer.currentHttpWebServer();

    }

    private void registerHandlers(Class<?>[] handlers) {

        if (handlerRegistrar == null) {
            handlerRegistrar = new DefaultRouteHandlerRegistrar();
        }
        handlerRegistrar.register(handlers);

    }

    private void collectRouteMaapings() {

        CompositeMappingCreator cmc = new CompositeMappingCreator(handlerRegistrar.getHandlers());
        repository = cmc.getRepository();

    }

    private void startupServer() {

        serverConfigurer.setHandler(repository);
        serverConfigurer.start();

    }
}