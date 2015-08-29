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

import javax.servlet.http.HttpServletResponse;

import ir.restcurt.util.Assert;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class ResponseHeaderManager {

    private static final String CONTENT_TYPE = "CONTENT-TYPE";

    private HttpServletResponse response;

    public ResponseHeaderManager(HttpServletResponse response) {
        Assert.notNull(response, "Response should not be null");
        this.response = response;
    }

    public void contentType(String type) {

        this.response.addHeader(CONTENT_TYPE, type);

    }

}
