package id.co.rumahcoding.myquran.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by blastocode on 2/14/17.
 */

public class Surah extends RealmObject {
    @PrimaryKey
    private int id;
    private String title;
    private int ayah;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAyah() {
        return ayah;
    }

    public void setAyah(int ayah) {
        this.ayah = ayah;
    }
}
