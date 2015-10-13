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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
class TokenInfo {

    private static final String CONTAIN_REGEX = "^:[\\w\\d]+(\\(.+\\))";
    private static final String REGEX_PART = "(\\(.+\\))";
    private static Pattern regexPartPattern = Pattern.compile(REGEX_PART);
    private String token;

    public TokenInfo(String token) {
        this.token = token;
    }

    boolean isContainRegex() {
        return Pattern.matches(CONTAIN_REGEX, token);
    }

    String getRegex() {
        Matcher matcher = regexPartPattern.matcher(token);
        String regex = null;
        if (matcher.find()) {
            regex = matcher.group();
            return regex.substring(1, regex.length() - 1);
        }
        return regex;
    }

    boolean isRegexMatches(String income) {
        return Pattern.matches(getRegex(), income);
    }

    boolean isPathVariable() {
        return token.startsWith(":");
    }
}
