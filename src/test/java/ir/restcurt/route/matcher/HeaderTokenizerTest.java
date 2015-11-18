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
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class HeaderTokenizerTest {
    private String[] headers = {"Accept:Application/json", "Host:restcut.ir", "Accept-Encoding:gzip,deflate"};

    @Test
    public void mappingHeadersTokenizeAsExpected() {
        HeaderTokenizer tokenizer = new HeaderTokenizer(headers);

        assertThat(tokenizer.getHeaders("Accept"), is("Application/json"));
        assertThat(tokenizer.getHeaders("Host"), is("restcut.ir"));
        assertThat(tokenizer.getHeaders("Accept-Encoding"), is("gzip,deflate"));
        assertThat(tokenizer.getHeaders("Foo"), nullValue());

    }
}