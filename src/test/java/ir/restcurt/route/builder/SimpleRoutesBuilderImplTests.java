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

package ir.restcurt.route.builder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ir.restcurt.route.builder.RoutesBuilder;
import ir.restcurt.route.builder.SimpleRoutesBuilderImpl;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class SimpleRoutesBuilderImplTests {

    @Test
    public void pathsCreatedAsExpected() {

        RoutesBuilder routes = new SimpleRoutesBuilderImpl();

        routes.route("/customers").get((req, res) -> System.out.println("/"))
                .get("/:id", (req, res) -> System.out.println("/:id"))
                .get("/:id/invoices", (req, res) -> System.out.println("/:id/invoices"));

        assertThat(routes.getRoutes().size(), is(3));
    }

}
