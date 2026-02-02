package pl.edu.wat.wcy.cop.domain.scenario.sar.resources;

import pl.edu.wat.wcy.cop.domain.scenario.ScenarioObject;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
// Base type for search resources.
public abstract class SearchResource extends ScenarioObject {

    private Date time;
    private int number;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
