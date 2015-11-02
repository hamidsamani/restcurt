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

package ir.restcurt.route.registrar;

import ir.restcurt.route.builder.ConfigurationBuilder;
import ir.restcurt.route.builder.ExceptionHandlerBuilder;
import ir.restcurt.route.builder.FilterBuilder;
import ir.restcurt.route.builder.RouteBuilder;
import ir.restcurt.route.handler.AbstractRouteHandler;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public abstract class DummyRouteHandlers {

    public static class Person {

        public String id;
        public String name;

        public Person(String id) {
            this.id = id;
        }

        public Person(String name, String id) {
            this.name = name;
            this.id = id;
        }
    }

    public static class PersonsResource extends AbstractRouteHandler {
        @Override
        public void filter(FilterBuilder filters) {
            filters.beforeAnyGet((req, res) -> System.out.println("beforeAny"));
            filters.beforeGet("/persons", (req, res) -> System.out.println("Filter Before /persons"));
            filters.beforeGet("/persons/:id", (req, res) -> System.out.println("before /persons/" + req.variable("id")));
        }

        @Override
        public void route(RouteBuilder route) {

            route.route("/persons")
                    .get((req, res) -> res.toJson().body(req.param("name")))
                    .post((req, res) -> res.toJson().body(req.param("name") + " POST request"))
                    .get("/:id", (req, res) -> res.toJson().body(new Person(req.variable("id", String.class))))
                    .get("/:id/:name/", (req, res) -> res.toJson().body(new Person(req.variable("name", String.class), req.variable("id", String.class))))
                    .get("/foo/bar/rx", (req, res) -> {
                        throw new RuntimeException("resource not found");
                    }).get("/foo/bar/ia", (req, res) -> {
                throw new IllegalArgumentException("illegal argument");
            });
        }

        @Override
        public void exception(ExceptionHandlerBuilder exception) {
            exception.exception(RuntimeException.class, (req, res) -> res.toJson().body(RuntimeException.class.getCanonicalName() + " handled"))
                    .exception(IllegalArgumentException.class, (req, res) -> res.toJson().body(IllegalArgumentException.class.getName()));
        }

        @Override
        public void config(ConfigurationBuilder configs) {
            configs.version("v1.2");
        }
    }
}
