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

    //temporary query obj for criteria queries
    DtActivityChangeQuery query = new DtActivityChangeQuery()
    bindData(query,params)

    //if the user has choose a shortcut for time interval
    if(params.shortcut!="null"){
      def test = new Date()
      int y = test.getYear()
      int m = test.getMonth()
      int d = test.getDate()
      int day = test.getDay()

      if(params.shortcut=="lastWeek"){
        if(day==0){
          query.minDate = new Date(y,m,d-6)
          query.maxDate = new Date(y,m,d)
        }
        if(day==1){
          query.minDate = new Date(y,m,d-7)
          query.maxDate = new Date(y,m,d-1)
        }
        if(2<=day&&day<=5){
          def temp = new Date()
          for(int i in 1..4){
            temp = new Date(y,m,d-i)
            if(temp.getDay()==1) break
          }
          query.minDate = new Date(y,m,temp.getDate()-7)
          query.maxDate = new Date(y,m,temp.getDate()-1)
        }
        if(d==6){
          query.minDate = new Date(y,m,d-5)
          query.maxDate = new Date(y,m,d+1)
        }
      }

      if(params.shortcut=="thisMonth"){
        query.minDate = new Date(y,m,1)
        query.maxDate = test
      }

      if(params.shortcut=="lastMonth"){
        query.minDate = new Date(y,m-1,1)
        query.maxDate = new Date(y,m-1,31)
      }

      if(params.shortcut=="thisYear"){
        query.minDate = new Date(y,0,1)
        query.maxDate = test
      }
    }

    //end data in a entries list
    def entries = DtActivityChange.withCriteria {
      and{
        between('activityRecordDate',query.minDate,query.maxDate)
        //TODO maxDate not recognize
        driver{
          eq('cardHolderName_holderSurname',params.driver)
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

    //driver information for results page
    def foundDriver = DtDriver.findByCardHolderName_holderSurname(params.driver)

    //card information for results page
    //this is a LIST, not just a VARIABLE
    def foundCard = DtCard.withCriteria {
      card2drivers{
        eq('driver',foundDriver)
      }
    }

    //driving statistic for results page
    int RZ = 0
    int RZDur = 0
    int AR = 0
    int ARDur = 0
    int LZ = 0
    int LZDur = 0

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
    }

    return [entries:temp,foundDriver:foundDriver,cardNumber:foundCard[0].cardNumber_driverIdentification,cardExp:dateTrans(foundCard[0].cardExpiryDate),RZ:RZ,RZDur:timeTrans(RZDur),AR:AR,ARDur:timeTrans(ARDur),LZ:LZ,LZDur:timeTrans(LZDur),sum:RZ+AR+LZ,sumDur:timeTrans(RZDur+ARDur+LZDur),date:dateTrans(new Date()),from:dateTrans(query.minDate),to:dateTrans(query.maxDate)]
  }

  //results action handles inputs from screenReport.gsp and sends result infos to screenResults.gsp (also, the view of the action screenResults)
  def screenResults = {

    //temporary query obj for criteria queries
    DtActivityChangeQuery query = new DtActivityChangeQuery()
    bindData(query,params)

    //if the user has choose a shortcut for time interval
    if(params.shortcut!="null"){
      def test = new Date()
      int y = test.getYear()
      int m = test.getMonth()
      int d = test.getDate()
      int day = test.getDay()

      if(params.shortcut=="lastWeek"){
        if(day==0){
          query.minDate = new Date(y,m,d-6)
          query.maxDate = new Date(y,m,d)
        }
        if(day==1){
          query.minDate = new Date(y,m,d-7)
          query.maxDate = new Date(y,m,d-1)
        }
        if(2<=day&&day<=5){
          def temp = new Date()
          for(int i in 1..4){
            temp = new Date(y,m,d-i)
            if(temp.getDay()==1) break
          }
          query.minDate = new Date(y,m,temp.getDate()-7)
          query.maxDate = new Date(y,m,temp.getDate()-1)
        }
        if(d==6){
          query.minDate = new Date(y,m,d-5)
          query.maxDate = new Date(y,m,d+1)
        }
      }

      if(params.shortcut=="thisMonth"){
        query.minDate = new Date(y,m,1)
        query.maxDate = test
      }

      if(params.shortcut=="lastMonth"){
        query.minDate = new Date(y,m-1,1)
        query.maxDate = new Date(y,m-1,31)
      }

      if(params.shortcut=="thisYear"){
        query.minDate = new Date(y,0,1)
        query.maxDate = test
      }
    }

    //end data in a entries list
    def entries = DtActivityChange.withCriteria {
      and{
        between('activityRecordDate',query.minDate,query.maxDate)
        //TODO maxDate not recognize
        driver{
          eq('cardHolderName_holderSurname',params.driver)
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

    //driver information for results page
    def foundDriver = DtDriver.findByCardHolderName_holderSurname(params.driver)

    //card information for results page
    //this is a LIST, not just a VARIABLE
    def foundCard = DtCard.withCriteria {
      card2drivers{
        eq('driver',foundDriver)
      }
    }

    //driving statistic for results page
    int RZ = 0
    int RZDur = 0
    int AR = 0
    int ARDur = 0
    int LZ = 0
    int LZDur = 0

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
    }

    return [entries:temp,foundDriver:foundDriver,cardNumber:foundCard[0].cardNumber_driverIdentification,cardExp:dateTrans(foundCard[0].cardExpiryDate),RZ:RZ,RZDur:timeTrans(RZDur),AR:AR,ARDur:timeTrans(ARDur),LZ:LZ,LZDur:timeTrans(LZDur),sum:RZ+AR+LZ,sumDur:timeTrans(RZDur+ARDur+LZDur),date:dateTrans(new Date()),from:dateTrans(query.minDate),to:dateTrans(query.maxDate),minDate:query.minDate,maxDate:query.maxDate]
  }
}
