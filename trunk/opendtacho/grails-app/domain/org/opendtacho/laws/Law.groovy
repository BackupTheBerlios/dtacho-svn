package org.opendtacho.laws
import org.opendtacho.domain.DtActivityChange
/**
 * Created by IntelliJ IDEA.
 * User: ilia
 * Date: 12/22/10
 * Time: 3:13 PM
 * To change this template use File | Settings | File Templates.
 */
class Law {

    Law() {
    }

    def checkLaw = { List<DtActivityChange> driver ->
    }

  public ArrayList<List<Object>> fillDates(List<DtActivityChange> driverAndDate) {

    int dayCount = 0;
    def dates = []
    for (int i = 1; i < driverAndDate.size(); i++) {
      if (!equalsDate((Date)driverAndDate.get(i).activityRecordDate,
              (Date)driverAndDate.get(i - 1).activityRecordDate)) {
        dates.add(driverAndDate.get(i - 1).activityRecordDate)
        dayCount++
      }
      if (i == driverAndDate.size() - 1) {
          dayCount++
          dates.add(driverAndDate.get(i - 1).activityRecordDate)
      }
    }
     return dates
   }

  /**
   * Compares two dates (equals).
   * An extra method "equalsDate" is likely to use, because the database column activityRecordDate
   * has "hours dummies" that might cause the false results in Data class reserved method "compareTo" .
   */
  def equalsDate = {Date d1, Date d2 ->
    if (d1.date == d2.date & d1.month == d2.month & d1.year == d2.year) {
      return true
    }
    return false
  }
    /**
     * Compares two dates (d1 ist either egual or older than d2)
     */
  def equalsAfter = {Date d1, Date d2 ->
    if(d1.after(d2)) return true
    if (d1.date == d2.date & d1.month == d2.month & d1.year == d2.year) {
      return true
    }
    return false
  }

  /**
   * format Date
   */
  def formatDate = { Date d ->
    int day = d.getDate()
    String dayTrans
    if(day<10) dayTrans = "0$day"
    else dayTrans = "$day"
    int month = d.getMonth()+1
    String monthTrans
    if(month<10) monthTrans = "0$month"
    else monthTrans = "$month"
    int year = d.getYear()+1900
    return "$dayTrans.$monthTrans.$year"
  }

  /**
   * format Time
   */
 String formatTime (int z) {
    if (z < 0) {
     z = -1*z
   }
    int hour = (int)(z/60)
    String hourTrans
    if (hour<10) hourTrans = "0"+(hour as String)
    else hourTrans = hour as String
    int min = (int)(z%60)
    String minTrans
    if (min<10) minTrans = "0"+(min as String)
    else minTrans = min as String
    return "$hourTrans:$minTrans"
  }
    String myNameIs(){
    }
 }
