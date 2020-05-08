package fr.sheepy.aboutlotr.presentation.model;

import fr.sheepy.aboutlotr.R;

public class Character implements Comparable<Character> {
    private String _id, height, race, gender, birth, spouse, death, realm, hair, name, wikiUrl;

    public Character() {
    }

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

    public int getRaceResource() {
        String tmp = this.getRace();
        if (tmp != null) {
            switch (tmp) {
                case "Hobbits":
                case "Hobbit":
                    return R.drawable.gnome;
                case "Elf":
                case "Elves":
                    return R.drawable.elf;
                case "Men":
                case "Human":
                    return R.drawable.man;
                case "Ent":
                case "Ents":
                    return R.drawable.tree;
                case "Urulóki":
                case "Dragons":
                case "Dragon":
                    return R.drawable.dragon;
                case "Dwarves":
                case "Dwarf":
                    return R.drawable.dwarf;
                case "Black Uruk":
                case "Uruk-hai":
                case "Uruk-hai,Orc":
                case "Orc":
                case "Orcs":
                    return R.drawable.ogre;
                case "Great Spiders":
                    return R.drawable.spider;
                case "Raven":
                    return R.drawable.crow;
                case "Orc,Goblin":
                case "Goblin,Orc":
                case "Goblin":
                    return R.drawable.goblin;
                case "Great Eagles":
                case "Eagle":
                case "Eagles":
                    return R.drawable.eagle;
                case "Werewolves":
                case "Wolfhound":
                    return R.drawable.werewolf;
                case "Ainur":
                case "Maiar":
                case "God":
                    return R.drawable.zeus;
                case "Balrogs":
                case "Balrog":
                case "Maiar,Balrogs":
                    return R.drawable.demon;
                case "Horse":
                    return R.drawable.horse;
                case "Men,Wraith":
                    return R.drawable.spirit;
                case "Vampire":
                    return R.drawable.dracula;
                case "Stone-trolls":
                    return R.drawable.golem;
                case "Half-elven":
                    return R.drawable.halfelven;
                default:
                    return R.drawable.unknown;
            }
        }
        return R.drawable.unknown;
    }

    @Override
    public int compareTo(Character o) {
        return this.getName().compareToIgnoreCase(o.getName());
    }
}
