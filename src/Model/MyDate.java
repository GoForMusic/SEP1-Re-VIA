package Model;
import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * A class that include all the information about the Date for Match
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */
public class MyDate implements Serializable
{
    private int day;
    private int month;
    private int year;

    /**
     * A three-argument constructor that will include day,month and year
     * @param day set the day
     * @param month set the month
     * @param year set the year
     */
    public MyDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * A no-argument constructor that will set the date with today date.
     */
    public MyDate() {
        MyDate date = today();
        this.day = date.day;
        this.month = date.month;
        this.year = date.year;
    }

    /**
     * A static method that will get the today dat using the GregorianCalendar library
     * @return a copy of today date
     */
    public static MyDate today() {
        GregorianCalendar currentDate = new GregorianCalendar();
        int currentDay = currentDate.get(GregorianCalendar.DATE);
        int currentMonth = currentDate.get(GregorianCalendar.MONTH) + 1;
        int currentYear = currentDate.get(GregorianCalendar.YEAR);

        return new MyDate(currentDay, currentMonth, currentYear);
    }

    /**
     * A method that will set the day of the date
     * @param day the new day
     * @throws Exception will throw an error exception if the day is 0
     */
    public void setDay(int day) throws Exception {
        if (day == 0) {
            throw new Exception(String.valueOf(day));
        } else
            this.day = day;
    }

    /**
     * A method that will set the month of the date
     * @param month the new month
     * @throws Exception will throw an error exception if the month is 0
     */
    public void setMonth(int month) throws Exception {
        if (day == 0) {
            throw new Exception(String.valueOf(month));
        } else
            this.month = month;
    }

    /**
     * A method that will set the year of the date
     * @param year the new year
     * @throws Exception will throw an error exception if the year is 0
     */
    public void setYear(int year) throws Exception {
        if (day == 0) {
            throw new Exception(String.valueOf(year));
        } else
            this.year = year;
    }

    /**
     * A method that will return the day
     * @return day
     */
    public int getDay() {
        return day;
    }

    /**
     * A method that will return the Month
     * @return month
     */
    public int getMonth() {
        return month;
    }

    /**
     * A method that will return the year
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * A method that will return a String with the date format as dd/mm/yyyy
     * @return the date
     */
    public String toString() {
        return this.day + "/" + this.month + "/" + this.year;
    }

    /**
     * A method that will verify if another object is equal with my date
     * @param obj the another obj
     * @return true if everything is matching otherwise false
     */
    public boolean equals(Object obj){
        if(!(obj instanceof MyDate)) return false;

        MyDate myDate = (MyDate) obj;

        if(this.day== myDate.day&&this.month==myDate.month&&this.year==myDate.year) return true;
        else return false;
    }

    /**
     * Check if the current DateTime object is before another DateTime object
     *
     * @param date2 the DateTime object to compare against
     * @return true if the current DateTime object is before the other DateTime object, false otherwise
     */
    public boolean isBefore(MyDate date2)
    {
        boolean ok = false;
        if (year < date2.year)
            ok = true;
        if (year == date2.year)
        {
            if (month < date2.month)
            {
                ok = true;
            }
            if (month == date2.month)
            {
                if (day < date2.day)
                {
                    ok = true;
                }
                if (day == date2.day)
                {
                   ok=false;
                }
            }
        }
        return ok;
    }

    /**
     * A method that will made a copy of the date
     * @return the copy of a date
     */
    public MyDate copy() {
        return new MyDate(this.day, this.month, this.year);
    }
}