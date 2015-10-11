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

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.restcurt.exception.RestCurtException;
import ir.restcurt.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class ResponseConfigurer {

    private HttpServletResponse response;
    private ObjectMapper mapper = new ObjectMapper();
    private ResponseHeaderManager header;
    private MediaType mediaType;

    public ResponseConfigurer(HttpServletResponse response, MediaType mediaType) {
        Assert.notNull(response, "response should not be null");
        this.response = response;
        this.mediaType = mediaType;
        this.header = new ResponseHeaderManager(this.response);
    }

    public ResponseConfigurer status(int status) {
        this.response.setStatus(status);
        return this;
    }

    public ResponseConfigurer status(HttpStatus status) {
        return status(status.value());
    }

    public ResponseConfigurer header(String name, String value) {
        this.response.addHeader(name, value);
        return this;
    }

    public ResponseConfigurer body(Object body) {
        header.contentType(mediaType.getType());
        determineWriter(body);

        return this;
    }

    private void determineWriter(Object body) {
        if (mediaType == MediaType.APPLICATION_JSON) {
            writeJson(body);
        }
    }

    private void writeJson(Object body) {

        try {
            mapper.writeValue(getWriter(), body);
        } catch (IOException e) {
            throw new RestCurtException(e);
        }
    }

    private PrintWriter getWriter() {

        try {
            return this.response.getWriter();
        } catch (IOException e) {
            throw new RestCurtException(e);
        }

    }
}
