package dk.yousee.model

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 18, 2010
 * Time: 4:52:22 PM
 */

class BetalingsAftale(val id : Int, val abonnementId : Int, val produktid : Int, val betaler : Int, val pris : Double,
                      val rabat : Double, val faktureringsPeriode : Periode, val status : BetalingsAftaleStatus) {
  def persist {
    //todo kald repository
    println("Persister BetalingsAftale for " + abonnementId)
  }
}

class BetalingsAftaleStatus extends Enumeration {
  val Lukket,Bero,Aktiv = Value
}