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

package ir.restcurt.route;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import ir.restcurt.http.HttpMethod;
import ir.restcurt.route.mapping.RouteMapping;
import ir.restcurt.route.mapping.RouteMappingInfo;
import ir.restcurt.route.mapping.RouteMappingInfoImpl;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class RouteMappingInfoImplTests {

    @Rule
    public ExpectedException expec = ExpectedException.none();

    private static final String PATH = "/customers/:id/invoices/:number/address/:postal";

    private static final String NOT_VALID_PATH = "customers?:id/invoices/:number/address/:postal";

    private RouteMapping mapping = Mockito.mock(RouteMapping.class);

    @Test
    public void variablesDeterminedAsExpected() {

        when(mapping.getPath()).thenReturn(PATH);
        when(mapping.getMethod()).thenReturn(HttpMethod.GET);
        when(mapping.getHandler()).thenReturn((req, res) -> System.out.println());

        RouteMappingInfo info = new RouteMappingInfoImpl(mapping);

        assertThat(info.getVariables(), hasItems("id", "number", "postal"));
    }

    @Test
    public void shouldThrowIllegalArgumentException() {

        when(mapping.getPath()).thenReturn(NOT_VALID_PATH);
        when(mapping.getMethod()).thenReturn(HttpMethod.GET);

        when(mapping.getHandler()).thenReturn((req, res) -> System.out.println());
        expec.expect(IllegalArgumentException.class);

        @SuppressWarnings("unused")
        RouteMappingInfo info = new RouteMappingInfoImpl(mapping);

    }

}
