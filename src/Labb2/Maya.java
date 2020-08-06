package Labb2;

import java.util.Objects;

public class Maya implements Comparable <Maya> {
    private String namn;
    private String typ;
    private String beskrivning;

    public Maya() {
    }


    public Maya(String namn, String typ, String beskrivning) {
        this.namn = namn;
        this.typ = typ;
        this.beskrivning = beskrivning;
    }

    public Maya (String namn) {
        this.namn = namn;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getBeskrivning() {
        return beskrivning;
    }

    @Override
    public int hashCode() {
        return Objects.hash(namn);
    }

    public void setBeskrivning(String beskrivning) {
        this.beskrivning = beskrivning;
    }

    @Override
    public int compareTo(Maya o) {
        return this.getNamn().compareToIgnoreCase(o.getNamn());
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if  ( ! (obj instanceof Maya)) {
            return false;
        }
        Maya ny = (Maya) obj;
        return ny.namn == namn;
    }
}

