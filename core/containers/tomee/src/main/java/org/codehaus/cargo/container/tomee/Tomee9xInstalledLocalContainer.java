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
package org.codehaus.cargo.container.tomee;

import org.codehaus.cargo.container.configuration.LocalConfiguration;

/**
 * Special container support for the Apache TomEE 9.x servlet container.
 */
public class Tomee9xInstalledLocalContainer extends Tomee8xInstalledLocalContainer
{
    /**
     * Unique container id.
     */
    public static final String ID = "tomee9x";

    /**
     * {@inheritDoc}
     * @see Tomee8xInstalledLocalContainer#Tomee8xInstalledLocalContainer(org.codehaus.cargo.container.configuration.LocalConfiguration)
     */
    public Tomee9xInstalledLocalContainer(LocalConfiguration configuration)
    {
        super(configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId()
    {
        return ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName()
    {
        return "TomEE " + getVersion("9.x");
    }
}