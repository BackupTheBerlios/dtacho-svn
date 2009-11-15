import org.opendtacho.domain.*


class DtActivityChangeController {
  def scaffold = DtActivityChange

  //empty action, just forward to the view report.gsp
  def report = { }

  //Transformation function from minute number to time String
  String timeTrans (int z) {
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

  //Transformation function from Date to standard Date
  String dateTrans (Date d) {
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

  //results action handles inputs from report.gsp and sends result infos to pdfResults.gsp (also, the view of the action pdfResults)
  def pdfResults = {
    //resultList holds all DtResult objects, every object represents a complete result
    def resultList = []

    //temporary query obj for criteria queries, also take the query.minDate and query.maxDate
    DtActivityChangeQuery query = new DtActivityChangeQuery()
    bindData(query,params)

    //CREATING RESULT LIST WITH EACH ITEM AS A COMPLETED RESULT
    def drivers = params.drivers
    drivers.each{aDriver->
      //a DtResult object
      def dtResult = new DtResult()


      /*DRIVER INFOS*/
      /*================================================================================================================================================================================*/
      def driverTemp = DtDriver.get(aDriver as long)
      def foundDriver = new DtDriverTemp(
              firstName:driverTemp.cardHolderName_holderFirstNames,
              lastName:driverTemp.cardHolderName_holderSurname,
              licenceNr:driverTemp.drivingLicenceNumber)

      dtResult.driver = foundDriver//into dtResult for field driver
      /*================================================================================================================================================================================*/


      /*CARD INFOS*/
      /*================================================================================================================================================================================*/
      //this is a LIST, not just a VARIABLE
      def cardTemp = DtCard.withCriteria{
        card2drivers{
          eq('driver',driverTemp)
        }
      }
      def foundCard = new DtCardTemp(
              cardNr:cardTemp[0].cardNumber_driverIdentification,
              cardExp:dateTrans(cardTemp[0].cardExpiryDate))

      dtResult.card = foundCard//into dtResult for field card
      /*================================================================================================================================================================================*/


      /*DRIVING ACTIVITIES*/
      /*================================================================================================================================================================================*/
      //end data in a entries list
      def entries = DtActivityChange.withCriteria {
        and{
          between('activityRecordDate',query.minDate,query.maxDate)
          //TODO maxDate not recognize
          driver{
            eq('id',aDriver as long)
          }
        }
        order('activityRecordDate','asc')
        order('time','asc')
      }

      //Transformation from original data to temporary
      def temp = []//temporary list
      for(int i in 0..entries.size()-1){
        temp[i] = new DtActivityChangeTemp()

        //activityRecordDate transformation
        temp[i].activityRecordDate = dateTrans(entries[i].activityRecordDate)

        //activityRecordDay transformation
        temp[i].activityRecordDay = entries[i].activityRecordDate.getDay()

        //activity transformation
        if(entries[i].activity=='00') temp[i].activity='RZ'
        if(entries[i].activity=='01') temp[i].activity='BS'
        if(entries[i].activity=='02') temp[i].activity='AR'
        if(entries[i].activity=='03') temp[i].activity='LZ'

        //time transformation
        temp[i].time = timeTrans(entries[i].time)

        //next and duration transformation
        if(i<entries.size()-1){
          temp[i].next = timeTrans(entries[i+1].time)
          if(entries[i+1].time!=0) temp[i].duration = timeTrans(entries[i+1].time-entries[i].time)
          if(entries[i+1].time==0) temp[i].duration = timeTrans(1440-entries[i].time)
        }else{
          temp[i].next = timeTrans(0)
          temp[i].duration = timeTrans(1440-entries[i].time)
        }
      }

      dtResult.activities = temp//into dtResult for field activities
      /*================================================================================================================================================================================*/


      /*DRIVING STATISTIC*/
      /*================================================================================================================================================================================*/
      int RZ = 0
      int RZDur = 0
      int AR = 0
      int ARDur = 0
      int LZ = 0
      int LZDur = 0
      int sum = 0
      int sumDur = 0

      for(int i in 0..entries.size()-1){

        if(entries[i].activity=='00'){
          RZ+=1
          if(i<entries.size()-1){
            if(entries[i+1].time!=0) RZDur += entries[i+1].time-entries[i].time
            if(entries[i+1].time==0) RZDur += 1440-entries[i].time
          }else{
            RZDur += 1440-entries[i].time
          }
        }

        if(entries[i].activity=='02'){
          AR+=1
          if(i<entries.size()-1){
            if(entries[i+1].time!=0) ARDur += entries[i+1].time-entries[i].time
            if(entries[i+1].time==0) ARDur += 1440-entries[i].time
          }else{
            ARDur += 1440-entries[i].time
          }
        }

        if(entries[i].activity=='03'){
        LZ+=1
        if(i<entries.size()-1){
          if(entries[i+1].time!=0) LZDur += entries[i+1].time-entries[i].time
          if(entries[i+1].time==0) LZDur += 1440-entries[i].time
        }else{
          LZDur += 1440-entries[i].time
        }
      }

        sum = AR+RZ+LZ
        sumDur =ARDur+RZDur+LZDur

      }

      def calculatedStatistic = new DtDrivingStatisticTemp(
              AR:AR,ARDur:timeTrans(ARDur),
              RZ:RZ,RZDur:timeTrans(RZDur),
              LZ:LZ,LZDur:timeTrans(LZDur),
              sum:sum,sumDur:timeTrans(sumDur))

      dtResult.statistic = calculatedStatistic//info dtResult for field statistic
      /*================================================================================================================================================================================*/
      dtResult.from = dateTrans(query.minDate)
      dtResult.to = dateTrans(query.maxDate)

      /*END*/
      resultList << dtResult
    }

    return [resultList:resultList]
  }

  //results action handles inputs from screenReport.gsp and sends result infos to screenResults.gsp (also, the view of the action screenResults)
  def screenResults = {
    //resultList holds all DtResult objects, every object represents a complete result
    def resultList = []

    //temporary query obj for criteria queries, also take the query.minDate and query.maxDate
    DtActivityChangeQuery query = new DtActivityChangeQuery()
    bindData(query,params)

    //CREATING RESULT LIST WITH EACH ITEM AS A COMPLETED RESULT
    def drivers = params.drivers
    drivers.each{aDriver->
      //a DtResult object
      def dtResult = new DtResult()


      /*DRIVER INFOS*/
      /*================================================================================================================================================================================*/
      def driverTemp = DtDriver.get(aDriver as long)
      def foundDriver = new DtDriverTemp(
              firstName:driverTemp.cardHolderName_holderFirstNames,
              lastName:driverTemp.cardHolderName_holderSurname,
              licenceNr:driverTemp.drivingLicenceNumber)

      dtResult.driver = foundDriver//into dtResult for field driver
      /*================================================================================================================================================================================*/


      /*CARD INFOS*/
      /*================================================================================================================================================================================*/
      //this is a LIST, not just a VARIABLE
      def cardTemp = DtCard.withCriteria{
        card2drivers{
          eq('driver',driverTemp)
        }
      }
      def foundCard = new DtCardTemp(
              cardNr:cardTemp[0].cardNumber_driverIdentification,
              cardExp:dateTrans(cardTemp[0].cardExpiryDate))

      dtResult.card = foundCard//into dtResult for field card
      /*================================================================================================================================================================================*/


      /*DRIVING ACTIVITIES*/
      /*================================================================================================================================================================================*/
      //end data in a entries list
      def entries = DtActivityChange.withCriteria {
        and{
          between('activityRecordDate',query.minDate,query.maxDate)
          //TODO maxDate not recognize
          driver{
            eq('id',aDriver as long)
          }
        }
        order('activityRecordDate','asc')
        order('time','asc')
      }

      //Transformation from original data to temporary
      def temp = []//temporary list
      for(int i in 0..entries.size()-1){
        temp[i] = new DtActivityChangeTemp()

        //activityRecordDate transformation
        temp[i].activityRecordDate = dateTrans(entries[i].activityRecordDate)

        //activityRecordDay transformation
        temp[i].activityRecordDay = entries[i].activityRecordDate.getDay()

        //activity transformation
        if(entries[i].activity=='00') temp[i].activity='RZ'
        if(entries[i].activity=='01') temp[i].activity='BS'
        if(entries[i].activity=='02') temp[i].activity='AR'
        if(entries[i].activity=='03') temp[i].activity='LZ'

        //time transformation
        temp[i].time = timeTrans(entries[i].time)

        //next and duration transformation
        if(i<entries.size()-1){
          temp[i].next = timeTrans(entries[i+1].time)
          if(entries[i+1].time!=0) temp[i].duration = timeTrans(entries[i+1].time-entries[i].time)
          if(entries[i+1].time==0) temp[i].duration = timeTrans(1440-entries[i].time)
        }else{
          temp[i].next = timeTrans(0)
          temp[i].duration = timeTrans(1440-entries[i].time)
        }
      }

      dtResult.activities = temp//into dtResult for field activities
      /*================================================================================================================================================================================*/


      /*DRIVING STATISTIC*/
      /*================================================================================================================================================================================*/
      int RZ = 0
      int RZDur = 0
      int AR = 0
      int ARDur = 0
      int LZ = 0
      int LZDur = 0
      int sum = 0
      int sumDur = 0

      for(int i in 0..entries.size()-1){

        if(entries[i].activity=='00'){
          RZ+=1
          if(i<entries.size()-1){
            if(entries[i+1].time!=0) RZDur += entries[i+1].time-entries[i].time
            if(entries[i+1].time==0) RZDur += 1440-entries[i].time
          }else{
            RZDur += 1440-entries[i].time
          }
        }

        if(entries[i].activity=='02'){
          AR+=1
          if(i<entries.size()-1){
            if(entries[i+1].time!=0) ARDur += entries[i+1].time-entries[i].time
            if(entries[i+1].time==0) ARDur += 1440-entries[i].time
          }else{
            ARDur += 1440-entries[i].time
          }
        }

        if(entries[i].activity=='03'){
        LZ+=1
        if(i<entries.size()-1){
          if(entries[i+1].time!=0) LZDur += entries[i+1].time-entries[i].time
          if(entries[i+1].time==0) LZDur += 1440-entries[i].time
        }else{
          LZDur += 1440-entries[i].time
        }
      }

        sum = AR+RZ+LZ
        sumDur =ARDur+RZDur+LZDur

      }

      def calculatedStatistic = new DtDrivingStatisticTemp(
              AR:AR,ARDur:timeTrans(ARDur),
              RZ:RZ,RZDur:timeTrans(RZDur),
              LZ:LZ,LZDur:timeTrans(LZDur),
              sum:sum,sumDur:timeTrans(sumDur))

      dtResult.statistic = calculatedStatistic//info dtResult for field statistic
      /*================================================================================================================================================================================*/
      dtResult.from = dateTrans(query.minDate)
      dtResult.to = dateTrans(query.maxDate)

      /*END*/
      resultList << dtResult
    }

    return [resultList:resultList,minDate:query.minDate,maxDate:query.maxDate,drivers:drivers]
  }
}
