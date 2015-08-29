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

import java.util.ArrayList;
import java.util.List;

import ir.restcurt.route.builder.RoutesBuilder;
import ir.restcurt.route.handler.AbstractRouteHandler;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public abstract class DummyRouteHandlers {

    public static class Person {

        public String fname = "fname";
        public String lname = "lname";
        public List<String> messages = new ArrayList<String>() {

            {
                add("msg1");
                add("msg2");
                add("msg3");

            }
        };

    }

    public static class DummyRouteHandler1 extends AbstractRouteHandler {

        @Override
        public void route(RoutesBuilder route) {

            route.route("/").get((req, res) -> res.println("Hello RESTCurt"))
                    .get("/foo", (req, res) -> res.toJson(new Person()))
                    .get("/:id/invoices/", (req, res) -> res.println("inside invoices"));
        }

    }
}
