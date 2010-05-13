package dk.yousee.model

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 11, 2010
 * Time: 6:34:00 PM
 */
import scala.collection.mutable.Map

class LeveringsAftale(val abonnementId : Int, val produktId : Int, val leveringsPeriode : Periode, val forbruger : Int, val betaler : Int, val properties : Map[dk.yousee.repository.Properties.Value,String]) {
  def persist {
    //todo kald repository
    println("Persister LeveringsAftale for " + produktId)
  }
}