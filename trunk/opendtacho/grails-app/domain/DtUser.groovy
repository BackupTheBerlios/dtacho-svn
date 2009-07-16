import java.text.SimpleDateFormat

class DtUser {
    static belongsTo = [DtPerson]

    // References
    DtPerson person
    
    // Data
    String username
    String password
    boolean deletionFlag
    Date validFrom
    Date validTo

    static constraints = {
        username(blank:false, unique:true)
        validTo(nullable:true)
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss z')

    String toString() {
        "DtUser: $username (deleted: $deletionFlag, validFrom: ${sdf.format(validFrom)}, validTo: ${sdf.format(validTo)}"
    }
}
