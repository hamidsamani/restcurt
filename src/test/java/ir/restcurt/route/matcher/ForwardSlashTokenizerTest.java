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

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class ForwardSlashTokenizerTest {

    @Test
    public void testTokenize() throws Exception {

        PathTokenizer pt = new ForwardSlashTokenizer();

        String[] tokens1 = pt.tokenize("/persons/:id/invoices/456");
        assertThat(Arrays.asList(tokens1), hasItems("persons", ":id", "invoices", "456"));

        String[] tokens2 = pt.tokenize("/persons/:id([a-z]{1,4})/invoices/456");
        assertThat(Arrays.asList(tokens2), hasItems("persons", ":id([a-z]{1,4})", "invoices", "456"));

    }
}