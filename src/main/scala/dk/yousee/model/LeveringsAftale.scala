package dk.yousee.model

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 11, 2010
 * Time: 6:34:00 PM
 */

class LeveringsAftale(val abonnementId : Int, val produktId : Int, leveringsPeriode : Periode, val forbruger : Int, val betaler : Int, val properties : scala.collection.mutable.Map[String,String]) {
  def persist {
    //todo kald repository
    println("Persister LeveringsAftale for " + produktId)
  }
}