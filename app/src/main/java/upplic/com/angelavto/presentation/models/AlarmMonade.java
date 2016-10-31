package upplic.com.angelavto.presentation.models;

import java.util.List;

import upplic.com.angelavto.domain.models.Alarm;


public class AlarmMonade {

    private int carId = -1;
    private List<Alarm> alarm;

    public AlarmMonade() {
    }

    public AlarmMonade(int carId, List<Alarm> alarm) {
        this.carId = carId;
        this.alarm = alarm;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public List<Alarm> getAlarms() {
        return alarm;
    }

    public void setAlarms(List<Alarm> alarm) {
        this.alarm = alarm;
    }
}
