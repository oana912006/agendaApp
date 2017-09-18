package com.feed.entities;

import java.io.Serializable;

/**
 * Entity from list of all users
 *
 * @author oana
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String gender;
    private Name name;
    private Location location;
    private String email;
    private Login login;
    private String dob;
    private String registered;
    private String phone;
    private String cell;
    private Id id;
    private Picture picture;
    private String nat;

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(String large, String medium, String thumbnail) {
        this.picture = new Picture();
        picture.setLarge(large);
        picture.setThumbnail(thumbnail);
        picture.setMedium(medium);
    }

    public Id getId() {
        return id;
    }

    public void setId(String name, String value) {
        this.id = new Id();
        id.setName(name);
        id.setValue(value);
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(String username, String password, String salt, String md5, String sha1, String sha256) {
        this.login = new Login();
        login.setUsername(username);
        login.setPassword(password);
        login.setSalt(salt);
        login.setMd5(md5);
        login.setSha1(sha1);
        login.setSha256(sha256);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(String street, String city, String state, String postcode) {
        this.location = new Location();
        location.setState(state);
        location.setStreet(street);
        location.setCity(city);
        location.setPostcode(postcode);
    }

    public Name getName() {
        return name;
    }

    public void setName(String title, String first, String last) {
        name = new Name();
        name.setFirst(first);
        name.setLast(last);
        name.setTitle(title);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    class Login  implements Serializable {
        String username, password, salt, md5, sha1,sha256;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getSha256() {
            return sha256;
        }

        public void setSha256(String sha256) {
            this.sha256 = sha256;
        }

        public String getSha1() {
            return sha1;
        }

        public void setSha1(String sha1) {
            this.sha1 = sha1;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public class Name  implements Serializable {
        String title, first, last;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }
    }

    public class Location  implements Serializable {
        private String street, city, state, postcode;

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }
    }
    class Id  implements Serializable {
        private String name, value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
    public class Picture  implements Serializable  {
        private String large,medium, thumbnail;

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }
}
