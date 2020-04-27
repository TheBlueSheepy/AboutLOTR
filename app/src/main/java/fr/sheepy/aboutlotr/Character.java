package fr.sheepy.aboutlotr;

public class Character {
    private String _id,height,race,gender,birth,spouse,death,realm,hair,name,wikiUrl;

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

    @Override
    public String toString() {
        return "Character{" +
                "_id='" + _id + '\'' +
                ", height='" + height + '\'' +
                ", race='" + race + '\'' +
                ", gender='" + gender + '\'' +
                ", birth='" + birth + '\'' +
                ", spouse='" + spouse + '\'' +
                ", death='" + death + '\'' +
                ", realm='" + realm + '\'' +
                ", hair='" + hair + '\'' +
                ", name='" + name + '\'' +
                ", wikiUrl='" + wikiUrl + '\'' +
                '}';
    }
}
