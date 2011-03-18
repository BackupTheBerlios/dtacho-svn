package org.opendtacho.laws.LZ
import org.opendtacho.domain.DtActivityChange
import org.opendtacho.laws.Law

/**
 * Created by IntelliJ IDEA.
 * User: ilia
 * Date: 3/8/11
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
class Lenkzeit extends Law {

  Lenkzeit(){
  }

  def datesAndTimesList = { ArrayList<DtActivityChange> driverAndDate->
      def dates = fillDates(driverAndDate)
      def checkLawList = new ArrayList<List<Object>>();
      int LZTimeSum = 0; // LZ day time
      int i = 0;

    for (int j = 0; j < dates.size(); j++) {
      for (i; i < driverAndDate.size(); i++) {
        if (j == dates.size() - 1) {
          if (driverAndDate.get(i).activity == '03') {
                 LZTimeSum += driverAndDate.get(i + 1).time - driverAndDate.get(i).time
          }
            if( i == driverAndDate.size() -1 ) {
              checkLawList.add(new LinkedList<Object>())
              checkLawList.get(j).add(0,dates.get(j))
              checkLawList.get(j).add(1, LZTimeSum)
              LZTimeSum = 0
              break
          }
        } else {

         if (equalsDate((Date)dates.get(j), (Date)driverAndDate.get(i).activityRecordDate)) {
          if (driverAndDate.get(i).activity == '03') {
            LZTimeSum += driverAndDate.get(i + 1).time - driverAndDate.get(i).time
          }
        } else {
             checkLawList.add(new LinkedList<Object>())
             checkLawList.get(j).add(0, dates.get(j))
             checkLawList.get(j).add(1, LZTimeSum)
          LZTimeSum = 0
          break
         }
        }
       }
      }
      return checkLawList
  }
}