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

import java.util.Set;

import ir.restcurt.route.handler.Handler;
import ir.restcurt.route.mapping.RouteMapping;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public interface RoutesBuilder {

    RoutesBuilder route(String route);

    RoutesBuilder get(Handler handler);

    RoutesBuilder get(String path, Handler handler);

    RoutesBuilder post(Handler handler);

    RoutesBuilder post(String path, Handler handler);

    Set<RouteMapping> getRoutes();
}
