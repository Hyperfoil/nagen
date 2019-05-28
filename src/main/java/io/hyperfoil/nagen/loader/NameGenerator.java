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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static io.hyperfoil.nagen.util.FileReader.readFile;
import static io.hyperfoil.nagen.util.FileReader.readFileInJar;

public class NameGenerator {

    private static final Object LOCK = new Object();
    private static volatile NameGenerator INSTANCE;

    private List<String> firstName;
    private List<String> lastName;
    private List<String> domainNames = new ArrayList<>();

    private NameGenerator(String female, String male, String last) {
        if(female != null && male != null && last != null) {
            firstName = readFile(female);
            firstName.addAll(readFile(male));
            lastName = readFile(male);
        }
        else {
            firstName = readFileInJar("female.txt");
            firstName.addAll(readFileInJar("male.txt"));
            lastName = readFileInJar("last.txt");
        }
    }

    public static NameGenerator getInstance() {
        return getInstance(null, null, null);
    }

    public static NameGenerator getInstance(String female, String male, String last) {
        if(INSTANCE == null) {
            synchronized (LOCK) {
                if(INSTANCE == null)
                    INSTANCE = new NameGenerator(female, male, last);
            }
        }
        return INSTANCE;
    }

    public String getRandomFirstName() {
        return firstName.get(randInt(0, firstName.size()-1));
    }

    public String getRandomLastName() {
        return lastName.get(randInt(0, lastName.size()-1));
    }

    private String getRandomDomainName() {
        if(domainNames.size() == 0)
            generateDomainNames();

        return domainNames.get(randInt(0, domainNames.size()-1));
    }

    public String getRandomEmail(String firstName, String lastName, int suffix) {
        return firstName+"."+lastName+"_"+suffix+"@"+getRandomDomainName();
    }

    private void generateDomainNames() {
        domainNames.add("spec.org");
        domainNames.add("google.com");
        domainNames.add("apple.com");
        domainNames.add("ibm.com");
        domainNames.add("intel.com");
        domainNames.add("oracle.com");
        domainNames.add("redhat.com");
        domainNames.add("ebay.com");
        domainNames.add("facebook.com");
        domainNames.add("hpe.com");
        domainNames.add("msn.com");
        domainNames.add("microsoft.com");
        domainNames.add("hotmail.com");
        domainNames.add("aol.com");
        domainNames.add("yahoo.com");
        domainNames.add("gmail.com");
        domainNames.add("outlook.com");
        domainNames.add("hushmail.com");
        domainNames.add("mail.com");
    }


    private int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}
