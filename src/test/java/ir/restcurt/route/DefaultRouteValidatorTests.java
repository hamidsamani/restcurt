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

package ir.restcurt.route;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class DefaultRouteValidatorTests {

    @Test
    public void test() {

        RouteValidator validator = new DefaultRouteValidator();
        assertThat(validator.isValidRoute("/"), is(true));
        assertThat(validator.isValidRoute("?customer/find/by234@"), is(false));
        assertThat(validator.isValidRoute("/cusomers/123/invoices/456"), is(true));
        assertThat(validator.isValidRoute("foo"), is(false));
        assertThat(validator.isValidRoute("/foo"), is(true));

    }

}
