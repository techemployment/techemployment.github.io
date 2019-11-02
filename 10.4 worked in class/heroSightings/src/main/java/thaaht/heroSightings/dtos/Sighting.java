/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thaaht.heroSightings.dtos;

import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Bill Gates
 */
public class Sighting {
    private int sightingID;
    @NotNull(message = "Please enter a valid date.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateSeen;
    private Character character;
    private Location location;

    public int getSightingID() {
        return sightingID;
    }

    public void setSightingID(int sightingID) {
        this.sightingID = sightingID;
    }

    public LocalDate getDateSeen() {
        return dateSeen;
    }

    public void setDateSeen(LocalDate dateSeen) {
        this.dateSeen = dateSeen;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.sightingID;
        hash = 41 * hash + Objects.hashCode(this.dateSeen);
        hash = 41 * hash + Objects.hashCode(this.character);
        hash = 41 * hash + Objects.hashCode(this.location);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sighting other = (Sighting) obj;
        if (this.sightingID != other.sightingID) {
            return false;
        }
        if (!Objects.equals(this.dateSeen, other.dateSeen)) {
            return false;
        }
        if (!Objects.equals(this.character, other.character)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return true;
    }
}
