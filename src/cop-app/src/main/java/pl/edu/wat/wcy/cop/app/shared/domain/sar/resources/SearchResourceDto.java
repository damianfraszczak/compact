package pl.edu.wat.wcy.cop.app.shared.domain.sar.resources;

import pl.edu.wat.wcy.cop.app.shared.domain.DefaultObject;

import java.util.Date;
// Carries search resource data.

public abstract class SearchResourceDto extends DefaultObject {

    private long time = new Date().getTime();
    private int number = 1;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public SearchResourceDto() {
    }

    public SearchResourceDto(int number) {
        this.number = number;
    }
}
