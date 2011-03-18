package org.opendtacho.laws.LZ
import org.opendtacho.domain.DtActivityChange

/**
 * Created by IntelliJ IDEA.
 * User: ilia
 * Date: 12/22/10
 * Time: 3:20 PM
 * To change this template use File | Settings | File Templates.
 */
class Wochenlenkzeit extends Lenkzeit {
    private int limitHours

    Wochenlenkzeit() {
    limitHours = 7*540
    }

    Wochenlenkzeit(limitHours) {
        this.limitHours = limitHours
    }

    def checkLaw = { List<DtActivityChange> driverAndDate ->
      def datesAndTimes = datesAndTimesList(driverAndDate)
      def checkLawList = new ArrayList<List<Object>>();
      checkLawList.add(new LinkedList<Object>())
      checkLawList.get(0).add("Wochenlenkzeit von")
      checkLawList.get(0).add("bis")
      checkLawList.get(0).add(formatTime(limitHours))
     // int day = this.calcTheDay(datesAndTimes.get(0).get(0))
        int day = 4 // for example
      int limit = 6 - day
      int i = 1
      for (i; i < datesAndTimes.size; i++) {
         // checkLawList.get(i).add(formatDate(datesAndTimes.get(0).get(0)))

          if ( i == datesAndTimes.size - 1) {

          } else {

          if (limit <= 0) {
              if (limit == 0) {

          }
        //  day =  this.calcTheDay((Date)datesAndTimes.get(i + 1).get(0))
          limit = 6 - day
          } else {

          }
        }
    }
      return checkLawList
  }

    String myNameIs() {
    return "Wochenlenkzeit"
    }
}
