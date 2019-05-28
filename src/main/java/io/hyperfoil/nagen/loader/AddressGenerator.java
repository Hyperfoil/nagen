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

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import io.hyperfoil.nagen.Address;

import static io.hyperfoil.nagen.util.FileReader.readFile;
import static io.hyperfoil.nagen.util.FileReader.readFileInJar;

public class AddressGenerator {

    private static final Object LOCK = new Object();
    private static volatile AddressGenerator INSTANCE;

    private final int STREET_NUM = 999;
    private List<String> streetNames;
    private static final String[] direction = new String[] {"SE", "SW", "NE", "NW", "E", "W", "N", "S"};
    private static final String[] streetType = new String[] {"Street", "boulevard", "drive", "avenue", "place", "lane", "trail", "Way", "Turnpike", "Alley", "Parkway"};
    private List<String> cityState;

    private AddressGenerator(String dictionary, String cityAndState) {
        if(cityAndState != null)
            cityState = readFile(cityAndState);
        else
            cityState = readFileInJar("city_state.txt");

        if(dictionary != null)
            streetNames = readFile(dictionary);
        else
            streetNames = readFileInJar("dictionary_words.txt");
    }

    public static AddressGenerator getInstance() {
        return getInstance(null, null);
    }

    public static AddressGenerator getInstance(String dictionary, String cityAndState) {
        if(INSTANCE == null) {
            synchronized (LOCK) {
                if(INSTANCE == null)
                    INSTANCE = new AddressGenerator(dictionary, cityAndState);
            }
        }
        return INSTANCE;
    }

    private int getRandomStreetNumber() {
        return randInt(1, STREET_NUM);
    }

    private String getRandomDirection() {
        return direction[randInt(0, direction.length-1)];
    }

    private String getRandomStreetName() {
        return streetNames.get(randInt(0, streetNames.size()-1));
    }

    private String getRandomStreetType() {
        return streetType[randInt(0, streetType.length-1)];
    }

    private String[] getRandomCityAndState() {
        return cityState.get(randInt(0, cityState.size()-1)).split(",");
    }

    private int getRandomZip() {
        return randInt(11111, 99999);
    }

    private int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public String getRandomStreet() {
        return getRandomStreetNumber()+" "+getRandomDirection()+" "+
                getRandomStreetName()+" "+getRandomStreetType();
    }

    public Address generateAddress() {
        if(cityState.size() > 0 && streetNames.size() > 0) {
            String[] city = getRandomCityAndState();
            return new Address(getRandomStreet(), city[0], city[1], String.valueOf(getRandomZip()), "USA");
        }
        return null;
    }

}
