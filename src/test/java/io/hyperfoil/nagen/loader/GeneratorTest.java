/*
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
 */
package io.hyperfoil.nagen.loader;

import io.hyperfoil.nagen.Address;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GeneratorTest {

    @Test
    public void testNames() {
        NameGenerator ng = NameGenerator.getInstance(
                "src/main/resources/io/hyperfoil/nagen/loader/female.txt",
                "src/main/resources/io/hyperfoil/nagen/loader/male.txt",
                "src/main/resources/io/hyperfoil/nagen/loader/last.txt");

        assertNotNull( ng.getRandomFirstName());
        assertNotNull( ng.getRandomLastName());
    }

    @Test
    public void testAddress() {
        AddressGenerator ag = AddressGenerator.getInstance(
                "src/main/resources/io/hyperfoil/nagen/loader/dictionary_words.txt",
               "src/main/resources/io/hyperfoil/nagen/loader/city_state.txt");

        Address a = ag.generateAddress();
        assertNotNull(a);
        assertNotNull(a.city());
        assertNotNull(a.country());
        assertNotNull(a.state());
        assertNotNull(a.street());
        assertNotNull(a.zip());
    }
}
