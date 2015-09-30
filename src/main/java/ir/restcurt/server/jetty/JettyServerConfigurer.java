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

package ir.restcurt.server.jetty;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ir.restcurt.exception.WebServerException;
import ir.restcurt.route.repository.MappingRepository;
import ir.restcurt.server.AbstractServerConfigurer;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class JettyServerConfigurer extends AbstractServerConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JettyServerConfigurer.class);
    private Server server;
    private Handler handler;

    public JettyServerConfigurer() {
        this((MappingRepository) null);
    }

    public JettyServerConfigurer(MappingRepository repository) {
        this(DEFAULT_PORT, repository);
    }

    public JettyServerConfigurer(int port, MappingRepository repository) {

        this.handler = new JettyHandler(repository);
        this.server = new Server(port);
    }

    /**
     * @return the handler
     */
    public Object getHandler() {

        return handler;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ir.restcurt.server.ServerConfigurer#setHandler(java.lang.Object)
     */
    @Override
    public void setHandler(Object handler) {

        if (handler.getClass().isInstance(Handler.class)) {
            this.handler = (Handler) handler;

        } else if (MappingRepository.class.isInstance(handler)) {
            this.handler = new JettyHandler((MappingRepository) handler);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see ir.restcurt.server.AbstractServerConfigurer#preStart()
     */
    @Override
    protected void preStart() {

        this.server.setHandler(handler);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ir.restcurt.server.AbstractServerConfigurer#doStart()
     */
    protected void doStart() throws Exception {

        this.server.start();
        this.server.join();

    }

    /*
     * (non-Javadoc)
     * 
     * @see ir.restcurt.server.AbstractServerConfigurer#postStart()
     */
    @Override
    protected void postStart() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see ir.restcurt.server.AbstractServerConfigurer#start()
     */
    @Override
    public void start() {

        try {
            preStart();
            doStart();
            postStart();
        } catch (Exception e) {

            LOGGER.debug("starting server failed: {}", e.getMessage());
            throw new WebServerException(e);

        }

    }

}
