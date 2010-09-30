import org.opendtacho.domain.DtSubsidiary

fixture {
    subsStuttgart(DtSubsidiary) {
        company = company0
        city = 'Stuttgart'
        
    }
    subsMuenchen(DtSubsidiary) {
        company = company0
        city = 'M�nchen'
    }
    subsKarlsruhe(DtSubsidiary) {
        company = company0
        city = 'Karlsruhe'
    }
}

