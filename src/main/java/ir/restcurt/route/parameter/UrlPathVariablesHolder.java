/*
 *
 *  * Copyright 2015 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 *
 *
 */

package ir.restcurt.route.parameter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hamid Samani
 */
public class UrlPathVariablesHolder implements UrlVariablesHolder {

    private static final String URL_REGEX = "(?<=/):?\\w+";
    private String templateUrl;
    private String givenUrl;

    private Map<String, String> pathVariables = new HashMap<>();

    public UrlPathVariablesHolder(String templateUrl, String givenUrl) {
        this.templateUrl = templateUrl;
        this.givenUrl = givenUrl;
        findPathVariables();
    }

    private void findPathVariables() {

        Pattern pattern = Pattern.compile(URL_REGEX);

        Matcher templateUrlMatcher = pattern.matcher(templateUrl);
        Matcher givenUrlMatcher = pattern.matcher(givenUrl);

        while (templateUrlMatcher.find() && givenUrlMatcher.find()) {
            String templatePath = templateUrlMatcher.group();
            String givenPath = givenUrlMatcher.group();
            if (isVariable(templatePath)) {
                pathVariables.put(templatePath.substring(1), givenPath);
            }
        }

    }

    private boolean isVariable(String templatePath) {
        return templatePath.startsWith(":");
    }

    @Override
    public boolean isContainVariable(String variable) {
        return pathVariables.containsKey(variable);
    }

    @Override
    public String getValue(String variable) {
        if (!isContainVariable(variable)) {
            return null;
        }

        return pathVariables.get(variable);
    }
}
