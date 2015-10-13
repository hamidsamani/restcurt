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

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class TokenInfoTest {

    @Test
    public void testIsPathVariable() throws Exception {
        TokenInfo t1 = new TokenInfo(":id");
        assertTrue(t1.isPathVariable());

        TokenInfo t2 = new TokenInfo("id");
        assertFalse(t2.isPathVariable());
    }

    @Test
    public void testIsContainRegex() throws Exception {
        TokenInfo t1 = new TokenInfo(":id([a-z])");
        assertTrue(t1.isContainRegex());

        TokenInfo t2 = new TokenInfo(":id([a-z]{5,10})");
        assertTrue(t2.isContainRegex());

        TokenInfo t3 = new TokenInfo(":id");
        assertFalse(t3.isContainRegex());
    }

    @Test
    public void getRegex() {
        TokenInfo t1 = new TokenInfo(":id([a-z]{5,10})");
        assertThat(t1.getRegex(), is("[a-z]{5,10}"));
    }
}