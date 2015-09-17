/*
 *
 *  * Copyright 2015 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 *
 *
 */

package ir.restcurt.route.parameter;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by Hamid Samani
 */
public class UrlPathVariablesHolderTests {
    private static final String TEMPLATE_URL = "/persons/:id/invoices/:num/details";
    private static final String GIVEN_URL = "/persons/123/invoices/1456/details";

    @Test
    public void testIsContainVariable() {
        UrlPathVariablesHolder pathVariable = new UrlPathVariablesHolder(TEMPLATE_URL, GIVEN_URL);

        assertThat(pathVariable.isContainVariable("id"), is(true));
        assertThat(pathVariable.isContainVariable("persons"), is(false));
        assertThat(pathVariable.isContainVariable("num"), is(true));
        assertThat(pathVariable.isContainVariable("details"), is(false));
        assertThat(pathVariable.isContainVariable("foo"), is(false));


    }

    @Test
    public void testgetValue() {
        UrlPathVariablesHolder pathVariable = new UrlPathVariablesHolder(TEMPLATE_URL, GIVEN_URL);
        assertThat(pathVariable.getValue("id"), is("123"));
        assertThat(pathVariable.getValue("num"), is("1456"));

    }
}
