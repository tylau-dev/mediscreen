import java.time.Period;

class GetAge {
    public static int calculateBasedOnBirthdate(DateTIme birthday) {
        return Period.between(birthday, Date.now()).getYears();
    }
}