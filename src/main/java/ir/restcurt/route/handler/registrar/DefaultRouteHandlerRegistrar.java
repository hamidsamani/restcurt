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

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ir.restcurt.route.handler.RouteHandler;
import ir.restcurt.util.Assert;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class DefaultRouteHandlerRegistrar implements RouteHandlerRegistrar<RouteHandler> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRouteHandlerRegistrar.class);

    private Set<RouteHandler> handlers = new HashSet<>();

    public DefaultRouteHandlerRegistrar() {
    }

    public DefaultRouteHandlerRegistrar(Class<?>... handlers) {
        Assert.notNull(handlers, "handlers should not be null");
        register(handlers);
    }

    @Override
    public void register(Class<?>[] handlers) {

        for (Class<?> clazz : handlers) {
            if (RouteHandler.class.isAssignableFrom(clazz)) {
                try {
                    register(((RouteHandler) clazz.newInstance()));
                } catch (InstantiationException | IllegalAccessException e) {
                    LOGGER.debug("Can't add {} becuase of exception {}", clazz, e.getMessage());
                }
            } else {
                LOGGER.debug("Can't add {} because is not type of {}", clazz, RouteHandler.class.getName());
            }

        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ir.restcurt.route.handler.registrar.RouteHandlerRegistrar#getHandlers()
     */
    @Override
    public Set<RouteHandler> getHandlers() {

        return this.handlers;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ir.restcurt.route.handler.registrar.RouteHandlerRegistrar#register(RouteHandler routeHandler)
     */
    @Override
    public void register(RouteHandler routeHandler) {

        this.handlers.add(routeHandler);

    }

}
