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

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class ForwardSlashTokenizer implements PathTokenizer {

    private static final String forwardSlashRegex = "(?<=/)(:?\\w+(\\(.+\\))?)";

    private Pattern pattern = Pattern.compile(forwardSlashRegex);

    /**
     * @param path to tokenize based on url.
     * @return array of tokens.
     */
    @Override
    public String[] tokenize(String path) {
        ArrayList<String> tokens = new ArrayList<>();
        Matcher matcher = pattern.matcher(path);

        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens.toArray(new String[tokens.size()]);

    }
}
