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

import ir.restcurt.exception.DuplicatePathDefenitionException;
import ir.restcurt.http.HttpMethod;
import ir.restcurt.route.handler.Handler;
import ir.restcurt.route.mapping.RouteMapping;
import ir.restcurt.route.mapping.RouteMapping.RouteMappingBuilder;
import ir.restcurt.util.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class DefaultRouteBuilderImpl implements RouteBuilder {

    private String rootPath;

    private Set<RouteMapping> routes = new HashSet<>();

    public DefaultRouteBuilderImpl() {
    }

    public DefaultRouteBuilderImpl(String rootPath) {
        this.rootPath = rootPath;
    }

    public Set<RouteMapping> getRouteMappings() {

        return routes;
    }

    /**
     * @return the rootPath
     */
    public String getRootPath() {

        return rootPath;
    }

    @Override
    public RouteBuilder route(String route) {

        if (this.rootPath != null) {
            throw new IllegalStateException("you already defined root path");
        }

        this.rootPath = route;
        return this;
    }

    @Override
    public RouteBuilder get(Handler handler) {
        checkPreCondition(handler);
        createRouteMapping(rootPath, handler, HttpMethod.GET);
        return this;
    }

    @Override
    public RouteBuilder get(String path, Handler handler) {
        checkPreConditions(path, handler);
        createRouteMapping(path, handler, HttpMethod.GET);
        return this;
    }

    @Override
    public RouteBuilder post(Handler handler) {
        checkPreCondition(handler);
        createRouteMapping(rootPath, handler, HttpMethod.POST);
        return this;
    }

    @Override
    public RouteBuilder post(String path, Handler handler) {
        checkPreConditions(path, handler);
        createRouteMapping(path, handler, HttpMethod.POST);
        return this;
    }

    @Override
    public RouteBuilder put(String path, Handler handler) {
        checkPreConditions(path, handler);
        createRouteMapping(path, handler, HttpMethod.PUT);
        return this;
    }

    @Override
    public RouteBuilder delete(String path, Handler handler) {
        checkPreConditions(path, handler);
        createRouteMapping(path, handler, HttpMethod.DELETE);
        return this;
    }

    private void createRouteMapping(String path, Handler handler, HttpMethod method) {
        checkDuplication(this.routes.add(RouteMappingBuilder
                .route()
                .path(rootPath).path(path)
                .handler(handler)
                .method(method)
                .build())
                , path);
    }

    private void checkPreCondition(Handler handler) {
        Assert.notNull(handler, "handler not specified for path");
    }

    private void checkPreConditions(String path, Handler handler) {
        Assert.notNull(path, "path not specified");
        Assert.notNull(handler, "handler not specified for path");
    }

    private void checkDuplication(boolean isDuplicate, String path) {
        if (!isDuplicate) {
            throw new DuplicatePathDefenitionException("Path already defined: " + path);
        }

    }

}
