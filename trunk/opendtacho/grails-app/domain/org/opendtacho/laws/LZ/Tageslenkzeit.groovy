package org.opendtacho.laws.LZ
import org.opendtacho.domain.DtActivityChange

/**
 * Created by IntelliJ IDEA.
 * User: ilia
 * Date: 12/22/10
 * Time: 3:19 PM
 * To change this template use File | Settings | File Templates.
 */
class Tageslenkzeit extends Lenkzeit {

  private def lawParams
  private int limitHours

  Tageslenkzeit() {
      lawParams = new ArrayList<List<Object>>()
      limitHours = 540
  }
  Tageslenkzeit(ArrayList<List<Object>> lawParams) {
      this.lawParams = lawParams
      }

  def checkLaw = { List<DtActivityChange> driverAndDate->
      def datesAndTimes = datesAndTimesList(driverAndDate)
      if(lawParams.size() == 0) {
            lawParams.add(0, new LinkedList<Object>())
            lawParams.get(0).add(0, driverAndDate.get(0).activityRecordDate)
            lawParams.get(0).add(1, limitHours)
        }
      ArrayList<List<Object>> tempLawParams = lawParams.clone()
      def checkLawList = new ArrayList<List<Object>>();
      int index = 0;
      for (int i = 0; i < datesAndTimes.size(); i++) {
        if(tempLawParams.size() != 0) {
           if (equalsAfter((Date)datesAndTimes.get(i).get(0),(Date)tempLawParams.get(0).get(0))) {
                           checkLawList.add(new LinkedList<Object>())
                             checkLawList.get(index).add(0, "Tageslenkzeit am: "
                             + formatDate((Date)tempLawParams.get(0).get(0)))
                           limitHours = (int)tempLawParams.get(0).get(1)
                           checkLawList.get(index).add(1, formatTime(limitHours) + " Stunden")
                           tempLawParams.remove(0)
                           index++
                        }
         }
           if (datesAndTimes.get(i).get(1) > limitHours) {
               int delta =  datesAndTimes.get(i).get(1) - limitHours
               checkLawList.add(new LinkedList<Object>())
               checkLawList.get(index).add(0, formatDate((Date)datesAndTimes.get(i).get(0)))
               checkLawList.get(index).add(1, "- " +  formatTime(delta))
               index++
             }
    }
      return checkLawList
  }
  String myNameIs() {
    return "Tageslenkzeit"
    }
}
