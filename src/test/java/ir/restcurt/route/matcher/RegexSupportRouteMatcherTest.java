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

package ir.restcurt.route.matcher;

import org.junit.Test;

import java.util.regex.Pattern;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by hamid on 10/12/15.
 */
public class RegexSupportRouteMatcherTest {

    @Test
    public void testIsSatisfyMapping() throws Exception {
        String income = "/persons/123/invoices/458";

        String pattern = "/persons/:id(\\d{1,4})/invoices/:invoice";

        RegexSupportRouteMatcher matcher = new RegexSupportRouteMatcher();

        assertTrue(matcher.isSatisfyMapping(income, pattern));

        String pattern2 = "/persons/:id(\\w{5,9})/invoices/:invoice";
        assertFalse(matcher.isSatisfyMapping(income, pattern2));
    }

    @Test
    public void test() {
        System.out.println(Pattern.matches("\\w", "hamidsam"));
    }
}