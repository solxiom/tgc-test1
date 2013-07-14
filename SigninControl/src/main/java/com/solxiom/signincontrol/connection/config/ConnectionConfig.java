/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solxiom.signincontrol.connection.config;

import com.solxiom.signincontrol.connection.entity.ConnectionCredential;

/**
 *
 * @author soleikav
 */
public interface ConnectionConfig {

	 ConnectionCredential buildDefaultConnectionCredential();
	 LoggingPolicy getDefaultLoggingPolicy();
}
