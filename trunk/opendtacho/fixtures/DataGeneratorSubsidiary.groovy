import org.opendtacho.domain.DtSubsidiary

fixture {
  //for ISCL
  subLinkenheim(DtSubsidiary) {
    company = comISCL
    city = 'Linkenheim'
  }
  subKarlsruhe(DtSubsidiary) {
    company = comISCL
    city = 'Karlsruhe'
  }
  subStuttgart(DtSubsidiary) {
    company = comISCL
    city = 'Stuttgart'
  }

  //for Apple
  subBerlin(DtSubsidiary){
    company = comApple
    city = 'Berlin'
  }
  subHannover(DtSubsidiary){
    company = comApple
    city = 'Hannover'
  }
}

