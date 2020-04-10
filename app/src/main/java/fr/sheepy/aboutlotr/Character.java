package fr.sheepy.aboutlotr;

public class Character {
    String _id,height,race,gender,birth,spouse,death,realm,hair,name,wikiUrl;

    public Character(String _id, String height, String race, String gender, String birth, String spouse, String death, String realm, String hair, String name, String wikiUrl) {
        this._id = _id;
        this.height = height;
        this.race = race;
        this.gender = gender;
        this.birth = birth;
        this.spouse = spouse;
        this.death = death;
        this.realm = realm;
        this.hair = hair;
        this.name = name;
        this.wikiUrl = wikiUrl;
    }

    public String get_id() {
        return _id;
    }

    public String getHeight() {
        return height;
    }

    public String getRace() {
        return race;
    }

    public String getGender() {
        return gender;
    }

    public String getBirth() {
        return birth;
    }

    public String getSpouse() {
        return spouse;
    }

    public String getDeath() {
        return death;
    }

    public String getRealm() {
        return realm;
    }

    public String getHair() {
        return hair;
    }

    public String getName() {
        return name;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }
}
