package dk.yousee.model

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 11, 2010
 * Time: 6:33:38 PM
 */

class Abonnement(val id : Int, val juridisk : Int, val faktureringsPeriode : Periode, val leveringer : List[LeveringsAftale], val pris : Double, val rabat : Double)  {
  def persist {
    leveringer.foreach(l => l.persist)
    //todo kald repository
    println("Persister Abonnement " + id)
  }
}