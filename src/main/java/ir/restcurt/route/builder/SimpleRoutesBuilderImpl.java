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

package ir.restcurt.route.builder;

import java.util.HashSet;
import java.util.Set;

import ir.restcurt.exception.DuplicatePathDefenitionException;
import ir.restcurt.http.HttpMethod;
import ir.restcurt.route.handler.Handler;
import ir.restcurt.route.mapping.RouteMapping;
import ir.restcurt.route.mapping.RouteMapping.RouteMappingBuilder;
import ir.restcurt.util.Assert;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class SimpleRoutesBuilderImpl implements RoutesBuilder {

    private String rootPath;

    private Set<RouteMapping> routes = new HashSet<>();

    /**
     * @return the routes
     */
    @Override
    public Set<RouteMapping> getRoutes() {

        return routes;
    }

    /**
     * @return the rootPath
     */
    public String getRootPath() {

        return rootPath;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ir.restcurt.handler.RouteBuilder#route(java.lang.String)
     */
    @Override
    public RoutesBuilder route(String route) {

        if (this.rootPath != null) {
            throw new IllegalStateException("you already defined root path");
        }

        this.rootPath = route;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ir.restcurt.handler.RouteBuilder#get(ir.restcurt.handler.PathHandler)
     */
    @Override
    public RoutesBuilder get(Handler handler) {

        Assert.notNull(this.rootPath, "root path not specified");

        boolean isDuplicate = this.routes
                .add(RouteMappingBuilder.route().method(HttpMethod.GET).path(rootPath).handler(handler).build());

        checkDuplication(isDuplicate, rootPath);

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ir.restcurt.handler.RouteBuilder#get(java.lang.String,
     * ir.restcurt.handler.PathHandler)
     */
    @Override
    public RoutesBuilder get(String path, Handler handler) {

        Assert.notNull(path, "path not specified");
        Assert.notNull(handler, "handler not specified for path");

        boolean isDuplicate = this.routes.add(
                RouteMappingBuilder.route().method(HttpMethod.GET).path(rootPath).path(path).handler(handler).build());

        checkDuplication(isDuplicate, path);

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ir.restcurt.handler.RouteBuilder#post(ir.restcurt.handler.PathHandler)
     */
    @Override
    public RoutesBuilder post(Handler handler) {

        Assert.notNull(this.rootPath, "root path not specified");

        boolean isDuplicate = this.routes
                .add(RouteMappingBuilder.route().method(HttpMethod.POST).path(rootPath).handler(handler).build());

        checkDuplication(isDuplicate, rootPath);

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ir.restcurt.handler.RouteBuilder#post(java.lang.String,
     * ir.restcurt.handler.PathHandler)
     */
    @Override
    public RoutesBuilder post(String path, Handler handler) {

        Assert.notNull(path, "path not specified");
        Assert.notNull(handler, "handler not specified for path");

        boolean isDuplicate = this.routes.add(
                RouteMappingBuilder.route().method(HttpMethod.POST).path(rootPath).path(path).handler(handler).build());

        checkDuplication(isDuplicate, path);
        return this;
    }

    private void checkDuplication(boolean isDuplicate, String path) {

        if (!isDuplicate) {
            throw new DuplicatePathDefenitionException("Path already defined: " + path);
        }

    }

}
