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

package ir.restcurt.http;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class HttpServletResponseHolderTests {
    private HttpServletResponse response;

    @Before
    public void before() throws IOException {
        response = mock(HttpServletResponse.class, RETURNS_DEEP_STUBS);

    }

    @Test
    public void toJsonWritesAsExpected() throws IOException {
        HttpServletResponseHolder res = new HttpServletResponseHolder(response);

        res
                .toJson()
                .status(201)
                .header("name", "value")
                .body("json content");

        verify(response).getWriter();
        verify(response).setStatus(201);
        verify(response).addHeader("name", "value");

    }
}
