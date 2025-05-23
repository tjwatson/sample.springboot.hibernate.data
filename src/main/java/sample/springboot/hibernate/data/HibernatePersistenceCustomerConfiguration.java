/*******************************************************************************
 * Copyright (c) 2025 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 *******************************************************************************/
package sample.springboot.hibernate.data;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import sample.springboot.hibernate.data.customer.Customer;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories(basePackageClasses = Customer.class, entityManagerFactoryRef = "customerEntityManagerFactory")
public class HibernatePersistenceCustomerConfiguration {

	@Bean()
	DataSource customerDataSource() {
		return new JndiDataSourceLookup().getDataSource("jdbc/CUSTOMER_UNIT");
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean customerEntityManagerFactory(
			@Qualifier("customerDataSource") DataSource customerDataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setJtaDataSource(customerDataSource);
		em.setPackagesToScan(Customer.class.getPackage().getName());

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		em.setJpaVendorAdapter(vendorAdapter);
		return em;
	}
}
