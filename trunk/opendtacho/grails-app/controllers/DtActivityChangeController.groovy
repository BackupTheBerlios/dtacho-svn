// Opendtacho - a program for analysing and interpreting digital tachograph data
// Copyright (C) 2008  Gerald Lang, ISCL - Internet Security Consulting Lang, http://www.iscl.de
//
// http://www.opendtacho.org
//
// gerald.lang@iscl.de
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; either version 2
// of the License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

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
    //drivers variable takes the selected drivers of repost.gsp
    def drivers = params.drivers

    //temporary query obj for criteria queries, also take the query.minDate and query.maxDate
    DtActivityChangeQuery query = new DtActivityChangeQuery()
    bindData(query,params)

    //two separated list, notFoundDrivers has drivers, who DON'T have any related activities in time duration, foundDrivers analog
    def notFoundDriverIds = []
    def notFoundDriverNames = []
    def foundDriverIds = []
    def foundDriverNames = []

    //resultList holds all DtResult objects, every object represents a complete result, based on foundDrivers list
    def resultList = []


    //FILTER DRIVERS INTO 2 SEPARATED LIST, notFoundDriverIds and foundDriverIds
    //=======================================================================================
    drivers.each {aDriver->
      def foundActivities = DtActivityChange.withCriteria {
        and{
          between('activityRecordDate',query.minDate,query.maxDate)
          //TODO maxDate not recognize
          driver{
            eq('id',aDriver as long)
          }
        }
      }
      //if foundActivities exists, than the driver is valid, otherwise not valid
      if(foundActivities) foundDriverIds << aDriver
      else notFoundDriverIds << aDriver

    }
    //=======================================================================================

    //CREATING foundDriverNames and notFoundDriverNames
    //=======================================================================================
    foundDriverIds.each{driver->
      def temp = DtDriver.get(driver as long)
      foundDriverNames << temp.cardHolderName_holderSurname + ", " + temp.cardHolderName_holderFirstNames
    }
    notFoundDriverIds.each{driver->
      def temp = DtDriver.get(driver as long)
      notFoundDriverNames << temp.cardHolderName_holderSurname + ", " + temp.cardHolderName_holderFirstNames
    }
    //=======================================================================================

    //CREATING RESULT LIST WITH EACH ITEM AS A COMPLETED RESULT
    //=======================================================================================
    foundDriverIds.each{aDriver->

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
    //=======================================================================================
    return [minDate:query.minDate,
            maxDate:query.maxDate,
            foundDriverIds:foundDriverIds,
            foundDriverNames:foundDriverNames,
            notFoundDriverIds:notFoundDriverIds,
            notFoundDriverNames:notFoundDriverNames,
            resultList:resultList]
  }

  //results action handles inputs from screenReport.gsp and sends result infos to screenResults.gsp (also, the view of the action screenResults)
  def screenResults = {
    //drivers variable takes the selected drivers of repost.gsp
    def drivers = params.drivers

    //temporary query obj for criteria queries, also take the query.minDate and query.maxDate
    DtActivityChangeQuery query = new DtActivityChangeQuery()
    bindData(query,params)

    //two separated list, notFoundDrivers has drivers, who DON'T have any related activities in time duration, foundDrivers analog
    def notFoundDriverIds = []
    def notFoundDriverNames = []
    def foundDriverIds = []
    def foundDriverNames = []

    //resultList holds all DtResult objects, every object represents a complete result, based on foundDrivers list
    def resultList = []


    //FILTER DRIVERS INTO 2 SEPARATED LIST, notFoundDriverIds and foundDriverIds
    //=======================================================================================
    drivers.each {aDriver->
      def foundActivities = DtActivityChange.withCriteria {
        and{
          between('activityRecordDate',query.minDate,query.maxDate)
          //TODO maxDate not recognize
          driver{
            eq('id',aDriver as long)
          }
        }
      }
      //if foundActivities exists, than the driver is valid, otherwise not valid
      if(foundActivities) foundDriverIds << aDriver
      else notFoundDriverIds << aDriver
      
    }
    //=======================================================================================

    //CREATING foundDriverNames and notFoundDriverNames
    //=======================================================================================
    foundDriverIds.each{driver->
      def temp = DtDriver.get(driver as long)
      foundDriverNames << temp.cardHolderName_holderSurname + ", " + temp.cardHolderName_holderFirstNames
    }
    notFoundDriverIds.each{driver->
      def temp = DtDriver.get(driver as long)
      notFoundDriverNames << temp.cardHolderName_holderSurname + ", " + temp.cardHolderName_holderFirstNames
    }
    //=======================================================================================

    //CREATING RESULT LIST WITH EACH ITEM AS A COMPLETED RESULT
    //=======================================================================================
    foundDriverIds.each{aDriver->

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
    //=======================================================================================
    return [minDate:query.minDate,
            maxDate:query.maxDate,
            foundDriverIds:foundDriverIds,
            foundDriverNames:foundDriverNames,
            notFoundDriverIds:notFoundDriverIds,
            notFoundDriverNames:notFoundDriverNames,
            resultList:resultList]
  }
}
