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

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class HeaderTokenizer {
    private final String[] headers;
    private Map<String, String> tokenizedHeaders = new ConcurrentHashMap<>();

    public HeaderTokenizer(String[] headers) {
        this.headers = headers;
        tokenize();
    }

    private void tokenize() {
        for (String header : headers) {
            int index = header.indexOf(":");
            String key = header.substring(0, index);
            String values = header.substring(index + 1);
            tokenizedHeaders.put(key, values);
        }
    }

    public String getHeaders(String key) {
        return tokenizedHeaders.get(key);
    }

    public Set<String> keys() {
        return tokenizedHeaders.keySet();
    }
}
