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

package ir.restcurt.route.handler;

import ir.restcurt.route.builder.ConfigurationBuilder;
import ir.restcurt.route.builder.ExceptionHandlerBuilder;
import ir.restcurt.route.builder.FilterBuilder;
import ir.restcurt.route.builder.RouteBuilder;

/**
 * @author Hamid Samani
 * @see {@link AbstractRouteHandler}
 * @since 0.0.1
 */
public interface RouteHandler {

    void filter(FilterBuilder filters);

    void route(RouteBuilder routes);

    void config(ConfigurationBuilder configs);

    void exception(ExceptionHandlerBuilder exceptions);
}
