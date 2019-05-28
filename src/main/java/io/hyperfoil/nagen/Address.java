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
package io.hyperfoil.nagen;

/**
 * An Address object built from real world data.
 */
public class Address {

    private String street;
    private String city;
    private String state;
    private String country;
    private String zip;

    public Address(String street, String city, String state, String zip, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
    }

    public String street() {
        return street;
    }

    public String city() {
        return city;
    }

    public String state() {
        return state;
    }

    public String country() {
        return country;
    }

    public String zip() {
        return zip;
    }

}
