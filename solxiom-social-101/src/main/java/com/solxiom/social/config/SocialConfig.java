/*
 * Copyright 2013 the original author or authors.
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
package com.solxiom.social.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableJdbcConnectionRepository;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.ReconnectFilter;
import org.springframework.social.facebook.config.annotation.EnableFacebook;
import org.springframework.social.facebook.web.DisconnectController;
import com.solxiom.social.facebook.PostToWallAfterConnectInterceptor;
import com.solxiom.social.signin.SimpleSignInAdapter;

/**
 * Spring Social Configuration.
 * 
 * This configuration is demonstrating the use of the simplified Spring Social configuration options from Spring Social 1.1.
 * It will only be used when the "simple" profile is active.
 * The more complex/explicit configuration is still available in ExplicitSocialConfig.java and will be used if the "explicit" profile is active.
 * 
 * @author Craig Walls
 */
@Configuration
@Profile("simple")
@EnableJdbcConnectionRepository
@EnableFacebook(appId="${facebook.clientId}", appSecret="${facebook.clientSecret}")
public class SocialConfig {

	@Inject
	private Environment environment;
	
	@Inject
	private ConnectionFactoryLocator connectionFactoryLocator;
	
	@Inject 
	private ConnectionRepository connectionRepository;

	@Inject 
	private UsersConnectionRepository usersConnectionRepository;

	@Bean
	public ConnectController connectController() {
		ConnectController connectController = new ConnectController(connectionFactoryLocator, connectionRepository);
		connectController.addInterceptor(new PostToWallAfterConnectInterceptor());
		return connectController;
	}

	@Bean
	public ProviderSignInController providerSignInController(RequestCache requestCache) {
		return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new SimpleSignInAdapter(requestCache));
	}
	
	@Bean
	public DisconnectController disconnectController() {
		return new DisconnectController(usersConnectionRepository, environment.getProperty("facebook.clientSecret"));
	}

	/*
	 * ReconnectFilter only available in latest 1.1.0.BUILD-SNAPSHOT builds.
	 * This comment will be removed when Spring Social 1.1.0.M3 is released.
	 */
	@Bean
	public ReconnectFilter apiExceptionHandler() {
		return new ReconnectFilter(usersConnectionRepository, userIdSource());
	}
	
	@Bean
	public UserIdSource userIdSource() {
		return new UserIdSource() {			
			@Override
			public String getUserId() {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if (authentication == null) {
					throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
				}
				return authentication.getName();
			}
		};
	}

}
