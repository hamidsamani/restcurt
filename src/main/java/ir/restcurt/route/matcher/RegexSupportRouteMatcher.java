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

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class RegexSupportRouteMatcher extends AbstractRouteMatcher {

    private PathTokenizer tokenizer = new ForwardSlashTokenizer();
    private RouteValidator routeValidator = new DefaultRouteValidator();

    /**
     * @param income  request path received from client.
     * @param pattern current declared path pattern inside handler.
     * @return true if this handler satisfy handling of request
     */
    @Override
    public boolean isSatisfyMapping(String income, String pattern) {

        if (!routeValidator.isValidRoute(income)) {
            return false;
        }
        String[] incomeTokens = tokenizer.tokenize(income);
        String[] patternTokens = tokenizer.tokenize(pattern);

        if (incomeTokens.length != patternTokens.length) {
            return false;
        }
        for (int i = 0; i < incomeTokens.length; i++) {
            if (incomeTokens[i].contentEquals(patternTokens[i])) {
                continue;
            }
            TokenInfo tokenInfo = new TokenInfo(patternTokens[i]);
            if (!tokenInfo.isPathVariable()) {
                return false;
            }
            if (tokenInfo.isContainRegex()) {
                if (!tokenInfo.isRegexMatches(incomeTokens[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}
