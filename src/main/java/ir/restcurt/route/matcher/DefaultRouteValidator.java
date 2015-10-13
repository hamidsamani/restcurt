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

import java.util.regex.Pattern;

/**
 * Check the validity of paths. This class returns true if path is just root
 * path ('/') or any path that starts with backslash forwarded by combinations
 * of letters.
 *
 * @author Hamid Samani
 * @since 0.0.1
 */
public class DefaultRouteValidator implements RouteValidator {

    /**
     * root path.
     */
    private static final String ROOT_PATH = "/";
    /**
     * any path that begins with backslash forwarded by combination of letters
     * with or without preceding colon, and maybe it forwarded by backslash.
     */
    public static final String VALID_PATH = "^\\/(:?\\w+\\/?)+";

    /*
     * (non-Javadoc)
     * 
     * @see ir.restcurt.route.matcher.RouteValidator#isValidPath(java.lang.String)
     */
    @Override
    public boolean isValidRoute(String route) {
        if (route == null) {
            return false;
        } else if (isRootPath(route)) {
            return true;
        }

        return Pattern.matches(VALID_PATH, route);
    }

    /**
     * @param route
     * @return
     */
    private boolean isRootPath(String route) {

        return route.contentEquals(ROOT_PATH);
    }

}
