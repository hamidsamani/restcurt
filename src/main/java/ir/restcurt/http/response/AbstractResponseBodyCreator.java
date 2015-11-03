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

/**
 * @author Hamid Samani
 */
public abstract class AbstractResponseBodyCreator {
    protected static final String HEADER = "<!DOCTYPE html>" +
            "<html>" +
            "<head>" +
            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />" +
            "</head>" +
            "<body>" +
            "<h3>RESTCurt</h3>";
    protected static final String FOOTER = "</body></html>";
    protected HttpServletResponse response;

    public AbstractResponseBodyCreator(HttpServletResponse response) {
        this.response = response;
    }

    public abstract void buildResponse();

}
