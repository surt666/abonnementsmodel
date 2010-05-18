package dk.yousee.model

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 18, 2010
 * Time: 4:52:22 PM
 */

class BetalingsAftale(val abonnementId : Int, val betaler : Int, val pris : Double, val rabat : Double, val faktureringsPeriode : Periode) {
  def persist {
    //todo kald repository
    println("Persister BetalingsAftale for " + abonnementId)
  }
}