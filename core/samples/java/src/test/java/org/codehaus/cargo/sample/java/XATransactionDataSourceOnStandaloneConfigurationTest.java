/*
 * ========================================================================
 *
 * Codehaus CARGO, copyright 2004-2011 Vincent Massol, 2012-2020 Ali Tokmen.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package org.codehaus.cargo.sample.java;

import java.net.MalformedURLException;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.Test;

import org.codehaus.cargo.container.configuration.ConfigurationType;
import org.codehaus.cargo.container.configuration.entry.ConfigurationFixtureFactory;
import org.codehaus.cargo.container.configuration.entry.DataSourceFixture;
import org.codehaus.cargo.sample.java.validator.HasDataSourceSupportValidator;
import org.codehaus.cargo.sample.java.validator.HasStandaloneConfigurationValidator;
import org.codehaus.cargo.sample.java.validator.HasWarSupportValidator;
import org.codehaus.cargo.sample.java.validator.HasXASupportValidator;
import org.codehaus.cargo.sample.java.validator.IsInstalledLocalContainerValidator;
import org.codehaus.cargo.sample.java.validator.Validator;

/**
 * Test for datasource with XA capabilities.
 */
public class XATransactionDataSourceOnStandaloneConfigurationTest extends
    AbstractDataSourceWarCapabilityContainerTestCase
{
    /**
     * Initializes the test case.
     * @param testName Test name.
     * @param testData Test environment data.
     * @throws Exception If anything goes wrong.
     */
    public XATransactionDataSourceOnStandaloneConfigurationTest(String testName,
        EnvironmentTestData testData) throws Exception
    {
        super(testName, testData);
    }

    /**
     * Creates the test suite, using the {@link Validator}s.
     * @return Test suite.
     * @throws Exception If anything goes wrong.
     */
    public static Test suite() throws Exception
    {
        CargoTestSuite suite =
            new CargoTestSuite("Tests that run on local containers supporting XADataSource "
                + "configured DataSources and WAR deployments");

        // The WebLogic 12.x, 12.1.x, 12.2.x and 14.x deployers
        // cannot deploy datasource-cmt-local
        Set<String> excludedContainerIds = new TreeSet<String>();
        excludedContainerIds.add("weblogic12x");
        excludedContainerIds.add("weblogic121x");
        excludedContainerIds.add("weblogic122x");
        excludedContainerIds.add("weblogic14x");

        suite.addTestSuite(XATransactionDataSourceOnStandaloneConfigurationTest.class,
            new Validator[] {
                new IsInstalledLocalContainerValidator(),
                new HasStandaloneConfigurationValidator(),
                new HasWarSupportValidator(),
                new HasDataSourceSupportValidator(ConfigurationType.STANDALONE),
                new HasXASupportValidator(ConfigurationType.STANDALONE)},
            excludedContainerIds);
        return suite;
    }

    /**
     * User configures javax.sql.XADataSource -&gt; container provides javax.sql.DataSource with XA
     * transaction support
     * @throws MalformedURLException If servlet WAR URL cannot be created.
     */
    public void testUserConfiguresXADataSourceAndRequestsDataSourceWithXaTransactionSupport()
        throws MalformedURLException
    {
        DataSourceFixture fixture =
            ConfigurationFixtureFactory.createXADataSourceConfiguredDataSource();

        testServletThatIssuesGetConnectionFrom(fixture, "datasource-cmt-local");
    }

}
