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

package ir.restcurt.server;

import java.util.HashSet;
import java.util.Set;

import ir.restcurt.exception.WebServerException;
import ir.restcurt.server.jetty.JettyServerConfigurer;
import ir.restcurt.util.ClassUtils;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class HttpWebServerDeterminer {

    private static final String JETTY_MAIN_CLASS = "org.eclipse.jetty.server.Server";
    private static final String GRIZZLY_MAIN_CLASS = "org.glassfish.grizzly.http.server.HttpServer";

    private static Set<WebServer> detectedServers = new HashSet<>(5);

    static {

        if (ClassUtils.isPresent(JETTY_MAIN_CLASS)) {
            detectedServers.add(WebServer.JETTY);
        }
        if (ClassUtils.isPresent(GRIZZLY_MAIN_CLASS)) {
            detectedServers.add(WebServer.GRIZZLY);
        }

    }

    private HttpWebServerDeterminer() {
    }

    private static WebServer getCurrentWebServer() {

        if (detectedServers.isEmpty()) {
            throw new WebServerException("There isn't any web server in the classpth.");

        } else if (detectedServers.size() > 1) {
            throw new WebServerException(
                    "There are multiple webserver in the classpath. check your project dependencies. current web servers are: "
                            + detectedServers);

        }
        return detectedServers.iterator().next();
    }

    public static ServerConfigurer currentHttpWebServer() {

        switch (getCurrentWebServer()) {
        case JETTY:
            return new JettyServerConfigurer();
        default:
            return new GeneralServerConfigurer();
        }
    }

}
