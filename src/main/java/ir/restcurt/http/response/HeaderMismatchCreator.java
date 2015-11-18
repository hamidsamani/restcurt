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

package ir.restcurt.http.response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class HeaderMismatchCreator extends AbstractResponseBodyCreator {
    private static final String HEADER_MISMATCH = "<p>Headers Mismatch</p>";
    private String[] headers;

    public HeaderMismatchCreator(HttpServletResponse httpServletResponse, String[] headers) {
        super(httpServletResponse);
        this.headers = headers;
    }

    @Override
    public void buildResponse() {
        response.setStatus(400);
        try {
            PrintWriter writer = response.getWriter();
            writer.println(HEADER);
            writer.println(HEADER_MISMATCH);
            writer.println("Expected headers: " + Arrays.toString(headers));
            writer.println(FOOTER);
        } catch (IOException e) {
            //ignore exception
        }

    }
}
