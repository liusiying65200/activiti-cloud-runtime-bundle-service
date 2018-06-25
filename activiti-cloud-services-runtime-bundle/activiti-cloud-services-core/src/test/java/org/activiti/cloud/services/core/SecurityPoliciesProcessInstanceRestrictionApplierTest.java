/*
 * Copyright 2018 Alfresco, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.cloud.services.core;

import java.util.Collections;
import java.util.Set;

import org.activiti.runtime.api.query.ProcessInstanceFilter;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SecurityPoliciesProcessInstanceRestrictionApplierTest {

    private SecurityPoliciesProcessInstanceRestrictionApplier restrictionApplier = new SecurityPoliciesProcessInstanceRestrictionApplier();

    @Test
    public void restrictToKeysAddFilterOnGivenKeys() {
        //given
        Set<String> keys = Collections.singleton("procDef");

        //when
        ProcessInstanceFilter filter = restrictionApplier.restrictToKeys(keys);

        //then
        assertThat(filter.getProcessDefinitionKeys()).isEqualTo(keys);
    }

    @Test
    public void denyAllShouldAddUnmatchableFilter() {
        //when
        ProcessInstanceFilter filter = restrictionApplier.denyAll();

        //then
        assertThat(filter.getProcessDefinitionKeys()).hasSize(1);
        assertThat(filter.getProcessDefinitionKeys().iterator().next()).startsWith("missing-");
    }

}