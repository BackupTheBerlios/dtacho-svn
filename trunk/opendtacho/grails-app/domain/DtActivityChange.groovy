import java.text.SimpleDateFormat

class DtActivityChange {
    static belongsTo = [DtVehicle, DtDriver]

    // References
    DtVehicle vehicle
    DtDriver driver

    // Data
    Date activityRecordDate
    String slot
    String drivingStatus
    String cardStatus
    String activity
    int time

    // no property 'domainKeys' here, because no identifying combination of attributes could be found

    private static SimpleDateFormat sdf = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss z')


    DtActivityChange() {
    }


    /**
     * Constructor, from XML data
     */
    DtActivityChange(dtVehicle, dtDriver, cardActivityDailyRecord_activityRecordDate, activityChangeInfo) {
        vehicle = dtVehicle
        driver = dtDriver
        activityRecordDate = sdf.parse(cardActivityDailyRecord_activityRecordDate as String)
        slot = activityChangeInfo.slot
        drivingStatus = activityChangeInfo.drivingStatus
        cardStatus = activityChangeInfo.cardStatus
        activity = activityChangeInfo.activity
        time = Integer.parseInt(activityChangeInfo.time as String)
    }


    String toString() {
        "DtActivityChange: [$vehicle / $driver], ${sdf.format(activityRecordDate)}"
    }
}
